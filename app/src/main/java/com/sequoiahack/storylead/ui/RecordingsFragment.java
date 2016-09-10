package com.sequoiahack.storylead.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.sequoiahack.storylead.R;
import com.sequoiahack.storylead.controller.data.DataManager;
import com.sequoiahack.storylead.controller.data.tablemodels.CallData;

import java.util.List;

/**
 * This fragment is loaded when user clicks on Recording button on BottomBar
 * Created by zac on 10/09/16.
 */
public class RecordingsFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.recordings_fragment, container, false);
        Button saveButton = (Button) layout.findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataManager.saveData("1", "2", "3", "4", "5", "6", "7");
            }
        });

        Button readButton = (Button) layout.findViewById(R.id.readButton);
        readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<CallData> callDataList = DataManager.getAllData();
                for (CallData callData : callDataList) {
                    showLog(callData.filename + " " + callData.analyzed + " " + callData.text + " " + callData.sentiment + " " + callData.emotion + " " + callData.politicalleaning+" "+callData.topics);
                }
            }
        });
        return layout;
    }
}
