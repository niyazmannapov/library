package ru.itis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.dto.UserConfirmDto;
import ru.itis.model.User;
import ru.itis.security.details.UserDetailsImpl;
import ru.itis.service.ConfirmService;

import java.util.Optional;

@Controller
public class ConfirmationController {

    @Autowired
    private ConfirmService service;

    @GetMapping(value = "/confirm")
    public ModelAndView confirm() {
        return new ModelAndView("confirm");
    }

    @PostMapping(value = "/confirm")
    @PreAuthorize("permitAll()")
    public ModelAndView getConfirmation(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                        @RequestParam(value = "code") String code) {
        if (userDetails == null) return new ModelAndView("redirect:/signIn");
        User user = userDetails.getUser();
        Optional<UserConfirmDto> userConfirmDto= service.checkCode(code, user);
        if (userConfirmDto.isPresent()) {

            return new ModelAndView("redirect:/profile");
        } else {
            return new ModelAndView("redirect:/confirm");
        }
    }
}
