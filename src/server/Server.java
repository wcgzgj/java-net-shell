package server;

import top.faroz.util.PropsUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

/**
 * @ClassName Server
 * @Description TODO
 * @Author FARO_Z
 * @Date 2020/11/30 下午8:07
 * @Version 1.0
 **/
public class Server {
    public static void main(String[] args) throws IOException {
        Properties props = PropsUtil.getProps();
        //配置文件中的端口号
        int port = Integer.parseInt(props.getProperty("port"));
        while (true) {
            ServerSocket ss = new ServerSocket(port);
            Socket s = ss.accept();
            try {
                InputStream ips = s.getInputStream();

                BufferedReader br = new BufferedReader(new InputStreamReader(ips));
                //获取客户端传过来的指令
                String ins = br.readLine();
                System.out.println(ins);

                Parser.parseCommand(ins,s);


            }  catch (Exception e) {
                System.out.println(e.getMessage());
                // TextAreaUtil.customError(e.getMessage());< ---这个是无效的
                //因为在运行的时候，开启的是两个互不相干的线程
                //所以，这里调用的根本不是正在运行的线程的方法！！！
            } finally {
                //必须要关闭
                //不然在下次循环的时候，会出现错误
                s.close();
                ss.close();
            }
        }
    }
}
