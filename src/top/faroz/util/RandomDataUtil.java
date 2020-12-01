package top.faroz.util;

import java.util.Random;

/**
 * @ClassName RandomDataUtil
 * @Description TODO
 * @Author FARO_Z
 * @Date 2020/11/30 下午10:45
 * @Version 1.0
 **/
public class RandomDataUtil {
    static String alp ="abcdefghijklmnopqrstuvwxyz";
    static String num="1234567890";
    //生成随机名字
    public static String getRandomName(int len) {
        String pool = "";
        int nums = (len / 2)+1;
        int alps = (len / 2)+1;
        for (int i = 0; i < nums; i++) {
            pool+=String.valueOf(alp.charAt(new Random().nextInt(alp.length())));
        }
        for (int i = 0; i < alps; i++) {
            pool+=String.valueOf(num.charAt(new Random().nextInt(num.length())));
        }

        return pool;
    }
}
