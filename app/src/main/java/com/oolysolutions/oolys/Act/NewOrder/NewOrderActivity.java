package com.oolysolutions.oolys.Act.NewOrder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.oolysolutions.oolys.Act.AddAddress.AddressDetails;
import com.oolysolutions.oolys.Act.AddAddress.BottomSheet.AddressBottomSheet;
import com.oolysolutions.oolys.Act.Drop.ChooseDropAddressActivity;
import com.oolysolutions.oolys.Act.Drop.DropViewModel;
import com.oolysolutions.oolys.Act.ParcelDetails.ParcelDetails;
import com.oolysolutions.oolys.Act.ParcelDetails.ParcelDetailsActivity;
import com.oolysolutions.oolys.Act.ParcelDetails.ParcelDetailsViewModel;
import com.oolysolutions.oolys.Act.PaymentDetails.PaymentDetailsActivity;
import com.oolysolutions.oolys.Act.Pickup.ChoosePickUpAddressActivity;
import com.oolysolutions.oolys.Act.Pickup.PickupViewModel;
import com.oolysolutions.oolys.R;
import com.oolysolutions.oolys.databinding.ActivityNewOrderBinding;

public class NewOrderActivity extends AppCompatActivity {

    public static AddressDetails pickUpAddress,dropAddress;
    public static ParcelDetails parcelDetails;

    public static ParcelDetailsViewModel parcelModel;
    private SharedPreferences sharedPreferences;
    ActivityNewOrderBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityNewOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ParcelDetailsActivity.isSavedData = false;

        try {
            getWindow().setStatusBarColor(Color.parseColor("#F7D7FF"));
        }catch (Exception e){

        }

        sharedPreferences = getSharedPreferences("OrderSharedPreferences",MODE_PRIVATE);

        if(sharedPreferences.contains("dropAddress")){
            DropViewModel model = new ViewModelProvider(this).get(DropViewModel.class);
            dropAddress = model.getItemDetails(sharedPreferences.getString("dropAddress", "null").toString());
            setUpDropView(R.drawable.ic_home_icon,dropAddress.getName(),dropAddress.getAddress()+", "+dropAddress.getArea());
            binding.dropArea.setVisibility(View.VISIBLE);
        }else{
            setUpDropView(R.drawable.ic_plus_black,"Choose Drop","We will drop the parcel  here. ");
            binding.dropArea.setVisibility(View.GONE);

        }

        if(sharedPreferences.contains("pickupAddress")){

            PickupViewModel model = new ViewModelProvider(this).get(PickupViewModel.class);
            pickUpAddress = model.getItemDetails(sharedPreferences.getString("pickupAddress", "null").toString());

            // check for any
            if(sharedPreferences.getString("pickupAddress","null").equals("anywhere")){
                setUpPickUpView(R.drawable.ic_anywhere_icon,"Anywhere nearby.","We’ll get you an item from anywhere nearby");
            }else{
                binding.editPickUpArea.setVisibility(View.VISIBLE);
                setUpPickUpView(R.drawable.ic_home_icon,pickUpAddress.getName(),pickUpAddress.getAddress()+", "+pickUpAddress.getArea());
            }

        }else{
            setUpPickUpView(R.drawable.ic_plus_black,"Choose pickup","We will pickup the parcel from here..");
            binding.editPickUpArea.setVisibility(View.GONE);

        }


        if(sharedPreferences.contains("parcelDetails")){

            parcelModel = new ViewModelProvider(this).get(ParcelDetailsViewModel.class);
            parcelDetails = parcelModel.getItemDetails(sharedPreferences.getString("parcelDetails", "null").toString());
            setUpParcelView(R.drawable.ic_home_icon,parcelDetails.getContent(),"Parcel content");

        }else{
            setUpParcelView(R.drawable.ic_plus_black,"Parcel Details","What's inside the parcel");
        }


