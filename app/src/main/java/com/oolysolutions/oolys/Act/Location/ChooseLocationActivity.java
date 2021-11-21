package com.oolysolutions.oolys.Act.Location;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.oolysolutions.oolys.Act.AddAddress.AddressDetails;
import com.oolysolutions.oolys.Act.Drop.ChooseDropAddressActivity;
import com.oolysolutions.oolys.Act.Drop.DropViewModel;
import com.oolysolutions.oolys.Act.NewOrder.NewOrderActivity;
import com.oolysolutions.oolys.Act.Pickup.ChoosePickUpAddressActivity;
import com.oolysolutions.oolys.Act.Pickup.PickupViewModel;
import com.oolysolutions.oolys.Database.Database;
import com.oolysolutions.oolys.LandingPageActivity;
import com.oolysolutions.oolys.R;

import java.io.IOException;
import java.util.List;

public class ChooseLocationActivity extends AppCompatActivity implements OnMapReadyCallback {


    public static AddressDetails addressDetails;

    private static  String CURRENT_TITLE ="Here, it is.";

    SupportMapFragment mapFragment;
    GoogleMap map;
    FusedLocationProviderClient fusedLocationProviderClient;

    FloatingActionButton currentLocationBtn;
    Marker marker;
    Button saveAdressBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_location);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        CURRENT_TITLE = addressDetails.getName();

        currentLocationBtn = findViewById(R.id.currentLocationBtn);
        currentLocationBtn = findViewById(R.id.currentLocationBtn);
        saveAdressBtn = findViewById(R.id.saveAdressBtn);


        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.google_map);


        // CureentLocation
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);


        mapFragment.getMapAsync(this);

        currentLocationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCurrentLocation();
            }
        });

        saveAdressBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LatLng loc = marker.getPosition();
                saveAdressBtn.setEnabled(false);
                saveAdressBtn.setText("Please wait ...");
                addressDetails.setLat(loc.latitude);
                addressDetails.setLon(loc.longitude);

                addressDetails.setId(System.currentTimeMillis());
                // uploadToFirebase();

                Intent i = new Intent(getApplicationContext(), ChoosePickUpAddressActivity.class);
                if(!addressDetails.getType().equals("pickup")){
                    saveToLocalPickupDataBase();
                    i = new Intent(getApplicationContext(), ChooseDropAddressActivity.class);
                }else{
                    saveToLocalDropDataBase();
                }
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }
        });


    }

    private void saveToLocalDropDataBase() {
        DropViewModel model = new ViewModelProvider(this).get(DropViewModel.class);
        model.addAddress(addressDetails);
    }

    private void saveToLocalPickupDataBase() {
        PickupViewModel model = new ViewModelProvider(this).get(PickupViewModel.class);
        model.addPickUpAddress(addressDetails);
    }


    private void showCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Dexter.withContext(getApplicationContext()).withPermission(Manifest.permission.ACCESS_FINE_LOCATION).withListener(new PermissionListener() {
                @Override
                public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                    if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    Task<Location> task = fusedLocationProviderClient.getLastLocation();
                    task.addOnSuccessListener(new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            if(marker != null){
                                marker.remove();
                            }

                            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                            marker = map.addMarker(new MarkerOptions()
                                    .position(latLng)
                                    .title(CURRENT_TITLE
                                    )
                            );
                            map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 14));

                        }
                    });
                }

                @Override
                public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                }

                @Override
                public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                    permissionToken.continuePermissionRequest();
                }
            }).check();

        }else{
            Task<Location> task = fusedLocationProviderClient.getLastLocation();
            task.addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if(marker != null){
                        marker.remove();
                    }

                    LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                    marker = map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title(CURRENT_TITLE
                            )
                    );
                    map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 14));

                }
            });
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.setBuildingsEnabled(false);

        String Location = addressDetails.getAddress()+" "+addressDetails.getArea()+" "+addressDetails.getDistrict();
        List<Address> addressList = null;

        if (Location != null || !Location.equals("")) {
            Geocoder geocoder = new Geocoder(getApplicationContext());
            try {
                addressList = geocoder.getFromLocationName(Location, 1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (marker != null) {
                marker.remove();
            }

            Address address = addressList.get(0);
            LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
            marker = map.addMarker(new MarkerOptions().position(latLng).title(CURRENT_TITLE));
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 14));

        }
        map.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                if(marker != null){
                    marker.remove();
                }

                marker = map.addMarker(new MarkerOptions()
                        .position(latLng)
                        .title(CURRENT_TITLE)
                );
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 14));

            }
        });
    }

}