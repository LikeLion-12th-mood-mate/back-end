package com.example.modemate.DTO;

import com.example.modemate.domain.Program;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewRegisterRequestDTO {

    private Program program;

    private String reviewText;

    private double rating;
}
