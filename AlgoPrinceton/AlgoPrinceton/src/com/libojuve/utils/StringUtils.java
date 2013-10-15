package com.libojuve.utils;

public class StringUtils {
    /**
     * 如果str为null，则返回空字符串（""）；否则返回<code>str</code>本身。
     * 
     * @param str
     *            要转换的字符串
     * @return
     */
    public static String getStringNonNull(String str) {
        return str == null ? "" : str;
    }
}
