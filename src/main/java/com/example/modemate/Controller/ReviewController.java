package com.example.modemate.Controller;


import com.example.modemate.DTO.ReviewRegisterRequestDTO;
import com.example.modemate.Service.ReviewService;
import com.example.modemate.domain.Review;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/program")
@RequiredArgsConstructor
@Slf4j
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/{programId}/reviews")
    public ResponseEntity<String> register(@PathVariable Long programId, @RequestBody ReviewRegisterRequestDTO requestDTO) {

        log.info("[Review Controller] add");

        Review review = reviewService.addReview(programId,requestDTO);

        // 로직 수행 후 성공 응답
        return ResponseEntity.ok("Review added successfully" + review);
    }
}
