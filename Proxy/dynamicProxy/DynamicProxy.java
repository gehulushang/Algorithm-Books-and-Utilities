package ProxyStudy.dynamicProxy;

import java.lang.reflect.Proxy;

public class DynamicProxy {
    public static void main( String args[] )
    {


        RealSubject real = new RealSubject();

        /**
         * 一共有三个参数：
         * 1、第一个参数是被代理类的类加载器，通过此类加载器将代理类加载入jvm中；
         *2、第二个参数则是被代理类所实现的所有接口，
         * 3、第三个参数则是真正的扩展，使用动态代理的主要目的就是能够对原方法进行扩展，尤其是对于大部分方法都具有的重复方法(例如记录日志)，
         *  可以理解为面向切面编程中的增强.
         */
        Subject proxySubject = (Subject) Proxy.newProxyInstance(Subject.class.getClassLoader(),
                new Class[]{Subject.class},
                new ProxyHandler(real));

        proxySubject.doSomething();

        //write proxySubject class binary data to file

    }

}
