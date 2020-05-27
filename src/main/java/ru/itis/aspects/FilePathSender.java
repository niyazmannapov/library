package ru.itis.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.dto.UserConfirmDto;
import ru.itis.service.SendEmailService;
import ru.itis.service.UploadFileService;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class FilePathSender {

    private String email;
    @Autowired
    private SendEmailService sendEmailService;
    @Autowired
    private UploadFileService fileService;
    @Pointcut("execution(* ru.itis.service.UploadFileService.saveFile(..))")
    public void selectAllMethodsAvaliable() {

    }
    @Before("selectAllMethodsAvaliable() && args(file, email)")
    public void logStringArguments(MultipartFile file, String email){
        System.out.println("String argument passed=" + email);
        this.email = email;
    }

    @AfterReturning(pointcut = "selectAllMethodsAvaliable()", returning = "someValue")
    public void afterReturningAdvice(Object someValue) {
        System.out.println("Value: " + someValue.toString());
        Map<String, Object> root = new HashMap<>();
        String[] f = someValue.toString().split("/");
        String filename = "localhost:8080/files/" + f[f.length - 1];
        root.put("email", email);
        root.put("file", filename);
        sendEmailService.sendFileInfo("File info", email, root);
    }



}
