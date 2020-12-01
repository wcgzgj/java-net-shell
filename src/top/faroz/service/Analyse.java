package top.faroz.service;

import top.faroz.gui.frame.MainFrame;
import top.faroz.gui.panel.MainPanel;
import top.faroz.model.DataCache;
import top.faroz.util.FileUtil;
import top.faroz.util.NetUtil;
import top.faroz.util.TextAreaUtil;

import javax.swing.*;
import java.net.Socket;

/**
 * @ClassName InstructionAnalyse
 * @Description TODO
 * @Author FARO_Z
 * @Date 2020/11/16 下午4:44
 * @Version 1.0
 **/
public class Analyse {
    /**
     * 解析指令
     * 如果不是指令，调用解析函数的方法
     * @param ins
     */
    public static void analyseInstruction(String ins) {
        MainPanel mainPanel = MainPanel.getInstance();
        JTextArea taUp = mainPanel.taUp;
        if (isBasicInstruction(ins)) { //处理基础指令集
            switch (ins) {
                case "cls":
                    taUp.setText("");
                    break;
                case "help":
                    TextAreaUtil.appendToTaUp(FileUtil.readFile("help"));
                    break;
                case "format":
                    TextAreaUtil.appendToTaUp(FileUtil.readFile("format"));
                    break;
                case "nobug":
                    TextAreaUtil.appendToTaUp(FileUtil.readFile("nobug"));
                    break;
                case "goddness":
                    TextAreaUtil.appendToTaUp(FileUtil.readFile("goddness"));
                    break;
                case "pig":
                    TextAreaUtil.appendToTaUp(FileUtil.readFile("pig"));
                    break;
                case "":
                    break;
                case "next":
                    DataCache.clear();
                    break;
                case "quit"://结束指令，结束进程
                    System.exit(0);
                    break;
                default:
                    TextAreaUtil.instructionFormatError();
                    break;
            }
        } else {//向服务器发送指令
            NetUtil.sendInfo(ins);
        }
    }

    public static boolean isBasicInstruction(String s) {
        //指令集中不会出现空格和括号
        if (s.indexOf(" ")!=-1 || s.indexOf("(")!=-1 || s.indexOf(")")!=-1) return false;
        return true;
    }

}
