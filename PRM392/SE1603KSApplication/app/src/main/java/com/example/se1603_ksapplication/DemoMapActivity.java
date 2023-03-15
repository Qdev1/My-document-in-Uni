package com.example.se1603_ksapplication;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class DemoMapActivity extends AppCompatActivity
        implements OnMapReadyCallback, GoogleMap.OnMapLoadedCallback, LocationListener {
    private GoogleMap googleMap;
    private Location currentLocation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_map);
        SupportMapFragment mapFragment = (SupportMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment_map);
        mapFragment.getMapAsync(this);
    }
    @Override
    protected void onStart() {
        super.onStart();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestCurrentLocation();
        }
    }
    @Override
    public void onMapLoaded() {
        //Add marker
        LatLng latLng = new LatLng(21.034169392766746, 105.85240341028133);
        googleMap.addMarker(new MarkerOptions()
                .title("Hoa Lac - Ha Noi")
                .position(latLng));
        if (currentLocation != null) {
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                    new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()), 10));
        } else {
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
        }
        // Add polylines to the map.
        // Polylines are useful to show a route or some other connection between points.
        googleMap.addPolyline(new PolylineOptions()
                .clickable(true)
                .add(
                        new LatLng(-35.016, 143.321),
                        new LatLng(-34.747, 145.592),
                        new LatLng(-34.364, 147.891),
                        new LatLng(-33.501, 150.217),
                        new LatLng(-32.306, 149.248),
                        new LatLng(-32.491, 147.309)));
    }
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        this.googleMap = googleMap;
        googleMap.setOnMapLoadedCallback(this);
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        this.currentLocation = location;
    }
    private final int REQUEST_LOCATION = 2;
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void requestCurrentLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this)
                            .setTitle("Reason for Request Location")
                            .setMessage("Please grant permission to access location service")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // Handle action when user click OK button
                                }
                            });

                }

            }
            requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        } else {
            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0,0, this);
            currentLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_LOCATION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Location service is granted", Toast.LENGTH_LONG).show();
                    requestCurrentLocation();
                } else {
                    Toast.makeText(this, "Location service is NOT granted", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}