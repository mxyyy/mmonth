package category.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.bwie.mmonth.R;

import java.util.ArrayList;
import java.util.List;

import adapter.RightCategoryAdapter;
import base.BaseFragment;
import category.presenter.RightPresenter;
import entity.CategoryTwo;
import entity.MessageBean;


public class RightFragment extends BaseFragment implements RightCategoryView {
    private RecyclerView rvRight;
    private RightPresenter presenter;
    private List<CategoryTwo> list;
    private RightCategoryAdapter adapter;

    @Override
    public int getContentView() {
        return R.layout.fragment_category_right;
    }

    @Override
    public void initView(View view) {
        rvRight = view.findViewById(R.id.rv_category_right);
    }

    @Override
    public void initData() {
        super.initData();
        presenter = new RightPresenter();
        presenter.attach(this);
        list = new ArrayList<>();
        adapter = new RightCategoryAdapter(context, list);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(context);
        rvRight.setLayoutManager(manager);
        rvRight.setAdapter(adapter);
    }

    @Override
    public void setMoreAction() {
        super.setMoreAction();
        presenter.getData(1);
    }

    @Override
    public void success(MessageBean<List<CategoryTwo>> listMessageBean) {
        if (listMessageBean != null) {
            List<CategoryTwo> data = listMessageBean.getData();
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

    public void setCid(int cid) {
        presenter.getData(cid);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (presenter != null) {
            presenter.detach();
        }
    }
}
