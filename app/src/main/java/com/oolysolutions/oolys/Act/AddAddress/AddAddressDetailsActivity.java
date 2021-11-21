package com.oolysolutions.oolys.Act.AddAddress;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.oolysolutions.oolys.Act.Location.ChooseLocationActivity;
import com.oolysolutions.oolys.R;
import com.oolysolutions.oolys.databinding.ActivityAddAddressDetailsBinding;
import com.oolysolutions.oolys.databinding.ActivityLandingPageBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AddAddressDetailsActivity extends AppCompatActivity {


    public static Boolean isPickup = false;

    ActivityAddAddressDetailsBinding binding;
    private RequestQueue mRequestQueue;
    private boolean isSearched = false;
    private AddressDetails addressDetails ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddAddressDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        try {
            getWindow().setStatusBarColor(Color.parseColor("#F7D7FF"));
        }catch (Exception e){

        }

        mRequestQueue = Volley.newRequestQueue(AddAddressDetailsActivity.this);

        binding.Pincode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(!binding.Pincode.getText().toString().equals("")){
                    if(binding.Pincode.getText().toString().length() == 6){
                        binding.progessbar.setVisibility(View.VISIBLE);
                        getDataFromPinCode(binding.Pincode.getText().toString());
                    }
                }

                if(isSearched){
                    binding.state.setText("");
                    binding.District.setText("");
                }

                validateEditText();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        binding.name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                validateEditText();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        binding.address.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                validateEditText();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        binding.phoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                validateEditText();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        binding.area.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                validateEditText();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        binding.District.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                validateEditText();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        binding.state.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                validateEditText();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });



        binding.nxtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    addressDetails = new AddressDetails(
                    binding.name.getText().toString(),
                    binding.phoneNumber.getText().toString(),
                    binding.address.getText().toString(),
                    binding.area.getText().toString(),
                    binding.Pincode.getText().toString(),
                    binding.state.getText().toString(),
                    binding.District.getText().toString());

                    if(isPickup){
                        addressDetails.setType("pickup");
                    }else{
                        addressDetails.setType("drop");
                    }

                    ChooseLocationActivity.addressDetails = addressDetails;
                    startActivity(new Intent(getApplicationContext(), ChooseLocationActivity.class));

            }
        });

    }


    private boolean validateEditText() {
        if( validateHelper(binding.name)
            && validateHelper(binding.phoneNumber)
            && validateHelper(binding.area)
            && validateHelper(binding.address)
            && validateHelper(binding.District)
            && validateHelper(binding.Pincode)
            && validateHelper(binding.state)
        ){
            binding.nxtBtn.setEnabled(true);
            return true;
        }else{
            binding.nxtBtn.setEnabled(false);
            return false;
        }
    }


    private void getDataFromPinCode(String pinCode) {

        String url = "http://www.postalpincode.in/api/pincode/" + pinCode;
        RequestQueue queue = Volley.newRequestQueue(AddAddressDetailsActivity.this);
        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray postOfficeArray = response.getJSONArray("PostOffice");
                    if (response.getString("Status").equals("Error")) {
                        Toast.makeText(getApplicationContext(), "Invalid pincode", Toast.LENGTH_SHORT).show();
                    } else {
                        JSONObject obj = postOfficeArray.getJSONObject(0);

                        String district = obj.getString("District");
                        String state = obj.getString("State");

                        binding.state.setText(state);
                        binding.District.setText(district);
                    }
                } catch (Exception e) {
                    // if we gets any error then it
                    // will be printed in log cat.
                    e.printStackTrace();
                    Log.e("AAAA",e.getMessage());
                    Toast.makeText(getApplicationContext(), "Invalid pincode", Toast.LENGTH_SHORT).show();
                    binding.state.setText("");
                    binding.District.setText("");
                }
                isSearched = true;
                binding.progessbar.setVisibility(View.GONE);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("AA",error.getMessage());
                Toast.makeText(getApplicationContext(), "Invalid pincode", Toast.LENGTH_SHORT).show();
                binding.progessbar.setVisibility(View.GONE);
                binding.state.setText("");
                binding.District.setText("");
            }
        });
        queue.add(objectRequest);
    }


    private boolean validateHelper(EditText editText) {
        if(TextUtils.isEmpty(editText.getText().toString())){
            return false;
        }else{
            return true;
        }
    }
}