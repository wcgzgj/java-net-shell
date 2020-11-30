package top.faroz.exception;

/**
 * @ClassName StackEmptyException
 * @Description TODO
 * @Author FARO_Z
 * @Date 2020/11/23 上午8:26
 * @Version 1.0
 **/
public class StackEmptyException extends Exception{
    public StackEmptyException() {
        super("栈为空");
    }
}
