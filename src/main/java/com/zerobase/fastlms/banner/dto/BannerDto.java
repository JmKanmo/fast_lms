package com.zerobase.fastlms.banner.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class BannerDto {
    private long id;
    private String name;
    private String linkUrl;
    private String imgDirectory;
    private String alterText;
    private int openSelect;
    private int sortOrder;
    private boolean visibility;
    private LocalDateTime regDt;//등록일(추가날짜)
}
