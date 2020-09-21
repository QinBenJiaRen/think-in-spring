package org.haiyang.www.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * 堆溢出测试
 * VM Args: -Xms20M -Xmx20M -Xmn10M -XX:+HeapDumpOnOutOfMemoryError
 * */

public class OomHeapTest {

    static class OOMObject{

    }

    public static void main(String[] args) {

        List<OOMObject> list = new ArrayList<>();
        while (true) {
            list.add(new OOMObject());
        }
    }
}
