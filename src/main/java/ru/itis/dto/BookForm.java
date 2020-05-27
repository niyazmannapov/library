package ru.itis.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class BookForm {
    @NotEmpty(message = "{NotEmpty.field")
    private String text;
    @Size(min = 2, max = 30, message = "{NotEmpty.field")
    private String name;
    @NotEmpty(message = "{NotEmpty.field")
    private String authorFirstName;
    @NotEmpty(message = "{NotEmpty.field")
    private String authorLastName;

}
