package com.zerobase.fastlms.admin.controller;

import com.zerobase.fastlms.banner.dto.BannerDto;
import com.zerobase.fastlms.banner.model.BannerInput;
import com.zerobase.fastlms.banner.model.BannerParam;
import com.zerobase.fastlms.banner.service.BannerService;
import com.zerobase.fastlms.course.controller.BaseController;
import com.zerobase.fastlms.util.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Controller
public class AdminBannerController extends BaseController {
    private final BannerService bannerService;

    @GetMapping("/admin/banner/update.do")
    public String updateBanner(
            Model model,
            @RequestParam(name = "id",required = false,defaultValue = "0")long id) {
        model.addAttribute("banner", bannerService.selectBannerById(id));
        return "/admin/banner/update";
    }

    @PostMapping("/admin/banner/update.do")
    public String updateBanner(MultipartFile multipartFile, BannerInput bannerInput) {
        if (bannerService.checkExist(bannerInput.getId())) {
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");

            if (multipartFile != null && !multipartFile.getOriginalFilename().isEmpty()) {
                try {
                    File newFile = new File(Utils.getNewSaveFile(multipartFile.getOriginalFilename(), Utils.IMAGE_LOCAL_DIRECTORY, uuid));
                    FileCopyUtils.copy(multipartFile.getInputStream(), new FileOutputStream(newFile));
                } catch (IOException e) {
                    log.info(e.getMessage());
                }
                bannerInput.setImgDirectory(Utils.getNewSaveFile(multipartFile.getOriginalFilename(), Utils.IMAGE_SAVE_DIRECTORY, uuid));
                bannerService.updateBanner(bannerInput);
            } else {
                bannerInput.setImgDirectory("");
                bannerService.updateBanner(bannerInput);
            }
        }
        return "redirect:/admin/banner/update.do";
    }

    @PostMapping("/admin/banner/delete.do")
    public String deleteBanner(BannerParam bannerParam) {
        bannerService.deleteBanner(bannerParam);
        return "redirect:/admin/banner/list.do";
    }

    @GetMapping("/admin/banner/list.do")
    public String bannerList(Model model, BannerParam bannerParam) {
        long totalCount = 0;
        List<BannerDto> bannerDtoList = bannerService.selectBanner(bannerParam);

        if (bannerDtoList != null && bannerDtoList.size() > 0) {
            totalCount = bannerDtoList.get(0).getTotalCount();
        }

        model.addAttribute("count", totalCount);
        model.addAttribute("bannerList", bannerDtoList);

        String queryString = bannerParam.getQueryString();
        String pagerHtml = getPaperHtml(totalCount, bannerParam.getPageSize(), bannerParam.getPageIndex(), queryString);
        model.addAttribute("pager", pagerHtml);
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
                log.info(e.getMessage());
            }
            bannerInput.setImgDirectory(Utils.getNewSaveFile(multipartFile.getOriginalFilename(), Utils.IMAGE_SAVE_DIRECTORY, uuid));
            bannerService.updateBanner(bannerInput);
        } else {
            bannerInput.setImgDirectory("");
            bannerService.updateBanner(bannerInput);
        }
        return "/admin/banner/add";
    }
}
