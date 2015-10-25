package com.example.designlab.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.designlab.R;
import com.example.designlab.activity.ContainerActivity;


public class ContainerFragment3 extends Fragment implements TabLayout.OnTabSelectedListener {

    public static final String LOG_TAG = ContainerFragment3.class.getSimpleName();

    public static ContainerFragment3 newInstance() {
        ContainerFragment3 fragment = new ContainerFragment3();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_container_fragment3, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((ContainerActivity)getActivity()).setSupportActionBar(toolbar);
        ((ContainerActivity)getActivity()).getSupportActionBar().setTitle("Fragment 3");

        ((ContainerActivity)getActivity()).prepareDrawer(toolbar);

        initTabs(view);
    }

    private void initTabs(View inflatedView) {

        TabLayout tabLayout = (TabLayout) inflatedView.findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Tab 1"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 2"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 3"));
        tabLayout.setOnTabSelectedListener(ContainerFragment3.this);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        Log.i(LOG_TAG, "onTabSelected " + tab.getText());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        Log.i(LOG_TAG, "onTabUnselected " + tab.getText());
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
        Log.i(LOG_TAG, "onTabReselected " + tab.getText());
    }
}
