package com.practice.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.apache.commons.codec.binary.Base64;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;

public class CreateQrCodeUtil {
    public static String createImage(String text, InputStream imgPath) throws Exception{
        HashMap map = new HashMap();
        map.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);//纠错程度，识别二维码的成功率
        map.put(EncodeHintType.CHARACTER_SET, "utf-8");//字符编码
        map.put(EncodeHintType.MARGIN,1);//边距

        BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE,300,300,map);
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x,y,bitMatrix.get(x,y) ? 0xFF000000 : 0xFFFFFFFF);
            }

        }
        if (imgPath == null) {
            return imageToString(image);
        }
        return null;
    }

    public static String imageToString(BufferedImage image) throws Exception{
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(image, "png", os);
        return Base64.encodeBase64String(os.toByteArray());
    }
}
