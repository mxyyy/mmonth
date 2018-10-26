package cart.view;

import java.util.List;

import base.BaseView;
import entity.MessageBean;
import entity.Product;
import entity.Shopper;


public interface CartView extends BaseView<MessageBean<List<Shopper<List<Product>>>>> {
}
