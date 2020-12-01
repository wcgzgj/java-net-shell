package top.faroz.util;

import java.io.FileInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @ClassName PropsUtil
 * @Description TODO
 * @Author FARO_Z
 * @Date 2020/11/30 下午7:01
 * @Version 1.0
 **/
public class PropsUtil {

    /**
     * 获取配置文件对象
     * @return
     */
    public static Properties getProps() {
        String path = "src/data.properties";
        Properties props = new Properties();
        try {
            props.load(new FileInputStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props;
    }
}
