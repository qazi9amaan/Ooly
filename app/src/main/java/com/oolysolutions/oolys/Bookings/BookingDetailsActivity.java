package com.oolysolutions.oolys.Bookings;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.oolysolutions.oolys.Frags.Booking.OrderDetails;
import com.oolysolutions.oolys.R;
import com.oolysolutions.oolys.Retrofit.RetrofitApi;
import com.oolysolutions.oolys.databinding.ActivityBookingDetailsBinding;

public class BookingDetailsActivity extends AppCompatActivity {

    public static OrderDetails o;
    ActivityBookingDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityBookingDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.orderid.setText(o.getOrder_id());
        binding.mainOrderId.setText(o.getOrder_id());
        binding.orderDate.setText(o.getOrdered_at());

        binding.pickUpname.setText(o.getCust_full_name());
        binding.pickUpaddress.setText(o.getCust_address());

        binding.dropName.setText(o.getReciever_name());
        binding.dropAddress.setText(o.getReciever_address());

        binding.payOnPickup.setText(o.getPay_at_pickup());
        double cost = Double.parseDouble(o.getFinal_cost())-Double.parseDouble(o.getPay_at_pickup());
        binding.cost.setText(cost+"");


        if(o.getOrder_status().equals("cancelled")){
            binding.viewImage.setImageResource(R.drawable.cancelled);
            binding.stepView.setVisibility(View.GONE);
        }else if(o.getOrder_status().equals("delivered")){
            binding.stepView.go(3,true);
            binding.stepView.done(true);
            binding.viewImage.setImageResource(R.drawable.donr);
        }else{
            if(o.getDelivery_state().equals("accpeted")){
                binding.viewImage.setImageResource(R.drawable.preparing);
            }else  if(o.getDelivery_state().equals("dropped")){
                binding.stepView.go(3,true);
                binding.viewImage.setImageResource(R.drawable.donr);
            }else if(o.getDelivery_state().equals("confirming")){
                binding.viewImage.setImageResource(R.drawable.preparing);
                binding.stepView.go(0,true);
            }else if(o.getDelivery_state().equals("picked")){
                binding.stepView.go(2,true);
            }else{
                binding.viewImage.setImageResource(R.drawable.main_scooter_bg);
            }
        }

        findViewById(R.id.backBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}