package captcha.generator;

public interface CodeGenerator {

    /**
     * 生成验证码
     */
    public String generate();

    public int getLength();

    public boolean verify(String code, String userInputCode);


}
