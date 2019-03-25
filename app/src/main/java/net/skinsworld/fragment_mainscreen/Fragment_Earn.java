package net.skinsworld.fragment_mainscreen;

import android.content.Context;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import  android.view.View;
import  android.view.ViewGroup;
import android.support.annotation.NonNull;
import android.widget.ImageView;
import android.widget.Toast;

import net.skinsworld.R;
import net.skinsworld.adapter.Adapter_RcvEarn;
import net.skinsworld.model.Model_LastEarning;

import java.util.ArrayList;

public class Fragment_Earn extends Fragment {
    View view;
    ImageView fyberwall;
    ImageView tapjoywall;
    ImageView sonicwall;
    ImageView fybervideo;
    ImageView tapjoyvideo;
    ImageView sonicvideo;
    ImageView btn_refresh_lastearning;


    RecyclerView ListItems;
    ArrayList<Model_LastEarning> arrayListItems;
    Adapter_RcvEarn adapter;






    public Fragment_Earn(Context context){
      //  Toast.makeText(context,"sdfgsdfgdsfg",Toast.LENGTH_LONG).show();
    }

    @NonNull
    @Override
    public  View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceStage) {
        view = inflater.inflate(R.layout.fragment_earn,container,false);
        anhxa();
        adapter = new Adapter_RcvEarn(getActivity(), arrayListItems);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        ListItems.setLayoutManager(layoutManager);
        ListItems.setAdapter(adapter);





        fyberwall = (ImageView) view.findViewById(R.id.fyberwall);
        tapjoywall = (ImageView) view.findViewById(R.id.tapjoywall);
        sonicwall = (ImageView) view.findViewById(R.id.sonicwall);
        fybervideo = (ImageView) view.findViewById(R.id.fybervideo);
        tapjoyvideo = (ImageView) view.findViewById(R.id.tapjoyvideo);
        sonicvideo = (ImageView) view.findViewById(R.id.sonicvideo);


        click_btn_refresh_lastearning();
        click_sonicvideo();
        click_tapjoyvideo();
        click_fybervideo();
        click_sonicwall();
        click_tapjoywall();
        click_fyberwall();

        return view;
    }

    private void click_btn_refresh_lastearning() {
        btn_refresh_lastearning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(),"đã bấm Refesh Last Earning",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void click_fyberwall() {
        fyberwall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(),"đã bấm Fyber wall",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void click_tapjoywall() {
        tapjoywall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(),"đã bấm tapjoy wall",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void click_sonicwall() {
        sonicwall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(),"đã bấm sonic wall",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void click_fybervideo() {
        fybervideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(),"đã bấm Fyber video",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void click_tapjoyvideo() {
        tapjoyvideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(),"đã bấm Tapjoy video",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void click_sonicvideo() {
        sonicvideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(),"đã bấm Sonic video",Toast.LENGTH_SHORT).show();
            }
        });
    }




    private void anhxa() {
        btn_refresh_lastearning = (ImageView) view.findViewById(R.id.btn_refresh_lastearning);
        ListItems = (RecyclerView) view.findViewById(R.id.rvearnitems);
        arrayListItems = new ArrayList<>();
        arrayListItems.add(new Model_LastEarning("Fyber wall","2019-03-15 19:02:32","50"));
        arrayListItems.add(new Model_LastEarning("Tapjoy wall","2019-03-14 05:02:32","120"));
        arrayListItems.add(new Model_LastEarning("Tapjoy wall","2019-03-23 12:02:32","530"));
        arrayListItems.add(new Model_LastEarning("Tapjoy wall","2019-03-03 10:02:32","630"));
        arrayListItems.add(new Model_LastEarning("Tapjoy wall","2019-01-18 17:02:32","1100"));
        arrayListItems.add(new Model_LastEarning("Fyber wall","2019-03-19 15:02:32","330"));



        //Thêm item từ database code ở đây, còn anh làm thế nào gọi được database thì kệ mẹ anh =))))))


    }

}
