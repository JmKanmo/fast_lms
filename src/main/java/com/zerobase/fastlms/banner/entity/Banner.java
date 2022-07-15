package com.zerobase.fastlms.banner.entity;

import com.zerobase.fastlms.banner.model.BannerInput;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Banner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String linkUrl;
    private String imgDirectory;
    private String alterText;

    private int openSelect;
    private int sortOrder;

    private boolean visibility;

    private LocalDateTime regDt;//등록일(추가날짜)
    private LocalDateTime udtDt;//수정일(수정날짜)

    public static Banner of(BannerInput bannerInput) {
        return Banner.builder()
                .id(bannerInput.getId())
                .name(bannerInput.getName())
                .linkUrl(bannerInput.getLinkUrl())
                .imgDirectory(bannerInput.getImgDirectory())
                .alterText(bannerInput.getAlterText())
                .openSelect(bannerInput.getOpenSelect())
                .sortOrder(bannerInput.getSortOrder())
                .visibility(bannerInput.isVisibility())
                .regDt(LocalDateTime.now())
                .build();
    }
}
