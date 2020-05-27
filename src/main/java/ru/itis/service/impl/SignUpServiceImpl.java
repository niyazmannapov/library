package ru.itis.service.impl;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import ru.itis.dto.TokenDto;
import ru.itis.dto.UserConfirmDto;
import ru.itis.model.Role;
import ru.itis.model.State;
import ru.itis.model.User;
import ru.itis.repository.UserRepository;
import ru.itis.service.SendEmailService;
import ru.itis.service.SignUpService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Component
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private SendEmailService emailService;

    @Value("${jwt.secret}")
    private String secret;

    @Override
    public TokenDto signUp(MultiValueMap <String, String> formData) {


        userRepository.save( User.builder().email(formData.getFirst("email"))
                .password(encoder.encode(formData.getFirst("password")))
                .confirmCode(UUID.randomUUID().toString())
                .state(State.NOT_CONFIRMED)
                .role(Role.USER)
                .build());
        Optional<User> userOptional = userRepository.findByEmail(formData.getFirst("email"));


        if (userOptional.isPresent()) {
            User user = userOptional.get();

            if (encoder.matches(formData.getFirst("password"), user.getPassword())) {

                UserConfirmDto userConfirmDto = UserConfirmDto.from(user);
                Map<String, Object> root = new HashMap<>();
                root.put("user", user);

                emailService.sendCode("Подтверждение", user.getEmail(), root);
                String token = Jwts.builder()
                        .setSubject(user.getId().toString())
                        .claim("email", user.getEmail())
                        .claim("role", user.getRole().name())
                        .signWith(SignatureAlgorithm.HS256, secret)
                        .compact();
                return new TokenDto(token);
            } else throw new AccessDeniedException("Wrong email/password");
        } else throw new AccessDeniedException("User not found");

    }

}
