package com.oolysolutions.oolys.Act.AddAddress;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.oolysolutions.oolys.Act.AddAddress.BottomSheet.AddressBottomSheet;
import com.oolysolutions.oolys.R;

import java.util.List;

public class AddressesAdapter extends RecyclerView.Adapter<AddressesAdapter.ViewHolder> {


    Context applicationContext;
    List<AddressDetails> arrayListLiveData;
    onClickListner onClickListner;

    public AddressesAdapter(Context applicationContext, List<AddressDetails> arrayListLiveData,onClickListner onClickListner) {
        this.applicationContext = applicationContext;
        this.arrayListLiveData = arrayListLiveData;
        this.onClickListner = onClickListner;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item_address,parent,false);
        return new ViewHolder(view,onClickListner);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(arrayListLiveData.get(position).getName());
        holder.address.setText(arrayListLiveData.get(position).getAddress()+", "+arrayListLiveData.get(position).getArea());
    }

    @Override
    public int getItemCount() {
        return arrayListLiveData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView address,name;
        LinearLayout showDetails,selectItem;
        ConstraintLayout viewMain;
        public ViewHolder(@NonNull View itemView, AddressesAdapter.onClickListner onClickListner) {
            super(itemView);

            address = itemView.findViewById(R.id.address);
            name = itemView.findViewById(R.id.name);
            showDetails = itemView.findViewById(R.id.showDetails);
            selectItem = itemView.findViewById(R.id.selectItem);
            viewMain = itemView.findViewById(R.id.viewMain);

            selectItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickListner.onClick(selectItem,viewMain,getAdapterPosition());
                }
            });

            showDetails.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AddressBottomSheet pickupSheet = new AddressBottomSheet("details",arrayListLiveData.get(getAdapterPosition()));
                    pickupSheet.show(((FragmentActivity)itemView.getContext()).getSupportFragmentManager(),
                            "pickupAddressBottomSheet");
                }
            });
        }
    }

    public interface onClickListner{
        public void onClick(LinearLayout selectItem, View view, int pos);
    }
}
