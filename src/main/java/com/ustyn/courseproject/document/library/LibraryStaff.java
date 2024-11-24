package com.ustyn.courseproject.document.library;

import com.ustyn.courseproject.dto.library.LibraryStaffDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@NoArgsConstructor
@Data
@Document(collection = "libraryStaffs")
public class LibraryStaff {
    @Id
    private ObjectId id;

    private String name;

    public LibraryStaff(LibraryStaffDto dto) {
        this.id = dto.getId() != null ? new ObjectId(dto.getId()) : new ObjectId();
        this.name = dto.getName();
    }

    public LibraryStaff(String name) {
        this.name = name;
    }
}
