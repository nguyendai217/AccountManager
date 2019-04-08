package com.example.accountmanager.adapter;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.accountmanager.R;
import com.example.accountmanager.model.Category;
import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    ArrayList<Category>arrCategory;
    public CategoryAdapter( ArrayList<Category>arrCategory){
        this.arrCategory= arrCategory;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater= LayoutInflater.from(viewGroup.getContext());
        View view= inflater.inflate(R.layout.item_category,viewGroup,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Category ct = arrCategory.get(i);
        viewHolder.tvCategory.setText(ct.getCategoryname());
    }
    @Override
    public int getItemCount() {
        return arrCategory.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvCategory;
        public ViewHolder( View itemView) {
            super(itemView);
            tvCategory=itemView.findViewById(R.id.tv_category);
        }
    }





}
