package top.faroz.test;

import top.faroz.util.RandomDataUtil;

/**
 * @ClassName RandomDataUtilTest
 * @Description TODO
 * @Author FARO_Z
 * @Date 2020/11/30 下午10:52
 * @Version 1.0
 **/
public class RandomDataUtilTest {
    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            System.out.println(RandomDataUtil.getRandomName(i+1));
        }
    }
}
