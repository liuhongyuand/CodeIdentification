package com.louie.authcode.rest.utils;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by liuhongyu.louie on 2016/9/11.
 */
public class HTMLUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(HTMLUtil.class);

    public static String getHTML(InputStream stream){
        int temp;
        byte[] bytes = new byte[1024];
        StringBuilder builder = new StringBuilder("");
        try {
            while ((temp = stream.read(bytes, 0, bytes.length)) > 0){
                builder.append(new String(bytes, 0, temp));
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return builder.toString();
    }

    public static byte[] InputStreamToBytes(InputStream inputStream){
        byte[] bytes = new byte[0];
        try {
            if (inputStream != null) {
                bytes = IOUtils.toByteArray(inputStream);
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return bytes;
    }

    public static byte[] BufferedImageToBytes(BufferedImage bufferedImage){
        byte[] imageInByte = new byte[0];
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()){
            ImageIO.write(bufferedImage, "jpg", byteArrayOutputStream );
            byteArrayOutputStream.flush();
            imageInByte = byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return imageInByte;
    }

}
