package com.annotation.annotation.model.entity;

import com.annotation.annotation.model.entity.dto.NoteDTO;
import com.annotation.annotation.model.entity.dto.NoteResponseDTO;

public class NoteMapper {

    public static Note toEntity(NoteDTO dto, User user){
        Note note = new Note();
        note.setTitle(dto.getTitle());
        note.setContent(dto.getContent());
        note.setUser(user);
        return note;
    }

    public static  void updateEntity(Note note, NoteDTO dto){
        note.setTitle(dto.getTitle());
        note.setContent(dto.getContent());
    }

    public static NoteResponseDTO toResponseDTO(Note note){
        return new NoteResponseDTO(
                note.getId(),
                note.getTitle(),
                note.getContent(),
                note.getDataCriacao(),
                note.getUser() != null ? note.getUser().getId() : null
        );
    }
}
