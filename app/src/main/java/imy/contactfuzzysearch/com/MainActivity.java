package imy.contactfuzzysearch.com;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

import imy.contactfuzzysearch.com.Adapter.ContactAdapter;
import imy.mylibrary.Entity.SystemContact;
import imy.mylibrary.Utils.FuzzySearchHelper;
import imy.mylibrary.Utils.SystemContactsHelper;

public class MainActivity extends AppCompatActivity {
    private ListView mainListView;
    private ListView mateListView;
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainListView = (ListView) findViewById(R.id.list_view);
        mateListView = (ListView) findViewById(R.id.mate_list_view);
        editText = (EditText) findViewById(R.id.edit_text);
        init();
    }

    private void init() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemContact contacts = SystemContactsHelper.getInstance().getContacts(MainActivity.this);
                if (contacts.getError_code()==0){
                    List<SystemContact.Contact> contacts1 = contacts.getContacts();
                    setAdapter(contacts1);
                }else {
                    //error // or no permission
                    Log.i("MainActivity","error");
                }
            }
        }).start();
        FuzzySearchHelper.getInstance().init(editText,MainActivity.this)
                .setOnFuzzySearchCallBack(new FuzzySearchHelper.FuzzySearchCallBack() {
                    @Override
                    public void onFuzzySearch(List<SystemContact.Contact> mateContactList) {
                        mateListView.setAdapter(new ContactAdapter(MainActivity.this,mateContactList));
                    }
                });
    }

    private void setAdapter(final List<SystemContact.Contact> contacts1) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mainListView.setAdapter(new ContactAdapter(MainActivity.this,contacts1));
            }
        });
    }

    /**
     * 获取首字母
     * @param contacts1
     */
    private void getFirstLetter(List<SystemContact.Contact> contacts1){
        String letter = contacts1.get(0).getLetter(); //
    }

}
