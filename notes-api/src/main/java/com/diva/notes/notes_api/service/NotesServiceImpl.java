package com.diva.notes.notes_api.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.diva.notes.notes_api.dto.NoteDto;
import com.diva.notes.notes_api.entity.NoteEntity;
import com.diva.notes.notes_api.exception.NoteNotFoundException;
import com.diva.notes.notes_api.repository.NoteRepository;

@Service
public class NotesServiceImpl implements NotesService {
    
    private final NoteRepository repo;

    public NotesServiceImpl(NoteRepository repo) {
        this.repo = repo;
    }

    @Override
    public NoteEntity createNote(NoteDto dto) {
        NoteEntity entity = new NoteEntity();
        entity.setTitle(dto.getTitle());
        entity.setContent(dto.getContent());
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());
        return repo.save(entity);
    }

    @Override
    public List<NoteEntity> getAllNotes() {
        return repo.findAll();
    }

    @Override
    public NoteEntity getNoteById(Long id) {
        return repo.findById(id).orElseThrow(() -> new NoteNotFoundException(id));
    }

    @Override
    public NoteEntity updateNote(Long id, NoteDto dto) {
        NoteEntity exist = repo.findById(id).orElseThrow(() -> new NoteNotFoundException(id));
        exist.setTitle(dto.getTitle());
        exist.setContent(dto.getContent());
        exist.setUpdatedAt(LocalDateTime.now());
        return repo.save(exist);
    }

    @Override
    public void deleteNote(Long id) {
        NoteEntity exist = repo.findById(id).orElseThrow(() -> new NoteNotFoundException(id));
        repo.deleteById(exist.getId());
    }

}