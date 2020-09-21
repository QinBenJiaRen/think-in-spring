package org.haiyang.www.oom;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * JVM 方法区溢出测试
 * 方法区主要用来存放类型相关信息，如类名，访问修饰符，常量池，字段描述，方法描述等
 * 对于这部分测试，基本思路为运行时产生大量的类去填满方法区，直到溢出为止。
 * 当前很多主流框架，如Spring，Hibernate对类进行增强时，都会使用到CGLib这类字节码技术，当增强的类越多，就需要越大的方法区以保证动态
 * 生成的新类型可以载入内存。
 * VM args: -XX:PermSize=10M -XX:MaxPermSize=10M
 * */
public class JavaMethodAreaOOMTest {

    public static void main(String[] args) {
        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OOMObject.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                    return methodProxy.invokeSuper(o, objects);
                }
            });
            enhancer.create();
        }
    }

    static class OOMObject {

    }


}
