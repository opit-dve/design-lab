package com.example.designlab.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.PopupWindowCompat;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.designlab.R;
import com.example.designlab.activity.ContainerActivity;
import com.example.designlab.custom.TagsPopupWindow;


public class ContainerFragment4 extends Fragment implements TabLayout.OnTabSelectedListener,
        SearchView.OnQueryTextListener {

    public static final String LOG_TAG = ContainerFragment4.class.getSimpleName();
    private Toolbar mToolbar;

    public static ContainerFragment4 newInstance() {
        ContainerFragment4 fragment = new ContainerFragment4();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_container_fragment4_3, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((ContainerActivity)getActivity()).setSupportActionBar(mToolbar);
        ((ContainerActivity)getActivity()).getSupportActionBar().setTitle("Fragment 4");

        ((ContainerActivity)getActivity()).prepareDrawer(mToolbar);

        initTabs(view);
    }

    private void initTabs(View inflatedView) {

        TabLayout tabLayout = (TabLayout) inflatedView.findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Tab 1"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 2"));
        tabLayout.setOnTabSelectedListener(ContainerFragment4.this);
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.menu_container_fragment4, menu);

        // Associate searchable configuration with the SearchView
        // SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        // searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(ContainerFragment4.this);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        Log.i(LOG_TAG, "onQueryTextSubmit " + query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        Log.i(LOG_TAG, "onQueryTextChange " + newText);
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_settings:
                Log.i(LOG_TAG, "Settings bottom: " + item);
                PopupWindowCompat.showAsDropDown(new TagsPopupWindow(getActivity()),
                        mToolbar, -240, 10, Gravity.END);

                //new TagsPopupWindow(getActivity()).showAtLocation(mToolbar, Gravity.BOTTOM, 0, 10);
                //new TagsPopupWindow((getActivity())).showAsDropDown(mToolbar, 10, 10);

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
