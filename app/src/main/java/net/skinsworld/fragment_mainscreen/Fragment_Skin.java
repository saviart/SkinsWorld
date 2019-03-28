package net.skinsworld.fragment_mainscreen;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
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


import com.google.gson.Gson;

import com.squareup.picasso.Picasso;

import net.skinsworld.Activity_MainScreen;
import net.skinsworld.R;

import net.skinsworld.adapter.Adapter_RcvSkin;
import net.skinsworld.library.DatabaseHandler;
import net.skinsworld.library.GlobalVariables;
import net.skinsworld.event.OnClickIteml;
import net.skinsworld.library.UserFunctions;
import net.skinsworld.model.Item;

import net.skinsworld.model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;

public class Fragment_Skin extends Fragment implements OnClickIteml {
    int resId = R.anim.layout_ryc_animation;

    View view;
    RecyclerView ListItems;
    Adapter_RcvSkin adapter;
    private Dialog myDialog;
    private SwipeRefreshLayout swipe_Fragment_Skins;
    ImageView Autochess_Filter;
    ImageView Dota2_Filter;
    ImageView Csgo_Filter;
    ImageView Other_Filter;
    boolean isShowDota = true;
    boolean isShowCSGO = true;
    boolean isShowChess = true;
    boolean isShowOther = true;
    private ArrayList<Item> arrayListItems;
    ProgressDialog pd;
    String clickedItemID;
    Boolean buySuccess = false;

    public Fragment_Skin() {
    }

    @NonNull
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceStage) {
        view = inflater.inflate(R.layout.fragment_skin, container, false);
        anhxa();
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getContext(), resId);
        ListItems.setLayoutAnimation(animation);

        adapter = new Adapter_RcvSkin(getActivity(), R.layout.content_listskins, GlobalVariables.listItem, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        ListItems.setLayoutManager(layoutManager);
        ListItems.setAdapter(adapter);
        reset_all_filter();
        goFilter();
        adapter.notifyDataSetChanged();


        // adapter.notifyDataSetChanged();
        myDialog = new Dialog(getContext());


        //-----Nút lọc item autochess----------------------------------------------------------
        //  đổi image khi click
        //trả về false nếu ko lọc, trả vè true nếu lọc
        Autochess_Filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isShowChess = !isShowChess;
                if (isShowChess) {
                    Autochess_Filter.setImageResource(R.drawable.ic_normal_autochess);

                } else {
                    Autochess_Filter.setImageResource(R.drawable.ic_autochess_off);
                }
                goFilter();

            }
        });

        //      -----------------------------------------------------------------------------------------------------

        //-----Nút lọc item dota2----------------------------------------------------------
        //  đổi image khi click
        //trả về false nếu ko lọc, trả vè true nếu lọc
        Dota2_Filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isShowDota = !isShowDota;
                if (isShowDota) {
                    Dota2_Filter.setImageResource(R.drawable.ic_normal_dota2);

                } else {
                    Dota2_Filter.setImageResource(R.drawable.ic_dota2_off);
                }
                goFilter();
            }
        });

        //      -----------------------------------------------------------------------------------------------------

        //-----Nút lọc item csgo----------------------------------------------------------
        //  đổi image khi click
        //trả về false nếu ko lọc, trả vè true nếu lọc
        Csgo_Filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isShowCSGO = !isShowCSGO;
                if (isShowCSGO) {
                    Csgo_Filter.setImageResource(R.drawable.ic_normal_csgo);

                } else {
                    Csgo_Filter.setImageResource(R.drawable.ic_csgo_off);
                }
                goFilter();
            }
        });

        //      -----------------------------------------------------------------------------------------------------

        //-----Nút lọc item csgo----------------------------------------------------------
        //  đổi image khi click
        //trả về false nếu ko lọc, trả vè true nếu lọc
        Other_Filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isShowOther = !isShowOther;
                if (isShowOther) {
                    Other_Filter.setImageResource(R.drawable.ic_normal_other);

                } else {
                    Other_Filter.setImageResource(R.drawable.ic_other_off);
                }
                goFilter();
            }
        });

        //      -----------------------------------------------------------------------------------------------------


