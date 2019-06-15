
//类加载顺序测试

package Classloading;

class Son extends ClassLoadingOrderTest.Father
{
    static{
        System. out.println("子类静态代码块初始化" );
    }
    {
        System. out.println("子类代码块初始化" );
    }
    private static  int i=1;
    private String s="子类私有成员变量" ;
    public void show()
    {
        System. out.println("子类show()方法：i=" +i);
    }
    public Son()
    {
        System. out.println("子类构造函数初始化完成" );
        show();
    }
}
public class ClassLoadingOrderTest {
    public static void main(String[] args)
    {
        new Son();
    }

    static class Father
    {
        static{
            System. out.println("父类静态代码块初始化" );
        }
        {
            System. out.println("父类代码块初始化" );
        }
        private static String s=print();
        public static String print()
        {
            System. out.println("父类静态方法" );
            return "父类静态成员变量的初始化" ;
        }
        public Father()
        {
            System. out.println("父类无参构造函数初始化完成" );
            show();
        }
        public void show()
        {
            System. out.println("父类show()方法" );
        }
    }
}

