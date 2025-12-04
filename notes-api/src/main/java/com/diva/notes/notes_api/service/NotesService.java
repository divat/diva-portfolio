package com.diva.notes.notes_api.service;

import java.util.List;

import com.diva.notes.notes_api.dto.NoteDto;
import com.diva.notes.notes_api.entity.NoteEntity;

public interface NotesService {
    NoteEntity createNote(NoteDto dto);
    List<NoteEntity> getAllNotes();
    NoteEntity getNoteById(Long id);
    NoteEntity updateNote(Long id, NoteDto dto);
    void deleteNote(Long id);
}
