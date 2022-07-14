package com.zerobase.fastlms.banner.service.impl;

import com.zerobase.fastlms.banner.dto.BannerDto;
import com.zerobase.fastlms.banner.entity.Banner;
import com.zerobase.fastlms.banner.model.BannerInput;
import com.zerobase.fastlms.banner.model.BannerParam;
import com.zerobase.fastlms.banner.repository.BannerRepository;
import com.zerobase.fastlms.banner.service.BannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BannerServiceImpl implements BannerService {
    private final BannerRepository bannerRepository;

    @Override
    public List<BannerDto> selectAllDto(BannerParam bannerParam) {
        return null;
    }

    @Override
    public boolean insertBanner(BannerInput bannerInput) {
        try {
            bannerRepository.save(Banner.of(bannerInput));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
