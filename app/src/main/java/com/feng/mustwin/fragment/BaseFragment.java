package com.feng.mustwin.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.feng.mustwin.R;

public class BaseFragment extends Fragment {
    protected View mMainView;
    protected Context mContext;

    public BaseFragment() {
        super();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = activity.getApplicationContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mMainView = inflater.inflate(R.layout.online_fragment_item, container, false);
//        ListView listView = (ListView) mMainView.findViewById(R.id.list);
        return mMainView;
    }

}
