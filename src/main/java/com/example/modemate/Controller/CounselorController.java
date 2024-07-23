package com.example.modemate.Controller;



import com.example.modemate.DTO.CounselorRegisterRequestDTO;
import com.example.modemate.Repository.CounselorRepository;
import com.example.modemate.Repository.UserRepository;
import com.example.modemate.Service.CounselorService;
import com.example.modemate.Service.ProgramService;
import com.example.modemate.Service.UserService;
import com.example.modemate.domain.Counselor;
import com.example.modemate.domain.Profile;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/counselor")
@RequiredArgsConstructor
@Slf4j
public class CounselorController {

    private final ProgramService programService;
    private final CounselorService counselorService;
    private final CounselorRepository counselorRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    /**
     * counselor 등록
     * @param requestDTO
     * @return
     * @throws IOException
     */

    @Operation(summary = "상담사 등록 기능", description = "상담사 관련 API")
    @PostMapping("/register")
    public String register(@RequestBody @Valid CounselorRegisterRequestDTO requestDTO)
            throws IOException {

        log.info("[Counselor Controller] register");


        Counselor counselor = counselorService.createCounselor(requestDTO);

        return "success to register Counselor" + counselor.getName();
    }

    @PutMapping("/{counselorId}")
    public UpdateCounselorResponse updateMember(@PathVariable("counselorId") Long counselorId, @RequestBody @Valid UpdateMemberRequest request) {
        counselorService.update(counselorId, request.getName(), request.getComment());
        Counselor findCounselor = counselorRepository.findById(counselorId)
                .orElseThrow(() -> new RuntimeException("Counselor not found"));
        return new UpdateCounselorResponse(findCounselor.getId(), findCounselor.getName(), findCounselor.getComment());

    }


    /**
     * counselor 조회
     * @return
     */
    @GetMapping("/")
    public Result findCounselor() {

        List<Counselor> findCounselor = counselorService.findCounselors();
        List<CounselorDto> collect = findCounselor.stream()
                .map(m -> new CounselorDto(m.getName()))
                .collect(Collectors.toList());

        return new Result(collect);

    }

    @Data
    @AllArgsConstructor
    static class CounselorDto {
        private String name;
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }

    @Data
    @AllArgsConstructor
    static class UpdateCounselorResponse {
        private Long id;
        private String name;
        private String comment;
    }

    @Data
    static class UpdateMemberRequest {
        private String name;
        private String comment;
        private Profile profile;
    }



}
