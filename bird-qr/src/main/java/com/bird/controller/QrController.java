package com.bird.controller;

import com.bird.utils.QrCodeUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author lipu
 * @Date 2021/5/6 10:46
 * @Description
 */
@RestController
@RequestMapping("/qr")
public class QrController {

    @GetMapping("/init")
    public String init(){
        return QrCodeUtils.initQrCode("小鸟程序员");
    }

    @PostMapping("/download")
    public void download(HttpServletResponse response){
        QrCodeUtils.download("小鸟程序员",response);
    }

    @PostMapping("/file")
    public void initToFile(){
        QrCodeUtils.initToFile("小鸟程序员","F:\\","qr");
    }
}
