package com.diva.notes.notes_api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diva.notes.notes_api.dto.NoteDto;
import com.diva.notes.notes_api.entity.NoteEntity;
import com.diva.notes.notes_api.service.NotesService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/notes")
public class NotesController {

    private final NotesService service;

    public NotesController(NotesService service) {
        this.service = service;
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Notes API running");
    }

    @PostMapping
    public ResponseEntity<NoteEntity> create(@Valid @RequestBody NoteDto dto) {
        NoteEntity created = service.createNote(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<NoteEntity>> all() {
        return ResponseEntity.ok(service.getAllNotes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoteEntity> one(@PathVariable Long id) {
        return ResponseEntity.ok(service.getNoteById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NoteEntity> update(@PathVariable Long id, @Valid @RequestBody NoteDto dto) {
        return ResponseEntity.ok(service.updateNote(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteNote(id);
        return ResponseEntity.noContent().build();
    }
}