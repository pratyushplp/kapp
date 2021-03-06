package com.kimbutech.kapp;

import android.Manifest;

import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;

import android.location.Location;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;

import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapsActivity extends FragmentActivity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener,
        GoogleMap.OnInfoWindowClickListener,NavigationView.OnNavigationItemSelectedListener

{
    private String[] key;

    //commented asof 16th april 2016 is.an.lognod
    /*
    private double currentLatitude;
    private double currentLongitude;

    */


    public static final String TAG = MapsActivity.class.getSimpleName();
    public  boolean satelliteMap=false;
    //is.an.lognod


    private GroundOverlay mGroundOverlayBlock10;
    Marker ttch, mttca, mttcb, multihall, kubh, social_hall, kulibpark, adminBlock, kulib, cv_raman, sosBlock, khetanpark, btBlock, mBlock, lecture, newdocse, cngBlock, pharBlock, canteen2, kucse, kusq, kughi, enved,
            football_ground, mttl, mpark, kuswim, volley, kucafe, basketball_court, kughii, kufsqu, kughiii,
            chemnmath;
    Marker itPark;
    boolean beenInside = false;


    //is.an.lognod

    //private FusedLocationProviderApi fusedLocationProviderApi = LocationServices.FusedLocationApi;
    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;

    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient; //Provide entry point to google play service
    private LocationRequest mLocationRequest;
    Location userLocation;
    double[] lat = new double[36];
    double[] lon = new double[36];

    LatLng libpark,admin,library,/*3*/cvraman,/*4*/khetan/*5 khetan park saraswoti temple*/,schoolosBlock,/*6*/ biotechBlock;//7
    LatLng mechanicalBlock;//8
    LatLng newcomp;//9 computer engineering block
    LatLng lechall;//10 Management block
    LatLng civilBlock;//11
    LatLng pharmacyBlock;//12
    LatLng mess;//13
    LatLng compdepart;//14
    LatLng kukusq;//15
    LatLng ttchostel;//16 TTC Boys Hostel
    LatLng kukughi;//17
    LatLng kukubh;//18
    LatLng enve; //19
    LatLng socialhall; //aa//20
    LatLng multi;//21 multi purpose hall
    LatLng footballg; //aa //22
    LatLng ttca;//23 TTC (a)
    LatLng ttcb;//24 TTC (b)
    LatLng ttl;//25 Turbine Testing Lab
    LatLng mainpark;//26 main entrance parking
    LatLng swim;//27swimming pool
    LatLng volleyballc;//28vollyball court
    LatLng kuffc;//29
    LatLng basketballc;// aa//30basketball court
    LatLng kukughii;//31
    LatLng kufsq;//32 family staff quater
    LatLng kukughiii; //aa //33
    LatLng chemandmath;//34 aquatic ecology Center
    LatLng iTPark;


    public void fetchCoordinates() {
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        for (int i = 1; i <= 35; i++) {
            lat[i] = databaseAccess.dataTodouble(i, 3);
            lon[i] = databaseAccess.dataTodouble(i, 4);
        }
        libpark = new LatLng(lat[1], lon[1]); //1
        admin = new LatLng(lat[2], lon[2]);//2
        library = new LatLng(lat[3], lon[3]);//3
        cvraman = new LatLng(lat[4], lon[4]);//4
        khetan = new LatLng(lat[5], lon[5]);//5 khetan park saraswoti temple
        schoolosBlock = new LatLng(lat[6], lon[6]);//6
        biotechBlock = new LatLng(lat[7], lon[7]);//7
        mechanicalBlock = new LatLng(lat[8], lon[8]);//8
        newcomp = new LatLng(lat[9], lon[9]);//9 computer engineering block
        lechall = new LatLng(lat[10], lon[10]);//10 Management block
        civilBlock = new LatLng(lat[11], lon[11]);//11
        pharmacyBlock = new LatLng(lat[12], lon[12]);//12
        mess = new LatLng(lat[13], lon[13]);//13
        compdepart = new LatLng(lat[14], lon[14]);//14
        kukusq = new LatLng(lat[15], lon[15]);//15
        ttchostel = new LatLng(lat[16], lon[16]);//16 TTC Boys Hostel
        kukughi = new LatLng(lat[17], lon[17]);//17
        kukubh = new LatLng(lat[18], lon[18]);//18
        enve = new LatLng(lat[19], lon[19]); //19
        socialhall = new LatLng(lat[20], lon[20]); //aa//20
        multi = new LatLng(lat[21], lon[21]);//21 multi purpose hall
        footballg = new LatLng(lat[22], lon[22]); //aa //22
        ttca = new LatLng(lat[23], lon[23]);//23 TTC (a)
        ttcb = new LatLng(lat[24], lon[24]);//24 TTC (b)
        ttl = new LatLng(lat[25], lon[25]);//25 Turbine Testing Lab
        mainpark = new LatLng(lat[26], lon[26]);//26 main entrance parking
        swim = new LatLng(lat[27], lon[27]);//27swimming pool
        volleyballc = new LatLng(lat[28], lon[28]);//28vollyball court
        kuffc = new LatLng(lat[29], lon[29]);//29
        basketballc = new LatLng(lat[30], lon[30]);// aa//30basketball court
        kukughii = new LatLng(lat[31], lon[31]);//31
        kufsq = new LatLng(lat[32], lon[32]);//32 family staff quater
        kukughiii = new LatLng(lat[33], lon[33]); //aa //33
        chemandmath = new LatLng(lat[34], lon[34]);//34 aquatic ecology Center
        iTPark = new LatLng(lat[35], lon[35]);
       /* for(int i=1;i<=33;i++)
    {
        lat[i] = databaseAccess.dataTodouble(i,3);
        lon[i] = databaseAccess.dataTodouble(i,4);
    }*/
    }


    String[] name;
    String[] nameScrolling;

    float initialZoom = 17.3f;
    boolean mRequestingLocation = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_maps);
        setUpMapIfNeeded();

        fetchCoordinates();



        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        //Create the LocationRequest object
        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(1000)
                //10 Seconds in millisecond
                .setFastestInterval(10 * 1000);


        //1 second, in milliseconds
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        Bundle b = getIntent().getExtras();
        name = b.getStringArray("data");
        nameScrolling = name;
        /*
        changes the camera level to the previous zoom level
        newly added!!
        16th april 2016
        is.an.lognod
        Note: conditon removed form on location changed method and implemented here
        */

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {


            return;
        }
        //this displays the location of the user
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setMapToolbarEnabled(false);
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener(){

            @Override
            public void onMapClick(LatLng latLng) {
                if(!satelliteMap){
                    mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);


                    satelliteMap=true;
                }else if (satelliteMap){
                    mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

                    satelliteMap=false;
                }

                Toast.makeText(MapsActivity.this, "Click", Toast.LENGTH_SHORT).show();
            }
        });
        mMap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(CameraPosition cameraPosition) {
                /*Location mylocation = mMap.getMyLocation();
                LatLng myLatLng = new LatLng(mylocation.getLatitude(), mylocation.getLongitude());
                CameraPosition myPosition = new CameraPosition.Builder()
                        .target(myLatLng).zoom(17).bearing(mylocation.getBearing()).build();
                mMap.animateCamera(
                        CameraUpdateFactory.newCameraPosition(myPosition));*/

                //this conditon is removed in the onLocaitonChanged Method
                if (cameraPosition.zoom < 16.0f) {


                    mMap.moveCamera(CameraUpdateFactory
                            .newLatLngZoom(new LatLng(27.6186480, 85.5375810), (float) 16.9));
                    mMap.animateCamera(CameraUpdateFactory.zoomTo(initialZoom), 2000, null);


                }


            }
        });
        Bundle m = getIntent().getExtras();
        String[] name = m.getStringArray("data");
        getNumbers(name);
        displayMarkers();
        mMap.setOnInfoWindowClickListener(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(userLocation!=null){
                    Intent sendIntent = new Intent(Intent.ACTION_SENDTO);
                    sendIntent.setData(Uri.parse("smsto:"));
                    sendIntent.putExtra("sms_body", "I am in "+userLocation.getLatitude()+","+userLocation.getLongitude());
                    if (sendIntent.resolveActivity(getPackageManager()) != null) {
                        startActivity(sendIntent);
                    } else {
                        Toast.makeText(MapsActivity.this, "Cannot open SMS app.", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Snackbar.make(view, "Can not determine your location", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }



            }
        });

    }


    String getNumber, getForBack;


    private void getNumbers(String[] name) {

        getNumber = name[1];
        getForBack = name[0];

    }


    @Override
    protected void onResume() {
        super.onResume();
        //location = null;
        //this eliminates the problem of not requesting the location after resume
        setUpMapIfNeeded();
        getNumbers(name);
        displayMarkers();
       /*
        if(!mRequestingLocation){
            handleNewLocation(location);
        }
*/
        mGoogleApiClient.connect();


    }

    @Override
    protected void onPause() {
        super.onPause();

        if (mGoogleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.
                    removeLocationUpdates(mGoogleApiClient, this);
            mGoogleApiClient.disconnect();
            mRequestingLocation = false;
            // location = null;
        }


    }


    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already
        // instantiated the map.
        if (mMap == null) {
            mMap = ((SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map))
                    .getMap();


            if (mMap != null) {
                setUpMap();

            }
        }
    }


    private void setUpMap() {


        mMap.moveCamera(CameraUpdateFactory
                .newLatLngZoom(new LatLng(27.6186480, 85.5375810), (float) 16.9));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(initialZoom), 2000, null);

        groundOverlay();


    }

    //HashMap <String, Integer> mMarkers = new HashMap<String, Integer>();
    Marker marker;
    private int counter = 1;
    private int c = 0;

    private void handleNewLocation(Location location) {

        userLocation=location;
        //yo part bharkhar change garaeko

        /*CameraPosition.Builder position = CameraPosition.builder()
                .bearing(location.getBearing())
                .zoom(mMap.getCameraPosition().zoom)
                .tilt(mMap.getCameraPosition().tilt);*/

        double currentLatitude = location.getLatitude();
        double currentLongitude = location.getLongitude();
        LatLng latlng = new LatLng(currentLatitude, currentLongitude);


        if (counter == 0) {
            mMap.clear();
            groundOverlay();
            displayMarkers();
            /*MarkerOptions options = new MarkerOptions()
                    .position(latlng)
                    .icon(BitmapDescriptorFactory
                            .fromResource(R.drawable.location));
            marker = mMap.addMarker(options);*/
            counter++;

            //

        }
        /*else {
            //commented by is.an.lognod
            //Toast.makeText(this, String.valueOf(location.getBearing()), Toast.LENGTH_SHORT).show();
           // marker.setPosition(latlng);
        }*/


        // mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(27.6186480, 85.5375810), (float) 16.3));
        // mMap.animateCamera(CameraUpdateFactory.zoomTo((float) 16.3), 1000, null);
        // mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(currentLatitude, currentLongitude), (float) 16.3));
        // mMap.animateCamera(CameraUpdateFactory.zoomTo((float) 19), 2000, null);


        //Dynamic Location updates
        Location location_current = new Location("point B");
        location_current.setLatitude(currentLatitude);
        location_current.setLongitude(currentLongitude);

        //For KUBH  27.6177657,85.5367331
        Location location_itPark = new Location("point A");
        location_itPark.setLatitude(27.617349);
        location_itPark.setLongitude(85.526768);

        //Toast.makeText(getApplicationContext(), "" + location_kubh.
        //       distanceTo(location_current), Toast.LENGTH_SHORT).show();

        if (location_itPark.distanceTo(location_current) <= 100) {
            itPark = mMap.addMarker(new MarkerOptions()
                    .position(iTPark)
                    .title("IT Park")
                    .snippet("IT Meet 2016"));

            // mMarkers.put(kubh.getId(), myObject.getId());
            counter = 1;
        }

        if (itPark != null && location_itPark.distanceTo(location_current) > 100) {
            counter = 0;
            itPark = null;
        }


        //For KUBH  27.6177657,85.5367331
        Location location_kubh = new Location("point A");
        location_kubh.setLatitude(27.6177657);
        location_kubh.setLongitude(85.5367331);

        //Toast.makeText(getApplicationContext(), "" + location_kubh.
        //       distanceTo(location_current), Toast.LENGTH_SHORT).show();

        if (location_kubh.distanceTo(location_current) <= 43) {
            kubh = mMap.addMarker(new MarkerOptions()
                    .position(kukubh)
                    .title("Kathmandu University Boys Hostel")
                    .snippet("Hostel"));

            // mMarkers.put(kubh.getId(), myObject.getId());
            counter = 1;
        }

        if (kubh != null && location_kubh.distanceTo(location_current) > 43) {
            counter = 0;
            kubh = null;
        }


        //For Social Hall - 27.61783,85.5364949
        Location location_socialhall = new Location("point A");
        location_socialhall.setLatitude(27.61783);
        location_socialhall.setLongitude(85.5364949);

        if (location_socialhall.distanceTo(location_current) <= 32) {
            social_hall = mMap.addMarker(new MarkerOptions()
                    .position(socialhall)
                    .title("Social Hall")
                    .snippet("Indoor Games and TV room"));
            counter = 1;
        }
        if (social_hall != null && location_socialhall.distanceTo(location_current) > 32) {
            counter = 0;
            social_hall = null;
        }

        //For KU Library Park   27.6193039,85.5386311
        Location location_kulibpark = new Location("point A");
        location_kulibpark.setLatitude(27.6193039);
        location_kulibpark.setLongitude(85.5386311);


        if (location_kulibpark.distanceTo(location_current) <= 39) {

            kulibpark = mMap.addMarker(new MarkerOptions()
                    .position(libpark)
                    .title("Main Square")
                    .snippet("Block 1"));
            counter = 1;


        }
        if (kulibpark != null && location_kulibpark.distanceTo(location_current) > 39) {
            counter = 0;
            kulibpark = null;
        }

        //For admin block  27.6195606,85.5386576
        Location location_admin = new Location("point A");
        location_admin.setLatitude(27.6195606);
        location_admin.setLongitude(85.5386576);


        if (location_admin.distanceTo(location_current) <= 42) {
            adminBlock = mMap.addMarker(new MarkerOptions()
                    .position(admin)
                    .title("Administrative Block")
                    .snippet("Block 2"));
            counter = 1;
        }

        if (adminBlock != null && location_admin.distanceTo(location_current) > 42) {
            counter = 0;
            adminBlock = null;
        }


        //For KU Library   27.6189249,85.5386187
        Location location_kulib = new Location("point A");
        location_kulib.setLatitude(27.6189249);
        location_kulib.setLongitude(85.5386187);

        if (location_kulib.distanceTo(location_current) <= 40) {
            kulib = mMap.addMarker(new MarkerOptions()
                    .position(library)
                    .title("KU Central Library")
                    .snippet("Block 3"));
            counter = 1;

        }
        if (kulib != null && location_kulib.distanceTo(location_current) > 40) {
            counter = 0;
            kulib = null;
        }

        //For CV Raman Auditorium - 27.6192472,85.5388976
        Location location_cvraman = new Location("point A");
        location_cvraman.setLatitude(27.6192472);
        location_cvraman.setLongitude(85.5388976);

        if (location_cvraman.distanceTo(location_current) <= 42) {
            cv_raman = mMap.addMarker(new MarkerOptions()
                    .position(cvraman)
                    .title("CV Raman Auditorium")
                    .snippet("Meeting Halls and Auditorium"));
            counter = 1;
        }
        if (cv_raman != null && location_cvraman.distanceTo(location_current) > 42) {
            counter = 0;
            cv_raman = null;
        }

        //For Khetan Park   27.6200878,85.5384453
        Location location_khetanpark = new Location("point A");
        location_khetanpark.setLatitude(27.5384453);
        location_khetanpark.setLongitude(85.5384453);


        if (location_khetanpark.distanceTo(location_current) <= 47) {

            khetanpark = mMap.addMarker(new MarkerOptions()
                    .position(khetan)
                    .title("Khetan Park / Saraswoti Temple")
                    .snippet("Park"));
            counter = 1;


        }
        if (khetanpark != null && location_khetanpark.distanceTo(location_current) > 47) {
            counter = 0;
            khetanpark = null;
        }

        //For School of Science   27.6189380,85.5393359
        Location location_sos = new Location("point A");
        location_sos.setLatitude(27.6189380);
        location_sos.setLongitude(85.5393359);

        if (location_sos.distanceTo(location_current) <= 45) {
            sosBlock = mMap.addMarker(new MarkerOptions()
                    .position(schoolosBlock)
                    .title("School of Science Block")
                    .snippet("Block 6"));
            counter = 1;
        }
        if (sosBlock != null && location_sos.distanceTo(location_current) > 45) {
            counter = 0;
            sosBlock = null;
        }

        //For Biotech  27.6192840,85.5395116
        Location location_bt = new Location("point A");
        location_bt.setLatitude(27.6192840);
        location_bt.setLongitude(85.5395116);

        if (location_bt.distanceTo(location_current) <= 45) {
            btBlock = mMap.addMarker(new MarkerOptions()
                    .position(biotechBlock)
                    .title("BioTechnology Block")
                    .snippet("Block 7"));
            counter = 1;
        }
        if (btBlock != null && location_bt.distanceTo(location_current) > 45) {
            counter = 0;
            btBlock = null;
        }

        //For mechanical  27.6197115,85.5393875
        Location location_mechanical = new Location("point A");
        location_mechanical.setLatitude(27.6197115);
        location_mechanical.setLongitude(85.5393875);

        if (location_mechanical.distanceTo(location_current) <= 45) {
            mBlock = mMap.addMarker(new MarkerOptions()
                    .position(mechanicalBlock)
                    .title("Mechanical and Electrical Block")
                    .snippet("Block 8"));
            counter = 1;
        }
        if (mBlock != null && location_mechanical.distanceTo(location_current) > 45) {
            counter = 0;
            mBlock = null;
        }

        //For New DoCSE   27.6199428,85.5390331
        Location location_newcomp = new Location("point A");
        location_newcomp.setLatitude(27.6199428);
        location_newcomp.setLongitude(85.5390331);

        if (location_newcomp.distanceTo(location_current) <= 39) {
            newdocse = mMap.addMarker(new MarkerOptions()
                    .position(newcomp)
                    .title("New DoCSE")
                    .snippet("Block 9"));
            counter = 1;
        }
        if (newdocse != null && location_newcomp.distanceTo(location_current) > 39) {
            counter = 0;
            newdocse = null;
        }

        //For Lecture Hall   27.6196631,85.538091
        Location location_lechall = new Location("point A");
        location_lechall.setLatitude(27.6196631);
        location_lechall.setLongitude(85.538091);

        if (location_lechall.distanceTo(location_current) <= 39) {
            lecture = mMap.addMarker(new MarkerOptions()
                    .position(lechall)
                    .title("Lecture Hall")
                    .snippet("Block 10"));
            counter = 1;
        }
        if (lecture != null && location_lechall.distanceTo(location_current) > 39) {
            counter = 0;
            lecture = null;
        }

        //For civil and geomatics   27.6192799,85.5380461
        Location location_civil = new Location("point A");
        location_civil.setLatitude(27.6192799);
        location_civil.setLongitude(85.5380461);

        if (location_civil.distanceTo(location_current) <= 40) {
            cngBlock = mMap.addMarker(new MarkerOptions()
                    .position(civilBlock)
                    .title("Civil and Geomatics Block")
                    .snippet("Block 11"));
            counter = 1;
        }
        if (cngBlock != null && location_civil.distanceTo(location_current) > 40) {
            counter = 0;
            cngBlock = null;
        }


        //For Pharmacy   27.6188990,85.5380645
        Location location_phar = new Location("point A");
        location_phar.setLatitude(27.6188990);
        location_phar.setLongitude(85.5380645);

        if (location_phar.distanceTo(location_current) <= 40) {
            pharBlock = mMap.addMarker(new MarkerOptions()
                    .position(pharmacyBlock)
                    .title("Pharmacy Block")
                    .snippet("Block 12"));
            counter = 1;
        }
        if (pharBlock != null && location_phar.distanceTo(location_current) > 40) {
            counter = 0;
            pharBlock = null;
        }


        // Mess/Canteen - 27.617944,85.5378142
        Location location_mess = new Location("point A");
        location_mess.setLatitude(27.617944);
        location_mess.setLongitude(85.5378142);

        if (location_mess.distanceTo(location_current) <= 45) {
            canteen2 = mMap.addMarker(new MarkerOptions()
                    .position(mess)
                    .title("Mess / Canteen 2")
                    .snippet("Lunch/Dinner"));
            counter = 1;
        }
        if (canteen2 != null && location_mess.distanceTo(location_current) > 45) {
            counter = 0;
            canteen2 = null;
        }


        //For KUCSE  27.6174532,85.5391800
        Location location_kucse = new Location("point A");
        location_kucse.setLatitude(27.6174532);
        location_kucse.setLongitude(85.5391800);


        if (location_kucse.distanceTo(location_current) <= 40) {
            kucse = mMap.addMarker(new MarkerOptions()
                    .position(compdepart)
                    .title("Department of Computer Science and Engineering")
                    .snippet("KUCSE"));
            counter = 1;
        }
        if (kucse != null && location_kucse.distanceTo(location_current) > 40) {
            counter = 0;
            kucse = null;
        }


        //For KU Staff Quarter 27.6176831,85.5393989
        Location location_kusq = new Location("point A");
        location_kusq.setLatitude(27.6176831);
        location_kusq.setLongitude(85.5393989);

        if (location_kusq.distanceTo(location_current) <= 48) {
            kusq = mMap.addMarker(new MarkerOptions()
                    .position(kukusq)
                    .title("Kathmandu University Staff Quater")
                    .snippet("Staff Quater"));
            counter = 1;
        }
        if (kusq != null && location_kusq.distanceTo(location_current) > 48) {
            counter = 0;
            kusq = null;
        }

        //For TTC Boys hostel  27.6174015, 85.5372052
        Location location_ttchostel = new Location("point A");
        location_ttchostel.setLatitude(27.6174015);
        location_ttchostel.setLongitude(85.5372052);

        if (location_ttchostel.distanceTo(location_current) <= 35) {
            ttch = mMap.addMarker(new MarkerOptions()
                    .position(ttchostel)
                    .title("TTC Boys Hostel")
                    .snippet("Hostel"));
            counter = 1;
        }
        if (ttch != null && location_ttchostel.distanceTo(location_current) > 35) {
            //    ttch.remove();
            counter = 0;
            ttch = null;
        }


        //For KUGH I   27.6181038,85.5393124
        Location location_kughi = new Location("point A");
        location_kughi.setLatitude(27.6181038);
        location_kughi.setLongitude(85.5393124);


        if (location_kughi.distanceTo(location_current) <= 51) {
            kughi = mMap.addMarker(new MarkerOptions()
                    .position(kukughi)
                    .title("Kathmandu University Girls Hostel I")
                    .snippet("Hostel"));
            counter = 1;
        }
        if (kughi != null && location_kughi.distanceTo(location_current) > 51) {
            counter = 0;
            kughi = null;
        }


        //For Environmental Science and Engineering Block - 27.6182764,85.5397969
        Location location_enve = new Location("point A");
        location_enve.setLatitude(27.6182764);
        location_enve.setLongitude(85.5397969);

        if (location_enve.distanceTo(location_current) <= 50) {
            enved = mMap.addMarker(new MarkerOptions()
                    .position(enve)
                    .title("Environmental Science and Engineering Block")
                    .snippet("ENVE and ENVS"));
            counter = 1;
        }
        if (enved != null && location_enve.distanceTo(location_current) > 50) {
            counter = 0;
            enved = null;
        }

        //For Multipurpose hall  27.6193721,85.5374158
        Location location_multi = new Location("point A");
        location_multi.setLatitude(27.6193721);
        location_multi.setLongitude(85.5374158);

        if (location_multi.distanceTo(location_current) <= 46) {
            multihall = mMap.addMarker(new MarkerOptions()
                    .position(multi)
                    .title("Multipurpose Hall")
                    .snippet("Hall"));
            counter = 1;
        }
        if (multihall != null && location_multi.distanceTo(location_current) > 46) {
            counter = 0;
            multihall = null;
        }


        //For KU Football Ground - 27.6186431,85.5373893
        Location location_footballg = new Location("point A");
        location_footballg.setLatitude(27.6186431);
        location_footballg.setLongitude(85.5373893);

        if (location_footballg.distanceTo(location_current) <= 55) {
            football_ground = mMap.addMarker(new MarkerOptions()
                    .position(footballg)
                    .title("KU Football Ground ")
                    .snippet("Football and Games"));
            counter = 1;
        }
        if (football_ground != null && location_footballg.distanceTo(location_current) > 55) {
            counter = 0;
            football_ground = null;
        }

        //For TTC a  27.6200704, 85.5377913
        Location location_ttca = new Location("point A");
        location_ttca.setLatitude(27.6200704);
        location_ttca.setLongitude(85.5377913);

        if (location_ttca.distanceTo(location_current) <= 44) {
            mttca = mMap.addMarker(new MarkerOptions()
                    .position(ttca)
                    .title("Technical Training Center (A)")
                    .snippet("TTC (a)"));
            counter = 1;

        }
        if (mttca != null && location_ttca.distanceTo(location_current) > 44) {
            counter = 0;
            mttca = null;
            //mMap.clear();
        }


        //For TTC b  27.6198939, 85.537474
        Location location_ttcb = new Location("point A");
        location_ttcb.setLatitude(27.6198939);
        location_ttcb.setLongitude(85.537474);

        if (location_ttcb.distanceTo(location_current) <= 50) {
            mttcb = mMap.addMarker(new MarkerOptions()
                    .position(ttcb)
                    .title("Technical Training Center (B)")
                    .snippet("TTC (b)"));
            counter = 1;
            beenInside = true;

        }
        if (mttcb != null && location_ttcb.distanceTo(location_current) > 50) {

            counter = 0;
            mttcb = null;
            //mMap.clear();
        }


        //For TTL  27.6197519, 85.5396222
        Location location_ttl = new Location("point A");
        location_ttl.setLatitude(27.6197519);
        location_ttl.setLongitude(85.5396222);

        if (location_ttl.distanceTo(location_current) <= 55) {
            mttl = mMap.addMarker(new MarkerOptions()
                    .position(ttl)
                    .title("Turbine Testing Lab")
                    .snippet("TTL"));
            counter = 1;
        }
        if (mttl != null && location_ttl.distanceTo(location_current) > 55) {
            counter = 0;
            mttl = null;
        }

        //For Main Entrance Park 27.6204824, 85.5380343
        Location location_mainpark = new Location("point A");
        location_mainpark.setLatitude(27.6204824);
        location_mainpark.setLongitude(85.5380343);

        if (location_mainpark.distanceTo(location_current) <= 39) {
            mpark = mMap.addMarker(new MarkerOptions()
                    .position(mainpark)
                    .title("Main Entrance Parking")
                    .snippet("Parking"));
            counter = 1;
        }
        if (mpark != null && location_mainpark.distanceTo(location_current) > 39) {
            counter = 0;
            mpark = null;
        }


        //For Swimming Pool    27.6193417, 85.5367187
        Location location_swim = new Location("point A");
        location_swim.setLatitude(27.6193417);
        location_swim.setLongitude(85.5367187);

        if (location_swim.distanceTo(location_current) <= 68) {
            kuswim = mMap.addMarker(new MarkerOptions()
                    .position(swim)
                    .title("Swimming Pool")
                    .snippet("swim"));
            counter = 1;
        }
        if (kuswim != null && location_swim.distanceTo(location_current) > 68) {
            counter = 0;
            kuswim = null;
        }

        //For Volleyball Court    27.6193369, 85.5370034
        Location location_volleyc = new Location("point A");
        location_volleyc.setLatitude(27.6193369);
        location_volleyc.setLongitude(85.5370034);

        if (location_volleyc.distanceTo(location_current) <= 63) {
            volley = mMap.addMarker(new MarkerOptions()
                    .position(volleyballc)
                    .title("Volleyball Court")
                    .snippet("Game"));
            counter = 1;
        }
        if (volley != null && location_volleyc.distanceTo(location_current) > 63) {
            counter = 0;
            volley = null;
        }


        //For Kucafe  27.6184459,85.5384113
        Location location_kucafe = new Location("point A");
        location_kucafe.setLatitude(27.6184459);
        location_kucafe.setLongitude(85.5384113);

        if (location_kucafe.distanceTo(location_current) <= 40) {
            kucafe = mMap.addMarker(new MarkerOptions()
                    .position(kuffc)
                    .title("KU Fast Food and Cafe")
                    .snippet("Breakfast"));

            counter = 1;
        }

        if (kucafe != null && location_kucafe.distanceTo(location_current) > 40) {
            counter = 0;
            kucafe = null;
        }


        //For KU Basketball Court - 27.6182419,85.5365391
        Location location_basketballc = new Location("point A");
        location_basketballc.setLatitude(27.6182419);
        location_basketballc.setLongitude(85.5365391);

        if (location_basketballc.distanceTo(location_current) <= 30) {
            basketball_court = mMap.addMarker(new MarkerOptions()
                    .position(basketballc)
                    .title("KU BasketBall Court")
                    .snippet("Basketball Games"));
            counter = 1;
        }
        if (basketball_court != null && location_basketballc.distanceTo(location_current) > 30) {
            counter = 0;
            basketball_court = null;
        }


        //For KUGH II   27.617729,85.5382571
        Location location_kughii = new Location("point A");
        location_kughii.setLatitude(27.617729);
        location_kughii.setLongitude(85.5382571);


        if (location_kughii.distanceTo(location_current) <= 34) {
            kughii = mMap.addMarker(new MarkerOptions()
                    .position(kukughii)
                    .title("Kathmandu University Girls Hostel II")
                    .snippet("Hostel"));
            counter = 1;
        }
        if (kughii != null && location_kughii.distanceTo(location_current) > 34) {
            counter = 0;
            kughii = null;
        }


        //For Ku family staff quater    27.6177514, 85.5383817
        Location location_kufsq = new Location("point A");
        location_kufsq.setLatitude(27.6177514);
        location_kufsq.setLongitude(85.5383817);

        if (location_kufsq.distanceTo(location_current) <= 39) {
            kufsqu = mMap.addMarker(new MarkerOptions()
                    .position(kufsq)
                    .title("KU Family Staff Quater")
                    .snippet("Staff Quater"));

            counter = 1;
        }

        if (kufsqu != null && location_kufsq.distanceTo(location_current) > 39) {
            counter = 0;
            kufsqu = null;
        }


        //For KUGH III   27.6176196,85.5379058
        Location location_kughiii = new Location("point A");
        location_kughiii.setLatitude(27.6176196);
        location_kughiii.setLongitude(85.5379058);

        if (location_kughiii.distanceTo(location_current) <= 34) {
            kughiii = mMap.addMarker(new MarkerOptions()
                    .position(kukughiii)
                    .title("Kathmandu University Girls Hostel III")
                    .snippet("Hostel"));
            counter = 1;
        }
        if (kughiii != null && location_kughiii.distanceTo(location_current) > 34) {
            counter = 0;
            kughiii = null;
        }


        //For Department of Chemistry and Mathematics - 27.6185378,85.5397442
        Location location_chemanmath = new Location("point A");
        location_chemanmath.setLatitude(27.6185378);
        location_chemanmath.setLongitude(85.5397442);

        if (location_chemanmath.distanceTo(location_current) <= 38) {
            chemnmath = mMap.addMarker(new MarkerOptions()
                    .position(chemandmath)
                    .title("Department of Chemistry and Mathematics")
                    .snippet("A.K.A. Aquatic Ecological Center"));
            counter = 1;
        }
        if (chemnmath != null && location_chemanmath.distanceTo(location_current) > 38) {
            counter = 0;
            chemnmath = null;
        }


    }


    public void onConnected(Bundle bundle) {
        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(3500);
        mLocationRequest.setFastestInterval(3500);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        // location = LocationServices.FusedLocationApi
        // .getLastLocation(mGoogleApiClient);;
        if (userLocation == null) {
            LocationServices.FusedLocationApi
                    .requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);

        } else {
            handleNewLocation(userLocation);
        }
    }


    @Override
    public void onConnectionSuspended(int i) {
        Log.i(TAG, "Location Service Suspended ! Please Reconnect.");
        mGoogleApiClient.connect();
    }


    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        if (connectionResult.hasResolution()) {
            try {
                //Start and Activity that tries to resolve the rror
                connectionResult.startResolutionForResult(this, CONNECTION_FAILURE_RESOLUTION_REQUEST);
            } catch (IntentSender.SendIntentException e) {
                e.printStackTrace();
            }
        } else {
            Log.i(TAG, "Location services connection failed with code " + connectionResult.getErrorCode());
        }
    }

    @Override
    public void onLocationChanged(Location location) {

        handleNewLocation(location);
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        String[] key;
        key = new String[2];
        Intent intent = new Intent(MapsActivity.this, ScrollingActivity.class);
        Bundle mBundle = new Bundle();
        key[0] = "MapsActivity";
        switch (marker.getTitle()) {
            case "Main Square":
                key[1] = "1";
                break;
            case "Administrative Block":
                key[1] = "2";
                break;
            case "KU Central Library":
                key[1] = "3";
                break;
            case "CV Raman Auditorium":
                key[1] = "4";
                break;
            case "Khetan Park / Saraswoti Temple":
                key[1] = "5";
                break;
            case "School of Science Block":
                key[1] = "6";
                break;
            case "BioTechnology Block":
                key[1] = "7";
                break;
            case "Mechanical and Electrical Block":
                key[1] = "8";
                break;
            case "New DoCSE":
                key[1] = "9";
                break;
            case "Lecture Hall":
                key[1] = "10";
                break;
            case "Civil and Geomatics Block":
                key[1] = "11";
                break;
            case "Pharmacy Block":
                key[1] = "12";
                break;
            case "Mess / Canteen 2":
                key[1] = "13";
                break;
            case "Department of Computer Science and Engineering":
                key[1] = "14";
                break;
            case "Kathmandu University Staff Quater":
                key[1] = "15";
                break;
            case "TTC Boys Hostel":
                key[1] = "16";
                break;
            case "Kathmandu University Girls Hostel I":
                key[1] = "17";
                break;
            case "Kathmandu University Boys Hostel":
                key[1] = "18";
                break;
            case "Environmental Science and Engineering Block":
                key[1] = "19";
                break;
            case "Social Hall":
                key[1] = "20";
                break;
            case "Multipurpose Hall":
                key[1] = "21";
                break;
            case "KU Football Ground":
                key[1] = "22";
                break;
            case "Technical Training Center (A)":
                key[1] = "23";
                break;
            case "Technical Training Center (B)":
                key[1] = "24";
                break;
            case "Turbine Testing Lab":
                key[1] = "25";
                break;
            case "Main Entrance Parking":
                key[1] = "26";
                break;
            case "Swimming Pool":
                key[1] = "27";
                break;
            case "Volleyball Court":
                key[1] = "28";
                break;
            case "KU Fast Food and Cafe":
                key[1] = "29";
                break;
            case "KU BasketBall Court":
                key[1] = "30";
                break;
            case "Kathmandu University Girls Hostel II":
                key[1] = "31";
                break;
            case "KU Family Staff Quater":
                key[1] = "32";
                break;
            case "Kathmandu University Girls Hostel III":
                key[1] = "33";
                break;
            case "Department of Chemistry and Mathematics":
                key[1] = "34";
                break;
            case "IT Park":
                key[1] = "35";
                break;
            default:
                key[1] = "0";
                Toast.makeText(this, "Info window clicked",
                        Toast.LENGTH_LONG).show();
                break;
        }
        mBundle.putStringArray("data", key);
        intent.putExtras(mBundle);
        startActivity(intent);
        finish();

    }

    @Override
    public void onBackPressed() {

        switch (nameScrolling[0]) {
            case "ScrollingActivity":
                nameScrolling[0] = "MainActivity";
                Bundle Scrolling = new Bundle();
                Scrolling.putStringArray("data", nameScrolling);
                Intent intentS = new Intent(MapsActivity.this, ScrollingActivity.class);
                intentS.putExtras(Scrolling);
                startActivity(intentS);
                finish();
                break;
            case "MainActivity":
                Bundle MAps = new Bundle();
                MAps.putStringArray("data", nameScrolling);
                Intent intentmain = new Intent(MapsActivity.this, MainActivity.class);
                startActivity(intentmain);
                finish();
                break;
        }


        // your code.
    }


    public void groundOverlay() {
        LatLngBounds latLngBounds = new LatLngBounds(new LatLng(27.619543, 85.537938), new LatLng(27.619888, 85.538187));


        mGroundOverlayBlock10 = mMap.addGroundOverlay(new GroundOverlayOptions()
                .image(BitmapDescriptorFactory.fromResource(R.drawable.block10)).anchor(0, 1)
                .positionFromBounds(latLngBounds)
                .bearing(5)
        );

    }

    public void displayMarkers() {
        LatLng markerPosition = null;
        String title = null;
        String snippet = null;
        switch (getNumber) {
            case "1":
                markerPosition=libpark;
                title ="Main Square";
                snippet ="Block 1";
                break;
            case "2":
                markerPosition=admin;
                title="Administrative Block";
                snippet="Block 2";
                break;
            case "3":
                markerPosition=library;
                        title="KU Central Library";
                        snippet="Block 3";


                break;
            case "4":
                markerPosition=cvraman;
                        title="CV Raman Auditorium";
                        snippet="Meeting Halls and Auditorium";


                break;
            case "5":
                markerPosition= khetan;
                        title="Khetan Park / Saraswoti Temple";
                        snippet="Park";


                break;
            case "6":
                markerPosition=schoolosBlock;
                        title="School of Science Block";
                        snippet="Block 6";

                break;
            case "7":
                markerPosition=biotechBlock;
                        title="BioTechnology Block";
                        snippet="Block 7";

                break;
            case "8":
                markerPosition=mechanicalBlock;
                        title="Mechanical and Electrical Block";
                        snippet="Block 8";


                break;
            case "9":
                markerPosition=newcomp;
                        title="New DoCSE";
                        snippet="Block 9";

                break;
            case "10":
                markerPosition= lechall;
                        title="Lecture Hall";
                        snippet="Block 10";


                break;
            case "11":
                markerPosition=civilBlock;
                        title="Civil and Geomatics Block";
                snippet="Block 11";



                break;
            case "12":
                markerPosition=pharmacyBlock;
                        title="Pharmacy Block";
                        snippet="Block 12";



                break;
            case "13":
                markerPosition=mess;
                        title="Mess / Canteen 2";
                        snippet="Lunch/Dinner";


                break;
            case "14":
                markerPosition=compdepart;
                        title="Department of Computer Science and Engineering";
                        snippet="KUCSE";

                break;
            case "15":
                markerPosition= kukusq;
                        title="Kathmandu University Staff Quater";
                        snippet="Staff Quater";

                break;
            case "16":
                markerPosition= ttchostel;
                        title="TTC Boys Hostel";
                        snippet="Hostel";

                break;
            case "17":
                markerPosition=kukughi;
                        title="Kathmandu University Girls Hostel I";
                        snippet="Hostel";

                break;
            case "18":
                markerPosition= kukubh;
                        title="Kathmandu University Boys Hostel";
                        snippet="Hostel";

                break;
            case "19":
                markerPosition= enve;
                        title="Environmental Science and Engineering Block ";
                        snippet="ENVE and ENVS";

                break;
            case "20":
                markerPosition=socialhall;
                        title="Social Hall";
                        snippet="Indoor Games and TV room";

                break;
            case "21":
                markerPosition= multi;
                        title="Multipurpose Hall";
                        snippet="Hall";

                break;
            case "22":
                markerPosition= footballg;
                        title="KU Football Ground";
                        snippet="Football and Games";

                break;
            case "23":
                markerPosition=ttca;
                        title="Technical Training Center (A)";
                        snippet="TTC (a)";

                break;
            case "24":
                markerPosition=ttcb;
                title="Technical Training Center (B)";
                snippet="TTC (b)";

                break;
            case "25":
                markerPosition=ttl;
                title="Turbine Testing Lab";
                snippet="TTL";

                break;
            case "26":
               markerPosition=mainpark;
                        title="Main Entrance Parking";
                        snippet="Parking";

                break;
            case "27":
                markerPosition=swim;
                        title="Swimming Pool";
                        snippet="swim";

                break;
            case "28":
                markerPosition=volleyballc;
                        title="Volleyball Court";
                        snippet="Games";

                break;
            case "29":
                markerPosition=kuffc;
                        title="KU Fast Food and Cafe";
                snippet="Breakfast";

                break;
            case "30":
                markerPosition=basketballc;
                        title="KU BasketBall Court";
                        snippet="Basketball Games";

                break;
            case "31":

                markerPosition=kukughii;
                        title="Kathmandu University Girls Hostel II";
                        snippet="Hostel";

                break;
            case "32":
                markerPosition=kufsq;
                title="KU Family Staff Quater";
                snippet="Staff Quater";

                break;
            case "33":
                markerPosition=kukughiii;
                        title="Kathmandu University Girls Hostel III";
                        snippet="Hostel";

                break;
            case "34":
               markerPosition=chemandmath;
                        title="Department of Chemistry and Mathematics";
                        snippet="Offices";

                break;
            case "35":
               markerPosition=iTPark;
               title="IT Park";
               snippet="IT MEET 2016";



                break;

        }
        if(markerPosition!=null){
            mMap.addMarker(new MarkerOptions()
                    .position(markerPosition)
                    .title(title)
                    .snippet(snippet)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
            if (c == 0) {
                mMap.moveCamera(CameraUpdateFactory
                        .newLatLngZoom(markerPosition, initialZoom));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(initialZoom), 1000, null);
                c++;
            }
        }



    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_ku) {
            Toast.makeText(this, "About Kathmandu University",
                    Toast.LENGTH_LONG).show();

        } else if (id == R.id.nav_blocks_KUSOA) {
            Toast.makeText(this, "About Kathmandu University",
                    Toast.LENGTH_LONG).show();
        } else if (id == R.id.nav_blocks_KUSOL) {
            Toast.makeText(this, "About Kathmandu University",
                    Toast.LENGTH_LONG).show();
        } else if (id == R.id.nav_blocks_KUSOM) {
            Toast.makeText(this, "About Kathmandu University",
                    Toast.LENGTH_LONG).show();
        } else if (id == R.id.nav_blocks_KUSMS) {
            Toast.makeText(this, "About Kathmandu University",
                    Toast.LENGTH_LONG).show();
        } else if (id == R.id.calendar) {
            Intent intent = new Intent(this, calendar.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_itPark) {
            Bundle mBundle = new Bundle();
            key = new String[3];
            key[0] = "MainActivity";
            key[1] = "35";
            Intent intent = new Intent(this, ScrollingActivity.class);
            mBundle.putStringArray("data", key);
            intent.putExtras(mBundle);
            startActivity(intent);
            finish();

        } else if (id == R.id.nav_us) {
            Intent intent = new Intent(this, about_us.class);
            startActivity(intent);
            finish();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}