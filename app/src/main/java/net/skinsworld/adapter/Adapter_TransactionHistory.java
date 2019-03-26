package net.skinsworld.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import net.skinsworld.R;
import net.skinsworld.model.Recent;

import java.util.ArrayList;
import java.util.List;

public class Adapter_TransactionHistory extends RecyclerView.Adapter<Adapter_TransactionHistory.ItemViewHolder> {
    private List<Recent> data = new ArrayList<>();
    private Context context;


    public Adapter_TransactionHistory(Context context, List<Recent> transitemsList) {
        this.data = transitemsList;
        this.context = context;
    }

    public List<Recent> getData() {
        return data;
    }

    public void setData(List<Recent> data) {
        this.data = data;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.content_transactionhistory, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        Recent item = data.get(position);
        holder.trans_username.setText(item.getPersonaName());

        holder.trans_skinsname.setText(item.getItemName());
        //holder.trans_time.setText(item.getTimeDiff() + " minutes ago");
        String display = "";
        int timeDiff = Integer.parseInt(item.getTimeDiff());
        int day = timeDiff / 60 / 24;
        if (day >= 1) {
            display += day + " D - ";
            timeDiff = timeDiff - day * 60 * 24;
            //sang gio
            display += timeDiff / 60 + " H - " + timeDiff % 60 + " M ago";
        } else {
            //sang gio
            display += timeDiff / 60 + " H - " + timeDiff % 60 + " M ago";
        }
        holder.trans_time.setText(display);
        Picasso.with(context).load(item.getImageURL()).into(holder.trans_skinsimage);


    }

    @Override
    public int getItemCount() {
        try {
            return data.size();
        } catch (Exception ee) {
            return 0;
        }
    }


    public class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView trans_skinsimage;
        TextView trans_username;
        TextView trans_skinsname;
        TextView trans_time;


        public ItemViewHolder(View itemView) {
            super(itemView);

            trans_skinsimage = (ImageView) itemView.findViewById(R.id.trans_skinsimage);
            trans_username = (TextView) itemView.findViewById(R.id.trans_username);
            trans_skinsname = (TextView) itemView.findViewById(R.id.trans_skinsname);
            trans_time = (TextView) itemView.findViewById(R.id.trans_time);


        }
    }
}
