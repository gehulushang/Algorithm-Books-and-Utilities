package captcha;

import captcha.generator.CodeGenerator;
import cn.hutool.core.img.GraphicsUtil;
import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.util.ImageUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.ThreadLocalRandom;

public class LineCaptcha extends AbstractCaptcha {
    private static final long serialVersionUID = 8691294460763091089L;


    public LineCaptcha(int width, int height) {
        super(width, height, 5, 150);
    }

    public LineCaptcha(int width, int height, int codeCount, int interfereCount) {
        super(width, height, codeCount, interfereCount);
    }


    @Override
    protected Image createImage(String code) {
        final BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_BGR);
        final  Graphics2D g = GraphicsUtil.createGraphics(image, ObjectUtil.defaultIfNull(this.background,Color.WHITE));


        // 干扰线
        drawInterfere(g);

        // 字符串
        drawString(g, code);

        return image;
    }

    private void drawString(Graphics2D g,String code){
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // 创建字体
        g.setFont(this.font);

        int minY = GraphicsUtil.getMinY(g);
        if(minY<0){
            minY = this.height - 1;

        }

        final int len = this.generator.getLength();

        int charWidth = width / len;
        for(int i = 0;i< len;i++){
            g.setColor(ImgUtil.randomColor());
            g.drawString(String.valueOf(code.charAt(i)), i * charWidth, RandomUtil.randomInt(minY, this.height));
        }

    }


    private void drawInterfere(Graphics2D g) {
        final ThreadLocalRandom random = RandomUtil.getRandom();
        // 干扰线
        for (int i = 0; i < this.interfereCount; i++) {
            int xs = random.nextInt(width);
            int ys = random.nextInt(height);
            int xe = xs + random.nextInt(width / 8);
            int ye = ys + random.nextInt(height / 8);
            g.setColor(ImgUtil.randomColor(random));
            g.drawLine(xs, ys, xe, ye);
        }
    }



}




















