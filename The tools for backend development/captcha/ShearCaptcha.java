package captcha;

import captcha.generator.CodeGenerator;
import cn.hutool.core.img.GraphicsUtil;
import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ShearCaptcha extends AbstractCaptcha {
    private static final long serialVersionUID = -7096627300356535494L;

    /**
     * 构造
     *
     * @param width 图片宽
     * @param height 图片高
     */
    public ShearCaptcha(int width, int height) {
        this(width, height, 5);
    }

    /**
     * 构造
     *
     * @param width 图片宽
     * @param height 图片高
     * @param codeCount 字符个数
     */
    public ShearCaptcha(int width, int height, int codeCount) {
        this(width, height, codeCount, 2);
    }

    /**
     * 构造
     *
     * @param width 图片宽
     * @param height 图片高
     * @param codeCount 字符个数
     * @param shearCount 干扰线条数
     */
    public ShearCaptcha(int width, int height, int codeCount, int shearCount) {
        super(width, height, codeCount, shearCount);
    }




    @Override
    public Image createImage(String code) {
        final BufferedImage image = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_RGB);
        final Graphics2D g = GraphicsUtil.createGraphics(image, ObjectUtil.defaultIfNull(this.background, Color.WHITE));

        // 画字符串
        drawString(g, code);

        shear(g, width, height, Color.white);
        for(int i = 0;i<this.interfereCount;i++){
            drawInterfere(g, 0, RandomUtil.randomInt(height) + 1, width, RandomUtil.randomInt(height) + 1, ImgUtil.randomColor());
        }


        return image;
    }

    // ----------------------------------------------------------------------------------------------------- Private method start
    /**
     * 绘制字符串
     *
     * @param g {@link Graphics2D}画笔
     * @param random 随机对象
     * @param code 验证码
     */
    private void drawString(Graphics2D g, String code) {
        // 抗锯齿
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // 创建字体
        g.setFont(this.font);

        // 文字
        int minY = GraphicsUtil.getMinY(g);
        if (minY < 0) {
            minY = this.height - 4;
        }

        final int len = this.generator.getLength();
        int charWidth = width / len;
        for (int i = 0; i < len; i++) {
            // 产生随机的颜色值，让输出的每个字符的颜色值都将不同。
            g.setColor(ImgUtil.randomColor());
            g.drawString(String.valueOf(code.charAt(i)), i * charWidth, minY);
        }
    }

    /**
     * 扭曲
     *
     * @param g {@link Graphics}
     * @param w1 w1
     * @param h1 h1
     * @param color 颜色
     */
    private void shear(Graphics g, int w1, int h1, Color color) {
        shearX(g, w1, h1, color);
        shearY(g, w1, h1, color);
    }

    /**
     * Y坐标扭曲
     *
     * @param g {@link Graphics}
     * @param w1 w1
     * @param h1 h1
     * @param color 颜色
     */
    private void shearX(Graphics g, int w1, int h1, Color color) {

        int period = RandomUtil.randomInt(2);

        boolean borderGap = true;
        int frames = 1;
        int phase = RandomUtil.randomInt(2);

        for (int i = 0; i < h1; i++) {
            double d = (double) (period >> 1) * Math.sin((double) i / (double) period + (6.2831853071795862D * (double) phase) / (double) frames);
            g.copyArea(0, i, w1, 1, (int) d, 0);
            if (borderGap) {
                g.setColor(color);
                g.drawLine((int) d, i, 0, i);
                g.drawLine((int) d + w1, i, w1, i);
            }
        }

    }

    /**
     * X坐标扭曲
     *
     * @param g {@link Graphics}
     * @param w1 w1
     * @param h1 h1
     * @param color 颜色
     */
    private void shearY(Graphics g, int w1, int h1, Color color) {

        int period = RandomUtil.randomInt(40) + 10; // 50;

        boolean borderGap = true;
        int frames = 20;
        int phase = 7;
        for (int i = 0; i < w1; i++) {
            double d = (double) (period >> 1) * Math.sin((double) i / (double) period + (6.2831853071795862D * (double) phase) / (double) frames);
            g.copyArea(i, 0, 1, h1, 0, (int) d);
            if (borderGap) {
                g.setColor(color);
                g.drawLine(i, (int) d, i, 0);
                g.drawLine(i, (int) d + h1, i, h1);
            }

        }

    }

    /**
     * 干扰线
     *
     * @param g {@link Graphics}
     * @param x1x1
     * @param y1 y1
     * @param x2 x2
     * @param y2 y2

     * @param c 颜色
     */
    private void drawInterfere(Graphics g, int x1, int y1, int x2, int y2, Color c) {

        // The thick line is in fact a filled polygon
        g.setColor(c);
        int dX = x2 - x1;
        int dY = y2 - y1;
        // line length
        double lineLength = Math.sqrt(dX * dX + dY * dY);

        double scale = (double) (4) / (2 * lineLength);

        // The x and y increments from an endpoint needed to create a
        // rectangle...
        double ddx = -scale * (double) dY;
        double ddy = scale * (double) dX;
        ddx += (ddx > 0) ? 0.5 : -0.5;
        ddy += (ddy > 0) ? 0.5 : -0.5;
        int dx = (int) ddx;
        int dy = (int) ddy;

        // Now we can compute the corner points...
        int xPoints[] = new int[4];
        int yPoints[] = new int[4];

        xPoints[0] = x1 + dx;
        yPoints[0] = y1 + dy;
        xPoints[1] = x1 - dx;
        yPoints[1] = y1 - dy;
        xPoints[2] = x2 - dx;
        yPoints[2] = y2 - dy;
        xPoints[3] = x2 + dx;
        yPoints[3] = y2 + dy;

        g.fillPolygon(xPoints, yPoints, 4);
    }

}
