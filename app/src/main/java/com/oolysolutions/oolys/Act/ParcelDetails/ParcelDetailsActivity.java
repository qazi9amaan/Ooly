package com.oolysolutions.oolys.Act.ParcelDetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.oolysolutions.oolys.R;
import com.oolysolutions.oolys.databinding.ActivityParcelDetailsBinding;

public class ParcelDetailsActivity extends AppCompatActivity {

    ActivityParcelDetailsBinding binding;
    public static  ParcelDetails mparcelDetails;
    private boolean alreadyPressed = false;

    public static ParcelDetailsViewModel parcelModel;

    public static Boolean isSavedData = false;

    int parcelQuant = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityParcelDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if(!isSavedData){
            mparcelDetails = new ParcelDetails();
            parcelModel= new ViewModelProvider(ParcelDetailsActivity.this).get(ParcelDetailsViewModel.class);
        }else{
            parcelQuant = Integer.parseInt(mparcelDetails.getQuantity());
        }


        try {
            getWindow().setStatusBarColor(Color.parseColor("#F7D7FF"));
        }catch (Exception e){

        }

        binding.addParcelContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParcelDetailsBottomSheet pickupSheet = new
                        ParcelDetailsBottomSheet("Parcel content","Please tell us what’s the item?","text",
                        ParcelDetailsActivity.this,mparcelDetails, parcelModel);
                pickupSheet.show(getSupportFragmentManager(),
                        "ParcelContentBottomSheet");

            }
        });

        binding.quantityImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ParcelDetailsBottomSheet pickupSheet = new ParcelDetailsBottomSheet("Quantity","Please tell us how many items?","number",
//                        ParcelDetailsActivity.this,mparcelDetails,parcelModel);
//                pickupSheet.show(getSupportFragmentManager(),
//                        "ParcelContentBottomSheet");
                parcelQuant = parcelQuant+1;
                parcelModel.setDetails().setQuantity(String.valueOf(parcelQuant));
                binding.quantityImgNegative.setVisibility(View.VISIBLE);

            }
        });


        binding.quantityImgNegative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(parcelQuant >0){
                    parcelQuant = parcelQuant-1;
                }
                parcelModel.setDetails().setQuantity(String.valueOf(parcelQuant));
            }
        });

        binding.codAmount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParcelDetailsBottomSheet pickupSheet = new ParcelDetailsBottomSheet(
                        "Collect amount"," Collect amount at drop.","number",ParcelDetailsActivity.this,mparcelDetails, parcelModel);
                pickupSheet.show(getSupportFragmentManager(),
                        "ParcelContentBottomSheet");

            }
        });
        binding.codAmountGive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParcelDetailsBottomSheet pickupSheet = new ParcelDetailsBottomSheet("Pay amount","Pay at pickup.",
                        "number",ParcelDetailsActivity.this,mparcelDetails, parcelModel);
                pickupSheet.show(getSupportFragmentManager(),
                        "ParcelContentBottomSheet");

            }
        });

        parcelModel.getListObserver().observe(this, new Observer<ParcelDetails>() {
            @Override
            public void onChanged(ParcelDetails parcelDetails) {
                if(parcelDetails != null){
                    mparcelDetails = parcelDetails;
                    validateItemDetails(
                            true, parcelDetails.getContent(),
                            binding.parcelContentTitle,
                            binding.parcelContentBody,
                            binding.parcelContentImage,
                            "Parcel content",
                            "What’s the item?");

                    validateItemDetails(
                            false,
                            parcelDetails.getQuantity(),
                            binding.quantTitle,
                            binding.quantBody,
                            binding.quantityImg,
                            "Quantity.",
                            "How many items?");

                    validateItemDetails(
                            true, parcelDetails.getCodAmount(),
                            binding.codAmountTitle,
                            binding.codAmountBody,
                            binding.codAmountImage,
                            "Do we have to collect any amount?",
                            "Does the reciever has to pay anything?");

                    validateItemDetails(
                            true, parcelDetails.getPayAmount(),
                            binding.payAmountTitle,
                            binding.payAmountBody,
                            binding.payAmountIamge,
                            "Do we have to pay any amount?",
                            "Do we have to pay anything while pickup?");



                }else{
                    validateItemDetails(
                            true, null,
                            binding.parcelContentTitle,
                            binding.parcelContentBody,
                            binding.parcelContentImage,
                            "Parcel content",
                            "What’s the item?");

                    validateItemDetails(
                            true, null,
                            binding.quantTitle,
                            binding.quantBody,
                            binding.quantityImg,
                            "Quantity.",
                            "How many items?");

                    validateItemDetails(
                            true, null,
                            binding.codAmountTitle,
                            binding.codAmountBody,
                            binding.codAmountImage,
                            "Do we have to collect any amount?",
                            "Does the reciever has to pay anything?");

                    validateItemDetails(
                            true, null,
                            binding.payAmountTitle,
                            binding.payAmountBody,
                            binding.payAmountIamge,
                            "Do we have to pay any amount?",
                            "Do we have to pay anything while pickup?");
                }
                checkButtonAvailibilty();
            }
        });


        binding.proceedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.proceedBtn.setEnabled(false);
                binding.proceedBtn.setText("Please wait");

                // save to Local
                if(!isSavedData){
                    saveToLocal();
                    saveCurrentDetailsToSharedPreferences();
                }else{
                    updateLocal();
                }
                finish();

            }
        });

    }

    private void updateLocal() {
        parcelModel.updateParcel(mparcelDetails);

    }

    private void saveToLocal() {
        mparcelDetails.setId(System.currentTimeMillis());
        parcelModel.insetParcel(mparcelDetails);
    }

    private void saveCurrentDetailsToSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("OrderSharedPreferences",MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        if(mparcelDetails !=null){
            myEdit.putString("parcelDetails", String.valueOf(mparcelDetails.getId()));
        }
        myEdit.commit();
    }


    private void checkButtonAvailibilty() {
        if(mparcelDetails.getContent() != null && mparcelDetails.getQuantity() != null){
            binding.proceedBtn.setEnabled(true);
        }else{
            binding.proceedBtn.setEnabled(false);

        }
    }

    private void validateItemDetails(boolean b, String item, TextView title, TextView body, ImageView imageView, String titleText, String bodyText) {
        if(!b){
            if(item != null && Integer.parseInt(item)>0){
                title.setText(item);
                body.setText(titleText);
                binding.quantityImgNegative.setVisibility(View.VISIBLE);
            }else{
                title.setText(titleText);
                body.setText(bodyText);
                imageView.setImageResource(R.drawable.ic_plus_black);
                binding.quantityImgNegative.setVisibility(View.GONE);
            }
        }else{
            if(item == null || item.equals("")){
                title.setText(titleText);
                body.setText(bodyText);
                imageView.setImageResource(R.drawable.ic_plus_black);
            }else{
                title.setText(item);
                body.setText(titleText);
                imageView.setImageResource(R.drawable.ic_pencil);

            }
        }

    }

    @Override
    public void onBackPressed() {
        if(!alreadyPressed){
            Toast.makeText(getApplicationContext(), "All the data will be lost.", Toast.LENGTH_SHORT).show();
            alreadyPressed = !alreadyPressed;
            return;
        }else{
            parcelModel = null;
            mparcelDetails = null;
            finish();
        }

        super.onBackPressed();

    }


}