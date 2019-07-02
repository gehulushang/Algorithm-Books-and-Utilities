package captcha.generator;


import cn.hutool.captcha.generator.CodeGenerator;
import cn.hutool.core.util.RandomUtil;

import java.util.Random;

/**
 * 随机字符验证码生成器
 * 可以通过传入的基础集合和长度随机生成验证码字符
 *
 *
 *
 */
public abstract class AbstractGenerator implements CodeGenerator {

          /** 基础字符集合，用于随机获取字符串的字符集合*/
          protected String baseStr;


          /*验证码长度 */
          protected int length;

            /**
             * 构造，使用字母和数字作为基础
             * @param count             //生成验证码长度
             */
            public AbstractGenerator(int count){

                     this(RandomUtil.BASE_CHAR_NUMBER,count);

            }

           /**
             * 构造，
             * @param baseStr
             * @param length
             */
            public AbstractGenerator(String baseStr, int length){
                      this.baseStr = baseStr;
                      this.length = length;
            }

            @Override
            public int getLength(){

                      return this.length;
            }

}
















