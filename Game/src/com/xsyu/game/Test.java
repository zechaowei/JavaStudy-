package com.xsyu.game;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * ClassName: Test
 * Package: com.xsyu.game
 * Description: 用java绘制一个班=拜年二维码
 *
 * @Author: Mr.weizechao
 * @Create: 2023/1/22 - 13:52
 * @Version: v1.0
 */
public class Test {
    public static void main(String[] args) throws WriterException, IOException {
        //画者 MultiFormatWriter
        MultiFormatWriter writer = new MultiFormatWriter();

        //内容，类型，宽度，高度，其他信息
        //String
        String content = "过年好！";
        //BarcodeFormat 二维码类型
        BarcodeFormat type = BarcodeFormat.QR_CODE;

        int width = 600;
        int height = 600;

        //Map
        HashMap map = new HashMap();
        map.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        //纠错等级
        map.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        //中间填充
        map.put(EncodeHintType.MARGIN, 2);

        //BitMatrix(虚拟二维码对象)
        BitMatrix bitMatrix = writer.encode(content, type, width, height, map);

        //I/O流
        int black = Color.BLACK.getRGB();
        int white = Color.WHITE.getRGB();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);

        //开始画画
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? black : white);
            }
        }

        //图片.jpg 文件
        File file = new File("E://test//zzz.jpg");
        ImageIO.write(image, ".jpg", file);
        System.out.println("图片打印成功！");
    }
}
