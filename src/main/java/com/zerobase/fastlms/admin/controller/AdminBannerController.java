package com.zerobase.fastlms.admin.controller;

import com.zerobase.fastlms.banner.model.BannerInput;
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

@Slf4j
@RequiredArgsConstructor
@Controller
public class AdminBannerController {
    @GetMapping("/admin/banner/add.do")
    public String bannerAdd() {
        return "/admin/banner/add";
    }

    @PostMapping("/admin/banner/add.do")
    public String bannerAdd(MultipartFile multipartFile, BannerInput bannerInput) {

        String saveFilename = "";
        String urlFilename = "";

        if (multipartFile != null) {
            String originalFilename = multipartFile.getOriginalFilename();

            String[] arrFilename = Utils.getNewSaveFile(Utils.USER_DIRECTORY, Utils.BASE_DIRECTORY, originalFilename);

            saveFilename = arrFilename[0];
            urlFilename = arrFilename[1];

            try {
                File newFile = new File(saveFilename);
                FileCopyUtils.copy(multipartFile.getInputStream(), new FileOutputStream(newFile));
            } catch (IOException e) {
                log.info("############################ - 1");
                log.info(e.getMessage());
            }
        }

        bannerInput.setFilename(saveFilename);
        bannerInput.setUrlFilename(urlFilename);
        return "/admin/banner/add";
    }
}
