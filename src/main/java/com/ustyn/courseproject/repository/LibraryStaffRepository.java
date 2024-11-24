package com.ustyn.courseproject.repository;

import com.ustyn.courseproject.document.library.LibraryStaff;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryStaffRepository extends MongoRepository<LibraryStaff, String> {
    boolean existsByName(String name);
}
