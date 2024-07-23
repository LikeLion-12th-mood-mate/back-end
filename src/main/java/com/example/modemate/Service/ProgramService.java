package com.example.modemate.Service;


import com.example.modemate.DTO.ProgramEnrollRequestDTO;
import com.example.modemate.Repository.CounselorRepository;
import com.example.modemate.Repository.ProgramRepository;
import com.example.modemate.domain.Counselor;
import com.example.modemate.domain.Program;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class ProgramService {


    private final CounselorRepository counselorRepository;
    private final ProgramRepository programRepository;


    @Transactional
    public Program createProgram(ProgramEnrollRequestDTO requestDTO) throws IOException {

        log.info("[Program Service] create program");


        Counselor counselor = counselorRepository.findById(requestDTO.getCounselor_id())
                .orElseThrow(() -> new IllegalArgumentException("Invalid counselor ID"));

        Program program = Program.builder()
                .name(requestDTO.getName())
                .counselor(counselor)
                .time(requestDTO.getTime())
                .place(requestDTO.getPlace())
                .details(requestDTO.getDetails())
                .keyWord(requestDTO.getKeyWord())
                .build();


        return programRepository.save(program);
    }

}
