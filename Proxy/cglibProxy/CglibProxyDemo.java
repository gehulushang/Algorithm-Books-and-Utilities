package ProxyStudy.cglibProxy;

import net.sf.cglib.proxy.Enhancer;

public class CglibProxyDemo {

    public static void main(String[] args) {


        /**
         * 创建字节码增强器
         */
        Enhancer enhancer = new Enhancer();

        /**
         * 被代理类设置为字节码增强父类，cglib使用的是继承方式去创建代理类
         */
        enhancer.setSuperclass(UserImpl.class);

        /**
         * 设置字节码增强器回调方法。对于代理类上所有方法的调用，都会调用CallBack，
         * 而Callback则需要实现intercept()方法进行拦截
         */
        enhancer.setCallback(new CglibDynamicProxy());

        /*
         * 创建代理实例

         */
        UserImpl userImpl = (UserImpl)enhancer.create();

        userImpl.work("敲代码");

    }
}
