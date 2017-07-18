package imy.mylibrary.Utils;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import imy.mylibrary.Entity.SystemContact;

/**
 * 模糊搜索 针对通讯录
 * Created by 4399-1500 on 2017/4/12.
 */

public class FuzzySearchHelper {
    private Context mContext;
    protected final String TAG = this.getClass().getSimpleName();
    private List<SystemContact.Contact> mateContactList = new ArrayList<>();// 匹配的集合数据
    private volatile static FuzzySearchHelper instance;

    public static FuzzySearchHelper getInstance() {
        if (instance == null) {
            synchronized (FuzzySearchHelper.class) {
                if (instance == null) {
                    instance = new FuzzySearchHelper();
                }
            }
        }
        return instance;
    }

    public FuzzySearchHelper init(final EditText text, Context mContext) {
        this.mContext = mContext;
        new Thread(new Runnable() {
            @Override
            public void run() {
                start(text);
            }
        }).start();
        return instance;
    }

    private void start(final EditText text) {
        final SystemContact contacts = SystemContactsHelper.getInstance().getContacts(mContext);
        if (contacts.getError_code() == 0) {
            text.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    String keyword = text.getText().toString().toLowerCase();
                    mateContactList.clear();
                    if (keyword.equals("")) {
                        if (callBack != null) {
                            callBack.onFuzzySearch(mateContactList);
                        }
                    } else {
                        if (StringUtils.isChinese(keyword)) {
                            for (int i = 0; i < contacts.getContacts().size(); i++) {
                                if (contacts.getContacts().get(i).getName().contains(keyword)
                                        || contacts.getContacts().get(i).getName().toLowerCase().contains(keyword)) {
                                    mateContactList.add(contacts.getContacts().get(i));
                                }
                            }
                        } else if (StringUtils.isLetterOrChinese(keyword)) { //混合的情况
                            //do nothing
                        } else {//字母
                            for (int i = 0; i < contacts.getContacts().size(); i++) {
                                String lowerCase = contacts.getContacts().get(i).getName().toLowerCase();//强转成小写
                                String name_first_letter = PinYinUtils.converterToFirstSpell(lowerCase);
                                String name_quan_pin = PinYinUtils.converterToSpell(lowerCase);
                                //拼接
                                String pin_yin = name_quan_pin + name_first_letter;
                                if (pin_yin.equals("")) {
                                    pin_yin = contacts.getContacts().get(i).getName();
                                }
                                if (pin_yin.contains(keyword)) {
                                    mateContactList.add(contacts.getContacts().get(i));
                                }
                            }
                        }
                        if (callBack != null) {
                            callBack.onFuzzySearch(mateContactList);
                        }
                    }

                }
            });
        }
    }


    public interface FuzzySearchCallBack {
        void onFuzzySearch(List<SystemContact.Contact> mateContactList);
    }

    public void setOnFuzzySearchCallBack(FuzzySearchCallBack callBack) {
        this.callBack = callBack;
    }

    private FuzzySearchCallBack callBack;



}
