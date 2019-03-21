package net.skinsworld.fragment_mainscreen;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.annotation.NonNull;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import net.skinsworld.R;
import net.skinsworld.adapter.Adapter_RcvSkin;
import net.skinsworld.model.Model_ListItems;
import net.skinsworld.event.OnClickIteml;

import java.util.ArrayList;

public class Fragment_Skin extends Fragment implements OnClickIteml {

    View view;
    RecyclerView ListItems;
    ArrayList<Model_ListItems> arrayListItems;
    Adapter_RcvSkin adapter;
    private Dialog myDialog;
    ImageView ic_refresh;


    public Fragment_Skin() {
    }

    @NonNull
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceStage) {
        view = inflater.inflate(R.layout.fragment_skin, container, false);




        anhxa();


        adapter = new Adapter_RcvSkin(getActivity(), R.layout.content_listskins, arrayListItems,this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        ListItems.setLayoutManager(layoutManager);
        ListItems.setAdapter(adapter);


        // adapter.notifyDataSetChanged();

        myDialog =new Dialog(getContext());
//        ic_refresh.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Animation ic_refresh_rotate = AnimationUtils.loadAnimation(getActivity(),R.anim.ic_refresh_rotate);
//                ic_refresh.startAnimation(ic_refresh_rotate);
//            }
//        });


        return view;
    }








    private void anhxa() {
        ic_refresh = (ImageView) view.findViewById(R.id.ic_refresh);
        ListItems = (RecyclerView) view.findViewById(R.id.listviewitems);
        arrayListItems = new ArrayList<>();

        arrayListItems.add(new Model_ListItems(R.drawable.sample_csgo, "XM1014|Blue Spruce", "Minimal Wear", "400", "CS:GO"));
        arrayListItems.add(new Model_ListItems(R.drawable.sample_dota2, "Staff of Gun-Yu", "Immortal weapon", "300", "DOTA2"));
        arrayListItems.add(new Model_ListItems(R.drawable.sample_csgo, "Tec-9", "Feal Test", "1200", "CS:GO"));
        arrayListItems.add(new Model_ListItems(R.drawable.sample_dota2, "AKM", "Immortal weapon", "4030", "DOTA2"));
        arrayListItems.add(new Model_ListItems(R.drawable.sample_dota2, "AKM", "Facoty New", "1200", "DOTA2"));
        arrayListItems.add(new Model_ListItems(R.drawable.sample_csgo, "AKM", "Minimal Wear", "98", "CS:GO"));
        arrayListItems.add(new Model_ListItems(R.drawable.sample_csgo, "AK-47", "Minimal Wear", "4000", "CS:GO"));
        arrayListItems.add(new Model_ListItems(R.drawable.sample_csgo, "AKM", "Minimalc", "9700", "CS:GO"));
        arrayListItems.add(new Model_ListItems(R.drawable.sample_dota2, "P2000|Turf", "Minimal Wear", "400", "DOTA2"));
        arrayListItems.add(new Model_ListItems(R.drawable.sample_dota2, "AKM", "SMinimal", "400", "DOTA2"));
        arrayListItems.add(new Model_ListItems(R.drawable.sample_dota2, "SG 553|Phantom", "Facoty New", "400", "DOTA2"));
        arrayListItems.add(new Model_ListItems(R.drawable.sample_csgo, "AKM", "Facoty New", "352", "CS:GO"));
        arrayListItems.add(new Model_ListItems(R.drawable.sample_csgo, "AKM", "Facoty New", "120", "CS:GO"));
        arrayListItems.add(new Model_ListItems(R.drawable.sample_csgo, "AKM", "Facoty New", "34", "CS:GO"));
        arrayListItems.add(new Model_ListItems(R.drawable.sample_csgo, "AKM", "Facoty New", "4767", "CS:GO"));
        arrayListItems.add(new Model_ListItems(R.drawable.sample_csgo, "AKM", "Facoty New", "1235", "CS:GO"));
        arrayListItems.add(new Model_ListItems(R.drawable.sample_csgo, "AKM", "Facoty New", "789", "CS:GO"));
        arrayListItems.add(new Model_ListItems(R.drawable.sample_csgo, "AKM", "Facoty New", "909", "CS:GO"));


    }


    public void createPopup(Model_ListItems data) {
        myDialog.setContentView(R.layout.popup_buy_item);
        workingDialog(myDialog,data);
        myDialog.show();


    }

    private void workingDialog(final Dialog myDialog, Model_ListItems data) {
        ImageView popup_imgitem = (ImageView) myDialog.findViewById(R.id.popup_imgitem);
        popup_imgitem.setImageResource(data.getImgitem());


        ImageView btnClose = (ImageView) myDialog.findViewById(R.id.btn_close_popup);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }

        });

        Button popup_numbcoin = (Button) myDialog.findViewById(R.id.popup_numbpoint);
        popup_numbcoin.setText(data.getNumbcoin());

        Button popup_txtgame = (Button) myDialog.findViewById(R.id.popup_txtgame);
        popup_txtgame.setText(data.getTxtgame());

        TextView popup_nameitem = (TextView) myDialog.findViewById(R.id.popup_nameitem);
        popup_nameitem.setText(data.getNameitem());

        TextView popup_desitem = (TextView) myDialog.findViewById(R.id.popup_desitem);
        popup_desitem.setText(data.getDesitem());

        Animation animstar3 = (Animation) AnimationUtils.loadAnimation(getActivity(),R.anim.animstar3);
        popup_imgitem.startAnimation(animstar3);

       //Code tính năng của popup ở đây.
        Button  popup_btnconfirm = (Button) myDialog.findViewById(R.id.popup_btnconfirm);
        popup_btnconfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(),"Confirm",Toast.LENGTH_SHORT).show();
                myDialog.dismiss();
            }
        });
    }


    @Override
    public void onClickItem(Model_ListItems data) {
        createPopup(data);
    }


}




