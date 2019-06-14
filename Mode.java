package singleton;


//在获取类实例的方法上加同步锁，并且给类实例对象加上volatile修饰符
public class Mode {

    private static volatile Mode Mode;

    private Mode() {
        System.out.println("create " + getClass().getSimpleName());
    }

    public static Mode getInstance(){
        synchronized (Mode.class) {
            if (Mode == null) {
                Mode = new Mode();
            }
        }
        return Mode;
    }

    public static void main(String[] args) {

        Mode.getInstance();
    }

}