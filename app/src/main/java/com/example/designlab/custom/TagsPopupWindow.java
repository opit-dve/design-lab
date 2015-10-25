package com.example.designlab.custom;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.example.designlab.R;

public class TagsPopupWindow extends PopupWindow implements View.OnClickListener {

    private Context mCtx;

    public TagsPopupWindow(Context ctx) {
        super(ctx);

        mCtx = ctx;

        init();
    }

    private void init() {

        View popupView = LayoutInflater.from(mCtx).inflate(R.layout.popup_window_tags, null);
        setContentView(popupView);

        popupView.findViewById(R.id.close).setOnClickListener(TagsPopupWindow.this);

        setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        setWidth(WindowManager.LayoutParams.WRAP_CONTENT);

        // Closes the popup window when touch outside of it - when looses focus
        setOutsideTouchable(true);
        setFocusable(true);

        // Removes default black background
        setBackgroundDrawable(new ColorDrawable(Color.GREEN));
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.close:
                dismiss();
                break;
        }
    }
}
