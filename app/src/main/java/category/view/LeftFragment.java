package category.view;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.bwie.mmonth.R;

import java.util.ArrayList;
import java.util.List;

import adapter.LeftCategoryAdapter;
import base.BaseFragment;
import category.presenter.LeftPresenter;
import entity.CategoryOne;
import entity.MessageBean;


public class LeftFragment extends BaseFragment implements LeftCategoryView {
    private RecyclerView rvLeft;
    private LeftPresenter presenter;
    private List<CategoryOne> list;
    private LeftCategoryAdapter adapter;

    private OnLeftCategoryClickListener listener;

    public interface OnLeftCategoryClickListener {
        void onCategoryClick(int id);
    }

    public void setOnLeftCategoryClickListener(OnLeftCategoryClickListener listener) {
        this.listener = listener;
    }

    @Override
    public int getContentView() {
        return R.layout.fragment_category_left;
    }

    @Override
    public void initView(View view) {
        rvLeft = view.findViewById(R.id.rv_categoty_left);
    }

    @Override
    public void initData() {
        super.initData();
        presenter = new LeftPresenter();
        presenter.attach(this);
        list = new ArrayList<>();
        adapter = new LeftCategoryAdapter(context, list);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(context);
        rvLeft.setLayoutManager(manager);
        rvLeft.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        adapter.setOnItemClickListener(new LeftCategoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                int cid = list.get(position).getCid();
                if (listener != null) {
                    listener.onCategoryClick(cid);
                }
            }
        });

        rvLeft.setAdapter(adapter);
    }

    @Override
    public void setMoreAction() {
        super.setMoreAction();
        presenter.getData();
    }

    @Override
    public void success(MessageBean<List<CategoryOne>> listMessageBean) {
        if (listMessageBean != null) {
            List<CategoryOne> data = listMessageBean.getData();
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
    public void onDestroyView() {
        super.onDestroyView();
        if (presenter != null) {
            presenter.detach();
        }
    }
}
