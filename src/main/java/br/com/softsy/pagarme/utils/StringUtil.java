package br.com.softsy.pagarme.utils;

public class StringUtil {
    public static String getOrEmpty(String value) {
        return value != null ? value : "";
    }
}
