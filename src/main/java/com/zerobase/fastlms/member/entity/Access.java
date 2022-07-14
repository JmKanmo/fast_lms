package com.zerobase.fastlms.member.entity;

import com.zerobase.fastlms.member.model.AccessData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class Access {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;

    private LocalDateTime loginTime;

    private String accessIp;

    private String userAgent;


    public static Access of(AccessData accessData) {
        return Access.builder()
                .userId(accessData.getUserId())
                .loginTime(accessData.getLoginTime())
                .accessIp(accessData.getAccessIp())
                .userAgent(accessData.getUserAgent())
                .build();
    }
}

