package ru.itis.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.dto.SignInDto;
import ru.itis.dto.TokenDto;
import ru.itis.service.SignInService;

@RestController
public class SignInRestController {

    @Autowired
    private SignInService authService;

    @PreAuthorize("permitAll()")
    @PostMapping("/api/signIn")
    public ResponseEntity<TokenDto> signIn(@RequestBody SignInDto signInData) {
        TokenDto token = authService.signIn(signInData);
        return ResponseEntity.ok(token);
    }
}
