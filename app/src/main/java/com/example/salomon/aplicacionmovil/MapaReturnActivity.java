package com.example.salomon.aplicacionmovil;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.DateFormat;
import java.util.Date;

public class MapaReturnActivity  extends FragmentActivity implements
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener,
        OnMapReadyCallback {

    public static final long UPDATE_INTERVAL_IN_MILLISECONDS = 5000;
    public static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS = UPDATE_INTERVAL_IN_MILLISECONDS/2;
    protected GoogleApiClient mGoogleClient;
    protected LocationRequest mLocationRequest;

    protected Location mCurrentLocation;
    LatLng mMyLocationLatLng;

    private GoogleMap mMap;

    protected Boolean mRequestingLocationUpdates;
    protected String mLastUpdateTime;

    private Button btnEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa_return);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.frMapa);
        mapFragment.getMapAsync(this);

        mRequestingLocationUpdates = true;
        btnEnviar = findViewById(R.id.btnEnviar);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMyLocationLatLng = new LatLng(mCurrentLocation.getLatitude(),mCurrentLocation.getLongitude());
                Intent returnIntent = new Intent();
                returnIntent.putExtra("Latitud",String.valueOf(mCurrentLocation.getLatitude()));
                returnIntent.putExtra("Longitud",String.valueOf(mCurrentLocation.getLongitude()));
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }
        });
        buildGoogleApiClient();
    }

    @Override
    protected void onStart(){
        super.onStart();
        mGoogleClient.connect();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(mGoogleClient.isConnected() && mRequestingLocationUpdates){
            startLocationUpdates();
        }
    }

    @Override
    protected void onPause() {
        if(mGoogleClient.isConnected()){
            stopLocationUpdates();
        }
        super.onPause();
    }

    @Override
    protected void onStop() {
        if(mGoogleClient.isConnected()){
            mGoogleClient.disconnect();
        }
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    protected synchronized void buildGoogleApiClient(){
        mGoogleClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        createLocationRequest();
    }

    private void createLocationRequest(){
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(UPDATE_INTERVAL_IN_MILLISECONDS);
        mLocationRequest.setFastestInterval(FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (mCurrentLocation == null){
            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                return;
            }
            mCurrentLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleClient);
            mLastUpdateTime = DateFormat.getTimeInstance().format(new Date());
        }

        if(mRequestingLocationUpdates){
            startLocationUpdates();
        }
    }

    protected void startLocationUpdates(){
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleClient,mLocationRequest,this);
    }

    protected void stopLocationUpdates(){
        LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleClient,this);
    }

    @Override
    public void onConnectionSuspended(int i) {
        mGoogleClient.connect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        //mGoogleClient.reconnect();
    }

    @Override
    public void onLocationChanged(Location location) {
        mCurrentLocation = location;
        mLastUpdateTime = DateFormat.getTimeInstance().format(new Date());
        mMyLocationLatLng = new LatLng(mCurrentLocation.getLatitude(),mCurrentLocation.getLongitude());
        /*tvLatitud.setText(String.valueOf(mCurrentLocation.getLatitude()));
        tvLongitud.setText(String.valueOf(mCurrentLocation.getLongitude()));
        tvExactitud.setText(String.valueOf(mCurrentLocation.getAccuracy()));
        //Log.i(TAG, "Latitud:"+Double.toString(mCurrentLocation.getLatitude()));
        //Log.i(TAG, "Longitud:"+Double.toString(mCurrentLocation.getLongitude()));*/

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mMyLocationLatLng,15));
        mMap.clear();
        mMap.addMarker(new MarkerOptions()
                .title("Mi Ubicación")
                .position(mMyLocationLatLng)
        );
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            return;
        }

        mMap.setMyLocationEnabled(true);
    }
}
