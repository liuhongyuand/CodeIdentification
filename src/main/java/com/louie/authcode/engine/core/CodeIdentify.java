package com.louie.authcode.engine.core;

import com.louie.authcode.engine.AuthCodeProcess;
import com.louie.authcode.engine.EngineConfiguration;
import com.louie.authcode.engine.brain.PointMap;
import com.louie.authcode.engine.core.cut.v2.DivideProcess;
import com.louie.authcode.engine.core.utils.PicUtil;
import com.louie.authcode.exception.ParameterException;
import com.louie.authcode.file.model.AuthcodeFile;
import com.louie.authcode.utils.FileDeleteUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Set;

import static com.louie.authcode.engine.config.EngineParameters.PROJECT_ROOT;

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

    public void trainingPicIdentifyForGUI(final String FILE, boolean importData){
        AuthCodeProcess process = new CodeImportImpl();
        Object[] results = process.process(FILE);
        List<?> letters = (List<?>) results[0];
        Set<?> buffers = (Set<?>) results[1];
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

        }
    }

    public void trainingPicIdentifyForREST(AuthcodeFile file){
        try {
            AuthCodeProcess process = new CodeProcessImpl();
            String[] letterStrings = getTrainingData(file.getAuthcode());
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
            FileDeleteUtil.fileQueue.offer(file);
        }
    }

    private void inputData(List<Point> letterList, String letter){
        if (!letter.equals("")) {
            PointMap.put(letter, letterList);
        }
    }

    public String getCode(AuthcodeFile file){
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
            FileDeleteUtil.fileQueue.offer(file);
        }
    }

    private String[] getTrainingData(String authcode){
        char[] letterChars = authcode.toCharArray();
        String[] letters = new String[letterChars.length];
        for (int i = 0; i < letterChars.length; i++) {
            letters[i] = (String.valueOf(letterChars[i]).matches(EngineConfiguration.getService().getIgnoredString())) ? "" : String.valueOf(letterChars[i]);
        }
        return letters;
    }

    public static void main(String[] args){
        strings = new String[]{"p", "a", "p", "e", "r", "", "", "", "", "", "", "", "", ""};
//        final String Learning = PROJECT_ROOT + "/learning/among.jpg";
        final String FILE = PROJECT_ROOT + "/training/water.jpg";
//        final String resources = PROJECT_ROOT + "/resources/captcha_s.jpg";
//        new CodeIdentify().outputRGB(resources);
//        new CodeIdentify().codeView(resources);
        new CodeIdentify().trainingPicIdentifyForGUI(FILE, false);
//        new CodeIdentify().getCode(resources);
    }

}
