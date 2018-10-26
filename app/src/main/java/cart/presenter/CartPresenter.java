package cart.presenter;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import cart.model.CartModel;
import cart.view.CartView;
import constant.ServerUrl;
import entity.MessageBean;
import entity.Product;
import entity.Shopper;
import inter.INetCallBack;


public class CartPresenter {
    private CartView cv;
    private CartModel model;

    public void attach(CartView cv) {
        this.cv = cv;
        model = new CartModel();
    }

    public void getData(String userid) {
        String url = ServerUrl.QUERY_CART_URL + userid;
        Type type = new TypeToken<MessageBean<List<Shopper<List<Product>>>>>() {
        }.getType();

        model.getData(url, new INetCallBack() {
            @Override
            public void success(Object obj) {
                MessageBean<List<Shopper<List<Product>>>> data = (MessageBean<List<Shopper<List<Product>>>>) obj;
                cv.success(data);
            }

            @Override
            public void failed(Exception e) {
                cv.failed(e);
            }
        }, type);

    }

    public void detach() {
        if (cv != null) {
            cv = null;
        }
    }
}
