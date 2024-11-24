package com.ustyn.courseproject.document.literature;

import com.ustyn.courseproject.dto.literature.BookDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.bson.types.ObjectId;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
@ToString(callSuper = true)
public class Book extends Literature {
    private int pages;

    public Book(String title, String author, String inventoryNumber, boolean available, int pages) {
        super(title, author, inventoryNumber, available);
        this.pages = pages;
    }

    public Book(BookDto dto) {
        ObjectId objectId = dto.getId() != null ? new ObjectId(dto.getId()) : new ObjectId();
        super(objectId, dto.getTitle(), dto.getAuthor(), dto.getInventoryNumber(), dto.isAvailable());
        this.pages = dto.getPages();
    }
}
