package top.faroz.test;

import top.faroz.util.PropsUtil;

import java.util.Properties;

/**
 * @ClassName PropUtilTest
 * @Description TODO
 * @Author FARO_Z
 * @Date 2020/11/30 下午7:04
 * @Version 1.0
 **/
public class PropUtilTest {
    public static void main(String[] args) {
        Properties props = PropsUtil.getProps();
        String port = props.getProperty("port");
        System.out.println(Integer.valueOf(port));
    }
}
