package com.ustyn.courseproject.dto.literature;

import com.ustyn.courseproject.document.literature.Article;
import com.ustyn.courseproject.validation.inPast.InPast;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@NoArgsConstructor
@Data
public class ArticleDto {
    private String id;

    @NotNull(message = "обов'язково")
    private String title;

    @NotNull(message = "обов'язково")
    private String author;

    @NotNull(message = "обов'язково")
    private String inventoryNumber;

    @NotNull(message = "обов'язково")
    private boolean available = true;

    @NotNull(message = "обов'язково")
    @InPast
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate publishDate;

    public ArticleDto(Article article) {
        this.id = article.getId().toString();
        this.title = article.getTitle();
        this.author = article.getAuthor();
        this.inventoryNumber = article.getInventoryNumber();
        this.available = article.isAvailable();
        this.publishDate = article.getPublishDate();
    }

}
