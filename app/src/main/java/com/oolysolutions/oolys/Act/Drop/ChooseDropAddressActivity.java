package com.oolysolutions.oolys.Act.Drop;

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
import android.widget.Toast;

import com.oolysolutions.oolys.Act.AddAddress.AddAddressDetailsActivity;
import com.oolysolutions.oolys.Act.AddAddress.AddressDetails;
import com.oolysolutions.oolys.Act.AddAddress.AddressesAdapter;
import com.oolysolutions.oolys.Act.NewOrder.NewOrderActivity;
import com.oolysolutions.oolys.R;

import java.util.List;

public class ChooseDropAddressActivity extends AppCompatActivity {

    RecyclerView recyclerview;
    Button chooseBtn;
    View mView;
    AddressesAdapter addressesAdapter;

    public static AddressDetails selectedDetails = null;
    private List<AddressDetails> mArray;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_drop_address);

        try {
            getWindow().setStatusBarColor(Color.parseColor("#F7D7FF"));
        }catch (Exception e){

        }
        recyclerview = findViewById(R.id.recyclerview);
        chooseBtn = findViewById(R.id.chooseBtn);


        loadDropAddresses();

        findViewById(R.id.addAddressBtn).setOnClickListener( v-> {
            AddAddressDetailsActivity.isPickup = false;
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
            myEdit.putString("dropAddress", String.valueOf(selectedDetails.getId()));
        }
        myEdit.commit();
    }

    private void loadDropAddresses() {
        DropViewModel model = new ViewModelProvider(this).get(DropViewModel.class);

        model.getListObserver().observe(this, new Observer<List<AddressDetails>>() {
            @Override
            public void onChanged(List<AddressDetails> addressDetails) {
                if(addressDetails != null){
                    mArray = addressDetails;
                    addressesAdapter = new AddressesAdapter(getApplicationContext(),addressDetails, (selectItem, view, i) -> ChooseDropAddressActivity.this.onClick(view, i));
                    recyclerview.setAdapter(addressesAdapter);
                    recyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,true));

                }else{
                    Toast.makeText(getApplicationContext(),"0", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void onClick(View view, int i) {
        if(mView != null){
            mView.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
        }
        selectedDetails = mArray.get(i);

        mView = view;
        chooseBtn.setEnabled(true);
        view.setBackgroundColor(Color.parseColor("#F6F6F7"));

    }
}