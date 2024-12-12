package com.sinhvien.venglorant;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class LearnVolcaFragment extends Fragment {

    RecyclerView recyclerView;
    private List<VolcabularyItem> volcabularyItemList;
    private VocalAdapter vocalAdapter;

    public LearnVolcaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview= inflater.inflate(R.layout.fragment_learn, container, false);
        volcabularyItemList=generateVocalItems();
        recyclerView=rootview.findViewById(R.id.recylerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        vocalAdapter=new VocalAdapter(volcabularyItemList);
        recyclerView.setAdapter(vocalAdapter);

        return rootview;
    }

    private List<VolcabularyItem> generateVocalItems(){
        List<VolcabularyItem> volcabularyItems= new ArrayList<>();
        volcabularyItems.add(new VolcabularyItem("Apple","aaa","Quả táo","/Noun/",R.drawable.vc_apple));
        volcabularyItems.add(new VolcabularyItem("Ant","aaa","Con kiến","/Noun/",R.drawable.ant));
        volcabularyItems.add(new VolcabularyItem("Angel","aaa","Thiên thần","Noun",R.drawable.angel));

        volcabularyItems.add(new VolcabularyItem("Bubble","aaa","Bong bóng","/Noun/",R.drawable.bubbles));
        volcabularyItems.add(new VolcabularyItem("Boss","aaa","Ông chủ","/Noun/",R.drawable.angry));
        volcabularyItems.add(new VolcabularyItem("Beat","aaa","Đánh bại","Noun",R.drawable.opponent));


        volcabularyItems.add(new VolcabularyItem("Cat","aaa","Con mèo","/Noun/",R.drawable.cat));
        volcabularyItems.add(new VolcabularyItem("Candy","aaa","Cây kẹo","/Noun/",R.drawable.sweets));
        volcabularyItems.add(new VolcabularyItem("Childish","aaa","Tính trẻ con","/Adjective/",R.drawable.children));

        return volcabularyItems;

    }


}