package com.zerobase.fastlms.member.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class AccessData {
    private final LocalDateTime loginTime;
    private final String accessIp;
    private final String userId;
    private final String userAgent;
}
