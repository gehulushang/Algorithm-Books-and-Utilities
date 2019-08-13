package ProxyStudy.staticProxy;

public class UserImpl implements User {
    @Override
    public void work(String workName){
        System.out.println("......." + workName + ".......");
    }
}
