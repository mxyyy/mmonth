package adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwie.mmonth.R;

import java.util.List;

import entity.Product;
import utils.StringUtils;
import widget.AddDecreaseView;


public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private Context context;
    private List<Product> list;

    // 二级条目（商品）点击监听
    public interface OnProductClickListener {
        void onProductClick(int position, boolean isChecked);
    }

    private OnProductClickListener productClickListener;

    public void setOnProductClickListener(OnProductClickListener listener) {
        this.productClickListener = listener;
    }

    // 加减器发生变化的监听
    public interface OnAddDecreaseProductListener {
        void onChange(int position, int num);
    }

    private OnAddDecreaseProductListener productListener;

    public void setOnAddDecreaseProductListener(OnAddDecreaseProductListener listener) {
        this.productListener = listener;
    }

    public ProductAdapter(Context context, List<Product> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = View.inflate(context, R.layout.item_product, null);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final Product product = list.get(position);
        String images = product.getImages();
        // 商品图片
        if (!TextUtils.isEmpty(images)) {
            String[] strings = images.split("\\|");
            if (strings.length > 0) {
                Glide.with(context)
                        .load(StringUtils.https2Http(strings[0]))
                        .into(holder.imgProduct);
            }
        }

        holder.txtProductName.setText(product.getTitle());
        holder.txtSinglePriice.setText(String.valueOf(product.getPrice()));

        holder.advProduct.setNum(product.getNum());
        // 加减器添加点击事件
        holder.advProduct.setOnAddDecreaseClickListener(new AddDecreaseView.OnAddDecreaseClickListener() {
            @Override
            public void add(int num) {
                product.setNum(num);

                if (productListener != null) {
                    productListener.onChange(position, num);
                }
            }

            @Override
            public void decrease(int num) {
                product.setNum(num);
                if (productListener != null) {
                    productListener.onChange(position, num);
                }
            }
        });


        // 商品的复选框
        holder.cbProduct.setChecked(product.isChecked());
        holder.cbProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                product.setChecked(holder.cbProduct.isChecked());
                if (productClickListener != null) {
                    productClickListener.onProductClick(position, holder.cbProduct.isChecked());
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private CheckBox cbProduct;
        private ImageView imgProduct;
        private TextView txtProductName;
        private TextView txtSinglePriice;
        private AddDecreaseView advProduct;

        public ViewHolder(View itemView) {
            super(itemView);
            cbProduct = itemView.findViewById(R.id.cb_product);
            imgProduct = itemView.findViewById(R.id.img_product);
            txtSinglePriice = itemView.findViewById(R.id.txt_single_price);
            advProduct = itemView.findViewById(R.id.adv_product);
            txtProductName = itemView.findViewById(R.id.txt_product_name);
        }
    }
}
