package com.annotation.annotation.repository;

import com.annotation.annotation.model.entity.Note;
import com.annotation.annotation.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findByUserId(Long userId);
}
