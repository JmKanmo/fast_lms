package com.zerobase.fastlms.banner.dto;

import com.zerobase.fastlms.banner.entity.Banner;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    //추가컬럼
    long totalCount;
    long seq;

    public String getRegDt() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        return regDt != null ? regDt.format(formatter) : "";
    }

    public static BannerDto fromEntity(Banner banner) {
        return BannerDto.builder()
                .id(banner.getId())
                .name(banner.getName())
                .linkUrl(banner.getLinkUrl())
                .imgDirectory(banner.getImgDirectory())
                .alterText(banner.getAlterText())
                .openSelect(banner.getOpenSelect())
                .sortOrder(banner.getSortOrder())
                .visibility(banner.isVisibility())
                .regDt(banner.getRegDt())
                .build();
    }

    public static BannerDto from() {
        return BannerDto.builder().build();
    }
}
