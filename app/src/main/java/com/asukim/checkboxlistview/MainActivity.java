package com.asukim.checkboxlistview;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Data를 관리해주는 Adapter
    private CustomAdapter mCustomAdapter = null;

    // 제네릭(String)을 사용한 ArrayList
    private ArrayList<String> mArrayList = new ArrayList<String>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setLayout();

        mCountBt.setText("current checked count = 0 개 입니다.");

        // ArrayList에 String으로 이루어진 값들을 Add 한다.
        mArrayList.add("a");
        mArrayList.add("b");
        mArrayList.add("c");
        mArrayList.add("d");
        mArrayList.add("e");
        mArrayList.add("f");
        mArrayList.add("g");
        mArrayList.add("h");

        mCustomAdapter = new CustomAdapter(MainActivity.this, mArrayList);
        mListView.setAdapter(mCustomAdapter);
        mListView.setOnItemClickListener(mItemClickListener);
    }

    // ListView 안에 Item을 클릭시에 호출되는 Listener
    private AdapterView.OnItemClickListener mItemClickListener = new
            AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                    Toast.makeText(getApplicationContext(), "" + mArrayList.get(position) + "", Toast.LENGTH_SHORT).show();

                    mCustomAdapter.setChecked(position);
                    // Data 변경시 호출 Adapter에 Data 변경 사실을 알려줘서 Update 함.
                    mCustomAdapter.notifyDataSetChanged();
                }
            };


    private ListView mListView = null;
    private CheckBox mAllCheckBox = null;
    private Button mCountBt = null;


    private void setLayout() {
        mListView = (ListView) findViewById(R.id.listView);
        mCountBt = (Button) findViewById(R.id.button);
        mCountBt.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mCountBt.setText("checked count = " + mCustomAdapter.getChecked().size() + "");

                // 체크되 있는 CheckBox의 Position을 출력.
                for (int i = 0; i < mCustomAdapter.getChecked().size(); i++) {
                    Log.d("test", "Position = " + mCustomAdapter.getChecked().get(i));
                }
            }
        });

        mAllCheckBox = (CheckBox) findViewById(R.id.allCheckBox);
        // 전체 체크 버튼 클릭시 Listener
        mAllCheckBox.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mCustomAdapter.setAllChecked(mAllCheckBox.isChecked());

                // Adapter 변화시 리스트 갱신
                mCustomAdapter.notifyDataSetChanged();
            }
        });
    }
}



