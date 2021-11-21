package com.oolysolutions.oolys.Act.OrderPlaced;

import static com.oolysolutions.oolys.Act.PaymentDetails.Payment.BASE_AMOUNT;
import static com.oolysolutions.oolys.Act.PaymentDetails.Payment.DISCOUNT;
import static com.oolysolutions.oolys.Act.PaymentDetails.Payment.TAX;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.oolysolutions.oolys.LandingPageActivity;
import com.oolysolutions.oolys.R;
import com.oolysolutions.oolys.databinding.ActivityOrderPlacedBinding;

public class OrderPlacedActivity extends AppCompatActivity {

    public static ComeFullOrder order;
    ActivityOrderPlacedBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrderPlacedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        try {
            getWindow().setStatusBarColor(Color.parseColor("#F7D7FF"));
        }catch (Exception e){


        }


        if(order.getPickUpDetails() == null){
            binding.pickUpCustomerName.setText("Anywhere nearby");
            binding.pickUpCustomerAddress.setText("We’ll get you an item from anywhere nearby");

        }else {
            binding.pickUpCustomerName.setText(order.getPickUpDetails().getName());
            binding.pickUpCustomerAddress.setText(order.getPickUpDetails().getAddress()+","+order.getPickUpDetails().getArea());
        }

        binding.dropCustomerName.setText(order.getDropDetails().getName());
        binding.dropCustomerAddress.setText(order.getDropDetails().getAddress()+","+order.getDropDetails().getArea());

        binding.baseAmount.setText(BASE_AMOUNT+"");
        binding.tax.setText(TAX+"");
        binding.discount.setText(DISCOUNT+"");

        binding.charges.setText(order.getPaymentDetails().getCHARGES()+" ( "+order.getPaymentDetails().getCPK()+"* "+order.getPaymentDetails().getKM()+"kms )");
        binding.finalCost.setText(order.getPaymentDetails().getCOST()+"");
        binding.payatpickup.setText(order.getPaymentDetails().getPAYATPICKUP()+"");

        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getApplicationContext(), LandingPageActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {

        Intent intent =new Intent(getApplicationContext(), LandingPageActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);

        super.onBackPressed();
    }
}