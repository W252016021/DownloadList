package com.hammer.downloadlist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

public class DownloadListAdapter extends ArrayAdapter<TaskBean> {
    private int mResourceId;
    private List<TaskBean> lists;
    private LayoutInflater inflater;
    private ListView listView;

    public DownloadListAdapter(Context context, int resource, ListView listView, List<TaskBean> objects) {
        super(context, resource, objects);
        this.mResourceId = resource;
        this.lists = objects;
        this.inflater = LayoutInflater.from(context);
        this.listView = listView;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        TaskBean item = getItem(position);
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(mResourceId, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (item != null) {
            viewHolder.title.setText(item.getTaskName());
            viewHolder.speed.setText(item.getSpeed());
            viewHolder.info.setText(item.getInfo());
            //viewHolder.progress.setIndeterminate(item.getProgress() <= 0);
            viewHolder.progress.setProgress(item.getProgress());
        }
        return convertView;
    }

    public class ViewHolder {
        public TextView title;
        public TextView speed;
        public ProgressBar progress;
        public ImageView image;
        public TextView info;

        private ViewHolder(View viewRoot) {
            title = viewRoot.findViewById(R.id.title);
            speed = viewRoot.findViewById(R.id.speed);
            image = viewRoot.findViewById(R.id.image);
            progress = viewRoot.findViewById(R.id.progress);
            info = viewRoot.findViewById(R.id.info);
        }
    }

    private View getViewByPosition(int pos, ListView listView) {
        final int firstListItemPosition = listView.getFirstVisiblePosition();
        final int lastListItemPosition = firstListItemPosition + listView.getChildCount() - 1;
        if (pos < firstListItemPosition || pos > lastListItemPosition) {
            return listView.getAdapter().getView(pos, null, listView);
        } else {
            final int childIndex = pos - firstListItemPosition;
            return listView.getChildAt(childIndex);
        }
    }

    public DownloadListAdapter.ViewHolder getViewHolder(int position) {
        return (DownloadListAdapter.ViewHolder) getViewByPosition(position, listView).getTag();
    }

    public boolean isShowing(int position) {
        int showViewCount = listView.getChildCount();
        int lastPosition = listView.getLastVisiblePosition();
        return position <= lastPosition && position > lastPosition - showViewCount;
    }
}
