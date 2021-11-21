package com.oolysolutions.oolys.Act.AddAddress.BottomSheet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.oolysolutions.oolys.Act.AddAddress.AddressDetails;
import com.oolysolutions.oolys.R;
import com.oolysolutions.oolys.databinding.AddressDetailsBottomSheetBinding;

public class AddressBottomSheet extends BottomSheetDialogFragment {
    String type;
    AddressDetails address;

    AddressDetailsBottomSheetBinding binding;
    public AddressBottomSheet(String type, AddressDetails address) {
        this.type = type;
        this.address = address;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable
            ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.address_details_bottom_sheet,
                container, false);
        binding = AddressDetailsBottomSheetBinding.bind(v);

        binding.name.setText(address.getName());
        binding.phoneNumber.setText(address.getPhone());
        binding.Pincode.setText(address.getPincode());
        binding.District.setText(address.getDistrict());
        binding.state.setText(address.getState());
        binding.area.setText(address.getArea());
        binding.address.setText(address.getAddress());

        binding.textView4.setText(type);
        return v;
    }

    @Override
    public int getTheme() {
        return R.style.AppBottomSheetDialogTheme;
    }
}
