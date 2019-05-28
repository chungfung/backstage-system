package com.system.service.common.utils;

import com.system.service.common.domain.UserVO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import java.util.concurrent.ConcurrentHashMap;

public class ShiroUtils {
    private static ConcurrentHashMap<String, Subject> subjectMap = new ConcurrentHashMap<>();

    public static Subject getSubjct() {
        return SecurityUtils.getSubject();
    }

    public static UserVO getUser() {
        Object object = getSubjct().getPrincipal();
        return (UserVO) object;
    }
    public static String getUserId() {
        return getUser().getUserId();
    }

    public static String getUserName() {
        UserVO userVO = (UserVO) getSubjct().getPrincipal();
        if(userVO==null){
            throw new RuntimeException();
        }
        return userVO.getLoginName();
    }
    public static void logout() {
        getSubjct().logout();
    }
    public static void setSubject(String sessionId,Subject subject){
        subjectMap.put(sessionId,subject);
    }

    public static Subject getSubject(String sessionId) {
        return subjectMap.get(sessionId);
    }

    public static void removeSubject(String sessionId) {
        subjectMap.remove(sessionId);
    }

}
