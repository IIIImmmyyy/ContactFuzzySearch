package imy.contactfuzzysearch.com.Utils;

import android.text.TextUtils;

/**
 * Created by 4399-1500 on 2017/4/6.
 */

public class StringUtils {
    /**
     * 是否为字母
     */
    public static boolean isLetter(String str) {
        if (TextUtils.isEmpty(str) || str.length() > 1) {
            return false;
        }
        return isLetter(str.charAt(0));
    }

    /**
     * 是否为字母
     */
    private static boolean isLetter(char c) {
        if (((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'))) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 输入的字符串是否为中英文混合
     * @param str
     * @return
     */
    public static boolean isLetterOrChinese(String str) {
        boolean letter =false;
        for (int i = 0; i < str.length(); i++) {
            if (isLetter(str.charAt(i))){
                letter=true;
            }
        }

        boolean isChinese = isChinese(str);
        if (letter && isChinese){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 判断该字符串是否为中文
     * @param string
     * @return
     */
    public static boolean isChinese(String string){
        for(int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            if ((c >= 0x4e00)&&(c <= 0x9fa5)){
                return  true;
            }
        }
        return false;
    }
}
