package net.skinsworld.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import net.skinsworld.R;
import net.skinsworld.model.TopUser;


import java.util.ArrayList;
import java.util.List;

public class Adapter_RcvTopuser extends RecyclerView.Adapter<Adapter_RcvTopuser.ItemViewHolder> {
    private List<TopUser> data = new ArrayList<>();
    private Context context;


    public Adapter_RcvTopuser(Context context, List<TopUser> itemsList) {
        this.data = itemsList;
        this.context = context;
    }

    public List<TopUser> getData() {
        return data;
    }

    public void setData(List<TopUser> data) {
        this.data = data;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.content_topuser, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        TopUser item = data.get(position);

        Picasso.with(context).load(item.getImageURL()).into(holder.img_avtuser);
        holder.user_name.setText(item.getPersonaName());
        holder.current_coins.setText(item.getTotalCoins());
//        Long abc = Long.parseLong(item.getOfferName());
//        Long def = Long.parseLong("76561197960265728");
//        holder.name_offer.setText("Input invitation code : SW"+(abc-def));
        // steam id 64 : item.getInvitationCode()
        Long steamid64 = Long.parseLong(item.getInvitationCode());
        Long def = Long.parseLong("76561197960265728");
        holder.member_since.setText("Invitation Code : SW"+(steamid64-def));
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
