package com.ustyn.courseproject.repository;

import com.ustyn.courseproject.document.literature.Literature;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LiteratureRepository extends MongoRepository<Literature, String> {
    boolean existsByTitle(String title);
    Literature findByTitle(String title);
    List<Literature> findAllByAuthor(String author);
}
