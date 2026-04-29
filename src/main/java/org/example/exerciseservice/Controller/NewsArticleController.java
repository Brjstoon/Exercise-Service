package org.example.exerciseservice.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.exerciseservice.Api.ApiResponse;
import org.example.exerciseservice.Model.NewsArticle;
import org.example.exerciseservice.Service.NewsArticleService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/article")
public class NewsArticleController {

    private final NewsArticleService newsArticleService;


    @GetMapping("/get")
    public ResponseEntity<?> getNewsArticles() {
        return ResponseEntity.status(200).body(newsArticleService.getNewsArticles());
    }


    @PostMapping("/add")
    public ResponseEntity<?> addNewsArticle(@RequestBody @Valid NewsArticle newsArticle, Errors errors) {
        if (errors.hasErrors()) {
            String m = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(m);
        }
        newsArticleService.addNewsArticles(newsArticle);
        return ResponseEntity.status(200).body(new ApiResponse("News Article Added Successfully"));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateNewsArticle(@PathVariable String id, @RequestBody @Valid NewsArticle newsArticle, Errors errors) {
        if (errors.hasErrors()) {
            String m = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(m);
        }
        if (newsArticleService.updateNewsArticle(newsArticle, id)) {
            return ResponseEntity.status(200).body(new ApiResponse("News Article Updated Successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Updating Failed"));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteNewsArticle(@PathVariable String id) {
        if (newsArticleService.deleteNewsArticle(id)) {
            return ResponseEntity.status(200).body(new ApiResponse("News Article Deleted Successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("News Article Not Found"));
    }

    @PutMapping("/publish/{id}")
    public ResponseEntity<?> publishNewsArticle(@PathVariable String id) {

        int stat = newsArticleService.publisNewsArticle(id);
        if (stat == 1){
            return ResponseEntity.status(400).body(new ApiResponse("The News Article is already Published"));
        } else if (stat == 2) {
            return ResponseEntity.status(400).body(new ApiResponse("News Article Not Found"));
        }
        return ResponseEntity.status(200).body(new ApiResponse("The Article has been published"));
    }


    @GetMapping("/search-published")
    public ResponseEntity<?> searchByIsPublished() {

        if (newsArticleService.searchIsPublished().isEmpty()) {
            return ResponseEntity.status(400).body(new ApiResponse("No published articles found"));
        }
        return ResponseEntity.status(200).body(newsArticleService.searchIsPublished());
    }


    @GetMapping("/search-category/{category}")
    public ResponseEntity<?> searchByCategory(@PathVariable String category) {

        if (newsArticleService.searchByCategory(category).isEmpty()) {
            return ResponseEntity.status(400).body(new ApiResponse("No articles found in (" + category + ") category"));
        }
        return ResponseEntity.status(200).body(newsArticleService.searchByCategory(category));
    }
}