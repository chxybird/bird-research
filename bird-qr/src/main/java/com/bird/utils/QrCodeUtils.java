package com.bird.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author lipu
 * @Date 2021/5/6 9:00
 * @Description 二维码工具类
 */
@Slf4j
public class QrCodeUtils {
    /**
     * 二维码参数容器
     */
    private static final Map<EncodeHintType, Object> MAP = new HashMap<>();
    /**
     * 宽度
     */
    private static final Integer QR_WIDTH = 200;
    /**
     * 长度
     */
    private static final Integer QR_HEIGHT = 200;
    /**
     * 默认图片格式PNG
     */
    private static final String IMG_TYPE = "png";
    /**
     * 默认文件下载名称
     */
    private static final String QR_NAME="二维码.png";
    /**
     * png格式后缀
     */
    private static final String PNG_SUFFIX=".png";

    /**
     * @Author lipu
     * @Date 2021/5/6 9:12
     * @Description 初始化参数
     */
    static {
        //设置二维码的纠错等级 级别越高,纠错能力越强,但是相对的编码量也就越大 LMQH四个级别
        MAP.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        //设置字符集编码 UTF-8
        MAP.put(EncodeHintType.CHARACTER_SET, StandardCharsets.UTF_8);
        //设置边距
        MAP.put(EncodeHintType.MARGIN, 2);
    }

    /**
     * @Author lipu
     * @Date 2021/5/6 9:00
     * @Description 生成二维码 转BASE64编码 可以直接前端解析
     */
    public static String initQrCode(String content) {
        if (content==null){
            log.info("请设置二维码内容");
            return null;
        }
        try {
            //生成二维码
            QRCodeWriter writer = new QRCodeWriter();
            BitMatrix bitMatrix = writer.encode(content, BarcodeFormat.QR_CODE, QR_WIDTH, QR_HEIGHT, MAP);
            //将二维码图片加载到内存中
            BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
            //将二维码图片写入到输出流中
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, IMG_TYPE, outputStream);
            //转换为BASE64编码
            return Base64.getEncoder().encodeToString(outputStream.toByteArray());
        } catch (Exception e) {
            log.info("二维码生成失败");
            return null;
        }
    }

    /**
     * @Author lipu
     * @Date 2021/5/6 11:02
     * @Description 生成二维码图片并下载
     */
    public static void download(String content, HttpServletResponse response){
        if (content==null){
            log.info("请设置二维码内容");
            return;
        }
        try {
            //生成二维码
            QRCodeWriter writer = new QRCodeWriter();
            BitMatrix bitMatrix = writer.encode(content, BarcodeFormat.QR_CODE, QR_WIDTH, QR_HEIGHT, MAP);
            response.setContentType("application/octet-stream");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-disposition", "attachment;filename="
                    + URLEncoder.encode(QR_NAME, "utf-8"));
            MatrixToImageWriter.writeToStream(bitMatrix,IMG_TYPE,response.getOutputStream());
        } catch (Exception e) {
            log.info("二维码生成失败");
        }
    }

    /**
     * @Author lipu
     * @Date 2021/5/6 11:32
     * @Description 生成二维码到文件
     */
    public static void initToFile(String content, String path, String name){
        if (content==null){
            log.info("请设置二维码内容");
            return;
        }
        if (path==null||name==null){
            log.info("请设置路径和文件名");
            return;
        }
        try {
            //生成二维码
            QRCodeWriter writer = new QRCodeWriter();
            BitMatrix bitMatrix = writer.encode(content, BarcodeFormat.QR_CODE, QR_WIDTH, QR_HEIGHT, MAP);
            //将二维码图片写入到文件中
            File file=new File(path,name+PNG_SUFFIX);
            MatrixToImageWriter.writeToPath(bitMatrix,IMG_TYPE,file.toPath());
        } catch (Exception e) {
            log.info("二维码生成失败");
        }
    }


}
