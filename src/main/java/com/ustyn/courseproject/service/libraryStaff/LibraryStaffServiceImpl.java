package com.ustyn.courseproject.service.libraryStaff;

import com.ustyn.courseproject.document.library.LibraryStaff;
import com.ustyn.courseproject.repository.LibraryStaffRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryStaffServiceImpl implements LibraryStaffService {

    private final LibraryStaffRepository libraryStaffRepository;

    public LibraryStaffServiceImpl(LibraryStaffRepository libraryStaffRepository) {
        this.libraryStaffRepository = libraryStaffRepository;
    }

    @Override
    public LibraryStaff save(LibraryStaff libraryStaff) {
        return libraryStaffRepository.save(libraryStaff);
    }

    @Override
    public List<LibraryStaff> findAll() {
        return libraryStaffRepository.findAll();
    }

    @Override
    public LibraryStaff findById(ObjectId id) {
        return libraryStaffRepository.findById(id.toString()).orElse(null);
    }

    @Override
    public void deleteById(ObjectId id) {
        libraryStaffRepository.deleteById(id.toString());
    }

    @Override
    public boolean existsByName(String name) {
        return libraryStaffRepository.existsByName(name);
    }
}
