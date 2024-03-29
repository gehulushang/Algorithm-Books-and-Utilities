package captcha;

import captcha.generator.CodeGenerator;
import captcha.generator.RandomGenerator;
import cn.hutool.core.codec.Base64;
import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.io.IoUtil;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public abstract class AbstractCaptcha implements ICaptcha{

    private static final long serialVersionUID = 3180820918087507254L;

    protected int width = 100;

    protected int height = 37;

    protected int interfereCount = 15;

    protected Font font;

    protected String code;

    protected byte[] imageBytes;

    protected RandomGenerator generator;

    protected Color background;

    /**
     * 构造，使用随机验证码生成器生成验证码
     *
     * @param width 图片宽
     * @param height 图片高
     * @param codeCount 字符个数
     * @param interfereCount 验证码干扰元素个数
     */
    public AbstractCaptcha(int width, int height, int codeCount, int interfereCount) {
        this(width, height, new RandomGenerator(codeCount), interfereCount);
    }


    /**
     * 构造
     *
     * @param width 图片宽
     * @param height 图片高
     * @param generator 验证码生成器
     * @param interfereCount 验证码干扰元素个数
     */
    public AbstractCaptcha(int width, int height, RandomGenerator generator, int interfereCount) {
        this.width = width;
        this.height = height;
        this.generator = generator;
        this.interfereCount = interfereCount;
        // 字体高度设为验证码高度-2，留边距
        this.font = new Font("Courier", Font.PLAIN, (int)(this.height * 0.75));
        createCode();
    }


    @Override
    public void createCode() {
        generateCode();

        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImgUtil.writePng(createImage(this.code), out);
        this.imageBytes = out.toByteArray();
    }

    /**
     * 生成验证码字符串
     *
     * @since 3.3.0
     */
    protected void generateCode() {
        this.code = generator.generate();
    }

    /**
     * 根据生成的code创建验证码图片
     *
     * @param code 验证码
     */
    protected abstract Image createImage(String code);

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public boolean verify(String userInputCode) {
        return this.generator.verify(this.code, userInputCode);
    }

    /**
     * 验证码写出到文件
     *
     * @param path 文件路径
     * @throws IORuntimeException IO异常
     */
    public void write(String path) throws IORuntimeException {
        this.write(FileUtil.touch(path));
    }

    /**
     * 验证码写出到文件
     *
     * @param file 文件
     * @throws IORuntimeException IO异常
     */
    public void write(File file) throws IORuntimeException {
        try (OutputStream out = FileUtil.getOutputStream(file)) {
            this.write(out);
        } catch (IOException e) {
            throw new IORuntimeException(e);
        }
    }

    @Override
    public void write(OutputStream out) {
        IoUtil.write(out, false, this.imageBytes);
    }

    /**
     * 获取验证码图
     *
     * @return 验证码图
     */
    public BufferedImage getImage() {
        if (null == this.imageBytes) {
            createCode();
        }
        return ImgUtil.read(new ByteArrayInputStream(this.imageBytes));
    }

    /**
     * 获得图片的Base64形式
     *
     * @return 图片的Base64
     * @since 3.3.0
     */
    public String getImageBase64() {
        return Base64.encode(this.imageBytes);
    }

    /**
     * 自定义字体
     *
     * @param font 字体
     */
    public void setFont(Font font) {
        this.font = font;
    }

    /**
     * 获取验证码生成器
     *
     * @return 验证码生成器
     */
    public RandomGenerator getGenerator() {
        return generator;
    }

    /**
     * 设置验证码生成器
     *
     * @param generator 验证码生成器
     */
    public void setGenerator(RandomGenerator generator) {
        this.generator = generator;
        // 重新生成验证码
        this.createCode();
    }

    /**
     * 设置背景色
     *
     * @param background 背景色
     * @since 4.1.22
     */
    public void setBackground(Color background) {
        this.background = background;
    }



}
