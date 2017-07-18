package imy.contactfuzzysearch.com.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.List;

import imy.contactfuzzysearch.com.Entity.SystemContact;
import imy.contactfuzzysearch.com.R;

/**
 * Created by 4399-蒋明伟 on 2017/7/18.
 */

public class ContactAdapter extends BaseAdapter {
    private List<SystemContact.Contact> lists;
    private Context mContext;
    public ContactAdapter(Context context, List<SystemContact.Contact> lists) {
        this.mContext = context;
        this.lists = lists;
    }
    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public SystemContact.Contact getItem(int position) {
        return lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null){
            holder = new ViewHolder();
            convertView =View.inflate(mContext, R.layout.list_item,null);
            holder.tv = (TextView) convertView.findViewById(R.id.text);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv.setText(getItem(position).getName());
        return convertView;
    }

    class ViewHolder {
        private TextView tv;
    }

}
