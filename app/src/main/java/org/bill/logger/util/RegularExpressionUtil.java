package org.bill.logger.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 作者：Bill
 * 时间：2018-06-09 17:15
 * 描述：正则表达式工具类
 */
public class RegularExpressionUtil
{
    private RegularExpressionUtil(){}

    /**
     * 是否是邮箱格式
     * @param email
     * @return
     */
    private static boolean isEmailVaild(String email)
    {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\\\{\\}]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * 是否是手机号格式
     * @param phoneNumber
     * @return
     */
    private static boolean isPhoneNumber(String phoneNumber)
    {
        Pattern pattern = Pattern.compile("[0-9]+");
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

}
