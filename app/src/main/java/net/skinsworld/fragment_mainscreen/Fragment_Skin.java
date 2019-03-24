package net.skinsworld.fragment_mainscreen;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.annotation.NonNull;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
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
    int resId = R.anim.layout_ryc_animation;

    View view;
    RecyclerView ListItems;
    ArrayList<Model_ListItems> arrayListItems;
    Adapter_RcvSkin adapter;
    private Dialog myDialog;
    ImageView ic_refresh;
    private SwipeRefreshLayout swipe_Fragment_Skins;
    ImageView Autochess_Filter;
    boolean isAutochess_Filter_Pressed=false;
    ImageView Dota2_Filter;
    boolean isDota2_Filter_Pressed=false;
    ImageView Csgo_Filter;
    boolean isCsgo_Filter_Pressed=false;
    ImageView Other_Filter;
    boolean isOther_Filter_Pressed=false;

    public Fragment_Skin() {
    }

    @NonNull
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceStage) {
        view = inflater.inflate(R.layout.fragment_skin, container, false);
        anhxa();
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getContext(), resId);
        ListItems.setLayoutAnimation(animation);

        adapter = new Adapter_RcvSkin(getActivity(), R.layout.content_listskins, arrayListItems,this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        ListItems.setLayoutManager(layoutManager);
        ListItems.setAdapter(adapter);


        // adapter.notifyDataSetChanged();
        myDialog =new Dialog(getContext());




        //-----Nút lọc item autochess----------------------------------------------------------
        //  đổi image khi click
        //trả về false nếu ko lọc, trả vè true nếu lọc
        Autochess_Filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mặc định false là không lọc
                if(isAutochess_Filter_Pressed){
                    Autochess_Filter.setImageResource(R.drawable.ic_normal_autochess);
                    isAutochess_Filter_Pressed=!isAutochess_Filter_Pressed;
                    Toast.makeText(getActivity().getApplicationContext(),""+ isAutochess_Filter_Pressed,Toast.LENGTH_SHORT).show();
                }else{
                    //click vào trả về true và lọc item
                    Autochess_Filter.setImageResource(R.drawable.ic_autochess_off);
                    isAutochess_Filter_Pressed=!isAutochess_Filter_Pressed;
                    Toast.makeText(getActivity().getApplicationContext(),""+ isAutochess_Filter_Pressed,Toast.LENGTH_SHORT).show();
                }
            }
        });

  //      -----------------------------------------------------------------------------------------------------




        //-----Nút lọc item dota2----------------------------------------------------------
        //  đổi image khi click
        //trả về false nếu ko lọc, trả vè true nếu lọc
        Dota2_Filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mặc định false là không lọc
                if(isDota2_Filter_Pressed){
                    Dota2_Filter.setImageResource(R.drawable.ic_normal_dota2);
                    isDota2_Filter_Pressed=!isDota2_Filter_Pressed;
                    Toast.makeText(getActivity().getApplicationContext(),""+ isDota2_Filter_Pressed,Toast.LENGTH_SHORT).show();
                }else{
                    //click vào trả về true và lọc item
                    Dota2_Filter.setImageResource(R.drawable.ic_dota2_off);
                    isDota2_Filter_Pressed=!isDota2_Filter_Pressed;
                    Toast.makeText(getActivity().getApplicationContext(),""+ isDota2_Filter_Pressed,Toast.LENGTH_SHORT).show();
                }
            }
        });

        //      -----------------------------------------------------------------------------------------------------





        //-----Nút lọc item csgo----------------------------------------------------------
        //  đổi image khi click
        //trả về false nếu ko lọc, trả vè true nếu lọc
        Csgo_Filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mặc định false là không lọc
                if(isCsgo_Filter_Pressed){
                    Csgo_Filter.setImageResource(R.drawable.ic_normal_csgo);
                    isCsgo_Filter_Pressed=!isCsgo_Filter_Pressed;
                    Toast.makeText(getActivity().getApplicationContext(),""+ isCsgo_Filter_Pressed,Toast.LENGTH_SHORT).show();
                }else{
                    //click vào trả về true và lọc item
                    Csgo_Filter.setImageResource(R.drawable.ic_csgo_off);
                    isCsgo_Filter_Pressed=!isCsgo_Filter_Pressed;
                    Toast.makeText(getActivity().getApplicationContext(),""+ isCsgo_Filter_Pressed,Toast.LENGTH_SHORT).show();
                }
            }
        });

        //      -----------------------------------------------------------------------------------------------------





        //-----Nút lọc item csgo----------------------------------------------------------
        //  đổi image khi click
        //trả về false nếu ko lọc, trả vè true nếu lọc
        Other_Filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mặc định false là không lọc
                if(isOther_Filter_Pressed){
                    Other_Filter.setImageResource(R.drawable.ic_normal_other);
                    isOther_Filter_Pressed=!isOther_Filter_Pressed;
                    Toast.makeText(getActivity().getApplicationContext(),""+ isCsgo_Filter_Pressed,Toast.LENGTH_SHORT).show();
                }else{
                    //click vào trả về true và lọc item
                    Other_Filter.setImageResource(R.drawable.ic_other_off);
                    isOther_Filter_Pressed=!isOther_Filter_Pressed;
                    Toast.makeText(getActivity().getApplicationContext(),""+ isCsgo_Filter_Pressed,Toast.LENGTH_SHORT).show();
                }
            }
        });

        //      -----------------------------------------------------------------------------------------------------




        //--------Nút reload Listskins-------------------------------------------------------
//        ic_refresh.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Animation ic_refresh_rotate = AnimationUtils.loadAnimation(getActivity(),R.anim.ic_refresh_rotate);
//                ic_refresh.startAnimation(ic_refresh_rotate);
//            }
//        });
//       ------------------------------------------------------------------------------------






//        -----------Swipe to reload data here-----------------------------------

        swipe_Fragment_Skins = (SwipeRefreshLayout) view.findViewById(R.id.swipe_Fragment_Skins);
        swipe_Fragment_Skins.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipe_Fragment_Skins.setRefreshing(true);
            }
        });
//        -----------------------------------------------------------------------


        return view;
    }






    private void anhxa() {
        Autochess_Filter = (ImageView) view.findViewById(R.id.Autochess_Filter) ;
        Dota2_Filter =(ImageView) view.findViewById(R.id.Dota2_Filter) ;
        Csgo_Filter = (ImageView) view.findViewById(R.id.Csgo_Filter) ;
        Other_Filter = (ImageView) view.findViewById(R.id.Other_Filter);
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
        myDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
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

        Button popup_numbcoin = (Button) myDialog.findViewById(R.id.popup_numbcoins);
        popup_numbcoin.setText(data.getNumbcoin());

        Button popup_txtgame = (Button) myDialog.findViewById(R.id.popup_txtgame);
        popup_txtgame.setText(data.getTxtgame());

        TextView popup_nameitem = (TextView) myDialog.findViewById(R.id.popup_nameitem);
        popup_nameitem.setText(data.getNameitem());

        TextView popup_desitem = (TextView) myDialog.findViewById(R.id.popup_desitem);
        popup_desitem.setText(data.getDesitem());

        Animation animstar3 = (Animation) AnimationUtils.loadAnimation(getActivity(),R.anim.anim_zoomin_out);
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




