package singleton;

public class HungryMode {
    private static HungryMode sHungryMode = new HungryMode();

    private HungryMode(){
        System.out.println("create"+getClass().getSimpleName());
    }

    private static void fun(){
        System.out.println("call fun in HungryMode");
    }

    public static HungryMode getInstance(){
        return sHungryMode;
    }

    public static void main(String[] args) {
        HungryMode.fun();
    }
}
