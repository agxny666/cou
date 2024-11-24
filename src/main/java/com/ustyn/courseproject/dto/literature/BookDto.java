package com.ustyn.courseproject.dto.literature;

import com.ustyn.courseproject.document.literature.Book;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class BookDto {
    private String id;

    @NotNull(message = "обов'язково")
    private String title;

    @NotNull(message = "обов'язково")
    private String author;

    @NotNull(message = "обов'язково")
    private String inventoryNumber;

    @NotNull(message = "обов'язково")
    private boolean available = true;

    @Min(value = 1, message = "мінімум 1 сторінка")
    private int pages;

    public BookDto(Book book) {
        this.id = book.getId().toString();
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.inventoryNumber = book.getInventoryNumber();
        this.available = book.isAvailable();
        this.pages = book.getPages();
    }
}
