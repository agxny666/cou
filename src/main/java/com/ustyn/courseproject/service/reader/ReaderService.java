package com.ustyn.courseproject.service.reader;

import com.ustyn.courseproject.document.literature.Literature;
import com.ustyn.courseproject.document.reader.Reader;
import lombok.Locked;

import java.time.LocalDate;
import java.util.List;

public interface ReaderService {
    Reader save(Reader reader);
    boolean existsByName(String name);
    List<Reader> findAll();
    Reader findById(String id);
    void deleteById(String id);
    void delete(Reader reader);
    List<Reader> findReadersByBorrowedLiterature(Literature literature);
    List<Reader> findByInstitutionOrSpecialty(String query);
    List<Reader> findReadersNotVisitSince(LocalDate date);
}
