package com.example.my38_locationmap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "main:MainActivity";

    SupportMapFragment mapFragment;
    GoogleMap map;
    EditText editText;

    MarkerOptions myMarker;

    Location myLoc, markerLoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkDangerousPermissions();

        editText = findViewById(R.id.editText);

        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {
                Log.d(TAG, "onMapReady: Google Map is Ready!!!");

                map = googleMap;
                try {
                    // ??? ????????? ?????? ?????? ?????????
                    map.setMyLocationEnabled(true);
                }catch (SecurityException e){

                }

                // ????????? ?????????????????? ???????????? - ??????????????? ????????? ?????????.
                map.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

                    @Override
                    public View getInfoWindow(Marker marker) {
                        return null;
                    }

                    @Override
                    public View getInfoContents(Marker marker) {
                        LinearLayout info = new LinearLayout(getApplicationContext());
                        info.setOrientation(LinearLayout.VERTICAL);

                        TextView title = new TextView(getApplicationContext());
                        title.setTextColor(Color.BLACK);
                        title.setGravity(Gravity.CENTER);
                        title.setTypeface(null, Typeface.BOLD);
                        title.setText(marker.getTitle());

                        TextView snippet = new TextView(getApplicationContext());
                        snippet.setTextColor(Color.GRAY);
                        snippet.setGravity(Gravity.LEFT);
                        snippet.setText(marker.getSnippet());

                        info.addView(title);
                        info.addView(snippet);

                        return info;
                    }
                });

            }
        });

        // ????????? ?????????
        MapsInitializer.initialize(this);

        // ????????? ??????
        findViewById(R.id.btnLoc).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestMyLocation();

            }
        });

        // ??????????????? ????????? ????????? ?????? ?????? ??????
        findViewById(R.id.btnClick).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText.getText().toString().length() > 0){
                    Location location = getLocationFromAddress
                            (getApplicationContext(), editText.getText().toString());

                    // ???????????? ??? ????????? ???????????? ????????????
                    showCurrentLocation(location);
                }
            }
        });


    }

    // ?????? ????????? ????????? Location ????????? ??????????????? ???????????? ?????????
    private Location getLocationFromAddress(Context context, String address) {
        Geocoder geocoder = new Geocoder(context);
        List<Address> addresses;
        Location resLocation = new Location("");

        try {                                   //   ????????????,   ?????? 5???
            addresses = geocoder.getFromLocationName(address, 5);
            if((addresses == null) || (addresses.size() == 0) ){
                return null;
            }

            // ???????????? ????????????????????? ?????? ????????? ????????? 0?????? ?????? ????????????
            Address addressLoc = addresses.get(0);
            resLocation.setLatitude(addressLoc.getLatitude());
            resLocation.setLongitude(addressLoc.getLongitude());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return resLocation;
    }

    private void requestMyLocation() {
        LocationManager manager =
                (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        try{
            long minTime = 10000;
            float minDistance = 0;

            manager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    minTime,
                    minDistance,
                    new LocationListener() {
                        @Override
                        public void onLocationChanged(@NonNull Location location) {
                            showCurrentLocation(location);
                        }
                    }
            );

            /*manager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER,
                    minTime,
                    minDistance,
                    new LocationListener() {
                        @Override
                        public void onLocationChanged(@NonNull Location location) {
                            showCurrentLocation(location);
                        }
                    }
            );*/

            // ?????????????????? ???????????? ????????? ?????? ????????????
            // ????????? ????????? ????????? ???????????? ??????
            Location lastLocation =
                    manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if(lastLocation != null){
                Double latitude = lastLocation.getLatitude();  // ??????
                Double longitude = lastLocation.getLongitude();  // ??????

                String msg = "Latitude : " + latitude
                        + "\nLongitude : " + longitude;
                Log.d(TAG, "requestMyLocation: " + msg);
            }

        }catch (SecurityException e){

        }

    }

    private void showCurrentLocation(Location location) {
        // ????????? ????????? ???????????? LatLng ????????? ????????? => ???????????????
        LatLng curPoint =
                new LatLng(location.getLatitude(), location.getLongitude());
        // ?????? ????????? ??????????????? ??????
        myLoc = location;

        String msg = "Latitude : " + curPoint.latitude
                + "\nLongitude : " + curPoint.longitude;
        Log.d(TAG, "showCurrentLocation1: " + msg);

        map.animateCamera(CameraUpdateFactory.newLatLngZoom(curPoint, 18));

        // ???????????? : Location ?????? : ???????????? DB??? API?????? ?????????
        Location location1 = new Location("");
        location1.setLatitude(35.153817);
        location1.setLongitude(126.8889);
        String name = "????????????";
        String phone = "010-1111-1111";
        showMyLocationMarker(location1, name, phone);

        Location location2 = new Location("");
        location2.setLatitude(35.153825);
        location2.setLongitude(126.8885);
        name = "????????????";
        phone = "010-1111-2222";
        showMyLocationMarker(location2, name, phone);
    }

    // location ????????? ?????? ???????????? ????????? ????????????
    private void showMyLocationMarker(Location location,
                                      String name, String phone) {
        markerLoc = location;
        // ????????? ?????? ???????????? ??????????????? ???????????? ????????? ?????????
        int distance = getDistance(myLoc, markerLoc);

        if(myMarker == null){
            myMarker = new MarkerOptions();
            myMarker.position(
                    new LatLng(location.getLatitude(), location.getLongitude()));
            myMarker.title("??? " + name);
            myMarker.snippet(phone + "\n?????? => " + distance + " m");
            myMarker.icon(BitmapDescriptorFactory.fromResource(R.drawable.mylocation));
            map.addMarker(myMarker);
            myMarker = null;
        }

    }

    private int getDistance(Location myLoc, Location markerLoc) {
        double distance = 0;
        // ????????? ???????????? Location ????????????
        distance = myLoc.distanceTo(markerLoc);

        return (int)distance;
    }

    private void checkDangerousPermissions() {
        String[] permissions = {
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        };

        int permissionCheck = PackageManager.PERMISSION_GRANTED;
        for (int i = 0; i < permissions.length; i++) {
            permissionCheck = ContextCompat.checkSelfPermission(this, permissions[i]);
            if (permissionCheck == PackageManager.PERMISSION_DENIED) {
                break;
            }
        }

        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "?????? ??????", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "?????? ??????", Toast.LENGTH_LONG).show();

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[0])) {
                Toast.makeText(this, "?????? ?????? ?????????.", Toast.LENGTH_LONG).show();
            } else {
                ActivityCompat.requestPermissions(this, permissions, 1);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1) {
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, permissions[i] + " ????????? ?????????.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, permissions[i] + " ????????? ???????????? ??????.", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}