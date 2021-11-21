package com.oolysolutions.oolys.Bookings;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.oolysolutions.oolys.Act.OrderPlaced.ComeFullOrder;
import com.oolysolutions.oolys.Frags.Booking.OrderDetails;
import com.oolysolutions.oolys.R;
import com.oolysolutions.oolys.databinding.BookingItemRecyclerItemBinding;

import java.util.ArrayList;

public class GetAllBookingAdapter extends RecyclerView.Adapter<GetAllBookingAdapter.ViewHolder> {
    ArrayList<OrderDetails> data;
    Context context;

    BookingItemRecyclerItemBinding binding;

    public GetAllBookingAdapter(ArrayList<OrderDetails> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         View view = LayoutInflater.from(parent.getContext())
                 .inflate(R.layout.booking_item_recycler_item,
                         parent,false);

        binding = BookingItemRecyclerItemBinding.bind(view);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        OrderDetails orderDetails = data.get(position);
        binding.pickupDetails.setText(orderDetails.getCust_full_name()+" "+orderDetails.getCust_address());
        binding.dropDetails.setText(orderDetails.getReciever_name()+" "+orderDetails.getReciever_address());
        binding.content.setText(orderDetails.getDelivery_item_type()+" ("+orderDetails.getQuantity()+"x)");
        binding.amount.setText(orderDetails.getFinal_cost());
        binding.date.setText(orderDetails.getOrdered_at());

        if(orderDetails.getOrder_status().equals("cancelled")){
            binding.canceled.setVisibility(View.VISIBLE);
        }else{
            if(orderDetails.getDelivery_state().equals("accpeted")){
                binding.waitingTag.setVisibility(View.VISIBLE);
            }else  if(orderDetails.getDelivery_state().equals("dropped")){
                binding.Completed.setVisibility(View.VISIBLE);
            }else  if(orderDetails.getDelivery_state().equals("picked")){
                binding.waitingTag.setVisibility(View.VISIBLE);
                binding.waitingTag.setText("Picked");

            }else{
                binding.waitingTag.setVisibility(View.VISIBLE);
            }
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BookingDetailsActivity.o = orderDetails;
                holder.itemView.getContext().startActivity(new Intent(holder.itemView.getContext(),BookingDetailsActivity.class));
            }
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);


        }
    }
}
