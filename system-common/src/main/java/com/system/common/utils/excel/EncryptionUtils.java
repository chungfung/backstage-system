package com.system.common.utils.excel;

import org.apache.shiro.crypto.hash.Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.SimpleByteSource;

/**
 * 加密操作工具类
 * <p> 对字符进行MD5加密
 *
 * @author cw
 */
public class EncryptionUtils {
    /**
     * 指定散列算法为md5
     */
    private final static String algorithmName = "MD5";
    /**
     * 散列迭代次数
     */
    private final static int hashIterations = 2;

        /**
     * 用户名生成盐值对密码进行加密
     * @param password
     * @return
     */
    public static String encrypt(String loginName,String password) {
        Hash hash=new SimpleHash(algorithmName, new SimpleByteSource(password),new SimpleByteSource(loginName),hashIterations);
        return   hash.toHex();
    }
}
