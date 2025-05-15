package com.annotation.annotation.model.entity.dto;

import java.time.LocalDateTime;

public class NoteResponseDTO {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime dataCriacao;
    private Long userId;

    public NoteResponseDTO() {
    }

    public NoteResponseDTO(Long id, String title, String content, LocalDateTime dataCriacao, Long userId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.dataCriacao = dataCriacao;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
