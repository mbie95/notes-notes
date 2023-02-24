package com.example.notes.logic;

import com.example.notes.model.ContentRepository;
import com.example.notes.model.note.Content;
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
public class MockContentRepository {
    Content content1, content2;
    ArrayList<Content> list;
    
    MockContentRepository() {
        content1 = new Content();
        content1.setId(1);
        content1.setText("Note1's content");
        content2 = new Content();
        content2.setId(2);
        content2.setText("Note2's content");
        list = new ArrayList<>() {
                        {
                            add(content1);
                            add(content2);
                        }
                    };
    }
    
    ContentRepository init() {
        return new ContentRepository() {
            public long count() {
                return list.size();
            }
            
            @Override
            public Optional<Content> findById(Integer id) {
                for (int i = 0 ; i < list.size() ; ++i)
                    if (list.get(i).getId() == id)
                        return Optional.of(list.get(i));
                return null;
            }
            
            @Override
            public Content save(Content content) {
                for (int i = 0; i < list.size(); ++i)
                    if (list.get(i).getId() == content.getId()) {
                        list.get(i).setText(content.getText());
                        return list.get(i);
                    }
                list.add(content);
                return content;
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
            public <S extends Content> List<S> saveAll(Iterable<S> arg0) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public List<Content> findAll() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public List<Content> findAll(Sort arg0) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public <S extends Content> S insert(S arg0) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public <S extends Content> List<S> insert(Iterable<S> arg0) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public <S extends Content> List<S> findAll(Example<S> arg0) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public <S extends Content> List<S> findAll(Example<S> arg0, Sort arg1) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public Page<Content> findAll(Pageable arg0) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public Iterable<Content> findAllById(Iterable<Integer> arg0) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void delete(Content arg0) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void deleteAllById(Iterable<? extends Integer> arg0) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void deleteAll(Iterable<? extends Content> arg0) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void deleteAll() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public <S extends Content> Optional<S> findOne(Example<S> arg0) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public <S extends Content> Page<S> findAll(Example<S> arg0, Pageable arg1) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public <S extends Content> long count(Example<S> arg0) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public <S extends Content> boolean exists(Example<S> arg0) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
            
        };
    };
};
