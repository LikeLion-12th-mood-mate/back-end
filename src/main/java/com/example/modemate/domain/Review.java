package com.example.modemate.domain;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "program_id")
    private Program program;

    private String reviewText;

    private double rating;

    private LocalDateTime createdAt;

    @Builder
    public Review(Long id, Program program, String reviewText, double rating, LocalDateTime createdAt) {
        this.id = id;
        this.program = program;
        this.reviewText = reviewText;
        this.rating = rating;
        this.createdAt = createdAt;
    }
}