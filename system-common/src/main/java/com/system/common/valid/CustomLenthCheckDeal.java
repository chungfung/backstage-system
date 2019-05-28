package com.system.common.valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description 自定义长度验证
 * @Author 丰涌
 * @Date 2018/11/22 15:20
 * @Version 1.0
 */
public class CustomLenthCheckDeal implements ConstraintValidator<CustomLenthCheck,String> {

    private int min;
    private int max;
    private static Pattern pattern = Pattern.compile("^[\u4E00-\u9Fa5]{0,}$");

    @Override
    public void initialize(CustomLenthCheck customLenthCheck) {
        this.min = customLenthCheck.min();
        this.max = customLenthCheck.max();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null){
            return true;
        }else {
            int chineseNums = chineseNums(s);
            int len = s.length();
            int total = (len-chineseNums)+chineseNums*3;
            if(len < min) {
                return false;
            }
            if(total > max) {
                return false;
            }
            return true;
        }
    }

    private static int chineseNums(String text){
        int amount = 0;
        for(int i = 0;i<text.length();i++){
            Matcher m = pattern.matcher(text.charAt(i)+"");
            if(m.matches()){
                amount ++;
            }
        }
        return amount;
    }
}
