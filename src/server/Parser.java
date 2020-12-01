package server;

import top.faroz.gui.frame.MainFrame;
import top.faroz.util.FileUtil;

import java.awt.*;
import java.io.*;
import java.net.Socket;

/**
 * @ClassName Parser
 * @Description 用来判断网络指令
 * @Author FARO_Z
 * @Date 2020/11/30 下午8:43
 * @Version 1.0
 **/
public class Parser {
    public static void parseCommand(String command, Socket socket) throws Exception {
        //忽略空格个数，获取字符串数组
        String[] split = command.split("\\s+");
        if (split.length<=0) return;

        //获取开头的指令
        String ins = split[0];
        switch (ins) {
            case "dir":
                if (split.length!=2)
                    return; //<---这里就是抛出异常，也没有用
                //Server端无法和GUI进项交互
                //两个都是不同的进程
                String dir_path = split[1];
                //想办法，将这个数据传送过去
                String[] fileList = FileUtil.getFileList(dir_path);
                String pool = "";
                for (String s : fileList) {
                    pool+=(s+"\n");
                }
                // System.out.println(pool); <-- 成功
                OutputStream ops = socket.getOutputStream();
                ops.write(pool.getBytes());
                socket.shutdownOutput();

                ops.close();
                socket.close();
                break;

            case "get":
                if (split.length!=2)
                    return;
                String get_path = split[1];//将get_path路径上文件的比特流，传输到socket
                File file = new File(get_path);
                FileInputStream fis = new FileInputStream(file);
                OutputStream get_ops = socket.getOutputStream();
                int get_len;
                byte[] get_b = new byte[1024];
                while ((get_len=fis.read(get_b))!=-1) {
                    get_ops.write(get_b,0,get_len);
                }
                socket.shutdownOutput();
                break;

            case "put":
                if (split.length!=3)
                    return;
                    // throw new Exception("输入错误");<---server和GUI无法交互，弹出错误也没用
                // String localPath = split[1];
                String remotePath = split[2];
                File remoteFile = new File(remotePath);
                FileOutputStream fos = new FileOutputStream(remoteFile);
                InputStream ips = socket.getInputStream();

                int put_len;
                byte[] put_b = new byte[1024];
                while ((put_len=ips.read(put_b))!=-1) {
                    fos.write(put_b, 0, put_len);
                }
                socket.shutdownOutput();


                //服务端返回信息
                OutputStream put_ops = socket.getOutputStream();
                put_ops.write("文件上传完毕！".getBytes());
                break;
        }
    }
}
