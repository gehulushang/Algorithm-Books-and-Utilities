package captcha.generator;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;


/**
 * 随机字符验证码生成器
 *
 *
 */

public class RandomGenerator extends AbstractGenerator{


    public RandomGenerator(int count){
        super(count);
    }

    public RandomGenerator(String baseStr, int length){
        super(baseStr,length);
    }

    @Override
    public String generate() {

        return RandomUtil.randomString(this.baseStr,this.length);
    }

    @Override
    public boolean verify(String code, String userInputCode) {
        if(StrUtil.isNotBlank(userInputCode)){
            return StrUtil.equalsIgnoreCase(code, userInputCode);
        }
        return false;
    }
}
