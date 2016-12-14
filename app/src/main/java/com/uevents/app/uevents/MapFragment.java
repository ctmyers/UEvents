package com.uevents.app.uevents;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Calendar;

/**
 * Created by Daniel on 11/29/16.
 */

//Map fragment that displays nearby events using markers and updates the user's current location
public class MapFragment extends Fragment implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener,GoogleMap.OnInfoWindowClickListener {

    private GoogleMap mMap;
    private Location mCurrentLocation;
    private GoogleApiClient mGoogleApiClient;
    private boolean mRequestingLocationUpdates;
    private LocationRequest mLocationRequest;


    //Inner class for the custom info windows when the markers are clicked
    class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

        private View mContents;

        CustomInfoWindowAdapter() {
            mContents = getActivity().getLayoutInflater().inflate(R.layout.custom_info_contents, null);
        }

        @Override
        public View getInfoWindow(Marker marker) {
            return null;
        }

        //Sets the contents of the info window with title and people attending
        @Override
        public View getInfoContents(Marker marker) {
            TextView title = (TextView)mContents.findViewById(R.id.info_title);
            title.setText(marker.getTitle());
            TextView snip = (TextView)mContents.findViewById(R.id.info_people);
            snip.setText(marker.getSnippet());
            final Button button  = (Button)mContents.findViewById(R.id.going_button);

            return mContents;

        }
    }

    //On create for the map fragment that sets up the google api for location requests
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.map_fragment, container, false);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //Initialize the googleapiclient to listen for locations
        mGoogleApiClient = new GoogleApiClient.Builder(mapFragment.getContext())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        //Update location every 2 seconds
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(2000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        mRequestingLocationUpdates = true;

        return fragmentView;
    }

    //Click listener for the marker's info window
    @Override
    public void onInfoWindowClick(Marker marker) {
        //Add the event to the attending events list and show a toast message
        Toast.makeText(getActivity(),"Going to "+marker.getTitle(),Toast.LENGTH_LONG).show();
        Event e = EventList.getEvent(marker.getTitle());
        EventList.attendEvent(e);
        marker.hideInfoWindow();
    }

    //Called to intialize the map
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        //Checks to see if the location permission is available
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
            return;
        }
        //set the window click listener and the map location settings
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.setOnInfoWindowClickListener(this);

        //Add markers for all events to the map
        for(Event e: EventList.allEvents){
            mMap.addMarker(new MarkerOptions()
            .position(new LatLng(e.lat,e.lon))
            .title(e.title)
            .snippet(e.currAttendance+"/"+e.maxAttendance));
        }
        mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter());
    }

    //Called once the location services is connected
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
            return;
        }
        //Sets the current location to the last updated location
        mCurrentLocation = LocationServices
                .FusedLocationApi
                .getLastLocation(mGoogleApiClient);

        initCamera();
    }

    //Called when we want to stop location updates
    public void stopLocationUpdates() {

        LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    //Once the fragment starts, connect to google api
    public void onStart() {
        mGoogleApiClient.connect();
        mRequestingLocationUpdates = true;
        super.onStart();
    }

    //Once the fragment stops, disconnect the google api
    public void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    //Initialize the camera to zoom in on our location
    private void initCamera() {

        if(mCurrentLocation != null){
            LatLng latlng = new LatLng(mCurrentLocation.getLatitude(),mCurrentLocation.getLongitude());
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng,16f));
        }

    }

    //Called when a new event has been added and needs to be updated to the map
    public void update(){
        mMap.clear();
        for(Event e: EventList.allEvents){
            mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(e.lat,e.lon))
                    .title(e.title)
                    .snippet(e.currAttendance+"/"+e.maxAttendance));
        }
    }

    //Changed our current location to the new location
    @Override
    public void onLocationChanged(Location location) {
        mCurrentLocation = location;
    }
}
