package com.bwie.mmonth;

import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.TextView;

import base.BaseActivity;
import cart.CartFragment;
import category.view.CategoryFragment;
import home.view.HomeFragment;

public class HomeActivity extends BaseActivity implements View.OnClickListener {
    private TextView txthome;
    private TextView txtCategory;
    private TextView txtCart;
    private FragmentManager manager;

    private HomeFragment homeFragment;
    private CategoryFragment categoryFragment;
    private CartFragment cartFragment;

    @Override
    public int getContentView() {
        return R.layout.activity_home;
    }

    @Override
    public void initView() {
        txthome = findViewById(R.id.txt_home);
        txtCategory = findViewById(R.id.txt_category);
        txtCart = findViewById(R.id.txt_cart);
    }

    @Override
    public void initData() {
        super.initData();
        manager = getSupportFragmentManager();
        homeFragment = new HomeFragment();
        categoryFragment = new CategoryFragment();
        cartFragment = new CartFragment();

        manager.beginTransaction()
                .add(R.id.content, homeFragment)
                .add(R.id.content, categoryFragment)
                .add(R.id.content, cartFragment)
                .hide(categoryFragment)
                .hide(cartFragment)
                .commit();
    }

    @Override
    public void setListener() {
        super.setListener();
        txthome.setOnClickListener(this);
        txtCategory.setOnClickListener(this);
        txtCart.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txt_home:
                manager.beginTransaction().show(homeFragment)
                        .hide(categoryFragment)
                        .hide(cartFragment)
                        .commit();
                break;
            case R.id.txt_category:
                manager.beginTransaction().show(categoryFragment)
                        .hide(homeFragment)
                        .hide(cartFragment)
                        .commit();
                break;
            case R.id.txt_cart:
                manager.beginTransaction().show(cartFragment)
                        .hide(homeFragment)
                        .hide(categoryFragment)
                        .commit();
                break;
        }
    }
}
