package com.ustyn.courseproject.service.libraryStaff;

import com.ustyn.courseproject.document.library.LibraryStaff;
import org.bson.types.ObjectId;

import java.util.List;

public interface LibraryStaffService {
    LibraryStaff save(LibraryStaff libraryStaff);
    List<LibraryStaff> findAll();
    LibraryStaff findById(ObjectId id);
    void deleteById(ObjectId id);
    boolean existsByName(String name);
}
