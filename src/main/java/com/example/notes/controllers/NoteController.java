package com.example.notes.controllers;

import com.example.notes.model.note.NotePresent;
import com.example.notes.model.note.NoteRead;
import com.example.notes.logic.NoteService;
import java.util.List;
import javax.transaction.Transactional;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author marcin
 */
@RestController
@RequestMapping("/notes")
public class NoteController {
    private final NoteService service;
    
    NoteController(NoteService noteService) {
        this.service = noteService;
    }
    
    @GetMapping
    ResponseEntity<List<NotePresent>> getAllNotes() {
        return new ResponseEntity<>(service.findAllNotes(), HttpStatus.OK);
    }
    
    @GetMapping(path = "/{id}")
    ResponseEntity<NoteRead> getNote(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(service.findNoteById(id), HttpStatus.OK);
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void postNote(@RequestBody JSONObject json) {
        service.addNewNote(json);
    }
   
    @PatchMapping(path = "/{id}", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    void patchNote(@PathVariable("id") Integer id, @RequestBody JSONObject json) {
        service.updateNote(json, id);
    }
    
    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteNote(@PathVariable("id") Integer id) {
        service.deleteNote(id);
    }
}
