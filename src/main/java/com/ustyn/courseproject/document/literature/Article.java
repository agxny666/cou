package com.ustyn.courseproject.document.literature;

import com.ustyn.courseproject.dto.literature.ArticleDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.bson.types.ObjectId;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
@ToString(callSuper = true)
public class Article extends Literature {
    private LocalDate publishDate;

    public Article(String title, String author, String inventoryNumber, boolean available, LocalDate publishDate) {
        super(title, author, inventoryNumber, available);
        this.publishDate = publishDate;
    }

    public Article(ArticleDto dto) {
        ObjectId objectId = dto.getId() != null ? new ObjectId(dto.getId()) : new ObjectId();
        super(objectId, dto.getTitle(), dto.getAuthor(), dto.getInventoryNumber(), dto.isAvailable());
        this.publishDate = dto.getPublishDate();
    }
}
