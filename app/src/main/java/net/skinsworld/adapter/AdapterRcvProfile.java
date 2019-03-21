package net.skinsworld.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import net.skinsworld.R;
import net.skinsworld.model.Model_Profile;
import java.util.ArrayList;
import java.util.List;


public class AdapterRcvProfile extends RecyclerView.Adapter<AdapterRcvProfile.ItemViewHolder> {
    private List<Model_Profile> data = new ArrayList<>();
    private Context context;


    public AdapterRcvProfile(Context context, List<Model_Profile> itemsList) {
        this.data = itemsList;
        context = context;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.content_lastoder, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        Model_Profile item = data.get(position);
        holder.name_trans_item.setText(item.getName_strans_item());
        holder.des_trans_item.setText(item.getDes_trans_item());
        holder.numb_trans_point.setText(item.getNumb_trans_coin());
        holder.trans_status.setText(item.getTxt_trans_game());
        holder.img_trans_item.setImageResource(item.getImg_trans_item());


    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView trans_status;
        TextView name_trans_item ;
        TextView des_trans_item;
        TextView numb_trans_point;
        ImageView img_trans_item ;

        public ItemViewHolder(View itemView) {
            super(itemView);

            img_trans_item = (ImageView) itemView.findViewById(R.id.img_trans_items);
            name_trans_item = (TextView) itemView.findViewById(R.id.name_trans_item);
            des_trans_item = (TextView) itemView.findViewById(R.id.des_trans_item);
            numb_trans_point = (TextView)itemView .findViewById(R.id.numb_trans_point);
            trans_status = (TextView)itemView .findViewById(R.id.trans_status);


        }
    }
}
