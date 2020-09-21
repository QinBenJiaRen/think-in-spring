package org.haiyang.www.oom;

import java.util.HashSet;
import java.util.Set;

/**
 * 方法区和运行时常量池溢出测试
 * VM args:
 * JDK 7及7之前： -XX:PermSize=6M -XX:MaxPermSize=6M
 * JDK 8: -Xmx6M
 * */
public class RuntimeConstantsPoolOOMTest {

    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        short i = 0;
        while (true) {
            set.add(String.valueOf(i++).intern());
        }
    }


}
