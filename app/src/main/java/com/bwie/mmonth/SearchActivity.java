package com.bwie.mmonth;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import base.BaseActivity;
import widget.FlowLayout;

public class SearchActivity extends BaseActivity implements View.OnClickListener {
    private TextView txtSearch;
    private EditText etSearch;
    private FlowLayout flSearch;

    @Override
    public int getContentView() {
        return R.layout.activity_search;
    }

    @Override
    public void initView() {
        txtSearch = findViewById(R.id.txt_search);
        etSearch = findViewById(R.id.et_search);
        flSearch = findViewById(R.id.fl_search);
    }

    @Override
    public void initData() {
        super.initData();
    }

    @Override
    public void setListener() {
        super.setListener();
        txtSearch.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txt_search:
                String text = etSearch.getText().toString().trim();
                if (!TextUtils.isEmpty(text)) {
                    TextView txt = new TextView(this);
                    txt.setText(text);
                    txt.setPadding(10, 10, 10, 10);
                    ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    txt.setLayoutParams(layoutParams);
                    flSearch.addView(txt);
                    etSearch.setText("");
                }
                break;
        }
    }
}
