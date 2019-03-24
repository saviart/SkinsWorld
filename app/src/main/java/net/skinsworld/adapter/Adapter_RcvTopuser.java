package net.skinsworld.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import net.skinsworld.R;

import net.skinsworld.model.Model_TopUser;

import java.util.ArrayList;
import java.util.List;

public class Adapter_RcvTopuser extends RecyclerView.Adapter<Adapter_RcvTopuser.ItemViewHolder> {
    private List<Model_TopUser> data = new ArrayList<>();
    private Context context;


    public Adapter_RcvTopuser(Context context, List<Model_TopUser> itemsList) {
        this.data = itemsList;
        context = context;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.content_topuser, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        Model_TopUser item = data.get(position);
        holder.img_avtuser.setImageResource(item.getImgavt());
        holder.user_name.setText(item.getUsername());
        holder.member_since.setText(item.getMembersince());
        holder.current_coins.setText(item.getCurrentcoins());


    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView img_avtuser;
        TextView user_name;
        TextView member_since;
       Button current_coins;


        public ItemViewHolder(View itemView) {
            super(itemView);

            img_avtuser = (ImageView) itemView.findViewById(R.id.img_avtuser) ;
            user_name = (TextView) itemView.findViewById(R.id.user_name);
            member_since = (TextView) itemView.findViewById(R.id.member_since);
            current_coins = (Button) itemView.findViewById(R.id.current_coins);


        }
    }
}
