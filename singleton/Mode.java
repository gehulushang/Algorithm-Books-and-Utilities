package singleton;


//在获取类实例的方法上加同步锁，并且给类实例对象加上volatile修饰符
public class Mode {

    private static volatile Mode sMode;

    private Mode() {
        System.out.println("create " + getClass().getSimpleName());
    }

    public static Mode getInstance(){
        synchronized (Mode.class) {
            if (sMode == null) {
                sMode = new Mode();
            }
        }
        return sMode;
    }

    public static void main(String[] args) {

        Mode.getInstance();
    }

}
