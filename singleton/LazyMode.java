package singleton;

//类实例对象做了懒加载，也就是所谓的延时加载，所以提升了一些性能
public class LazyMode {
    private static LazyMode sLazyMode;

    private LazyMode(){
        System.out.println("create"+getClass().getSimpleName());
    }


    public static LazyMode getInstance(){
        if(sLazyMode==null){
            sLazyMode = new LazyMode();
        }
        return sLazyMode;
    }

    public static void main(String[] args) {
        LazyMode.getInstance();
    }
}
