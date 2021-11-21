package com.oolysolutions.oolys.Act.ParcelDetails;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.oolysolutions.oolys.R;
import com.oolysolutions.oolys.databinding.ParcelDetailsBottomSheetBinding;

public class ParcelDetailsBottomSheet extends BottomSheetDialogFragment {

    String title;
    String body;
    String type;

    ParcelDetailsBottomSheetBinding binding;
    ParcelDetails mparcelDetails;
    ParcelDetailsViewModel model;
    public ParcelDetailsBottomSheet(String title, String body, String type, ViewModelStoreOwner owner, ParcelDetails mparcelDetails, ParcelDetailsViewModel parcelModel) {
        this.title = title;
        this.body = body;
        this.type = type;
        this.model = parcelModel;
        this.mparcelDetails = mparcelDetails;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable
            ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.parcel_details_bottom_sheet,
                container, false);
        binding = ParcelDetailsBottomSheetBinding.bind(v);
        binding.title.setText(title);
        binding.editText.setHint(body);
        if(type.equals("text")){
            binding.editText.setInputType(InputType.TYPE_CLASS_TEXT);

        }
        switch (title){
            case "Parcel content" : binding.editText.setText(mparcelDetails.getContent()); break;
            case "Quantity" : binding.editText.setText(mparcelDetails.getQuantity()); break;
            case "Collect amount" : binding.editText.setText(mparcelDetails.getCodAmount()); break;
            case "Pay amount" : binding.editText.setText(mparcelDetails.getPayAmount()); break;
        }

        binding.editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(binding.editText.getText().toString().length() > 0){
                    binding.saveBtn.setEnabled(true);
                }else{
                    binding.saveBtn.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        binding.saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.saveBtn.setEnabled(false);
                binding.saveBtn.setText("Please wait.");
                switch (title){
                    case "Parcel content" : model.setDetails().setContent(binding.editText.getText().toString()); break;
                    case "Quantity" : model.setDetails().setQuantity(binding.editText.getText().toString()); break;
                    case "Collect amount" : model.setDetails().setCodAmount(binding.editText.getText().toString()); break;
                    case "Pay amount" : model.setDetails().setPayAmount(binding.editText.getText().toString()); break;
                }
                dismiss();
            }
        });

        return v;
    }

    @Override
    public int getTheme() {
        return R.style.AppBottomSheetDialogTheme;
    }
}
