package org.example.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Setter
@Getter
@Table("REVIEWS")
public class Review {
    private Long id;
    private Long bookId;
    private String reviewerName;
    private String reviewText;
    private Long rating;
    private Date reviewDate;

    @PersistenceCreator
    public Review(Long id, Long bookId, String reviewerName, String reviewText, Long rating, Date reviewDate) {
        this.id = id;
        this.bookId = bookId;
        this.reviewerName = reviewerName;
        this.reviewText = reviewText;
        this.rating = rating;
        this.reviewDate = reviewDate;
    }
}
