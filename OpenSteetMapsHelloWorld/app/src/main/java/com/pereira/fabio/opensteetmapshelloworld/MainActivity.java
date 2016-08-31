package com.pereira.fabio.opensteetmapshelloworld;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.osmdroid.api.IMapController;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.mapview_open_street)
    MapView openStreetMapView;
    private Location lastLocation;
    IMapController mapController;
    GeoPoint startPoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initOpenStreetMapView();
    }


    private void initOpenStreetMapView(){
        org.osmdroid.tileprovider.constants.OpenStreetMapTileProviderConstants.setUserAgentValue(BuildConfig.APPLICATION_ID);
        // TODO: Descobrir o que é tile source e mapnik
        openStreetMapView.setTileSource(TileSourceFactory.MAPNIK);
        // Add default zoom buttons
        openStreetMapView.setBuiltInZoomControls(true);
        // Enable zoom with 2 fingers
        openStreetMapView.setMultiTouchControls(true);

        LocationManager mLocationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        lastLocation = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        mapController = openStreetMapView.getController();

        startPoint = new GeoPoint(lastLocation);
        mapController.setCenter(startPoint);
        mapController.setZoom(15);
    }

}
