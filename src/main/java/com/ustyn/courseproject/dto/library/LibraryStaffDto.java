package com.ustyn.courseproject.dto.library;

import com.ustyn.courseproject.document.library.LibraryStaff;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class LibraryStaffDto {
    private String id;

    @NotNull(message = "обов'язково")
    private String name;

    public LibraryStaffDto(LibraryStaff libraryStaff) {
        this.id = libraryStaff.getId().toString();
        this.name = libraryStaff.getName();
    }
}