//        -----------Swipe to reload data here-----------------------------------

        swipe_Fragment_Skins = (SwipeRefreshLayout) view.findViewById(R.id.swipe_Fragment_Skins);
        swipe_Fragment_Skins.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipe_Fragment_Skins.setRefreshing(true);

                //refresh roi tat no di
                new loadItem().execute();

            }
        });
//        -----------------------------------------------------------------------


        return view;
    }

    private void reset_all_filter() {
        isShowChess = true;
        isShowCSGO = true;
        isShowDota = true;
        isShowOther = true;
        Other_Filter.setImageResource(R.drawable.ic_normal_other);
        Autochess_Filter.setImageResource(R.drawable.ic_normal_autochess);
        Csgo_Filter.setImageResource(R.drawable.ic_normal_csgo);
        Dota2_Filter.setImageResource(R.drawable.ic_normal_dota2);
    }

    private void goFilter() {
        ArrayList<Item> newList = new ArrayList<Item>();
        for (Item item : GlobalVariables.listItem) {
            if (isShowDota && item.getGame().contains("Dota") && item.getEnable().equals("1"))
                newList.add(item);
            if (isShowOther && item.getGame().contains("Other") && item.getEnable().equals("1"))
                newList.add(item);
            if (isShowCSGO && item.getGame().contains("CSGO") && item.getEnable().equals("1"))
                newList.add(item);
            if (isShowChess && item.getGame().contains("Auto") && item.getEnable().equals("1"))
                newList.add(item);
        }
        adapter.setData(newList);
        adapter.notifyDataSetChanged();
    }

    class loadItem extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            //fill data from
            swipe_Fragment_Skins.setRefreshing(false);
            reset_all_filter();
            ArrayList<Item> newList = new ArrayList<>();
            for (int i = 0; i < GlobalVariables.listItem.size(); i++) {
                if (GlobalVariables.listItem.get(i).getEnable().equals("1"))
                    newList.add(GlobalVariables.listItem.get(i));
            }
            adapter.setData(newList);
            adapter.notifyDataSetChanged();

        }

        @Override
        protected String doInBackground(String... strings) {
            UserFunctions uf = new UserFunctions();
            JSONObject itemObj = uf.getItem();
            JSONArray itemArray = null;
            try {
                itemArray = itemObj.getJSONArray("item");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            GlobalVariables.listItem = new ArrayList<Item>();
            Gson gson = new Gson();
            for (int i = 0; i < itemArray.length(); i++) {
                try {
                    GlobalVariables.listItem.add(gson.fromJson(itemArray.getJSONObject(i).toString(), Item.class));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }


    private void anhxa() {
        Autochess_Filter = (ImageView) view.findViewById(R.id.Autochess_Filter);
        Dota2_Filter = (ImageView) view.findViewById(R.id.Dota2_Filter);
        Csgo_Filter = (ImageView) view.findViewById(R.id.Csgo_Filter);
        Other_Filter = (ImageView) view.findViewById(R.id.Other_Filter);
        ListItems = (RecyclerView) view.findViewById(R.id.listviewitems);
        arrayListItems = new ArrayList<>();

        for (int i = 0; i < GlobalVariables.listItem.size(); i++) {
            if (GlobalVariables.listItem.get(i).getEnable().equals("1")) {
                arrayListItems.add(GlobalVariables.listItem.get(i));
            }
        }
    }


    public void createPopup(Item data) {
        if (Integer.parseInt(GlobalVariables.user.getCoins()) >= Integer.parseInt(data.getPrice())) {
            myDialog.setContentView(R.layout.popup_buy_item);
            myDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            workingDialog(myDialog, data, true);
            myDialog.show();
        } else {
            myDialog.setContentView(R.layout.popup_not_enoughcoin);
            myDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            workingDialog(myDialog, data, false);
            myDialog.show();
        }


    }

    private void workingDialog(final Dialog myDialog, final Item data, Boolean isEnoughCoin) {
        if (isEnoughCoin) {
            ImageView popup_imgitem = (ImageView) myDialog.findViewById(R.id.popup_imgitem);
            //popup_imgitem.setImageResource(data.getImgitem());
            Picasso.with(getActivity().getApplicationContext()).load(data.getImageURL()).into(popup_imgitem);


            ImageView btnClose = (ImageView) myDialog.findViewById(R.id.btn_close_popup);
            btnClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myDialog.dismiss();
                }

            });

            Button popup_numbcoin = (Button) myDialog.findViewById(R.id.popup_numbcoins);
            popup_numbcoin.setText(data.getPrice());

            Button popup_txtgame = (Button) myDialog.findViewById(R.id.popup_txtgame);
            popup_txtgame.setText(data.getGame());

            TextView popup_nameitem = (TextView) myDialog.findViewById(R.id.popup_nameitem);
            popup_nameitem.setText(data.getName());

            TextView popup_desitem = (TextView) myDialog.findViewById(R.id.popup_desitem);
            popup_desitem.setText(data.getDescription());

            Animation animstar3 = (Animation) AnimationUtils.loadAnimation(getActivity(), R.anim.anim_zoomin_out);
            popup_imgitem.startAnimation(animstar3);

            //Code tính năng của popup ở đây.
            Button popup_btnconfirm = (Button) myDialog.findViewById(R.id.popup_btnconfirm);
            popup_btnconfirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(getActivity().getApplicationContext(), data.getID(), Toast.LENGTH_SHORT).show();
                    clickedItemID = data.getID();
                    new buyItem().execute();
                    myDialog.dismiss();
                }
            });
        } else {
            Button popup_btnconfirm = (Button) myDialog.findViewById(R.id.btn_getmore_popuperror);
            popup_btnconfirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Activity_MainScreen.main.selectIndex(2);
                    myDialog.dismiss();
                }
            });
        }

    }

    class buyItem extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(getActivity());
            pd.setMessage("Loading...please wait !");
            pd.setCancelable(false);
            pd.setIndeterminate(false);
            pd.show();
        }

        @Override
        protected void onPostExecute(String s) {
            pd.cancel();
            if (buySuccess) {
                //mua thanh cong, bat len thong bao da mua thanh cong
                final BottomSheetDialog dialog_buysuccess = new BottomSheetDialog(getContext());
                dialog_buysuccess.setContentView(R.layout.buy_success);
                dialog_buysuccess.getWindow().findViewById(R.id.design_bottom_sheet).setBackgroundResource(android.R.color.transparent);
                // set the custom dialog components - text, image and button
                dialog_buysuccess.show();

            } else {
                //mua that bai, co the do loi ket noi hoac thieu coin, nem ra thong bao o day
               // Toast.makeText(getActivity(), "Error occurred ! Please try again !", Toast.LENGTH_SHORT).show();
                final BottomSheetDialog dialog_buyerror = new BottomSheetDialog(getContext());
                dialog_buyerror.setContentView(R.layout.buy_error);
                dialog_buyerror.getWindow().findViewById(R.id.design_bottom_sheet).setBackgroundResource(android.R.color.transparent);
                // set the custom dialog components - text, image and button
                dialog_buyerror.show();
            }
        }

        @Override
        protected String doInBackground(String... strings) {
            UserFunctions uf = new UserFunctions();
            DatabaseHandler db = new DatabaseHandler(getActivity());
            JSONObject json_registered = uf.buyItem(clickedItemID, GlobalVariables.user.getUserID());
            try {
                //mua thanh cong, load lai order, load lai user
                Gson gson = new Gson();
                GlobalVariables.user = gson.fromJson(json_registered.getJSONArray("user").getJSONObject(0).toString(),User.class);
                db.addUser(GlobalVariables.user);
                buySuccess = true;
            } catch (Exception ee) {
                buySuccess = false;
            }
            return null;
        }
    }


    @Override
    public void onClickItem(Item data) {
        createPopup(data);
    }


}




