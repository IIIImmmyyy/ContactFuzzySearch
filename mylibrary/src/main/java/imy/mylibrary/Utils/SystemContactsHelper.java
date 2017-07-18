package imy.mylibrary.Utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import imy.mylibrary.Entity.SystemContact;


/**
 * Created by 4399-1500 on 2017/4/6.
 */

public class SystemContactsHelper {
    private volatile static SystemContactsHelper instance;
    private static final String TAG = "SystemContactsHelper";
    private static final String[] CONTACTOR_ION = new String[]{
            ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
            ContactsContract.Contacts.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER};
    private SystemContactsHelper() {
        // 私有构造方法
    }

    public static SystemContactsHelper getInstance() {
        if (instance == null) {
            synchronized (SystemContactsHelper.class) {
                if (instance == null) {
                    instance = new SystemContactsHelper();
                }
            }
        }
        return instance;
    }



    public SystemContact getContacts(Context context){
        ContentResolver resolver = context.getContentResolver();
        SystemContact systemContact = new SystemContact();
        List<SystemContact.Contact> list = new ArrayList<>();
        Cursor phones = null;
        try {
            phones = resolver
                    .query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI
                            , CONTACTOR_ION, null, null, "sort_key");
            if (phones != null) {
                final int contactIdIndex = phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID);
                final int displayNameIndex = phones.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
                final int phoneIndex = phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                String phoneString, displayNameString = null, contactIdString;
                while (phones.moveToNext()) {
                    SystemContact.Contact contact =
                            new SystemContact.Contact();
                    phoneString = phones.getString(phoneIndex);
                    displayNameString = phones.getString(displayNameIndex);
                    contactIdString = phones.getString(contactIdIndex);
                    String s = phoneString.replaceAll(" ", "");
                    String s1 = s.replaceAll("-", "");
                    contact.setPhone(s1);
                    contact.setName(displayNameString);
                    list.add(contact);
                }
            }else {
                systemContact.setError_code(2);
                systemContact.setContacts(list);
                return systemContact;
            }
        } catch (Exception e) {
            systemContact.setError_code(1);
            systemContact.setContacts(list);
            return systemContact;
        } finally {
            if (phones != null)
                phones.close();
        }
        if (list.size() == 0) {
            systemContact.setError_code(3);
            systemContact.setContacts(list);
            return systemContact;
        }
        systemContact.setError_code(0);
        List<SystemContact.Contact> contactList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            SystemContact.Contact contact = list.get(i);
            String name_pinyin;
            String name_first_letter;
            String name = contact.getName();
            String lowerCase = name.toLowerCase();//强转成小写
            name_pinyin = PinYinUtils.converterToSpell(lowerCase);
            name_first_letter = PinYinUtils.converterToFirstSpell(lowerCase);
            String letter = ""; //首字母
            if (name_first_letter.length() > 0) {
                letter = name_first_letter.substring(0, 1).toUpperCase();
            }
            if (!StringUtils.isLetter(letter)) {//首字符不是字母的用#标识
                letter = "#";
            }
            contact.setLetter(letter);
            contact.setQuanPin(name_pinyin);
            contact.setAbbreviate(name_first_letter);
            contactList.add(contact);
        }
        Collections.sort(contactList);
        systemContact.setContacts(contactList);
        return systemContact;
    }
}
