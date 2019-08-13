package ProxyStudy.dynamicProxy;

/**
 * 被代理类
 */
public class RealSubject implements Subject {

    public void doSomething()
    {
        System.out.println( "call doSomething()" );
    }
}
