package com.practice.controller;

import com.practice.utils.CreateQrCodeUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class QrCodeController {

    @PostMapping("/create")
    public String createQrCode(@RequestParam(value = "logFile",required = false)MultipartFile file,
                               @RequestParam(value = "text")String text,
                               @RequestParam(value = "flag")String flag){
        try {
            if (file == null) {
                return CreateQrCodeUtil.createImage(text, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
