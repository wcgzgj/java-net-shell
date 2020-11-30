package top.faroz.exception;

/**
 * @ClassName DivideZeroException
 * @Description TODO
 * @Author FARO_Z
 * @Date 2020/11/21 下午2:15
 * @Version 1.0
 **/
public class DivideZeroException extends Exception{
    public DivideZeroException() {
        super("除数不可为0");
    }
}
