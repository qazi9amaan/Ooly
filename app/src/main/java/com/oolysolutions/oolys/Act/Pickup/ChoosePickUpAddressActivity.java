package com.oolysolutions.oolys.Act.Pickup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.oolysolutions.oolys.Act.AddAddress.AddAddressDetailsActivity;
import com.oolysolutions.oolys.Act.AddAddress.AddressDetails;
import com.oolysolutions.oolys.Act.AddAddress.AddressesAdapter;
import com.oolysolutions.oolys.Act.NewOrder.NewOrderActivity;
import com.oolysolutions.oolys.R;

import java.util.List;

public class ChoosePickUpAddressActivity extends AppCompatActivity implements AddressesAdapter.onClickListner{

    RecyclerView recyclerview;
    LinearLayout chooseFromAnyWhere;
    Button chooseBtn;
    View mView;
    AddressesAdapter addressesAdapter;

    public static AddressDetails selectedDetails = null;
    private List<AddressDetails> mArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_pick_up_address);

        try {
            getWindow().setStatusBarColor(Color.parseColor("#F7D7FF"));
        }catch (Exception e){

        }

        recyclerview = findViewById(R.id.recyclerview);
        chooseFromAnyWhere = findViewById(R.id.chooseFromAnyWhere);
        chooseBtn = findViewById(R.id.chooseBtn);


        chooseFromAnyWhere.setOnClickListener(v->{
           chooseBtn.setEnabled(true);
           chooseFromAnyWhere.setBackgroundColor(Color.parseColor("#F6F6F7"));
            if(mView !=null){
                mView.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
            }
            selectedDetails = null;
            });


        loadPickupAddresses();

        findViewById(R.id.addAddressBtn).setOnClickListener( v-> {
            AddAddressDetailsActivity.isPickup = true;
            startActivity(new Intent(getApplicationContext(), AddAddressDetailsActivity.class));
        });


        chooseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseBtn.setEnabled(false);
                chooseBtn.setText("Please wait ...");
                saveCurrentAddressToSharedPreferences();
                Intent i = new Intent(getApplicationContext(), NewOrderActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }
        });
    }

    private void saveCurrentAddressToSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("OrderSharedPreferences",MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        if(selectedDetails !=null){
            myEdit.putString("pickupAddress", String.valueOf(selectedDetails.getId()));
        }else{
            myEdit.putString("pickupAddress","anywhere");
            Toast.makeText(getApplicationContext(), "Any", Toast.LENGTH_SHORT).show();
        }
        myEdit.commit();
    }

    private void loadPickupAddresses() {

        PickupViewModel model = new ViewModelProvider(this).get(PickupViewModel.class);

        model.getListObserver().observe(this, new Observer<List<AddressDetails>>() {
            @Override
            public void onChanged(List<AddressDetails> addressDetails) {
                if(addressDetails != null){
                    mArray = addressDetails;
                    addressesAdapter = new AddressesAdapter(getApplicationContext(),addressDetails,ChoosePickUpAddressActivity.this::onClick);
                    recyclerview.setAdapter(addressesAdapter);
                    recyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,true));

                }else{
                    Toast.makeText(getApplicationContext(),"0", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    public void onClick(LinearLayout selectItem, View view, int pos) {
        if(mView != null){
            mView.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
        }
        selectedDetails = mArray.get(pos);
        mView = view;
        chooseBtn.setEnabled(true);
        view.setBackgroundColor(Color.parseColor("#F6F6F7"));
        chooseFromAnyWhere.setBackgroundColor(Color.parseColor("#FFFFFFFF"));

    }
}