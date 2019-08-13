package ProxyStudy.staticProxy;

public class StaticProxyDemo {
    public static void main(String[] args) {
        User proxyUser = new ProxyUser(new UserImpl());
        proxyUser.work("开发");
    }
}
