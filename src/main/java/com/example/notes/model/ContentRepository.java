package com.example.notes.model;

import com.example.notes.model.note.Content;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 *
 * @author marcin
 */
@RepositoryRestResource
public interface ContentRepository extends MongoRepository<Content, Integer> {
    @Query("{id : ?0}")   
    public Optional<Content> findById(Integer id);
    public Content save(Content content);
    public boolean existsById(Integer id);
    public void deleteById(Integer id);
}
