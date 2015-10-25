package com.example.designlab.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.designlab.R;
import com.example.designlab.activity.ContainerActivity;


public class ContainerFragment1 extends Fragment {

    public static ContainerFragment1 newInstance() {
        ContainerFragment1 fragment = new ContainerFragment1();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_container_fragment1, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((ContainerActivity)getActivity()).setSupportActionBar(toolbar);
        ((ContainerActivity)getActivity()).getSupportActionBar().setTitle("Fragment 1");

        ((ContainerActivity)getActivity()).prepareDrawer(toolbar);
    }
}
