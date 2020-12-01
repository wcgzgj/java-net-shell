package top.faroz.util;

import com.sun.org.apache.xpath.internal.WhitespaceStrippingElementMatcher;
import org.apache.commons.lang.StringUtils;

import java.io.*;
import java.net.Socket;
import java.net.SocketImplFactory;
import java.util.Properties;

/**
 * @ClassName NetUtil
 * @Description 网络相关工具
 * @Author FARO_Z
 * @Date 2020/11/30 下午8:06
 * @Version 1.0
 **/
public class NetUtil {

    //获得客户端socket
    public static Socket getSocket() {
        Properties props = PropsUtil.getProps();
        Socket socket=null;
        try {
             socket = new Socket(props.getProperty("ip"), Integer.parseInt(props.getProperty("port")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return socket;
    }

    /**
     * 向Server发送信息
     */
    public static void sendInfo(String str) {
        Properties props = PropsUtil.getProps();
        try (Socket socket = new Socket(props.getProperty("ip"), Integer.parseInt(props.getProperty("port")));
             OutputStream ops = socket.getOutputStream()) {
            ops.write(str.getBytes());
            socket.shutdownOutput();


            if (str.startsWith("dir")) {
                //接受服务端的信息
                InputStream ips = socket.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(ips));
                String tmp=null;
                String pool="";
                while ((tmp=br.readLine())!=null) {
                    pool+=(tmp+"\n");
                }
                // System.out.println(pool);
                if (pool.length()>0) {
                    TextAreaUtil.appendText(pool);
                }
            }

            if (str.startsWith("get")) {
                //获取文件名
                String fileName = FileUtil.getFileName(str);
                File file = new File("src/download");
                FileOutputStream fos = new FileOutputStream(file + "/" + fileName);
                InputStream ips = socket.getInputStream();
                byte[] b = new byte[1024];
                int len;
                while ((len=ips.read(b))!=-1) {
                    fos.write(b, 0, len);
                }
                fos.close();

                //提示用户，传输完毕
                Thread.sleep(500);
                TextAreaUtil.appendText("   文件传输完毕,请在 src/download目录下查看");
            }

            if (str.startsWith("put")) {
                String[] split = str.split("\\s+");
                if (split.length!=3) return;//put指令一定要是有三个数据
                String localPath = split[1];


                /**
                 * 因为socket已经写出过一次了
                 * 且服务端也已经传回来过信息 （完成了三次握手）
                 *
                 * 所以，要传输文件，只能再新建一个socket
                 */
                //本地文件写出
                Socket put_socket = getSocket();
                OutputStream put_ops = put_socket.getOutputStream();
                File file = new File(localPath);
                FileInputStream fis = new FileInputStream(file);
                int len;
                byte[] b = new byte[1024];
                while ((len=fis.read(b))!=-1) {
                    put_ops.write(b,0,len);
                }
                put_socket.shutdownOutput();

                //接受服务端信息
                InputStream ips = put_socket.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(ips));
                String info = br.readLine();
                TextAreaUtil.appendText(info);
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
