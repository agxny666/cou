package com.ustyn.courseproject.dto.library;

import com.ustyn.courseproject.document.library.Library;
import com.ustyn.courseproject.document.library.LibraryStaff;
import com.ustyn.courseproject.document.literature.Literature;
import com.ustyn.courseproject.document.reader.Reader;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
public class LibraryDto {
    private String id;

    @NotNull(message = "обов'язково")
    private String name;

    private List<Literature> literatures = new ArrayList<>();

    private List<LibraryStaff> libraryStaffs = new ArrayList<>();

    private List<Reader> readers = new ArrayList<>();

    public LibraryDto(Library library) {
        this.id = library.getId().toString();
        this.name = library.getName();
        this.literatures = library.getLiteratures();
        this.libraryStaffs = library.getLibraryStaffs();
        this.readers = library.getReaders();
    }
}
