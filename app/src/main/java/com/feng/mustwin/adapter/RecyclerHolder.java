package com.feng.mustwin.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 　　　　　　　 ┏┓　      ┏┓
 * 　　　　　　　┏┛┻━━━━━━━━┛┻┓
 * 　　　　　　　┃　　　　　　　┃
 * 　　　　　　　┃　　　━      ┃
 * 　　　　　　　┃　＞　　　＜　┃
 * 　　　　　　　┃　　　　　　　┃
 * 　　　　　　　┃...　⌒　...  ┃
 * 　　　　　　　┃　　　　　　　┃
 * 　　　　　　　┗━┓　　   　┏━┛
 * 　　　　　    　┃　　   　┃　Code is far away from bug with the animal protecting
 * 　　　　　    　┃　　   　┃	             神兽保佑,代码无bug
 * 　　　　　    　┃　　   　┃
 * 　　　　　    　┃　　   　┃
 * 　　　　　    　┃　　   　┃
 * 　　　　　    　┃　　   　┃
 * 　　　　　    　┃　　   　┗━━━┓
 * 　　　　　    　┃　　　　　　　┣┓
 * 　　　　　    　┃　　　　　　　┏┛
 * 　　　　　    　┗┓┓┏━━━━━━┳┓┏┛
 * 　　　　　    　 ┃┫┫      ┃┫┫
 * 　　　　　    　 ┗┻┛    　┗┻┛
 * Created by paulxu on 15/11/27.
 */
public class RecyclerHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> mViews;

    public RecyclerHolder(View itemView) {
        super(itemView);
        this.mViews = new SparseArray<>(10);
    }

    public <T extends View> T getView(int viewID) {
        View view = mViews.get(viewID);
        if (view == null) {
            view = itemView.findViewById(viewID);
            mViews.put(viewID, view);
        }
        return (T) view;
    }

    public RecyclerHolder setText(int viewID, String text) {
        TextView view = getView(viewID);
        view.setText(text);
        return this;
    }

    public RecyclerHolder setImageResourse(int viewID, int resourseID) {
        ImageView imageView = getView(viewID);
        imageView.setImageResource(resourseID);
        return this;
    }
}
