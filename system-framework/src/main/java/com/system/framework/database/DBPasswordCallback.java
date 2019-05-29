package com.system.framework.database;

import com.alibaba.druid.filter.config.ConfigTools;
import com.alibaba.druid.util.DruidPasswordCallback;

import java.util.Properties;

/**
 * @Description
 * @Author 丰涌
 * @Date 2018/12/21 17:44
 * @Version 1.0
 */
public class DBPasswordCallback extends DruidPasswordCallback {

    private static final long serialVersionUID = -4601105662788634420L;

    /**
     * password的属性
     */
    private static final String DB_PWD = "password";
    /**
     * 数据对应的公钥
     */
    public static final String PUBLIC_KEY_STRING = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCDuKVRrWUjhHtGRAoEGnWgYunGvT/JSO/tEdm4OKoG+Ffph1lBeKDw3ef/QEdcIoGnSKM1LqS7NbRrazTENETkhDHy8wZH4IUihrmqdWFV6zHNp6x5+mWfWfeiSszB5/6NAMcuLc0/lG2Ziw++QZHRCI5BzQXv2IhcgvBwGbtO6wIDAQAB";

    @Override
    public void setProperties(Properties properties) {
        super.setProperties(properties);
        String pwd = properties.getProperty(DB_PWD);
        if (pwd != null && !"".equals(pwd.trim())) {
            try {
                //这里的password是将jdbc.properties配置得到的密码进行解密之后的值
                //所以这里的代码是将密码进行解密
                String password = ConfigTools.decrypt(PUBLIC_KEY_STRING, pwd);
                setPassword(password.toCharArray());
            } catch (Exception e) {
                setPassword(pwd.toCharArray());
            }
        }
    }
}
