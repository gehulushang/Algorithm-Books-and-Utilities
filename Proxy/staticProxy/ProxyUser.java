package ProxyStudy.staticProxy;

import javax.jws.soap.SOAPBinding;

public class ProxyUser implements User {

    private User user;

    public ProxyUser(User user){
        this.user = user;

    }

    @Override
    public void work(String workName) {
        System.out.println("工作前先放松放松=============");
        /*
         * 调用实际业务逻辑处理方法
         */
        user.work(workName);
        /*
         * 调用实际业务逻辑处理后也可以定制一些功能
         */
        System.out.println("工作后还是要放松放松=============");
    }
}
