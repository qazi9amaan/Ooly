package com.oolysolutions.oolys.Frags.Home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oolysolutions.oolys.Act.Drop.ChooseDropAddressActivity;
import com.oolysolutions.oolys.Act.Pickup.ChoosePickUpAddressActivity;
import com.oolysolutions.oolys.R;

public class HomeFragment extends Fragment {

    View rootView;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("ResourceType")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_home, container, false);

        try {
            getActivity().getWindow().setStatusBarColor(Color.parseColor("#F7D7FF"));
        }catch (Exception e){

        }


        rootView.findViewById(R.id.pickUpAddressButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ChoosePickUpAddressActivity.class));
            }
        });

        rootView.findViewById(R.id.dropAddressButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ChooseDropAddressActivity.class));
            }
        });


        return rootView;
    }
}