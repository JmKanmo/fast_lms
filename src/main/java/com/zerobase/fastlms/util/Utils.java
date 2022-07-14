package com.zerobase.fastlms.util;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Utils {
    public static final String IMAGE_SAVE_DIRECTORY = "/files";
    public static final String IMAGE_LOCAL_DIRECTORY = "/library/opt/files/";

    public static LocalDate getLocalDate(String value, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        try {
            return LocalDate.parse(value, formatter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getIPAddress(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");

        if (ip == null) {
            ip = request.getHeader("Proxy-Client-IP");
        }

        if (ip == null) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
    public static String getNewSaveFile(String originalFilename, String baseUrlPath, String uuid) {
        LocalDate now = LocalDate.now();
        String urlDir = String.format("%s/%d/%02d/%02d/", baseUrlPath, now.getYear(), now.getMonthValue(), now.getDayOfMonth());

        String[] dirs = {
                String.format("%s/%d/", baseUrlPath, now.getYear()),
                String.format("%s/%d/%02d/", baseUrlPath, now.getYear(), now.getMonthValue()),
                String.format("%s/%d/%02d/%02d/", baseUrlPath, now.getYear(), now.getMonthValue(), now.getDayOfMonth())};

        for (String dir : dirs) {
            File file = new File(dir);
            if (!file.isDirectory()) {
                file.mkdir();
            }
        }

        String fileExtension = "";
        if (originalFilename != null) {
            int dotPos = originalFilename.lastIndexOf(".");
            if (dotPos > -1) {
                fileExtension = originalFilename.substring(dotPos + 1);
            }
        }

        String newUrlFilename = String.format("%s%s", urlDir, uuid);

        if (fileExtension.length() > 0) {
            newUrlFilename += "." + fileExtension;
        }

        return newUrlFilename;
    }
}
