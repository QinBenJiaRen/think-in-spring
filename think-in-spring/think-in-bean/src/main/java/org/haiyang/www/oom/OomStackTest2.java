package org.haiyang.www.oom;

/**
 * 多线程情况下虚拟机栈和本地方法栈溢出
 * VM args: -Xss2M
 * 慎用，容易导致系统蓝屏
 * */
public class OomStackTest2 {

    private void dontStop() {
        while (true) {

        }
    }

    public void stackLeakByThread() {
        while (true) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    dontStop();
                }
            });
            thread.start();
        }
    }

    public static void main(String[] args) {
        OomStackTest2 test = new OomStackTest2();
        test.stackLeakByThread();
    }


}
