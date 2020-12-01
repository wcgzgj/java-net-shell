package top.faroz.util;
import top.faroz.gui.panel.MainPanel;

import javax.swing.*;
import java.util.List;

/**
 * @ClassName TextAreaUtil
 * @Description TODO
 * @Author FARO_Z
 * @Date 2020/11/16 上午11:44
 * @Version 1.0
 **/
public class TextAreaUtil {
    /**
     * 获取JTextArea中的最后一行的文字
     * 因为修改了输入方式
     * 所以这个函数应该没有什么用了
     * @param str
     * @return
     */
    public static String getLastLine(String str) {
        String[] split = str.split("\n");
        for (int i = 0; i < split.length; i++) {
            split[i]=split[i].replaceAll(">","");
            split[i]=split[i].trim();
        }
        return split[split.length-1];
    }


    /**
     * 将 List按照既定格式，输出到taUp上
     * @param list
     */
    public static void appendToTaUp(List<String> list) {
        JTextArea taUp = MainPanel.getInstance().getTaUp();
        for (String s : list) {
            taUp.append(s+"\n");
        }
    }

    public static void appendText(String str) {
        JTextArea taUp = MainPanel.getInstance().getTaUp();
        taUp.append("\n");
        taUp.append(str);
    }

    /**
     * 向输出框中显示，函数输入格式错误
     */
    public static void functionFormatError() {
        JTextArea taUp = MainPanel.getInstance().getTaUp();
        taUp.append("\n     错误 , 函数输入错误\n" +
                "     请输入 \"format\"，阅读标准 函数 输入格式手册");
    }

    public static void instructionFormatError() {
        JTextArea taUp = MainPanel.getInstance().getTaUp();
        taUp.append("\n     错误 , 请输入 \"help\"\n" +
                "     阅读标准输入格式手册");
    }

    /**
     * 在输出框中，显示自定义错误
     */
    public static void customError(String s) {
        JTextArea taUp = MainPanel.getInstance().getTaUp();
        // taUp.setForeground(Color.RED);

        taUp.append("\n     错误 , " + s +"\n"+
                "     请输入 \"format\"，阅读标准 函数 输入格式手册");
        // taUp.setForeground(Color.WHITE);
    }


}
