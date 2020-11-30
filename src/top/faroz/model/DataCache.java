package top.faroz.model;

import top.faroz.exception.StackEmptyException;

import java.util.HashMap;
import java.util.Stack;

/**
 * @ClassName NumCache
 * @Description 数据缓冲区，存储变量及其值
 * 其中维护一个 HashMap
 * @Author FARO_Z
 * @Date 2020/11/16 下午3:06
 * @Version 1.0
 **/
public class DataCache {
    private static HashMap<String,Float> map=new HashMap<>();
    //下面这两个数据区的数据会一直存在，直到程序关闭
    private static Stack<String> preStack=new Stack<>();//存放初始输入的字符
    private static Stack<String> nextStack=new Stack<>();//存放按上方向键后，出栈的字符

    /**
     * 更新键值对
     * 有一样的，更新
     * 没有一样的，添加
     * @param k
     * @param v
     */
    public static void update(String k,Float v) {
        if (map.containsKey(k)) map.replace(k,v);
        else map.put(k,v);
    }

    /**
     * 清空维护的HashMap，方便下一次使用
     */
    public static void clear() {
        map.clear();
        while (!nextStack.isEmpty()) {
            //每次在开始下一个程序之前，
            // 都要将之前在nextStack中的内容再次放回preStack中
            preStack.push(nextStack.pop());
        }
    }

    public static boolean contains(String k) {
        if (map.containsKey(k)) return true;
        return false;
    }

    public static float getValue(String k) {
        return map.get(k);
    }

    public static void addInput(String s) {
        while (!nextStack.isEmpty()) {
            //每次在新增输入之前
            // 都要将之前在nextStack中的内容再次放回preStack中
            preStack.push(nextStack.pop());
        }
        if (s.length()>0) {
            preStack.push(s);
        }
    }

    public static String getPre() throws StackEmptyException {
        if (preStack.size()<=0) throw new StackEmptyException();
        String tmp = preStack.pop();
        nextStack.push(tmp);
        return tmp;
    }

    public static String getNext() throws StackEmptyException {
        if (nextStack.size()<=0) throw new StackEmptyException();
        String tmp = nextStack.pop();
        preStack.push(tmp);
        return tmp;
    }

}
