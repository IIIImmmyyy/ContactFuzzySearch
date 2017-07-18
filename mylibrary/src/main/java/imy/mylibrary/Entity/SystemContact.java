package imy.mylibrary.Entity;

import android.text.TextUtils;

import java.util.List;

/**
 * Created by 4399-蒋明伟 on 2017/7/18.
 */

public class SystemContact {

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    private int error_code;

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    private List<Contact> contacts;


    public static class Contact implements Comparable<Contact>{
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        private String name;
        private String phone;

        public String getLetter() {
            return letter;
        }

        public void setLetter(String letter) {
            this.letter = letter;
        }

        public String getQuanPin() {
            return QuanPin;
        }

        public void setQuanPin(String quanPin) {
            QuanPin = quanPin;
        }

        public String getAbbreviate() {
            return abbreviate;
        }

        public void setAbbreviate(String abbreviate) {
            this.abbreviate = abbreviate;
        }

        private String letter;
        private String QuanPin;
        private String abbreviate;

        @Override
        public int compareTo(Contact o) {
            if (TextUtils.isEmpty(letter) && TextUtils.isEmpty(o.getLetter())) {
                return 0;//如果两个值都为空，返回0
            }
            if (TextUtils.isEmpty(letter)) {
                return -1;
            }
            if (TextUtils.isEmpty(o.getLetter())) {
                return 1;
            }

            String letter1 = letter.toLowerCase().trim();
            String letter2 = o.getLetter().toLowerCase().trim();

            char a = letter1.charAt(0);
            char b = letter2.charAt(0);
            if (a > b) {
                if (b == '#') {//让#排在后面
                    return -1;
                }
                return 1;
            } else if (a < b) {
                if (a == '#') {//让#排在后面
                    return 1;
                }
                return -1;
            } else {
                return 0;//如果两个值相等一定要返回0，不然会报错
            }
        }
    }

}
