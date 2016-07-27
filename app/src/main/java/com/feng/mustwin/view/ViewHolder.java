package com.feng.mustwin.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewHolder {
    private final SparseArray<View> mViews;
    private int mPosition;
    private View mConvertView;
    private Context mContext;

    private ViewHolder(Context context, ViewGroup parent, int layoutId,
                       int position) {
        mContext = context;
        this.mPosition = position;
        this.mViews = new SparseArray<View>();
        mConvertView = LayoutInflater.from(context).inflate(layoutId, parent,
                false);
        //setTag
        mConvertView.setTag(this);
    }

    /**
     * 拿到一个ViewHolder对象
     *
     * @param context
     * @param convertView
     * @param parent
     * @param layoutId
     * @param position
     * @return
     */
    public static com.feng.mustwin.view.ViewHolder get(Context context, View convertView,
                                 ViewGroup parent, int layoutId, int position) {

        if (convertView == null) {
            return new com.feng.mustwin.view.ViewHolder(context, parent, layoutId, position);
        }
        return (com.feng.mustwin.view.ViewHolder) convertView.getTag();
    }


    /**
     * 通过控件的Id获取对于的控件，如果没有则加入views
     *
     * @param viewId
     * @return
     */
    public <T extends View> T getView(int viewId) {

        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public View getConvertView() {
        return mConvertView;
    }

    /**
     * 为TextView设置字符串
     *
     * @param viewId
     * @param text
     * @return
     */
    public com.feng.mustwin.view.ViewHolder setText(int viewId, String text) {
        TextView view = getView(viewId);
        view.setText(text);
        return this;
    }

    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @param drawableId
     * @return
     */
    public com.feng.mustwin.view.ViewHolder setImageResource(int viewId, int drawableId) {
        ImageView view = getView(viewId);
        view.setImageResource(drawableId);

        return this;
    }

    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @return
     */
    public com.feng.mustwin.view.ViewHolder setImageBitmap(int viewId, Bitmap bm) {
        ImageView view = getView(viewId);
        view.setImageBitmap(bm);
        return this;
    }

//    /**
//     * 为ImageView设置图片
//     *
//     * @param viewId
//     * @param url
//     * @param url    是否是网络图片
//     * @return
//     */
//    public ViewHolder setImageByUrl(int viewId, String url, boolean isNet) {
////        AbImageLoader.getInstance(mContext).display((ImageView) getView(viewId), url);
//        ImageView imageview = getView(viewId);
//        imageview.setImageResource(R.drawable.pictures_no);
//        ImageLoader.getInstance(3, ImageLoader.Type.LIFO).loadImage(url, imageview
//                , isNet);
//        return this;
//    }


    public int getPosition() {
        return mPosition;
    }
}

