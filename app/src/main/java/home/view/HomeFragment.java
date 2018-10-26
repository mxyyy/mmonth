package home.view;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bwie.mmonth.R;
import com.bwie.mmonth.SearchActivity;

import java.util.ArrayList;
import java.util.List;

import adapter.BannerAdapter;
import base.BaseFragment;
import entity.Banner;
import entity.MessageBean;
import home.presenter.HomePresenter;


public class HomeFragment extends BaseFragment implements HomeView, View.OnClickListener {
    private LinearLayout llSearch;
    private ViewPager vpBanner;
    private HomePresenter presenter;
    private BannerAdapter adapter;
    private List<Banner> list;

    @Override
    public int getContentView() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView(View view) {
        llSearch = view.findViewById(R.id.ll_home_search);
        vpBanner = view.findViewById(R.id.vp_banner);
    }

    @Override
    public void initData() {
        super.initData();
        presenter = new HomePresenter();
        presenter.attach(this);
        list = new ArrayList<>();
        adapter = new BannerAdapter(context, list);
        vpBanner.setAdapter(adapter);
    }

    @Override
    public void setListener() {
        super.setListener();
        llSearch.setOnClickListener(this);
    }

    @Override
    public void setMoreAction() {
        super.setMoreAction();
        presenter.getData();
    }

    @Override
    public void success(MessageBean<List<Banner>> listMessageBean) {
        if (listMessageBean != null) {
            List<Banner> data = listMessageBean.getData();
            if (data != null) {
                list.clear();
                list.addAll(data);
                adapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void failed(Exception e) {
        Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_home_search:
                Intent intent = new Intent(context, SearchActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detach();
        }
    }
}
