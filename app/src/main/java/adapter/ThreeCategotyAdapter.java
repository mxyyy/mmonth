package adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwie.mmonth.R;


import java.util.List;

import entity.CategoryThree;


public class ThreeCategotyAdapter extends RecyclerView.Adapter<ThreeCategotyAdapter.ViewHolder> {

    private Context context;
    private List<CategoryThree> list;

    public ThreeCategotyAdapter(Context context, List<CategoryThree> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = View.inflate(context, R.layout.item_category_three, null);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getIcon()).into(holder.imgLogo);
        holder.txtTitle.setText(list.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgLogo;
        private TextView txtTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            imgLogo = itemView.findViewById(R.id.img_category);
            txtTitle = itemView.findViewById(R.id.txt_three_category);
        }
    }
}
