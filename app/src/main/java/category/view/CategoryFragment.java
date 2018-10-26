package category.view;

import android.view.View;

import com.bwie.mmonth.R;

import base.BaseFragment;


public class CategoryFragment extends BaseFragment {
    private LeftFragment leftFragment;
    private RightFragment rightFragment;

    @Override
    public int getContentView() {
        return R.layout.fragment_category;
    }

    @Override
    public void initView(View view) {
    }

    @Override
    public void initData() {
        super.initData();

        leftFragment = (LeftFragment) getChildFragmentManager().findFragmentById(R.id.fragment_left);
        rightFragment = (RightFragment) getChildFragmentManager().findFragmentById(R.id.fragment_right);
        leftFragment.setOnLeftCategoryClickListener(new LeftFragment.OnLeftCategoryClickListener() {
            @Override
            public void onCategoryClick(int id) {
                rightFragment.setCid(id);
            }
        });
    }
}
