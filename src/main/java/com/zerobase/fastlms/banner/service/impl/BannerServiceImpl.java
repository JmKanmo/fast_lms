package com.zerobase.fastlms.banner.service.impl;

import com.zerobase.fastlms.banner.dto.BannerDto;
import com.zerobase.fastlms.banner.entity.Banner;
import com.zerobase.fastlms.banner.mapper.BannerMapper;
import com.zerobase.fastlms.banner.model.BannerInput;
import com.zerobase.fastlms.banner.model.BannerParam;
import com.zerobase.fastlms.banner.repository.BannerRepository;
import com.zerobase.fastlms.banner.service.BannerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BannerServiceImpl implements BannerService {
    private final BannerRepository bannerRepository;
    private final BannerMapper bannerMapper;
    @Override
    public List<BannerDto> selectBanner(BannerParam bannerParam) {
        long totalCount = bannerMapper.selectListCount();
        List<BannerDto> bannerDtoList = bannerMapper.selectList(bannerParam);

        if (!CollectionUtils.isEmpty(bannerDtoList)) {
            int i = 0;
            for (BannerDto bannerDto : bannerDtoList) {
                bannerDto.setTotalCount(totalCount);
                bannerDto.setSeq(totalCount - bannerParam.getPageStart() - i);
            }
        }
        return bannerDtoList;
    }

    @Override
    public List<BannerDto> selectAllBanner() {
        return bannerMapper.selectAllList();
    }

    @Override
    public boolean updateBanner(BannerInput bannerInput) {
        try {
            bannerRepository.save(Banner.of(bannerInput));
            return true;
        } catch (Exception e) {
            log.info(e.getMessage());
            return false;
        }
    }

    @Transactional
    @Override
    public int deleteBanner(BannerParam bannerParam) {
        int i = 0;
        for (String id : bannerParam.getIdList().split(",")) {
            bannerRepository.deleteById(Long.parseLong(id));
            i++;
        }
        return i;
    }

    @Override
    public BannerDto selectBannerById(long id) {
        Optional<Banner> bannerOptional = bannerRepository.findById(id);

        if(bannerOptional.isPresent() == false) {
            return BannerDto.from();
        }else{
            return BannerDto.fromEntity(bannerOptional.get());
        }
    }

    @Override
    public boolean checkExist(long id) {
        return bannerRepository.findById(id).isPresent();
    }
}
