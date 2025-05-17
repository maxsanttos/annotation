package com.annotation.annotation.model.entity.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public class NoteDTO {

    @NotBlank(message = "O título não pode estar em branco")
    @Size(min = 3, max = 100, message = "O título deve ter entre 3 e 100 caracteres")
    private String title;
    @NotBlank(message = "O conteúdo não pode estar em branco")
    private String content;


    public NoteDTO() {
    }

    public NoteDTO(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
