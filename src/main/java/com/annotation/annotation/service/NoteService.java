package com.annotation.annotation.service;

import com.annotation.annotation.model.entity.Note;
import com.annotation.annotation.model.entity.User;
import com.annotation.annotation.model.entity.dto.NoteDTO;
import com.annotation.annotation.repository.NoteRepository;
import com.annotation.annotation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {
    private final NoteRepository noteRepository;

    private final UserRepository userRepository;

    private final UserService userService;

    public NoteService(NoteRepository noteRepository, UserRepository userRepository, UserService userService) {
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    public List<Note> getAllNotes(){
        return noteRepository.findAll();
    }

    public Optional<Note> getNoteById(Long id){
        return noteRepository.findById(id);
    }

    public Note createNote(NoteDTO noteDTO){
        Note note = new Note();
        note.setTitle(noteDTO.getTitle());
        note.setContent(noteDTO.getContent());
        User loggedInUser = userService.getLoggedInUser();
        if (loggedInUser == null){
            throw new RuntimeException("Usuário não autenticado");
        }
        note.setUser(loggedInUser);
        return noteRepository.save(note);
    }

}
