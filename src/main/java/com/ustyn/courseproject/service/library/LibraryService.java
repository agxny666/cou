package com.ustyn.courseproject.service.library;

import com.ustyn.courseproject.document.library.Library;
import com.ustyn.courseproject.document.library.LibraryStaff;
import org.bson.types.ObjectId;

import java.util.List;

public interface LibraryService {
    Library save(Library library);
    List<Library> findAll();
    Library findById(String id);
    void deleteById(String id);
    boolean existsByName(String name);
    List<LibraryStaff> findStaffByLibraryId(ObjectId libraryId);
}
