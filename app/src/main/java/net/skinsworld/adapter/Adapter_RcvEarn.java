package net.skinsworld.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import net.skinsworld.R;
import net.skinsworld.model.History;

import java.util.ArrayList;
import java.util.List;

public class Adapter_RcvEarn extends RecyclerView.Adapter<Adapter_RcvEarn.ItemViewHolder> {
    private List<History> data = new ArrayList<>();
    private Context context;


    public Adapter_RcvEarn(Context context, List<History> itemsList) {
        this.data = itemsList;
        this.context = context;
    }

    public List<History> getData() {
        return data;
    }

    public void setData(List<History> data) {
        this.data = data;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.content_lastearning, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        History item = data.get(position);
        if(item.getOfferName().matches("^[0-9]*$"))
        {
            Long abc = Long.parseLong(item.getOfferName());
            Long def = Long.parseLong("76561197960265728");
            holder.name_offer.setText("Input invitation code : SW"+(abc-def));
        }
        else
        {
            holder.name_offer.setText(item.getOfferName());
        }

        holder.time_complete.setText(item.getTime());
        holder.numb_earn_coins.setText(item.getCoins());
    }

    @Override
    public int getItemCount() {
        try
        {
            return data.size();
        }catch (Exception ee){
            return 0;
        }

    }


    public class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView name_offer;
        TextView time_complete;
        TextView numb_earn_coins;


        public ItemViewHolder(View itemView) {
            super(itemView);


            name_offer = (TextView) itemView.findViewById(R.id.name_offer);
            time_complete = (TextView) itemView.findViewById(R.id.time_complete);
            numb_earn_coins = (TextView) itemView.findViewById(R.id.numb_earn_coins);


        }
    }
}
