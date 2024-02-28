package org.example.dtos;

import java.util.List;

public class PageDto {
    private List<DetailedBookDto> detailedBookDto;
    private Long countBooks;
    private Long page;
    private Long bookPerPage;

    private Long countPage;

    public List<DetailedBookDto> getDetailedBookDto() {
        return detailedBookDto;
    }

    public void setDetailedBookDto(List<DetailedBookDto> detailedBookDto) {
        this.detailedBookDto = detailedBookDto;
    }

    public Long getCountBooks() {
        return countBooks;
    }

    public void setCountBooks(Long countBooks) {
        this.countBooks = countBooks;
    }

    public Long getPage() {
        return page;
    }

    public void setPage(Long page) {
        this.page = page;
    }

    public Long getBookPerPage() {
        return bookPerPage;
    }

    public void setBookPerPage(Long bookPerPage) {
        this.bookPerPage = bookPerPage;
    }

    public Long getCountPage() {
        return countPage;
    }

    public void setCountPage(Long countPage) {
        this.countPage = countPage;
    }

    public PageDto(List<DetailedBookDto> detailedBookDto, Long countBooks, Long page, Long bookPerPage, Long countPage) {
        this.detailedBookDto = detailedBookDto;
        this.countBooks = countBooks;
        this.page = page;
        this.bookPerPage = bookPerPage;
        this.countPage = countPage;
    }

    public PageDto() {
    }
}
