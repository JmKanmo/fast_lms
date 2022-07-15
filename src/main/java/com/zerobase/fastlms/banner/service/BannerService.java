package com.zerobase.fastlms.banner.service;

import com.zerobase.fastlms.banner.dto.BannerDto;
import com.zerobase.fastlms.banner.model.BannerInput;
import com.zerobase.fastlms.banner.model.BannerParam;

import java.util.List;

public interface BannerService {
    List<BannerDto> selectBanner(BannerParam bannerParam);

    List<BannerDto> selectAllBanner();

    boolean updateBanner(BannerInput bannerInput);

    int deleteBanner(BannerParam bannerParam);
    BannerDto selectBannerById(long id);

    boolean checkExist(long id);
}
