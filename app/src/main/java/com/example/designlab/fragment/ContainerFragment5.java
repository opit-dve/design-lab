package com.example.designlab.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.designlab.R;
import com.example.designlab.activity.ContainerActivity;
import com.example.designlab.custom.FlowLayout;
import com.example.designlab.custom.MyGridLayout;


public class ContainerFragment5 extends Fragment {

    private FlowLayout mFlowContainer;
    private MyGridLayout mMyGridContainer;

    public static ContainerFragment5 newInstance() {
        ContainerFragment5 fragment = new ContainerFragment5();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_container_fragment5, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((ContainerActivity)getActivity()).setSupportActionBar(toolbar);
        ((ContainerActivity)getActivity()).getSupportActionBar().setTitle("Fragment 5");

        ((ContainerActivity)getActivity()).prepareDrawer(toolbar);

        mFlowContainer = (FlowLayout) view.findViewById(R.id.flow_container);
        mMyGridContainer = (MyGridLayout) view.findViewById(R.id.grid_container);

        populateContainers();
    }

    private void populateContainers() {

        mFlowContainer.removeAllViews();
        for (int i = 0; i < 10; i++) {
            mFlowContainer.addView(generateTextView("Text " + i));
        }

        mMyGridContainer.removeAllViews();
        for (int i = 0; i < 10; i++) {
            mMyGridContainer.addView(generateTextView("Text " + i));
        }
    }

    private AppCompatTextView generateTextView(CharSequence text) {

        AppCompatTextView tv = new AppCompatTextView(getActivity());
        tv.setText(text);
        tv.setBackgroundColor(Color.CYAN);

        return tv;
    }
}
