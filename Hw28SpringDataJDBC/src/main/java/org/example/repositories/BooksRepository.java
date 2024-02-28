package org.example.repositories;

import org.example.dtos.DetailedBookDto;
import org.example.entities.Book;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BooksRepository extends ListCrudRepository<Book, Long> {
    @Query("select * from v_books")
    List<DetailedBookDto> findAllDetailedBooks();

    @Query("update books set title = :title where id = :id")
    @Modifying
    void changeTitleById(Long id, String title);

    @Query("select * from v_books" +
            " where id>:bookByPage * (:page - 1)" +
            " and id<=:bookByPage * :page")
    List<DetailedBookDto> findAllDetailedBooksPagin(Long bookByPage, Long page);

    long count();

    @Query("select * from v_books" +
            " where id = :id")
    DetailedBookDto findDetailedBooksById(Long id);

    @Query("select * from top10books")
    List<DetailedBookDto> findTop10BookByReview();
}