        binding.showPickupArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sharedPreferences.contains("pickupAddress")) {
                    if(sharedPreferences.getString("pickupAddress","null").equals("anywhere")){
                        startActivity(new Intent(getApplicationContext(), ChoosePickUpAddressActivity.class));
                    }else{
                        AddressBottomSheet pickupSheet = new AddressBottomSheet("pickup",pickUpAddress);
                        pickupSheet.show(getSupportFragmentManager(),
                                "pickupAddressBottomSheet");
                    }
                }else{
                    startActivity(new Intent(getApplicationContext(), ChoosePickUpAddressActivity.class));
                }
            }
        });

        binding.showDropArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sharedPreferences.contains("dropAddress")) {
                    AddressBottomSheet pickupSheet = new AddressBottomSheet("drop",dropAddress);
                    pickupSheet.show(getSupportFragmentManager(),
                            "dropAddressBottomSheet");
                }else{
                    startActivity(new Intent(getApplicationContext(), ChooseDropAddressActivity.class));
                }
            }
        });
        binding.editPickUpArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ChoosePickUpAddressActivity.class));
            }
        });
        binding.dropArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    startActivity(new Intent(getApplicationContext(), ChooseDropAddressActivity.class));
                }
        });
        binding.parcelDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sharedPreferences.contains("parcelDetails")) {
                    ParcelDetailsActivity.mparcelDetails = parcelDetails;
                    parcelModel= new ViewModelProvider(NewOrderActivity.this).get(ParcelDetailsViewModel.class);
                    parcelModel.setCurrentParcel(parcelDetails);
                    ParcelDetailsActivity.parcelModel = parcelModel;
                    ParcelDetailsActivity.isSavedData = true;
                }
                startActivity(new Intent(getApplicationContext(), ParcelDetailsActivity.class));

            }
        });

        checkForButton();

        binding.ContinueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.ContinueBtn.setEnabled(false);
                binding.ContinueBtn.setText("Please wait ...");
                startActivity(new Intent(getApplicationContext(), PaymentDetailsActivity.class));
                binding.ContinueBtn.setText("Continue");

            }
        });

    }

    private void checkForButton() {
        if(sharedPreferences.contains("parcelDetails")
        && sharedPreferences.contains("pickupAddress")
        && sharedPreferences.contains("dropAddress")
        ){
            binding.ContinueBtn.setEnabled(true);
        }else{
            binding.ContinueBtn.setEnabled(false);
        }
    }

    private void setUpPickUpView(int img, String h, String b) {
        binding.pickupAreaHeading.setText(h);
        binding.pickupAreaBody.setText(b);
        binding.pickupAreaImage.setImageResource(img);
    }

    private void setUpParcelView(int img, String h, String b) {
        binding.parcelDetailsHeading.setText(h);
        binding.parcelDetailsBody.setText(b);
        binding.parcelDetailsImage.setImageResource(img);
    }

    private void setUpDropView(int img, String h, String b) {
        binding.dropAreaHeading.setText(h);
        binding.dropAreaBody.setText(b);
        binding.dropAreaImage.setImageResource(img);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getSharedPreferences("OrderSharedPreferences",MODE_PRIVATE);

        if(sharedPreferences.contains("dropAddress")){
            DropViewModel model = new ViewModelProvider(this).get(DropViewModel.class);
            dropAddress = model.getItemDetails(sharedPreferences.getString("dropAddress", "null").toString());
            setUpDropView(R.drawable.ic_home_icon,dropAddress.getName(),dropAddress.getAddress()+", "+dropAddress.getArea());
            binding.dropArea.setVisibility(View.VISIBLE);
        }else{
            setUpDropView(R.drawable.ic_plus_black,"Choose Drop","We will drop the parcel  here. ");
            binding.dropArea.setVisibility(View.GONE);

        }

        if(sharedPreferences.contains("pickupAddress")){
            binding.editPickUpArea.setVisibility(View.GONE);
            PickupViewModel model = new ViewModelProvider(this).get(PickupViewModel.class);
            pickUpAddress = model.getItemDetails(sharedPreferences.getString("pickupAddress", "null").toString());
            // check for any
            if(sharedPreferences.getString("pickupAddress","null").equals("anywhere")){
                setUpPickUpView(R.drawable.ic_anywhere_icon,"Anywhere nearby.","We’ll get you an item from anywhere nearby");
            }else{
                setUpPickUpView(R.drawable.ic_home_icon,pickUpAddress.getName(),pickUpAddress.getAddress()+", "+pickUpAddress.getArea());
                binding.editPickUpArea.setVisibility(View.VISIBLE);

            }

        }else{
            setUpPickUpView(R.drawable.ic_plus_black,"Choose pickup","We will pickup the parcel from here..");
            binding.editPickUpArea.setVisibility(View.GONE);

        }


        if(sharedPreferences.contains("parcelDetails")){

            ParcelDetailsViewModel model = new ViewModelProvider(this).get(ParcelDetailsViewModel.class);
            parcelDetails = model.getItemDetails(sharedPreferences.getString("parcelDetails", "null").toString());
            setUpParcelView(R.drawable.ic_home_icon,parcelDetails.getContent(),"Parcel content");

        }else{
            setUpParcelView(R.drawable.ic_plus_black,"Parcel Details","What's inside the parcel");
        }

        checkForButton();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        checkForButton();

    }
}