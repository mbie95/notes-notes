package com.example.notes.model.note;

import java.util.Optional;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author marcin
 */
@Document(collection = "content")
public class Content {
    @Id
    private Integer id;
    private String text;

    public Content() {
    }

    public Content(Optional<Content> content) {
        this();
        if (content.isPresent()) {
            this.id = content.get().getId();
            this.text = content.get().getText();
        }
    }

    public Content(Integer id, String text) {
        this.id = id;
        this.text = text;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
