package top.faroz.gui.frame;

import top.faroz.gui.panel.MainPanel;

import javax.swing.*;
import java.awt.*;

/**
 * @ClassName MainFrame
 * @Description TODO
 * @Author FARO_Z
 * @Date 2020/11/16 上午9:38
 * @Version 1.0
 **/
public class MainFrame extends JFrame {
    private static MainFrame instance=new MainFrame();

    public static MainFrame getInstance() {
        return instance;
    }

    public MainFrame() throws HeadlessException {
        JTextArea taDown = MainPanel.getInstance().getTaDown();
        this.setTitle("MyLisp 解释器");
        this.setLayout(new BorderLayout());
        this.setBounds(new Rectangle(200,200,500,500));


        this.setContentPane(MainPanel.getInstance());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //设置默认焦点
        // taDown.dispatchEvent(new FocusEvent(taDown,FocusEvent.FOCUS_GAINED,true));
        // taDown.requestFocusInWindow();


        //设置为不可变大小
        this.setResizable(false);
        this.setVisible(true);
    }
}
