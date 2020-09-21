package org.haiyang.www.oom;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * VM args: -Xms20M -XX:MaxDirectMemorySize=10M
 * 由直接内存导致的内存溢出，一个明显的特征是Heap Dump文件中看不到有什么明显的异常情况
 * 如果发现内存溢出之后的Dump文件很小，而程序中又直接或间接使用了DirectMemory(典型的间接使用就是NIO),那可以考虑重点检查一下直接内存方面的原因。
 * */
public class DirectMemoryOOMTest {

    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) throws Exception {
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);
        while (true) {
            unsafe.allocateMemory(_1MB);
        }
    }
}
