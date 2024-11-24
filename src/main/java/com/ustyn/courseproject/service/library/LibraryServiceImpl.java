package com.ustyn.courseproject.service.library;

import com.ustyn.courseproject.document.library.Library;
import com.ustyn.courseproject.document.library.LibraryStaff;
import com.ustyn.courseproject.document.literature.Literature;
import com.ustyn.courseproject.repository.LibraryRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LibraryServiceImpl implements LibraryService {
    private final LibraryRepository libraryRepository;

    public LibraryServiceImpl(LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    @Override
    public Library save(Library library) {
        if (library.getId() != null) {
            Optional<Library> existingLibraryOpt = libraryRepository.findById(library.getId().toString());
            if (existingLibraryOpt.isPresent()) {
                // Retrieve the existing library
                Library existingLibrary = existingLibraryOpt.get();

                // Update the name only
                existingLibrary.setName(library.getName());

                // Save the updated library (preserving lists)
                return libraryRepository.save(existingLibrary);
            }
        }

        return libraryRepository.save(library);
    }

    @Override
    public List<Library> findAll() {
        return libraryRepository.findAll();
    }

    @Override
    public Library findById(String id) {
        return libraryRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(String id) {
        libraryRepository.deleteById(id);
    }

    @Override
    public boolean existsByName(String name) {
        return libraryRepository.existsByName(name);
    }

    @Override
    public List<LibraryStaff> findStaffByLibraryId(ObjectId libraryId) {
        Optional<Library> library = libraryRepository.findById(libraryId.toString());

        if (library.isPresent()) {
            return library.get().getLibraryStaffs();
        }
        return new ArrayList<>();
    }
}
