package com.example.notes.logic;

import com.example.notes.model.box.Box;
import com.example.notes.model.BoxRepository;
import com.example.notes.model.ContentRepository;
import com.example.notes.model.note.Note;
import com.example.notes.model.note.NotePresent;
import com.example.notes.model.note.NoteRead;
import com.example.notes.model.NoteRepository;
import com.example.notes.model.note.Content;
import com.example.notes.model.note.NoteWrite;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author marcin
 */
@Service
public class NoteService {
    private final NoteRepository repository;
    private final BoxRepository boxRepository;
    private final ContentRepository contentRepository;
    
    NoteService(NoteRepository noteRepository, BoxRepository boxRepository,
                   ContentRepository contentRepository) {
        this.repository = noteRepository;
        this.boxRepository = boxRepository;
        this.contentRepository = contentRepository;
    }
    
    public List<NotePresent> findAllNotes() {
        List<NotePresent> all = new ArrayList<>();
        repository.findAll()
                        .forEach((Note note) -> {
                            all.add(new NotePresent(note));
        });
        return all;
    }
    
    public NoteRead findNoteById(int id) {
        return new NoteRead(contentRepository.findById(id));
    }
    
    public void addNewNote(JSONObject json) {
        NoteWrite newNote = new NoteWrite();
        Content newContent = new Content();
        //note name
        if (json.get("name") != null && !json.get("name").toString().isBlank())
            newNote.setName(json.get("name").toString());
        else
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Lack of note name!");
        //note content
        if (json.get("content") != null && !json.get("content").toString().isBlank())
            newContent.setText(json.get("content").toString());
        else
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Lack of note content!");
        //note box_id
        if (json.get("boxId") != null) {
            try {
                newNote.setBoxId(Integer.parseInt(json.get("boxId").toString()));
                if (newNote.getBoxId() < 1)
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Box id should be greater than 0!");
            }
            catch (NumberFormatException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong box id!", e);
            }
        }
        else
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Lack of box id!");
        
        //try catch if one of both saves throw an error
        Note note = repository.save(new Note(newNote.getName(), boxRepository.findById(newNote.getBoxId())));
        newContent.setId(note.getId());
        contentRepository.save(newContent);
    }
    
    //try catch above
    //split into box change and content update
    public void updateNote(JSONObject json, int id) {
        Note updateNote;
        try {
            updateNote = new Note(repository.findById(id));
            if (updateNote.equals(new Note()))
               throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } catch (NullPointerException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        //note name
        if (json.get("name") != null) {
            if (!json.get("name").toString().isBlank())
                updateNote.setName(json.get("name").toString());
            else
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Lack of note name!");
        }
        
        //note box_id
        if (json.get("boxId") != null) {
            try {
                int boxId = Integer.parseInt(json.get("boxId").toString());
                if (boxId < 1)
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Box id should be greater than 0!");
                if (boxRepository.existsById(boxId));
                    updateNote.setBox(new Box(boxRepository.findById(boxId)));
            }
            catch (NumberFormatException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong box id!", e);
            }
        }
        
        repository.save(updateNote);
        
        //note content
        if (json.get("content") != null) {
            if (!json.get("content").toString().isBlank()) {
                contentRepository.save(new Content(id, json.get("content").toString()));
            }
            else
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Lack of note content!");
        }
    }
    
    public void deleteNote(int id) {
        if (!repository.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        if (!contentRepository.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        repository.deleteById(id);
        contentRepository.deleteById(id);
    }
}
