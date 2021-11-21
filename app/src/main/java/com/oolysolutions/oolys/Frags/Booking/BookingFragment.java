package com.oolysolutions.oolys.Frags.Booking;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.oolysolutions.oolys.Act.NewOrder.NewOrderActivity;
import com.oolysolutions.oolys.Act.OrderPlaced.ComeFullOrder;
import com.oolysolutions.oolys.Bookings.GetAllBookingAdapter;
import com.oolysolutions.oolys.R;
import com.oolysolutions.oolys.Retrofit.RetrofitApi;
import com.oolysolutions.oolys.databinding.FragmentBookingBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BookingFragment extends Fragment {


    FragmentBookingBinding binding;
    View rootView;
    private SharedPreferences userSharedPreferences;
    private String currentUserId;
    public BookingFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_booking, container, false);
        binding = FragmentBookingBinding.bind(rootView);

        userSharedPreferences = rootView.getContext().getSharedPreferences("UserSharedPreferences",MODE_PRIVATE);
        currentUserId = userSharedPreferences.getString("CurrentUserId","").toString();
        try {
            getActivity().getWindow().setStatusBarColor(Color.parseColor("#F7D7FF"));
        }catch (Exception e){

        }

        SharedPreferences sharedPreferences = rootView.getContext().getSharedPreferences("OrderSharedPreferences",MODE_PRIVATE);
        if(sharedPreferences.contains("dropAddress") ||sharedPreferences.contains("pickupAddress")  || sharedPreferences.contains("parcelDetails")  ){
            binding.showCartItem.setVisibility(View.VISIBLE);
        }else{
            binding.showCartItem.setVisibility(View.GONE);
        }

        binding.showCartItem.setOnClickListener(v->{startActivity(new Intent(rootView.getContext(), NewOrderActivity.class));});





        getAllBooking();

        return  rootView;
    }

    private void getAllBooking() {
        new RetrofitApi().get().getAllOrdersFor(currentUserId).enqueue(new Callback<ArrayList<OrderDetails>>() {
            @Override
            public void onResponse(Call<ArrayList<OrderDetails>> call, Response<ArrayList<OrderDetails>> response) {
                if(response.isSuccessful()){
                    if(response.body().size() ==0){
                        binding.showNothing.setVisibility(View.VISIBLE);
                    }
                    GetAllBookingAdapter getAllBookingAdapter = new GetAllBookingAdapter(response.body(),rootView.getContext());
                    binding.recyclerviewc.setAdapter(getAllBookingAdapter);
                    binding.recyclerviewc.setLayoutManager(new LinearLayoutManager(rootView.getContext()));
                }
            }

            @Override
            public void onFailure(Call<ArrayList<OrderDetails>> call, Throwable t) {
                Toast.makeText(rootView.getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public void onResume() {
        super.onResume();

        getAllBooking() ;
    }
}