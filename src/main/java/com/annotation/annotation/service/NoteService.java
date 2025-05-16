package com.annotation.annotation.service;

import com.annotation.annotation.exception.ResourceNotFoundException;
import com.annotation.annotation.exception.UnauthorizedException;
import com.annotation.annotation.model.entity.Note;
import com.annotation.annotation.model.entity.NoteMapper;
import com.annotation.annotation.model.entity.User;
import com.annotation.annotation.model.entity.dto.NoteDTO;
import com.annotation.annotation.model.entity.dto.NoteResponseDTO;
import com.annotation.annotation.repository.NoteRepository;
import com.annotation.annotation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {
    private final NoteRepository noteRepository;
    private final UserService userService;

    public NoteService(NoteRepository noteRepository, UserService userService) {
        this.noteRepository = noteRepository;
        this.userService = userService;
    }

    public List<NoteResponseDTO> getAllNotes() {
        return noteRepository.findAll().stream()
                .map(NoteMapper::toResponseDTO)
                .toList();
    }

    public NoteResponseDTO getNoteById(Long id) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nota não encontrada"));
        return NoteMapper.toResponseDTO(note);
    }

    public NoteResponseDTO createNote(NoteDTO noteDTO) {
        User user = userService.getLoggedInUser();
        if (user == null) {
            throw new UnauthorizedException("Usuário não autenticado");
        }

        Note note = NoteMapper.toEntity(noteDTO, user);
        note = noteRepository.save(note);
        return NoteMapper.toResponseDTO(note);
    }

    public NoteResponseDTO updateNote(Long id, NoteDTO noteDTO) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nota não encontrada"));

        NoteMapper.updateEntity(note, noteDTO);
        note = noteRepository.save(note);
        return NoteMapper.toResponseDTO(note);
    }

    public void deleteNote(Long id) {
        if (!noteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Nota não encontrada para deletar");
        }
        noteRepository.deleteById(id);
    }
}
