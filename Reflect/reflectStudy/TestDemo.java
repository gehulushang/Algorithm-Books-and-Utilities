package reflectStudy;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class TestDemo {

    public static void main(String[] args) {
        try {
            Class clazz = Class.forName("reflectStudy.TestClass");

            String str = clazz.getSimpleName();
            ClassLoader classLoader = clazz.getClassLoader();

            String name = clazz.getName();
            Constructor constructor = clazz.getConstructor();

            Method methods[] = clazz.getMethods();

            System.out.println(str+"\n"+classLoader.toString()+"\n"+name+"\n"+constructor.toString());

            for(Method method : methods){
                System.out.println(method.toString());
            }
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (NoSuchMethodException e){
            e.printStackTrace();

        }


    }
}
