package com.zerobase.fastlms.admin.controller;

import com.zerobase.fastlms.banner.model.BannerInput;
import com.zerobase.fastlms.banner.service.BannerService;
import com.zerobase.fastlms.util.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Controller
public class AdminBannerController {
    private final BannerService bannerService;

    @GetMapping("/admin/banner/list.do")
    public String bannerList() {
        return "/admin/banner/list";
    }

    @GetMapping("/admin/banner/add.do")
    public String bannerAdd() {
        return "/admin/banner/add";
    }

    @PostMapping("/admin/banner/add.do")
    public String bannerAdd(MultipartFile multipartFile, BannerInput bannerInput) {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");

        if (multipartFile != null && !multipartFile.getOriginalFilename().isEmpty()) {
            try {
                File newFile = new File(Utils.getNewSaveFile(multipartFile.getOriginalFilename(), Utils.IMAGE_LOCAL_DIRECTORY, uuid));
                FileCopyUtils.copy(multipartFile.getInputStream(), new FileOutputStream(newFile));
            } catch (IOException e) {
                log.info("############################ - 1");
                log.info(e.getMessage());
            }
            bannerInput.setImgDirectory(Utils.getNewSaveFile(multipartFile.getOriginalFilename(), Utils.IMAGE_SAVE_DIRECTORY, uuid));
            bannerService.insertBanner(bannerInput);
        } else {
            bannerInput.setImgDirectory("");
            bannerService.insertBanner(bannerInput);
        }
        return "/admin/banner/add";
    }
}
