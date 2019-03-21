package net.skinsworld.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import net.skinsworld.R;
import net.skinsworld.model.Model_LastEarning;

import java.util.ArrayList;
import java.util.List;

public class Adapter_RcvEarn extends RecyclerView.Adapter<Adapter_RcvEarn.ItemViewHolder> {
    private List<Model_LastEarning> data = new ArrayList<>();
    private Context context;


    public Adapter_RcvEarn(Context context, List<Model_LastEarning> itemsList) {
        this.data = itemsList;
        context = context;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.content_lastearning, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        Model_LastEarning item = data.get(position);
        holder.name_offer.setText(item.getName_offer());
        holder.time_complete.setText(item.getTime_complete());
        holder.numb_earn_point.setText(item.getNumb_earn_point());


    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView name_offer;
        TextView time_complete;
        TextView numb_earn_point;


        public ItemViewHolder(View itemView) {
            super(itemView);


            name_offer = (TextView) itemView.findViewById(R.id.name_offer);
            time_complete = (TextView) itemView.findViewById(R.id.time_complete);
            numb_earn_point = (TextView) itemView.findViewById(R.id.numb_earn_point);


        }
    }
}
