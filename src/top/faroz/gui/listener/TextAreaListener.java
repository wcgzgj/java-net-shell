package top.faroz.gui.listener;

import top.faroz.exception.StackEmptyException;
import top.faroz.gui.panel.MainPanel;
import top.faroz.model.DataCache;
import top.faroz.service.Analyse;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @ClassName TextAreaListener
 * @Description 输入框监听器
 *  当用户按下回车以后，提取输入框的信息进行处理
 *  然后清空输入框
 * @Author FARO_Z
 * @Date 2020/11/16 上午10:54
 * @Version 1.0
 **/
public class TextAreaListener implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        MainPanel mainPanel = MainPanel.getInstance();
        JTextArea taDown = mainPanel.getTaDown();
        JTextArea taUp = mainPanel.getTaUp();
        int code = e.getKeyCode();

        switch (code) {
            //回车键
            case 10:
                String text = taDown
                        .getText() //获取文本
                        .replaceAll("\n","") //去除换行符
                        .trim(); //去除开头、结尾的空格


                //显示用户输入的指令
                taUp.append("\n > "+text);
                //将输入存放到数据缓冲区
                DataCache.addInput(text);

                //处理指令或者函数
                //并对用户的输入做出相应


                Analyse.analyseInstruction(text);


                //每次输入完后，taUp的光标置于最底下
                //通过这种方式，实现JTextArea每次显示最下面一行的内容
                taUp.setCaretPosition(taUp.getDocument().getLength());

                //清空输入框
                taDown.setText("");

                break;

            case 38:// 上方向键
                taDown.setText("");
                try {
                    String tmp = DataCache.getPre();
                    taDown.setText(tmp);
                } catch (StackEmptyException stackEmptyException) {
                    stackEmptyException.printStackTrace();
                }
                break;
            case 40://下方向键
                taDown.setText("");
                try {
                    String tmp = DataCache.getNext();
                    taDown.setText(tmp);
                } catch (StackEmptyException stackEmptyException) {
                    stackEmptyException.printStackTrace();
                }
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
