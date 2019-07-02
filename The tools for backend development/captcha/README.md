//这是一个用于生产验证码的工具包，主要来自Hutool，我只是抄了一下




    // ShearCaptcha中构造函数原本是通过控制干扰线宽度改变干扰效果，但是，干扰线宽度变宽会导致验证码字符被盖住，于是我改了构造函数，
    //  改为通过控制干扰线条数改变干扰效果
   
   
    // The constructors of ShearCaptcha was intended to vary the interference effect by controlling the width of the interference line. 
    // However, widening the width of the interference line would cause the captcha characters to be covered, 
    // so I changed the constructors to change the interference effect by controlling the number of interference lines.
  
    
    //用弯曲线实现干扰有待于之后实现
    
    // Interference with curved lines will be implemented later.




//hutool中AbstractCaptcha两个构造函数有问题，会出现转型错误，现在已经改过来，经过测试没有问题


//There are transition error in the constructors of AbstractCaptcha. Now, they have been corrected and tested without problems


    


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
  
    
    public AbstractCaptcha(int width, int height, int codeCount, int interfereCount) {
        this(width, height, new RandomGenerator(codeCount), interfereCount);
    }
 
