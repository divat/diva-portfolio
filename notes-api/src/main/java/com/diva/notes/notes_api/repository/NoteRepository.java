package com.diva.notes.notes_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diva.notes.notes_api.entity.NoteEntity;

public interface NoteRepository extends JpaRepository<NoteEntity, Long> {
    
}
