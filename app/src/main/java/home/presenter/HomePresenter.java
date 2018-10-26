package home.presenter;


import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import constant.ServerUrl;
import entity.Banner;
import entity.MessageBean;
import home.model.HomeModel;
import home.view.HomeView;
import inter.INetCallBack;


public class HomePresenter {
    private HomeView hv;
    private HomeModel model;

    public void attach(HomeView hv) {
        this.hv = hv;
        model = new HomeModel();
    }

    public void getData() {
        String url = ServerUrl.BANNER_URL;
        Type type = new TypeToken<MessageBean<List<Banner>>>() {
        }.getType();
        model.getData(url, new INetCallBack() {
            @Override
            public void success(Object obj) {
                hv.success((MessageBean<List<Banner>>) obj);
            }

            @Override
            public void failed(Exception e) {
                hv.failed(e);
            }
        }, type);
    }


    public void detach() {
        if (hv != null) {
            hv = null;
        }
    }
}
