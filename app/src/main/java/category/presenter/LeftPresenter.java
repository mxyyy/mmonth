package category.presenter;


import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import category.model.CategoryModel;
import category.view.LeftCategoryView;
import constant.ServerUrl;
import entity.CategoryOne;
import entity.MessageBean;
import inter.INetCallBack;


public class LeftPresenter {
    private LeftCategoryView lcv;
    private CategoryModel model;

    public void attach(LeftCategoryView lcv) {
        this.lcv = lcv;
        model = new CategoryModel();
    }

    public void getData() {
        String url = ServerUrl.LEFT_CATEGORY_URL;
        Type type = new TypeToken<MessageBean<List<CategoryOne>>>() {
        }.getType();
        model.getData(url, new INetCallBack() {
            @Override
            public void success(Object obj) {
                lcv.success((MessageBean<List<CategoryOne>>) obj);
            }

            @Override
            public void failed(Exception e) {
                lcv.failed(e);
            }
        }, type);

    }

    public void detach() {
        if (lcv != null) {
            lcv = null;
        }
    }
}
