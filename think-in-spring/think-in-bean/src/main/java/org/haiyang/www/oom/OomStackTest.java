package org.haiyang.www.oom;

/**
 * 单线程虚拟机栈和本地方法栈溢出测试
 * VM args: -Xss128k
 * */
public class OomStackTest {

    private Integer stackLength = 1;

    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) {
        OomStackTest oomStackTest = new OomStackTest();
        try {
            oomStackTest.stackLeak();
        }catch (Throwable e) {
            System.out.println("stack length:" + oomStackTest.stackLength);
            throw e;
        }
    }
}
