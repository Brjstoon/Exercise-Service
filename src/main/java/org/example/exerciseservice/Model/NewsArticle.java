package org.example.exerciseservice.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class NewsArticle {

    @NotEmpty(message = "ID should not be empty")
    private String id;

    @NotEmpty(message = "title should not be empty")
    @Size(max = 100, message = "title must be at most 100 characters")
    private String title;

    @NotEmpty(message = "author should not be empty")
    @Size(min = 5,max = 20, message = "author must have between 5 and 20 characters")
    private String author;

    @NotEmpty(message = "content should not be empty")
    @Size(min = 4, message = "content must have at least 201 characters")
    private String content;

    @NotEmpty(message = "category should not be empty")
    @Pattern(regexp = "^(politics|sports|technology)$", message = "category must be (politics) or (sports) or (technology) only")
    private String category;

    @NotEmpty(message = "imageUrl should not be empty")
    private String imageUrl;

    @AssertFalse(message = "Published must be initially set to false")
    private boolean published;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate publishDate;
}
