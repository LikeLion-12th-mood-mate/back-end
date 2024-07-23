package com.example.modemate.Controller;


import com.example.modemate.DTO.ProgramEnrollRequestDTO;
import com.example.modemate.Service.ProgramService;
import com.example.modemate.domain.Program;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/program")
@RequiredArgsConstructor
@Slf4j
public class ProgramController {

    private final ProgramService programService;

    @PostMapping("/enroll")
    public String enroll(@Valid @RequestBody ProgramEnrollRequestDTO requestDTO)
            throws IOException {

        log.info("[Program Controller] enroll");

        Program program = programService.createProgram(requestDTO);

        return "success to enroll program :" + program.getName();

    }



}
