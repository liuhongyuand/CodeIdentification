package com.louie.authcode.engine.core;

import com.louie.authcode.engine.AuthCodeProcess;
import com.louie.authcode.engine.EngineConfiguration;
import com.louie.authcode.engine.brain.PointMap;
import com.louie.authcode.engine.config.EngineParameters;
import com.louie.authcode.engine.core.cut.v2.DivideProcess;
import com.louie.authcode.engine.core.utils.PicUtil;
import com.louie.authcode.exception.ParameterException;
import com.louie.authcode.file.model.AuthcodeFile;
import com.louie.authcode.utils.FileDeleteUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import static com.louie.authcode.engine.config.EngineParameters.PROJECT_ROOT;
import static com.louie.authcode.engine.config.EngineParameters.eliminateValue;

/**
 * Created by liuhongyu.louie on 2016/8/21.
 */
public class CodeIdentify {

    private static final Logger LOGGER = LoggerFactory.getLogger(CodeIdentify.class);
    private static String[] strings;

    private void outputRGB(String FILE){
        PicUtil.getRGBFromImageFile(FILE);
    }

    private void codeView(final String FILE){
        CodeImportImpl.isDivide = false;
        AuthCodeProcess process = new CodeImportImpl();
        JFrame frame = new JFrame();
        frame.setLayout(null);
        frame.setSize(600, 100);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Object[] results = process.process(FILE);
        int[][] imageRGB = DivideProcess.forTestDivide((int[][]) results[0]);
        BufferedImage image = new BufferedImage(imageRGB.length, imageRGB[0].length, BufferedImage.TYPE_INT_RGB);
        image = AbstractPicProcess.setBufferedImage(image, imageRGB);
        JLabel label = new JLabel(new ImageIcon(image));
        JLabel labe2 = new JLabel(new ImageIcon(FILE));
        label.setSize(image.getWidth(), image.getHeight());
        labe2.setBounds(image.getWidth(), 0, 250, 70);
        frame.add(label);
        frame.add(labe2);
        frame.setVisible(true);
    }

    public BufferedImage trainingPicIdentifyForGUI(AuthcodeFile file, boolean importData){
        AuthCodeProcess process = new CodeImportImpl();
        Object[] results = process.process(file.getFile().getAbsolutePath());
        List<?> letters = (List<?>) results[0];
        Set<?> buffers = (Set<?>) results[1];
        BufferedImage bufferedImage = new BufferedImage(buffers.size() * 50 * 2, 50, BufferedImage.TYPE_INT_RGB);
        if (importData) {
            int letterNum = 0;
            for (Object letterObj : letters) {
                List<Point> letterList = (List<Point>) letterObj;
                if (!strings[letterNum].equals("")) {
                    PointMap.put(strings[letterNum], letterList);
                }
                letterNum++;
            }
        } else {
            bufferedImage = PicUtil.initImage(bufferedImage);
            int width = 50;
            for (Object object : buffers){
                BufferedImage afterDrawBoundary = PicUtil.drawBoundary((BufferedImage) object);
                bufferedImage = PicUtil.mergeImage(afterDrawBoundary, bufferedImage, width);
                width += 50 + afterDrawBoundary.getWidth();
            }
        }
        return bufferedImage;
    }

    public void trainingPicIdentifyForREST(AuthcodeFile file, boolean needDelete){
        try {
            AuthCodeProcess process = new CodeProcessImpl();
            String[] letterStrings = mapAuthcodeToArray(file.getAuthcode());
            if (file.getFile() == null){
                return;
            }
            Object[] results = process.process(file.getFile().getAbsolutePath());
            if (results == null) {
                return;
            }
            List<?> letters = (List<?>) results[0];
            int letterNum = 0;
            if (letters.size() == letterStrings.length) {
                for (Object letterObj : letters) {
                    List<Point> letterList = (List<Point>) letterObj;
                    inputData(letterList, letterStrings[letterNum]);
                    letterNum++;
                }
            }
        } catch (ParameterException ignored){
        } finally {
            if (needDelete) {
                FileDeleteUtil.fileQueue.offer(file);
            }
        }
    }

    private void inputData(List<Point> letterList, String letter){
        if (!letter.equals("")) {
            PointMap.put(letter, letterList);
        }
    }

    public String getCode(AuthcodeFile file, boolean needDelete){
        try {
            AuthCodeProcess process = new CodeProcessImpl();
            if (file.getFile() == null){
                return "";
            }
            Object[] results = process.process(file.getFile().getAbsolutePath());
            if (results == null) {
                return "";
            }
            List<?> letters = (List<?>) results[0];
            String authCode = PointMap.getAuthCode(letters);
            LOGGER.info("Authcode: " + authCode);
            return authCode;
        } finally {
            if (needDelete) {
                FileDeleteUtil.fileQueue.offer(file);
            }
        }
    }

    private String[] mapAuthcodeToArray(String authcode){
        char[] letterChars = authcode.toCharArray();
        String[] letters = new String[letterChars.length];
        for (int i = 0; i < letterChars.length; i++) {
            letters[i] = (String.valueOf(letterChars[i]).matches(EngineConfiguration.getService().getIgnoredString())) ? "" : String.valueOf(letterChars[i]);
        }
        return letters;
    }

    public static void main(String[] args){
        AuthcodeFile file = new AuthcodeFile();
        strings = new String[]{"p", "a", "p", "e", "r", "", "", "", "", "", "", "", "", ""};
        String path = PROJECT_ROOT + "/training/complete.jpg";
        file.setFile(new File(path));
        new CodeIdentify().trainingPicIdentifyForGUI(file, false);
//        new CodeIdentify().getCode(file);
    }

}
