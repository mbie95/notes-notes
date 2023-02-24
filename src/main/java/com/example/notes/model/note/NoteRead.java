package com.example.notes.model.note;

import java.util.Objects;
import java.util.Optional;

/**
 *
 * @author marcin
 */
public class NoteRead {
    private Integer id;
    private String content;

    public NoteRead() {
    }

    public NoteRead(Optional<Content> content) {
        this();
        if (content.isPresent()) {
            this.id = content.get().getId();
            this.content = content.get().getText();
        }
    }
    
    public NoteRead(Content content) {
        this.id = content.getId();
        this.content = content.getText();
    }

    public Integer getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
   
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final NoteRead other = (NoteRead) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.content, other.content)) {
            return false;
        }
        return true;
    } 
}
