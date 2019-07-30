package com.zjf.security_jwt.comm;

import sun.reflect.misc.FieldUtil;

public class Const {

    public static final long EXPIRATION_TIME = 432_000_000;
    public static final String SECRET = "ZJFSecret";                       // JWT密码
    public static final String TOKEN_PREFIX = "Bearer";               // Token前缀
    public static final String HEADER_STRING = "Authorization"; // 存放Token的Header Key

}
