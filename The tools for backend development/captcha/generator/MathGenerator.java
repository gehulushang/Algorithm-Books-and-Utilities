package captcha.generator;

import cn.hutool.core.util.CharUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;


/**
 * 数字验证码生成器
 *
 *
 */
public class MathGenerator implements CodeGenerator{

    private static final String operators = "+-";

    private int numberLength;

    public MathGenerator(){
        this(2);

    }

    public MathGenerator(int numberLength){
        this.numberLength = numberLength;
    }

    @Override
    public String generate(){
        final String code = StrUtil.builder()
                                   .append(StrUtil.padAfter(Integer.toString(RandomUtil.randomInt(getLimit())), this.numberLength, CharUtil.SPACE))
                                   .append(RandomUtil.randomChar(operators))
                                   .append(StrUtil.padAfter(Integer.toString(RandomUtil.randomInt(getLimit())), this.numberLength, CharUtil.SPACE))
                                   .toString();

        return code;

    }

    @Override
    public boolean verify(String code,String userInputCode){
        int result;
        try{
            result = Integer.parseInt(userInputCode);

        }catch (NumberFormatException e){
            return  false;
        }

        final int a = Integer.parseInt(StrUtil.sub(code, 0, this.numberLength).trim());
        final char operator = code.charAt(this.numberLength);
        final int b = Integer.parseInt(StrUtil.sub(code, this.numberLength + 1, this.numberLength + 1 + this.numberLength).trim());

        switch (operator){
            case '+':
                return (a+b) == result;
            case '-':
                return (a - b) == result;
            case '*':
                return (a * b) == result;
            default:
                return false;
          }

    }

    @Override
    public int getLength() {
        return this.numberLength * 2 +1;
    }

    private int getLimit() {
        return Integer.parseInt("1" + StrUtil.repeat('0', this.numberLength));
    }


}








