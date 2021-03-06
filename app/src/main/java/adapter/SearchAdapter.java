package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gietb.banhangkhoapham.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import models.Product;
import models.SearchProduct;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder>{
    private Context context;
    private ArrayList<SearchProduct> ds;
    private IClickListener clickListener;

    public SearchAdapter(Context context, ArrayList<SearchProduct> ds) {
        this.context = context;
        this.ds = ds;
    }

    public void setClickListener(IClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_search, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SearchProduct product = ds.get(position);
        holder.tvName.setText(product.getName());
        holder.tvPrice.setText(String.valueOf(product.getPrice()).concat(" $"));
        holder.tvColor.setText(product.getColor());
        holder.tvMaterial.setText(product.getMaterial());
        holder.tvColor.setText(product.getColor());
        if (product.getIsNew() == 0) {
            holder.tvState.setText("Empty");
        } else {
            holder.tvState.setText("");
        }
        Picasso.with(context).load(product.getImages()[0]).into(holder.imgSearch);
    }

    @Override
    public int getItemCount() {
        return ds.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imgSearch;
        TextView tvName, tvPrice, tvMaterial, tvColor, tvState;

        ViewHolder(View itemView) {
            super(itemView);
            imgSearch = itemView.findViewById(R.id.searchImage);
            tvName = itemView.findViewById(R.id.nameText);
            tvPrice = itemView.findViewById(R.id.priceText);
            tvMaterial = itemView.findViewById(R.id.materialText);
            tvColor = itemView.findViewById(R.id.colorText);
            tvState = itemView.findViewById(R.id.tvState);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) {
                clickListener.itemClick(view, getPosition());
            }
        }
    }
}
