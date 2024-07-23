package com.example.modemate.Service;



import com.example.modemate.DTO.ReviewRegisterRequestDTO;
import com.example.modemate.Repository.ProgramRepository;
import com.example.modemate.Repository.ReviewRepository;
import com.example.modemate.domain.Program;
import com.example.modemate.domain.Review;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class ReviewService {

    private final ProgramRepository programRepository;
    private final ReviewRepository reviewRepository;

    @Transactional
    public Review addReview(Long programId, ReviewRegisterRequestDTO requestDTO) {

        Program program = programRepository.findById(programId)
                .orElseThrow(() -> new RuntimeException("Program not found"));


        Review review = Review.builder()
                .program(program)
                .reviewText(requestDTO.getReviewText())
                .rating(requestDTO.getRating())
                .createdAt(LocalDateTime.now())
                .build();

        return reviewRepository.save(review);
    }

}

