package net.skinsworld.fragment_mainscreen;

import android.app.Dialog;
import android.content.Intent;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import net.skinsworld.Activity_MainScreen;
import net.skinsworld.Activity_Signup;
import net.skinsworld.R;
import net.skinsworld.adapter.AdapterRcvProfile;

import net.skinsworld.library.GlobalVariables;
import net.skinsworld.model.Model_ListItems;
import net.skinsworld.model.Model_Profile;

import java.util.ArrayList;

public class Fragment_Profile extends Fragment {
    View view;
    RecyclerView ListItems;
    ArrayList<Model_Profile> arrayListItems;
    AdapterRcvProfile adapter;
    Button btn_getmore;
    Button btn_invite;
    ImageView btn_dailypoint;
    Button btn_share;

    ImageView ivAvatar;
    TextView tvUsername;
    TextView tvCoins;
    TextView tvJoinDate;
    EditText etTradeURL;
    TextView invitecode;
    private Dialog inviteDialog;
    public Fragment_Profile() {
    }

    @NonNull
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceStage) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        anhxa();
        adapter = new AdapterRcvProfile(getActivity(), arrayListItems);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        ListItems.setLayoutManager(layoutManager);
        ListItems.setAdapter(adapter);


        // adapter.notifyDataSetChanged();


        clickbtn_getmore();
        clickbtn_invite();
        clickbtn_share();
        clickbtn_dailypoint();


        //set toan bo thong tin user vao giao dien
        setUserProfile();

        return view;
    }

    private void setUserProfile(){
        //avatar
        Picasso.with(getActivity().getApplicationContext()).load(GlobalVariables.user.getAvatar()).into(ivAvatar);
        //name
        //created date
        //coins
        //trade url
        tvUsername.setText(GlobalVariables.user.getPersonaName());
        tvCoins.setText(GlobalVariables.user.getCoins());
        tvJoinDate.setText(GlobalVariables.user.getCreated_date());
        etTradeURL.setText(GlobalVariables.user.getTradeURL());
    }


    private void clickbtn_dailypoint() {
        btn_dailypoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(),"đã bấm daily gift",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void clickbtn_share() {
        btn_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(),"đã bấm share",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void clickbtn_invite() {
        btn_invite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(),"đã bấm invite",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void  clickbtn_getmore() {
        btn_getmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragment();
            }
        });
    }

    private void changeFragment() {
//        FragmentManager manager = getFragmentManager();
//        FragmentTransaction transaction = manager.beginTransaction();
//        transaction.replace(R.id.fragment_one, AFragment.newInstance());
//        transaction.commit();
      Activity_MainScreen.main.selectIndex(2);


    }


    private void anhxa() {
        btn_share = (Button) view.findViewById(R.id.btn_share);
        btn_dailypoint = (ImageView) view.findViewById(R.id.btn_dailypoint);
        btn_invite = (Button) view.findViewById(R.id.btn_invite);
        btn_getmore = (Button) view.findViewById(R.id.btn_getmore);
        ListItems = (RecyclerView) view.findViewById(R.id.rvtransitems);
        arrayListItems = new ArrayList<>();
        arrayListItems.add(new Model_Profile(R.drawable.sample_csgo, "AKM", "Facoty New", "1235", "PENDING"));
        arrayListItems.add(new Model_Profile(R.drawable.sample_dota2, "AKM", "Facoty New", "789", "SUCCESS"));
        arrayListItems.add(new Model_Profile(R.drawable.sample_dota2, "AKM", "Facoty New", "9092", "SUCCESS"));
        arrayListItems.add(new Model_Profile(R.drawable.sample_csgo, "AKM", "Facoty New", "1235", "SUCCESS"));
        arrayListItems.add(new Model_Profile(R.drawable.sample_csgo, "AKM", "Facoty New", "789", "SUCCESS"));
        arrayListItems.add(new Model_Profile(R.drawable.sample_csgo, "AKM", "Facoty New", "9092", "SUCCESS"));

        ivAvatar = view.findViewById(R.id.ivAvatar);
        tvUsername = view.findViewById(R.id.tvUsername);
        tvCoins = view.findViewById(R.id.tvCoins);
        tvJoinDate = view.findViewById(R.id.tvJoinDate);
        etTradeURL = view.findViewById(R.id.etTradeURL);
        invitecode = view.findViewById(R.id.popup_invitecode);

        //Thêm item từ database code ở đây


    }

    }





