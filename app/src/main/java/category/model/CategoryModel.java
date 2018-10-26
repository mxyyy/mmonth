package category.model;


import java.lang.reflect.Type;

import inter.INetCallBack;
import utils.HttpUtils;


public class CategoryModel {
    public void getData(String url, INetCallBack callBack, Type type) {
        HttpUtils.getInstance().get(url, callBack, type);
    }
}
