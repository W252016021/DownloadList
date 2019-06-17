package com.hammer.downloadlist;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private List<TaskBean> data = new ArrayList<>();
    private DownloadListAdapter adapter;
    private Handler handler = new Handler();

    private int progress = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        handler.post(new Runnable() {
            @Override
            public void run() {
                progress = progress + 1;
                if (progress >= 1024) {
                    progress = 0;
                }
                for (int i = 0; i < data.size(); i++) {
                    if (adapter.isShowing(i)) {
                        /*if (data.get(i).getTag().equals("ID=5")) {*/
                        DownloadListAdapter.ViewHolder viewHolder = adapter.getViewHolder(i);
                        viewHolder.progress.setProgress((int) ((double) progress / 1024 * 100));
                        viewHolder.info.setText(progress + " M/1023 M");
                        viewHolder.speed.setText(i + ".3 M/s");
                        /* }*/
                    }
                }
                handler.postDelayed(this, 50);
            }
        });


    }

    private void init() {
        listView = findViewById(R.id.list);
        listView.setDivider(null);
        adapter = new DownloadListAdapter(this, R.layout.item_download_list, listView, data);
        listView.setAdapter(adapter);

        for (int i = 0; i < 100; i++) {
            data.add(new TaskBean("神奇四侠HD1080.MKV", "0 K/s", "0 K/1023 M", 0, "ID=" + i));
        }
        adapter.notifyDataSetChanged();
    }


}
