package com.oolysolutions.oolys;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.firebase.FirebaseApp;
import com.oolysolutions.oolys.Act.Drop.DropViewModel;
import com.oolysolutions.oolys.Act.NewOrder.NewOrderActivity;
import com.oolysolutions.oolys.databinding.ActivityLandingPageBinding;


public class LandingPageActivity extends AppCompatActivity {


    private ActivityLandingPageBinding binding;

    public static LinearLayout banner ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FirebaseApp.initializeApp(getApplicationContext());

        binding = ActivityLandingPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        banner = binding.pendingOrderhere;

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_landing_page);
        NavigationUI.setupWithNavController(binding.navView, navController);



        SharedPreferences sharedPreferences = getSharedPreferences("OrderSharedPreferences",MODE_PRIVATE);
        if(sharedPreferences.contains("dropAddress") ||sharedPreferences.contains("pickupAddress")  || sharedPreferences.contains("parcelDetails")  ){
            binding.pendingOrderhere.setVisibility(View.VISIBLE);
        }else{
            binding.pendingOrderhere.setVisibility(View.GONE);
        }

        binding.pendingOrderhere.setOnClickListener(v->{startActivity(new Intent(getApplicationContext(), NewOrderActivity.class));});
        binding.pendingOrderclose.setOnClickListener(v->{
          try {
              NewOrderActivity.parcelModel.removeParcel(NewOrderActivity.parcelDetails);
          }catch (Exception e){
          }
            sharedPreferences.edit().clear().commit();
            binding.pendingOrderhere.setVisibility(View.GONE);
        });
    }

}