package imy.contactfuzzysearch.com.Entity;

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


    public static class Contact{
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
    }

}
