package com.ustyn.courseproject.document.library;

import com.ustyn.courseproject.document.literature.Literature;
import com.ustyn.courseproject.document.reader.Reader;
import com.ustyn.courseproject.dto.library.LibraryDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@NoArgsConstructor
@Data
@Document(collection = "libraries")
public class Library {

    @Id
    private ObjectId id;

    private String name;

    @DBRef
    private List<Literature> literatures;

    @DBRef
    private List<LibraryStaff> libraryStaffs;

    @DBRef
    private List<Reader> readers;

    public Library(LibraryDto dto) {
        this.id = dto.getId() != null ? new ObjectId(dto.getId()) : new ObjectId();
        this.name = dto.getName();
        this.literatures = dto.getLiteratures();
        this.libraryStaffs = dto.getLibraryStaffs();
        this.readers = dto.getReaders();
    }

    public Library(String name, List<Literature> literatures, List<LibraryStaff> libraryStaffs, List<Reader> readers) {
        this.name = name;
        this.literatures = literatures;
        this.libraryStaffs = libraryStaffs;
        this.readers = readers;
    }
}
