package singleton;

/**
 * 利用枚举的方式实现单例，Android不推荐
 */
public enum  EnumMode {

    INSTANCE;

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
