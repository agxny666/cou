package com.ustyn.courseproject.service.literature;

import com.ustyn.courseproject.document.literature.Literature;

import java.util.List;

public interface LiteratureService {
    Literature save(Literature literature);
    boolean existsByTitle(String title);
    List<Literature> findAll();
    Literature findByTitle(String title);
    Literature findById(String id);
    void deleteById(String id);
    List<Literature> findAllByAuthor(String author);
}
