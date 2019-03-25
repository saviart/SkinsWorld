package net.skinsworld.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import net.skinsworld.R;
import net.skinsworld.event.OnClickIteml;
import net.skinsworld.model.Item;

import java.util.ArrayList;
import java.util.List;


public class Adapter_RcvSkin extends RecyclerView.Adapter<Adapter_RcvSkin.ItemViewHolder> {
    private ArrayList<Item> data = new ArrayList<Item>();
    private Context context;
    private int layout;
    OnClickIteml onClickIteml;


    public Adapter_RcvSkin(Context context, int layout, ArrayList<Item> itemsList, OnClickIteml OnClickIteml) {
        this.data = itemsList;
        this.layout = layout;
        this.context = context;
        onClickIteml = OnClickIteml;
    }

    public ArrayList<Item> getData() {
        return data;
    }

    public void setData(ArrayList<Item> data) {
        this.data = data;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(layout, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {

        Item item = data.get(position);
        holder.nameitem.setText(item.getName());
        holder.desitem.setText(item.getDescription());
        holder.numbcoin.setText(item.getPrice());
        holder.txtgame.setText(item.getGame());
        //holder.imgitem.setImageResource(item.getImgitem());
        Picasso.with(context).load(item.getImageURL()).into(holder.imgitem);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class ItemViewHolder extends RecyclerView.ViewHolder {


        TextView txtgame;
        TextView nameitem;
        TextView desitem;
        TextView numbcoin;
        ImageView imgitem;

        public ItemViewHolder(View itemView) {
            super(itemView);
            nameitem = (TextView) itemView.findViewById(R.id.nameitem);
            desitem = (TextView) itemView.findViewById(R.id.desitem);
            numbcoin = (TextView) itemView.findViewById(R.id.numbcoin);
            txtgame = (TextView) itemView.findViewById(R.id.txtgame);
            imgitem = (ImageView) itemView.findViewById(R.id.imgitem);

            // click(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Item itemCurentClick = data.get(getAdapterPosition());
                    onClickIteml.onClickItem(itemCurentClick);
                }
            });

        }
    }

    //private void click(final View itemView) {

    // }


}
