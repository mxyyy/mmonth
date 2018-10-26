package category.presenter;


import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import category.model.CategoryModel;
import category.view.RightCategoryView;
import constant.ServerUrl;
import entity.CategoryTwo;
import entity.MessageBean;
import inter.INetCallBack;


public class RightPresenter {
    private RightCategoryView rcv;
    private CategoryModel model;

    public void attach(RightCategoryView rcv) {
        this.rcv = rcv;
        model = new CategoryModel();
    }

    public void getData(int id) {
        String url = ServerUrl.RIGHT_CATEGORY_URL + id;
        Type type = new TypeToken<MessageBean<List<CategoryTwo>>>() {
        }.getType();
        model.getData(url, new INetCallBack() {
            @Override
            public void success(Object obj) {
                rcv.success((MessageBean<List<CategoryTwo>>) obj);
            }

            @Override
            public void failed(Exception e) {
                rcv.failed(e);
            }
        }, type);

    }

    public void detach() {
        if (rcv != null) {
            rcv = null;
        }
    }
}
