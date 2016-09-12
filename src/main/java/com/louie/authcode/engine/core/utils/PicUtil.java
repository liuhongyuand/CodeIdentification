package com.louie.authcode.engine.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static com.louie.authcode.engine.core.noise.AbstractNoiseProcess.imageToRGBArray;

/**
 * Created by liuhongyu.louie on 2016/8/26.
 */
public class PicUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(PicUtil.class);

    /**
     *
     * @param RGB
     * @param pos
     * @param length
     * @param isVertical height = vertical
     * @return
     */
    public static int getWhitePointCount(int[][] RGB, int pos, int length, boolean isVertical){
        int whiteCount = 0;
        for (int i = 0; i < length; i++) {
            if (isVertical){
                if (RGB[i][pos] == -1) {
                    whiteCount++;
                }
            }else {
                if (RGB[pos][i] == -1) {
                    whiteCount++;
                }
            }
        }
        return whiteCount;
    }

    public static int[][] setColor(int[][] RGB, int pos, int length, int RGBColor, boolean isVertical){
        for (int i = 0; i < length; i++) {
            if (isVertical){
                RGB[i][pos] = RGBColor;
            }else {
                RGB[pos][i] = RGBColor;
            }
        }
        return RGB;
    }

    public static BufferedImage initImage(BufferedImage bufferedImage){
        for (int width = 0; width < bufferedImage.getWidth(); width++) {
            for (int height = 0; height < bufferedImage.getHeight(); height++) {
                bufferedImage.setRGB(width, height, -1);
            }
        }
        return bufferedImage;
    }

    public static BufferedImage mergeImage(BufferedImage src, BufferedImage target, int startWidth){
        for (int width = 0; width < src.getWidth(); width++) {
            for (int height = 0; height < src.getHeight(); height++) {
                target.setRGB(width + startWidth, height, src.getRGB(width, height));
            }
        }
        return target;
    }

    public static int[][] getRGBFromImageFile(String image){
        int[][] srcRGB = new int[0][];
        try {
            File pic = new File(image);
            BufferedImage bufferedImage = ImageIO.read(pic);
            srcRGB = imageToRGBArray(bufferedImage);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return srcRGB;
    }

    public static BufferedImage drawBoundary(BufferedImage bufferedImage){
        for (int width = 0; width < bufferedImage.getWidth(); width++) {
            for (int height = 0; height < bufferedImage.getHeight(); height++) {
                if (width == 0 || height == 0 || width == bufferedImage.getWidth() - 1 || height == bufferedImage.getHeight() - 1){
                    bufferedImage.setRGB(width, height, 5555);
                }
            }
        }
        return bufferedImage;
    }

}
