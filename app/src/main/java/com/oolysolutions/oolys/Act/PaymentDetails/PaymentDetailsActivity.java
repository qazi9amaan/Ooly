package com.oolysolutions.oolys.Act.PaymentDetails;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.oolysolutions.oolys.Act.AddAddress.AddressDetails;
import com.oolysolutions.oolys.Act.NewOrder.NewOrderActivity;
import com.oolysolutions.oolys.Act.OrderPlaced.ComeFullOrder;
import com.oolysolutions.oolys.Act.OrderPlaced.OrderPlacedActivity;
import com.oolysolutions.oolys.Act.ParcelDetails.ParcelDetails;
import com.oolysolutions.oolys.Act.Verification.PhoneNumberVerifyBottomSheet;
import com.oolysolutions.oolys.LandingPageActivity;
import com.oolysolutions.oolys.R;
import com.oolysolutions.oolys.Retrofit.DefaultResponse;
import com.oolysolutions.oolys.Retrofit.RetrofitApi;
import com.oolysolutions.oolys.databinding.ActivityPaymentDetailsBinding;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentDetailsActivity extends AppCompatActivity implements PhoneNumberVerifyBottomSheet.onVerificationComplete{

    private static double BASE_AMOUNT = Payment.BASE_AMOUNT ;
    private static double CPK = Payment.CPK;
    private static double TAX = Payment.TAX;
    private static double DISCOUNT =  Payment.DISCOUNT;
    private static double CHARGES ,COST ;
    private static double KM,PAYATPICKUP ;


    ActivityPaymentDetailsBinding binding;

    SharedPreferences orderSharedPreferences, userSharedPreferences;
    private PhoneNumberVerifyBottomSheet bottomSheet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPaymentDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        orderSharedPreferences = getSharedPreferences("OrderSharedPreferences",MODE_PRIVATE);
        userSharedPreferences = getSharedPreferences("UserSharedPreferences",MODE_PRIVATE);

        try {
            getWindow().setStatusBarColor(Color.parseColor("#F7D7FF"));
        }catch (Exception e){

        }


        binding.baseAmount.setText(BASE_AMOUNT+"");
        binding.taxes.setText(TAX+"");
        binding.discount.setText(DISCOUNT+"");

        Location pickUpLocation = new Location("");
        Location dropLocation = new Location("");

        if(NewOrderActivity.pickUpAddress != null){
            dropLocation.setLatitude(NewOrderActivity.dropAddress.getLat());
            dropLocation.setLongitude(NewOrderActivity.dropAddress.getLon());

            pickUpLocation.setLatitude(NewOrderActivity.pickUpAddress.getLat());
            pickUpLocation.setLongitude(NewOrderActivity.pickUpAddress.getLon());

            KM = Double.parseDouble(String.format("%.1f",Double.valueOf(pickUpLocation.distanceTo(dropLocation))*0.001));

        }else{
            KM = 15;
        }
        if(NewOrderActivity.parcelDetails.getPayAmount() != null){
            PAYATPICKUP = Double.parseDouble(NewOrderActivity.parcelDetails.getPayAmount());
        }
        CHARGES = Double.parseDouble(String.format("%.2f",(CPK*KM)));
        COST = Math.rint( BASE_AMOUNT + TAX +DISCOUNT + CHARGES+PAYATPICKUP);

        binding.charges.setText(CHARGES+" ( "+CPK+"* "+KM+"kms )");
        binding.finalCost.setText(COST+"");
        binding.payatpickup.setText(PAYATPICKUP+"");


        if(COST != 0){
            binding.pymtbtn.setEnabled(true);
            binding.pymtbtn.setText("Place this order");
        }


        binding.pymtbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.pymtbtn.setEnabled(false);
                binding.pymtbtn.setText("Please wait ...");
                if(!userSharedPreferences.contains("CurrentUserId")){
                    showBottomSheetForVerification();
                }else{
                    clearsharedPrefeerences();
                    savetoDatabase();
                }

            }
        });

    }

    private void savetoDatabase() {


        Payment payment = new Payment();
        payment.setCHARGES(CHARGES);
        payment.setCOST(COST);
        payment.setKM(KM);
        payment.setPAYATPICKUP(PAYATPICKUP);

        ComeFullOrder comeFullOrder = new ComeFullOrder();
        comeFullOrder.setPaymentDetails(payment);
        comeFullOrder.setDropDetails(NewOrderActivity.dropAddress);
        comeFullOrder.setPickUpDetails(NewOrderActivity.pickUpAddress);
        comeFullOrder.setParcelDetails(NewOrderActivity.parcelDetails);
        comeFullOrder.setCurrentUserId(userSharedPreferences.getString("CurrentUserId", "null").toString());
        comeFullOrder.setCurrentUserPhoneNumber(userSharedPreferences.getString("CurrentUserPhoneNumber", "null").toString());

        OrderPlacedActivity.order = comeFullOrder;
        Toast.makeText(getApplicationContext(), "Saved.", Toast.LENGTH_SHORT).show();


        RetrofitApi retrofitApi = new RetrofitApi();
        retrofitApi.get().placeOrder(comeFullOrder).enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {

                if(response.body().getCode().equals("200")){
                    Intent intent =new Intent(getApplicationContext(), OrderPlacedActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });






    }

    private void clearsharedPrefeerences() {
        orderSharedPreferences.edit().clear().commit();
        LandingPageActivity.banner.setVisibility(View.GONE);
        try {
            NewOrderActivity.parcelModel.removeParcel(NewOrderActivity.parcelDetails);
        }catch (Exception e){

        }
    }

    private void showBottomSheetForVerification() {
        bottomSheet = new PhoneNumberVerifyBottomSheet(getApplicationContext(),this,PaymentDetailsActivity.this);
        bottomSheet.show(getSupportFragmentManager(),"PhoneNumberVerify");
    }

    @Override
    public void onComplete() {
        clearsharedPrefeerences();
        savetoDatabase();
        bottomSheet.dismiss();
    }

}