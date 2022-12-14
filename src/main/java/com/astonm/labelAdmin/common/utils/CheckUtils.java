package com.astonm.labelAdmin.common.utils;

/**
 * @Author Astonm
 * @Date 2021/7/23
 * @Description:
 **/

import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class CheckUtils {

    public static boolean isBlank(Object obj) {
        if (obj == null) {
            return true;
        }
        return StringUtils.isBlank(obj.toString());
    }

    public static boolean isNotBlank(Object obj) {
        return !isBlank(obj);
    }

    public static boolean isBlankOrNull(Object obj) {
        if (obj == null) {
            return true;
        }
        if ("null".equals(obj)) {
            return true;
        }
        return StringUtils.isBlank(obj.toString());
    }


    public static boolean checkSignature(String token, String signature, String timestamp,
                                         String nonce) {
        String[] arr = new String[] {token, timestamp, nonce};
        // 排序
        Arrays.sort(arr);
        // 生成字符串
        StringBuffer content = new StringBuffer();
        for (int i = 0; i < arr.length; i++) {
            content.append(arr[i]);
        }
        // sha1加密
        String temp = getSha1(content.toString());
        if (CheckUtils.isBlank(temp)) {
            return false;
        }
        return temp.equals(signature);
    }

    public static String getSha1(String str) {
        if (null == str || 0 == str.length()) {
            return null;
        }
        char[] hexDigits =
                {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes("UTF-8"));

            byte[] md = mdTemp.digest();
            int j = md.length;
            char[] buf = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(buf);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
