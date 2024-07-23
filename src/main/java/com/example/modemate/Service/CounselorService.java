package com.example.modemate.Service;


import com.example.modemate.DTO.CounselorRegisterRequestDTO;
import com.example.modemate.Repository.CounselorRepository;
import com.example.modemate.domain.Counselor;
import com.example.modemate.domain.Profile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class CounselorService {

    private final CounselorRepository counselorRepository;


    @Transactional
    public Counselor createCounselor(CounselorRegisterRequestDTO requestDTO) {

        log.info("[Counselor Service] create counselor");

        Profile profile = requestDTO.getProfile();

        Counselor counselor = Counselor.builder()
                .name(requestDTO.getName())
                .comment(requestDTO.getComment())
                .profile(profile)
                .build();

        return counselorRepository.save(counselor);

    }

    public List<Counselor> findCounselors() {
        return counselorRepository.findAll();
    }

    @Transactional
    public void update(Long id, String name, String comment) {
        Counselor counselor = counselorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Counselor not found"));
        counselor.setName(name);
        counselor.setComment(comment);
    }
}

