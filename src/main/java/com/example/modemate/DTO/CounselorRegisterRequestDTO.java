package com.example.modemate.DTO;


import com.example.modemate.domain.Profile;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CounselorRegisterRequestDTO {

    private String name;

    private String comment;

    private Profile profile;


    @Builder
    public CounselorRegisterRequestDTO(String name, String comment, Profile profile) {
        this.name = name;
        this.comment = comment;
        this.profile = profile;
    }
}
