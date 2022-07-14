package com.zerobase.fastlms.banner.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BannerInput {
    private String name;
    private String linkUrl;
    private int openSelect;
    private boolean visibility;

    private String filename;
    private String urlFilename;
}
