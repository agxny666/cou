package com.ustyn.courseproject.service.literature;

import com.ustyn.courseproject.document.literature.Article;
import com.ustyn.courseproject.document.literature.Book;
import com.ustyn.courseproject.document.literature.Literature;
import com.ustyn.courseproject.document.reader.Reader;
import com.ustyn.courseproject.document.reader.Scientist;
import com.ustyn.courseproject.document.reader.Student;
import com.ustyn.courseproject.repository.LiteratureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LiteratureServiceImpl implements LiteratureService {

    private final LiteratureRepository literatureRepository;

    @Autowired
    public LiteratureServiceImpl(LiteratureRepository literatureRepository) {
        this.literatureRepository = literatureRepository;
    }

    @Override
    public Literature save(Literature literature) {
        Optional<Literature> existingLiteratureOpt = Optional.empty();
        if (literature.getId() != null) {
            existingLiteratureOpt = literatureRepository.findById(literature.getId().toString());
        }

        if (existingLiteratureOpt.isPresent()) {
            Literature existingLiterature = existingLiteratureOpt.get();

            existingLiterature.setTitle(literature.getTitle());
            existingLiterature.setAuthor(literature.getAuthor());
            existingLiterature.setInventoryNumber(literature.getInventoryNumber());
            existingLiterature.setAvailable(literature.isAvailable());

            if (literature instanceof Book && existingLiterature instanceof Book) {
                ((Book) existingLiterature).setPages(((Book) literature).getPages());
            } else if (literature instanceof Article && existingLiterature instanceof Article) {
                ((Article) existingLiterature).setPublishDate(((Article) literature).getPublishDate());
            } else {
                throw new IllegalArgumentException("Unsupported Literature type for update: " + literature.getClass());
            }

            return literatureRepository.save(existingLiterature);
        } else {
            return literatureRepository.save(literature);
        }
    }


    @Override
    public boolean existsByTitle(String title) {
        return literatureRepository.existsByTitle(title);
    }

    @Override
    public List<Literature> findAll() {
        return literatureRepository.findAll();
    }

    @Override
    public Literature findByTitle(String title) {
        return literatureRepository.findByTitle(title);
    }

    @Override
    public Literature findById(String id) {
        return literatureRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(String id) {
        literatureRepository.deleteById(id);
    }

    @Override
    public List<Literature> findAllByAuthor(String author) {
        return literatureRepository.findAllByAuthor(author);
    }
}
