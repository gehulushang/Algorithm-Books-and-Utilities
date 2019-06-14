package singleton;

/**
 * 静态内部类的方式实现单例，可以保证多线程的对象唯一性，还有提升性能，不用同步锁机制
 */
public class InnerStaticMode {

    private static class SingleTonHolder {

        public static InnerStaticMode sInnerStaticMode = new InnerStaticMode();

    }

    public static InnerStaticMode getInstance(){
        return SingleTonHolder.sInnerStaticMode;
    }

}
