package ru.itis.service;

import org.springframework.web.multipart.MultipartFile;
import ru.itis.dto.UserConfirmDto;

import java.io.File;

public interface UploadFileService {
    void saveFile(MultipartFile file, UserConfirmDto user);

    File findFile(String fileName);
}
