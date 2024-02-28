package org.example.controllers;

import org.example.dtos.DetailedBookDto;
import org.example.dtos.PageDto;
import org.example.services.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
public class BooksController {
    private final BooksService booksService;

    @Autowired
    public BooksController(BooksService booksService) {
        this.booksService = booksService;
    }

    @GetMapping
    public PageDto findAllDetailedBooksPageDto(@RequestParam(defaultValue = "100") Long bookByPage, @RequestParam(defaultValue = "1") Long page) {
        return booksService.findAllDetailedBooksPageDto(bookByPage, page);
    }

    @GetMapping("/{id}")
    public DetailedBookDto findDetailedBooksById(@PathVariable Long id) {
        return booksService.findDetailedBooksById(id);
    }

    @GetMapping("/top10")
    public List<DetailedBookDto> findTop10BookByReview() {
        return booksService.findTop10BookByReview();
    }

    @PatchMapping("/{id}/title")
    public void updateTitleById(@PathVariable Long id, @RequestParam String value) {
        booksService.updateTitleById(id, value);
    }
}
