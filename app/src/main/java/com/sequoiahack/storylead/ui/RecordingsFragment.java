package com.sequoiahack.storylead.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.sequoiahack.storylead.R;

/**
 * This fragment is loaded when user clicks on Recording button on BottomBar
 * Created by zac on 10/09/16.
 */
public class RecordingsFragment extends BaseFragment {

    private String[] name = new String[]{"Zac", "Anoop", "Bots"};
    private String[] number = new String[]{"9677887766", "7788778877", "9988776655"};
    private ListView recordingsListView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.recordings_fragment, container, false);
        recordingsListView = (ListView) layout.findViewById(R.id.recordingslistview);

        /*
        DataManager.saveData("1", "2", "3", "4", "5", "6", "7");
        List<CallData> callDataList = DataManager.getAllData();
        for (CallData callData : callDataList) {
            showLog(callData.filename + " " + callData.analyzed + " " + callData.text + " " + callData.sentiment + " " + callData.emotion + " " + callData.politicalleaning + " " + callData.topics);
        }
        */

        CustomAdapter mCustomAdapter = new CustomAdapter();
        recordingsListView.setAdapter(mCustomAdapter);


        return layout;
    }


    private class CustomAdapter extends BaseAdapter {
        private LayoutInflater mInflater;

        public CustomAdapter() {
            mInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return name.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            final ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.listitem, null);
                holder.nameTextView = (TextView) convertView
                        .findViewById(R.id.listitemtextview1);
                holder.numberTextView = (TextView) convertView
                        .findViewById(R.id.listitemtextview2);
                holder.uploadNextImageView = (ImageView) convertView
                        .findViewById(R.id.listitemimagebutton);
                convertView.setTag(holder);


            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.nameTextView.setText(name[position]);
            holder.numberTextView.setText(number[position]);
            return convertView;
        }
    }

    public static class ViewHolder {
        public TextView nameTextView;
        public TextView numberTextView;
        public ImageView uploadNextImageView;
    }

}
