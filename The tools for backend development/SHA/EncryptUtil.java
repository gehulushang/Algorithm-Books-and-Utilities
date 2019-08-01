package util;


import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 通用加密类
 */
public class EncryptUtil {

    /**
     * 利用Java原生的方法实现SHA256加密
     *
     * @param str   待加密报文
     * @return
     */
    /**
     *
     * SHA256算法
     *
     */
    public String getSHA256(String str){
        MessageDigest messageDigest;
        String encodeStr = "";
        try{
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes(StandardCharsets.UTF_8));
            encodeStr = byte2Hex(messageDigest.digest());
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return encodeStr;

    }

    /**
     * 将bytes转化为16进制
     * @param bytes
     * @return
     */
    private static String byte2Hex(byte[] bytes){
        StringBuilder sb = new StringBuilder();
        String temp = null;
        for(int i = 0; i < bytes.length; i ++){
            temp = Integer.toHexString(bytes[i]&0xFF);
            if(temp.length() == 1){
                sb.append("0");
            }
            sb.append(temp);
        }
        return sb.toString();

    }
}
