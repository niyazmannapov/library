package ru.itis.service;

import org.springframework.util.MultiValueMap;
import ru.itis.dto.TokenDto;
import ru.itis.dto.UserConfirmDto;

import javax.servlet.http.HttpServletRequest;

public interface SignUpService {
    TokenDto signUp(MultiValueMap<String, String> formData);


}
