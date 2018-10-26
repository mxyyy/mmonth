package adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwie.mmonth.R;

import java.util.List;

import entity.CategoryTwo;


public class RightCategoryAdapter extends RecyclerView.Adapter<RightCategoryAdapter.ViewHolder> {
    private static final String TAG = "CategoryAdapter";
    private Context context;
    private List<CategoryTwo> list;

    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }

    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public RightCategoryAdapter(Context context, List<CategoryTwo> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = View.inflate(context, R.layout.item_right_category, null);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.txtName.setText(list.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(v, position);
                }
            }
        });

        RecyclerView.LayoutManager manager = new GridLayoutManager(context, 3);
        holder.rvThird.setLayoutManager(manager);
        ThreeCategotyAdapter adapter = new ThreeCategotyAdapter(context, list.get(position).getList());
        holder.rvThird.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtName;
        private RecyclerView rvThird;

        public ViewHolder(View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txt_sencond_name);
            rvThird = itemView.findViewById(R.id.rv_third);
        }
    }
}
