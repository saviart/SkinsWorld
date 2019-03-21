package net.skinsworld.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import android.widget.ImageView;
import android.widget.TextView;

import net.skinsworld.R;
import net.skinsworld.model.Model_TransactionHistory;

import java.util.ArrayList;
import java.util.List;

public class Adapter_TransactionHistory extends RecyclerView.Adapter<Adapter_TransactionHistory.ItemViewHolder> {
    private List<Model_TransactionHistory> data = new ArrayList<>();
    private Context context;


    public Adapter_TransactionHistory(Context context, List<Model_TransactionHistory> transitemsList) {
        this.data = transitemsList;
        context = context;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.content_transactionhistory, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        Model_TransactionHistory item = data.get(position);
        holder.trans_username.setText(item.getTrans_username());
        holder.trans_skinsimage.setImageResource(item.getTrans_skinsimage());
        holder.trans_skinsname.setText(item.getTrans_skinsname());
        holder.trans_time.setText(item.getTrans_time());


    }

    @Override
    public int getItemCount() {
        return data.size();
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
