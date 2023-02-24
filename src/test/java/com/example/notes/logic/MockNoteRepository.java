package com.example.notes.logic;

import com.example.notes.model.box.Box;
import com.example.notes.model.note.Note;
import com.example.notes.model.NoteRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 *
 * @author marcin
 */
public class MockNoteRepository {
    Box box1, box2;
    Note note1, note2;
    ArrayList<Note> list;
    int newIndex = 3;

    MockNoteRepository() {
        box1 = new Box();
        box1.setId(1);
        box1.setName("Box1");
        box2 = new Box();
        box2.setId(2);
        box2.setName("Box2");
        note1 = new Note();
        note1.setId(1);
        note1.setName("Note1");
        note1.setBox(box1);
        note2 = new Note();
        note2.setId(2);
        note2.setName("Note2");
        note2.setBox(box1);
        list = new ArrayList<>() {
                        {
                            add(note1);
                            add(note2);
                        }
                    };
    }
    
    NoteRepository init() {
        return new NoteRepository() {
            public long count() {
                return list.size();
            }
            
            @Override
            public List<Note> findAll() {
                return list;
            }

            @Override
            public Optional<Note> findById(Integer id) {
                for (int i = 0 ; i < list.size() ; ++i)
                    if (list.get(i).getId() == id)
                        return Optional.of(list.get(i));
                return null;
            }
            
            @Override
            public Note save(Note note) {
                for (int i = 0; i < list.size(); ++i)
                    if (list.get(i).getId() == note.getId()) {
                        list.get(i).setName(note.getName());
                        list.get(i).setBox(note.getBox());
                        return list.get(i);
                    }
                note.setId(newIndex++);
                list.add(note);
                return note;
            }
            
            @Override
            public boolean existsById(Integer id) {
                for (int i = 0; i < list.size(); ++i)
                    if (list.get(i).getId() == id)
                        return true;
                return false;
            }
            
            @Override
            public void deleteById(Integer id) {
                for (int i = 0; i < list.size(); ++i) {
                    if (list.get(i).getId() == id)
                       list.remove(list.get(i));
                }
            }

            @Override
            public List<Note> findAll(Sort arg0) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public List<Note> findAllById(Iterable<Integer> arg0) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public <S extends Note> List<S> saveAll(Iterable<S> arg0) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void flush() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public <S extends Note> S saveAndFlush(S arg0) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public <S extends Note> List<S> saveAllAndFlush(Iterable<S> arg0) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void deleteAllInBatch(Iterable<Note> arg0) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void deleteAllByIdInBatch(Iterable<Integer> arg0) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void deleteAllInBatch() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public Note getOne(Integer arg0) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public Note getById(Integer arg0) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public <S extends Note> List<S> findAll(Example<S> arg0) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public <S extends Note> List<S> findAll(Example<S> arg0, Sort arg1) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public Page<Note> findAll(Pageable arg0) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void delete(Note arg0) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void deleteAllById(Iterable<? extends Integer> arg0) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void deleteAll(Iterable<? extends Note> arg0) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void deleteAll() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public <S extends Note> Optional<S> findOne(Example<S> arg0) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public <S extends Note> Page<S> findAll(Example<S> arg0, Pageable arg1) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public <S extends Note> long count(Example<S> arg0) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public <S extends Note> boolean exists(Example<S> arg0) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
            
        };
    }
}
