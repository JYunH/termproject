package com.example.googlemap;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.PriorityQueue;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {


    private GoogleApiClient mGoogleApiClient = null;
    private GoogleMap mGoogleMap = null;
    private Marker currentMarker = null;

    private static final LatLng DEFAULT_LOCATION = new LatLng(35.231602, 129.084156);//초기위치 부산대
    private static final String TAG = "googlemap_example";
    private static final int GPS_ENABLE_REQUEST_CODE = 2001;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 2002;
    private static final int UPDATE_INTERVAL_MS = 1000;  // 1초
    private static final int FASTEST_UPDATE_INTERVAL_MS = 1000; // 1초

    private AppCompatActivity mActivity;
    boolean askPermissionOnceAgain = false;

    LatLng CROSS1 = new LatLng(35.230440, 129.082588);
    LatLng CROSS2 = new LatLng(35.231305, 129.082895);
    LatLng CROSS3 = new LatLng(35.231726, 129.083442);
    LatLng CROSS4 = new LatLng(35.232094, 129.083152);
    LatLng CROSS5 = new LatLng(35.233373, 129.083678);
    LatLng CROSS6 = new LatLng(35.230814, 129.081586);
    LatLng CROSS7 = new LatLng(35.231431, 129.081326);
    LatLng CROSS8 = new LatLng(35.231659, 129.081465);
    LatLng CROSS9 = new LatLng(35.232413, 129.081883);
    LatLng CROSS10 = new LatLng(35.233149, 129.082151);
    LatLng CROSS11 = new LatLng(35.233780, 129.082419);
    LatLng CROSS12 = new LatLng(35.234095, 129.082687);
    LatLng CROSS13 = new LatLng(35.230835, 129.080252);
    LatLng CROSS14 = new LatLng(35.231168, 129.080456);
    LatLng CROSS15 = new LatLng(35.231659, 129.080703);
    LatLng CROSS16 = new LatLng(35.234218, 129.081894);
    LatLng CROSS17 = new LatLng(35.235024, 129.081840);
    LatLng CROSS18 = new LatLng(35.235024, 129.081840);
    LatLng CROSS19 = new LatLng(35.236374, 129.081786);
    LatLng CROSS20 = new LatLng(35.231835, 129.080359);
    LatLng CROSS21 = new LatLng(35.232659, 129.080713);
    LatLng CROSS22 = new LatLng(35.233115, 129.080702);
    LatLng CROSS23 = new LatLng(35.233422, 129.080793);
    LatLng CROSS24 = new LatLng(35.234075, 129.081065);
    LatLng CROSS25 = new LatLng(35.234171, 129.081123);
    LatLng CROSS26 = new LatLng(35.232812, 129.080380);
    LatLng CROSS27 = new LatLng(35.231530, 129.078503);
    LatLng CROSS28 = new LatLng(35.232493, 129.078810);
    LatLng CROSS29 = new LatLng(35.233019, 129.079250);
    LatLng CROSS30 = new LatLng(35.234333, 129.080591);
    LatLng CROSS31 = new LatLng(35.235314, 129.080902);
    LatLng CROSS32 = new LatLng(35.233737, 129.079124);
    LatLng CROSS33 = new LatLng(35.234543, 129.079414);
    LatLng CROSS34 = new LatLng(35.234823, 129.079425);
    LatLng CROSS35 = new LatLng(35.235857, 129.079801);
    LatLng CROSS36 = new LatLng(35.236628, 129.080498);
    LatLng CROSS37 = new LatLng(35.232176, 129.077548);
    LatLng CROSS38 = new LatLng(35.233666, 129.077966);
    LatLng CROSS39 = new LatLng(35.233964, 129.078020);
    LatLng CROSS40 = new LatLng(35.235016, 129.078396);
    LatLng CROSS41 = new LatLng(35.236541, 129.078857);
    LatLng CROSS42 = new LatLng(35.237049, 129.079318);
    LatLng CROSS43 = new LatLng(35.232404, 129.076239);
    LatLng CROSS44 = new LatLng(35.233648, 129.076443);
    LatLng CROSS45 = new LatLng(35.234226, 129.076604);
    LatLng CROSS46 = new LatLng(35.234506, 129.076615);
    LatLng CROSS47 = new LatLng(35.235663, 129.077012);
    LatLng CROSS48 = new LatLng(35.237503, 129.078525);
    LatLng CROSS49 = new LatLng(35.236802, 129.077635);
    LatLng CROSS50 = new LatLng(35.232420, 129.075693);
    LatLng CROSS51 = new LatLng(35.232788, 129.075253);
    LatLng CROSS52 = new LatLng(35.233805, 129.075500);
    LatLng CROSS53 = new LatLng(35.234629, 129.075704);
    LatLng CROSS54 = new LatLng(35.235698, 129.075908);
    LatLng CROSS55 = new LatLng(35.236171, 129.076713);

    List<Vertex> FinalPath;

    class Vertex implements Comparable<Vertex> {
        public final String name;
        public Edge[] adjacencies;
        public double minDistance = Double.POSITIVE_INFINITY;
        public Vertex previous;

        public Vertex(String argName) {
            name = argName;
        }

        public String toString() {
            return name;
        }

        public int compareTo(Vertex other) {
            return Double.compare(minDistance, other.minDistance);
        }

    }

    class Edge {
        public final Vertex target;
        public final double weight;

        public Edge(Vertex argTarget, double argWeight) {
            target = argTarget;
            weight = argWeight;
        }
    }

    public class Dijkstra {
        public void computePaths(Vertex source) {
            source.minDistance = 0.;
            PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
            vertexQueue.add(source);


            while (!vertexQueue.isEmpty()) {
                Vertex u = vertexQueue.poll();


                // Visit each edge exiting u
                for (Edge e : u.adjacencies) {
                    if ( e == null ) continue;
                    Vertex v = e.target;
                    double weight = e.weight;
                    double distanceThroughU = u.minDistance + weight;
                    if (distanceThroughU < v.minDistance) {
                        v.minDistance = distanceThroughU;
                        v.previous = u;
                        vertexQueue.add(v);
                    }
                }


            }
        }

        public List<Vertex> getShortestPathTo(Vertex target) {
            List<Vertex> path = new ArrayList<Vertex>();
            for (Vertex vertex = target; vertex != null; vertex = vertex.previous)
                path.add(vertex);

            Collections.reverse(path);
            return path;
        }

        public void VertexEdge(String Start, String End) {
            // mark all the vertices
            Vertex N1 = new Vertex("1");Vertex N2 = new Vertex("2");Vertex N3 = new Vertex("3");Vertex N4 = new Vertex("4");Vertex N5 = new Vertex("5");
            Vertex N6 = new Vertex("6");Vertex N7 = new Vertex("7");Vertex N8 = new Vertex("8");Vertex N9 = new Vertex("9");Vertex N10 = new Vertex("10");
            Vertex N11 = new Vertex("11");Vertex N12 = new Vertex("12");Vertex N13 = new Vertex("13");Vertex N14 = new Vertex("14");Vertex N15 = new Vertex("15");
            Vertex N16 = new Vertex("16");Vertex N17 = new Vertex("17");Vertex N18 = new Vertex("18");Vertex N19 = new Vertex("19");Vertex N20 = new Vertex("20");
            Vertex N21 = new Vertex("21");Vertex N22 = new Vertex("22");Vertex N23 = new Vertex("23");Vertex N24 = new Vertex("24");Vertex N25 = new Vertex("25");
            Vertex N26 = new Vertex("26");Vertex N27 = new Vertex("27");Vertex N28 = new Vertex("28");Vertex N29 = new Vertex("29");Vertex N30 = new Vertex("30");
            Vertex N31 = new Vertex("31");Vertex N32 = new Vertex("32");Vertex N33 = new Vertex("33");Vertex N34 = new Vertex("34");Vertex N35 = new Vertex("35");
            Vertex N36 = new Vertex("36");Vertex N37 = new Vertex("37");Vertex N38 = new Vertex("38");Vertex N39 = new Vertex("39");Vertex N40 = new Vertex("40");
            Vertex N41 = new Vertex("41");Vertex N42 = new Vertex("42");Vertex N43 = new Vertex("43");Vertex N44 = new Vertex("44");Vertex N45 = new Vertex("45");
            Vertex N46 = new Vertex("46");Vertex N47 = new Vertex("47");Vertex N48 = new Vertex("48");Vertex N49 = new Vertex("49");Vertex N50 = new Vertex("50");
            Vertex N51 = new Vertex("51");Vertex N52 = new Vertex("52");Vertex N53 = new Vertex("53");Vertex N54 = new Vertex("54");Vertex N55 = new Vertex("55");

            // set the edges and weightd
            N1.adjacencies = new Edge[5]; N2.adjacencies = new Edge[5]; N3.adjacencies = new Edge[5];N4.adjacencies = new Edge[5]; N5.adjacencies = new Edge[5]; N6.adjacencies = new Edge[5];N7.adjacencies = new Edge[5]; N8.adjacencies = new Edge[5]; N9.adjacencies = new Edge[5];N10.adjacencies = new Edge[5];
            N11.adjacencies = new Edge[5]; N12.adjacencies = new Edge[5]; N13.adjacencies = new Edge[5];N14.adjacencies = new Edge[5]; N15.adjacencies = new Edge[5]; N16.adjacencies = new Edge[5];N17.adjacencies = new Edge[5]; N18.adjacencies = new Edge[5]; N19.adjacencies = new Edge[5];N20.adjacencies = new Edge[5];
            N21.adjacencies = new Edge[5]; N22.adjacencies = new Edge[5]; N23.adjacencies = new Edge[5];N24.adjacencies = new Edge[5]; N25.adjacencies = new Edge[5]; N26.adjacencies = new Edge[5];N27.adjacencies = new Edge[5]; N28.adjacencies = new Edge[5]; N29.adjacencies = new Edge[5];N30.adjacencies = new Edge[5];
            N31.adjacencies = new Edge[5]; N32.adjacencies = new Edge[5]; N33.adjacencies = new Edge[5];N34.adjacencies = new Edge[5]; N35.adjacencies = new Edge[5]; N36.adjacencies = new Edge[5];N37.adjacencies = new Edge[5]; N38.adjacencies = new Edge[5]; N39.adjacencies = new Edge[5];N40.adjacencies = new Edge[5];
            N41.adjacencies = new Edge[5]; N42.adjacencies = new Edge[5]; N43.adjacencies = new Edge[5];N44.adjacencies = new Edge[5]; N45.adjacencies = new Edge[5]; N46.adjacencies = new Edge[5];N47.adjacencies = new Edge[5]; N48.adjacencies = new Edge[5]; N49.adjacencies = new Edge[5];N50.adjacencies = new Edge[5];
            N51.adjacencies = new Edge[5]; N52.adjacencies = new Edge[5]; N53.adjacencies = new Edge[5];N54.adjacencies = new Edge[5]; N55.adjacencies = new Edge[5];

            N1.adjacencies[0] = new Edge(N2, CalculationByDistance(CROSS1, CROSS2));
            N1.adjacencies[1] = new Edge(N6, CalculationByDistance(CROSS1, CROSS6));
            N2.adjacencies[0] = new Edge(N1, CalculationByDistance(CROSS2, CROSS1));
            N2.adjacencies[1] = new Edge(N3, CalculationByDistance(CROSS2, CROSS3));
            N3.adjacencies[0] = new Edge(N2, CalculationByDistance(CROSS3, CROSS2));
            N3.adjacencies[1] = new Edge(N4, CalculationByDistance(CROSS3, CROSS4));
            N4.adjacencies[0] = new Edge(N3, CalculationByDistance(CROSS4, CROSS3));
            N4.adjacencies[1] = new Edge(N5, CalculationByDistance(CROSS4, CROSS5));
            N4.adjacencies[2] = new Edge(N9, CalculationByDistance(CROSS4, CROSS9));
            N5.adjacencies[0] = new Edge(N4, CalculationByDistance(CROSS5, CROSS4));
            N5.adjacencies[1] = new Edge(N11, CalculationByDistance(CROSS5, CROSS11));
            N5.adjacencies[2] = new Edge(N12, CalculationByDistance(CROSS5, CROSS12));
            N6.adjacencies[0] = new Edge(N1, CalculationByDistance(CROSS6, CROSS1));
            N6.adjacencies[1] = new Edge(N7, CalculationByDistance(CROSS6, CROSS7));
            N6.adjacencies[2] = new Edge(N13, CalculationByDistance(CROSS6, CROSS13));
            N7.adjacencies[0] = new Edge(N6, CalculationByDistance(CROSS7, CROSS6));
            N7.adjacencies[1] = new Edge(N7, CalculationByDistance(CROSS7, CROSS8));
            N7.adjacencies[2] = new Edge(N15, CalculationByDistance(CROSS7, CROSS15));
            N8.adjacencies[0] = new Edge(N2, CalculationByDistance(CROSS8, CROSS2));
            N8.adjacencies[1] = new Edge(N7, CalculationByDistance(CROSS8, CROSS7));
            N8.adjacencies[2] = new Edge(N9, CalculationByDistance(CROSS8, CROSS9));
            N9.adjacencies[0] = new Edge(N4, CalculationByDistance(CROSS9, CROSS4));
            N9.adjacencies[1] = new Edge(N8, CalculationByDistance(CROSS9, CROSS8));
            N9.adjacencies[2] = new Edge(N10, CalculationByDistance(CROSS9, CROSS10));
            N10.adjacencies[0] = new Edge(N9, CalculationByDistance(CROSS10, CROSS9));
            N10.adjacencies[1] = new Edge(N11, CalculationByDistance(CROSS10, CROSS11));
            N10.adjacencies[2] = new Edge(N23, CalculationByDistance(CROSS10, CROSS23));
            N11.adjacencies[0] = new Edge(N5, CalculationByDistance(CROSS11, CROSS5));
            N11.adjacencies[1] = new Edge(N10, CalculationByDistance(CROSS11, CROSS10));
            N11.adjacencies[2] = new Edge(N12, CalculationByDistance(CROSS11, CROSS12));
            N11.adjacencies[3] = new Edge(N24, CalculationByDistance(CROSS11, CROSS24));
            N12.adjacencies[0] = new Edge(N5, CalculationByDistance(CROSS12, CROSS5));
            N12.adjacencies[1] = new Edge(N11, CalculationByDistance(CROSS12, CROSS11));
            N12.adjacencies[2] = new Edge(N16, CalculationByDistance(CROSS12, CROSS16));
            N13.adjacencies[0] = new Edge(N14, CalculationByDistance(CROSS13, CROSS14));
            N13.adjacencies[1] = new Edge(N27, CalculationByDistance(CROSS13, CROSS27));
            N14.adjacencies[0] = new Edge(N6, CalculationByDistance(CROSS14, CROSS6));
            N14.adjacencies[1] = new Edge(N13, CalculationByDistance(CROSS14, CROSS13));
            N14.adjacencies[2] = new Edge(N15, CalculationByDistance(CROSS14, CROSS15));
            N15.adjacencies[0] = new Edge(N7, CalculationByDistance(CROSS15, CROSS7));
            N15.adjacencies[1] = new Edge(N14, CalculationByDistance(CROSS15, CROSS14));
            N15.adjacencies[2] = new Edge(N20, CalculationByDistance(CROSS15, CROSS20));
            N16.adjacencies[0] = new Edge(N12, CalculationByDistance(CROSS16, CROSS12));
            N16.adjacencies[1] = new Edge(N17, CalculationByDistance(CROSS16, CROSS17));
            N16.adjacencies[2] = new Edge(N25, CalculationByDistance(CROSS16, CROSS25));
            N17.adjacencies[0] = new Edge(N16, CalculationByDistance(CROSS17, CROSS16));
            N17.adjacencies[1] = new Edge(N18, CalculationByDistance(CROSS17, CROSS18));
            N17.adjacencies[2] = new Edge(N31, CalculationByDistance(CROSS17, CROSS31));
            N18.adjacencies[0] = new Edge(N17, CalculationByDistance(CROSS18, CROSS17));
            N18.adjacencies[1] = new Edge(N19, CalculationByDistance(CROSS18, CROSS19));
            N19.adjacencies[0] = new Edge(N18, CalculationByDistance(CROSS19, CROSS18));
            N19.adjacencies[1] = new Edge(N36, CalculationByDistance(CROSS19, CROSS36));
            N20.adjacencies[0] = new Edge(N15, CalculationByDistance(CROSS20, CROSS15));
            N20.adjacencies[1] = new Edge(N21, CalculationByDistance(CROSS20, CROSS21));
            N21.adjacencies[0] = new Edge(N20, CalculationByDistance(CROSS21, CROSS20));
            N21.adjacencies[1] = new Edge(N22, CalculationByDistance(CROSS21, CROSS22));
            N21.adjacencies[2] = new Edge(N26, CalculationByDistance(CROSS21, CROSS26));
            N22.adjacencies[0] = new Edge(N21, CalculationByDistance(CROSS22, CROSS21));
            N22.adjacencies[1] = new Edge(N23, CalculationByDistance(CROSS22, CROSS23));
            N22.adjacencies[2] = new Edge(N26, CalculationByDistance(CROSS22, CROSS26));
            N23.adjacencies[0] = new Edge(N10, CalculationByDistance(CROSS23, CROSS10));
            N23.adjacencies[1] = new Edge(N22, CalculationByDistance(CROSS23, CROSS22));
            N23.adjacencies[2] = new Edge(N24, CalculationByDistance(CROSS23, CROSS24));
            N23.adjacencies[3] = new Edge(N32, CalculationByDistance(CROSS23, CROSS32));
            N24.adjacencies[0] = new Edge(N11, CalculationByDistance(CROSS24, CROSS11));
            N24.adjacencies[1] = new Edge(N23, CalculationByDistance(CROSS24, CROSS23));
            N24.adjacencies[2] = new Edge(N25, CalculationByDistance(CROSS24, CROSS25));
            N25.adjacencies[0] = new Edge(N16, CalculationByDistance(CROSS25, CROSS16));
            N25.adjacencies[1] = new Edge(N24, CalculationByDistance(CROSS25, CROSS24));
            N25.adjacencies[2] = new Edge(N30, CalculationByDistance(CROSS25, CROSS30));
            N26.adjacencies[0] = new Edge(N21, CalculationByDistance(CROSS26, CROSS21));
            N26.adjacencies[1] = new Edge(N22, CalculationByDistance(CROSS26, CROSS22));
            N26.adjacencies[2] = new Edge(N29, CalculationByDistance(CROSS26, CROSS29));
            N27.adjacencies[0] = new Edge(N13, CalculationByDistance(CROSS27, CROSS13));
            N27.adjacencies[1] = new Edge(N28, CalculationByDistance(CROSS27, CROSS28));
            N27.adjacencies[2] = new Edge(N37, CalculationByDistance(CROSS27, CROSS37));
            N28.adjacencies[0] = new Edge(N27, CalculationByDistance(CROSS28, CROSS27));
            N28.adjacencies[1] = new Edge(N29, CalculationByDistance(CROSS28, CROSS29));
            N29.adjacencies[0] = new Edge(N26, CalculationByDistance(CROSS29, CROSS26));
            N29.adjacencies[1] = new Edge(N28, CalculationByDistance(CROSS29, CROSS28));
            N30.adjacencies[0] = new Edge(N25, CalculationByDistance(CROSS30, CROSS25));
            N30.adjacencies[1] = new Edge(N31, CalculationByDistance(CROSS30, CROSS31));
            N30.adjacencies[2] = new Edge(N33, CalculationByDistance(CROSS30, CROSS33));
            N31.adjacencies[0] = new Edge(N17, CalculationByDistance(CROSS31, CROSS17));
            N31.adjacencies[1] = new Edge(N30, CalculationByDistance(CROSS31, CROSS30));
            N31.adjacencies[2] = new Edge(N35, CalculationByDistance(CROSS31, CROSS35));
            N32.adjacencies[0] = new Edge(N23, CalculationByDistance(CROSS32, CROSS23));
            N32.adjacencies[1] = new Edge(N33, CalculationByDistance(CROSS32, CROSS33));
            N32.adjacencies[2] = new Edge(N39, CalculationByDistance(CROSS32, CROSS39));
            N33.adjacencies[0] = new Edge(N30, CalculationByDistance(CROSS33, CROSS30));
            N33.adjacencies[1] = new Edge(N32, CalculationByDistance(CROSS33, CROSS32));
            N33.adjacencies[2] = new Edge(N34, CalculationByDistance(CROSS33, CROSS34));
            N34.adjacencies[0] = new Edge(N33, CalculationByDistance(CROSS34, CROSS33));
            N34.adjacencies[1] = new Edge(N35, CalculationByDistance(CROSS34, CROSS35));
            N34.adjacencies[2] = new Edge(N40, CalculationByDistance(CROSS34, CROSS40));
            N35.adjacencies[0] = new Edge(N31, CalculationByDistance(CROSS35, CROSS31));
            N35.adjacencies[1] = new Edge(N34, CalculationByDistance(CROSS35, CROSS34));
            N35.adjacencies[2] = new Edge(N36, CalculationByDistance(CROSS35, CROSS36));
            N35.adjacencies[3] = new Edge(N41, CalculationByDistance(CROSS35, CROSS41));
            N36.adjacencies[0] = new Edge(N19, CalculationByDistance(CROSS36, CROSS19));
            N36.adjacencies[1] = new Edge(N35, CalculationByDistance(CROSS36, CROSS35));
            N36.adjacencies[2] = new Edge(N42, CalculationByDistance(CROSS36, CROSS42));
            N37.adjacencies[0] = new Edge(N27, CalculationByDistance(CROSS37, CROSS27));
            N37.adjacencies[1] = new Edge(N38, CalculationByDistance(CROSS37, CROSS38));
            N37.adjacencies[2] = new Edge(N43, CalculationByDistance(CROSS37, CROSS43));
            N38.adjacencies[0] = new Edge(N35, CalculationByDistance(CROSS38, CROSS35));
            N38.adjacencies[1] = new Edge(N37, CalculationByDistance(CROSS38, CROSS37));
            N38.adjacencies[2] = new Edge(N43, CalculationByDistance(CROSS38, CROSS43));
            N39.adjacencies[0] = new Edge(N32, CalculationByDistance(CROSS39, CROSS32));
            N39.adjacencies[1] = new Edge(N38, CalculationByDistance(CROSS39, CROSS38));
            N39.adjacencies[2] = new Edge(N40, CalculationByDistance(CROSS39, CROSS40));
            N39.adjacencies[3] = new Edge(N45, CalculationByDistance(CROSS39, CROSS45));
            N40.adjacencies[0] = new Edge(N34, CalculationByDistance(CROSS40, CROSS34));
            N40.adjacencies[1] = new Edge(N39, CalculationByDistance(CROSS40, CROSS39));
            N40.adjacencies[2] = new Edge(N41, CalculationByDistance(CROSS40, CROSS41));
            N41.adjacencies[0] = new Edge(N35, CalculationByDistance(CROSS41, CROSS35));
            N41.adjacencies[1] = new Edge(N40, CalculationByDistance(CROSS41, CROSS40));
            N41.adjacencies[2] = new Edge(N42, CalculationByDistance(CROSS41, CROSS42));
            N42.adjacencies[0] = new Edge(N36, CalculationByDistance(CROSS42, CROSS36));
            N42.adjacencies[1] = new Edge(N41, CalculationByDistance(CROSS42, CROSS41));
            N42.adjacencies[2] = new Edge(N48, CalculationByDistance(CROSS42, CROSS48));
            N43.adjacencies[0] = new Edge(N37, CalculationByDistance(CROSS43, CROSS37));
            N43.adjacencies[1] = new Edge(N44, CalculationByDistance(CROSS43, CROSS44));
            N43.adjacencies[2] = new Edge(N50, CalculationByDistance(CROSS43, CROSS50));
            N44.adjacencies[0] = new Edge(N43, CalculationByDistance(CROSS44, CROSS43));
            N44.adjacencies[1] = new Edge(N45, CalculationByDistance(CROSS44, CROSS45));
            N44.adjacencies[2] = new Edge(N52, CalculationByDistance(CROSS44, CROSS52));
            N45.adjacencies[0] = new Edge(N39, CalculationByDistance(CROSS45, CROSS39));
            N45.adjacencies[1] = new Edge(N44, CalculationByDistance(CROSS45, CROSS44));
            N45.adjacencies[2] = new Edge(N46, CalculationByDistance(CROSS45, CROSS46));
            N46.adjacencies[0] = new Edge(N45, CalculationByDistance(CROSS46, CROSS45));
            N46.adjacencies[1] = new Edge(N47, CalculationByDistance(CROSS46, CROSS47));
            N46.adjacencies[2] = new Edge(N53, CalculationByDistance(CROSS46, CROSS53));
            N47.adjacencies[0] = new Edge(N46, CalculationByDistance(CROSS47, CROSS46));
            N47.adjacencies[1] = new Edge(N54, CalculationByDistance(CROSS47, CROSS54));
            N48.adjacencies[0] = new Edge(N42, CalculationByDistance(CROSS48, CROSS42));
            N48.adjacencies[1] = new Edge(N49, CalculationByDistance(CROSS48, CROSS49));
            N49.adjacencies[0] = new Edge(N48, CalculationByDistance(CROSS49, CROSS48));
            N50.adjacencies[0] = new Edge(N43, CalculationByDistance(CROSS50, CROSS43));
            N50.adjacencies[1] = new Edge(N51, CalculationByDistance(CROSS50, CROSS51));
            N51.adjacencies[0] = new Edge(N50, CalculationByDistance(CROSS51, CROSS50));
            N51.adjacencies[1] = new Edge(N52, CalculationByDistance(CROSS51, CROSS52));
            N52.adjacencies[0] = new Edge(N44, CalculationByDistance(CROSS52, CROSS44));
            N52.adjacencies[1] = new Edge(N51, CalculationByDistance(CROSS52, CROSS51));
            N52.adjacencies[2] = new Edge(N53, CalculationByDistance(CROSS52, CROSS51));
            N53.adjacencies[0] = new Edge(N46, CalculationByDistance(CROSS52, CROSS53));
            N53.adjacencies[1] = new Edge(N52, CalculationByDistance(CROSS53, CROSS46));
            N53.adjacencies[2] = new Edge(N54, CalculationByDistance(CROSS53, CROSS52));
            N54.adjacencies[0] = new Edge(N47, CalculationByDistance(CROSS54, CROSS47));
            N54.adjacencies[1] = new Edge(N53, CalculationByDistance(CROSS54, CROSS53));
            N54.adjacencies[2] = new Edge(N55, CalculationByDistance(CROSS54, CROSS55));
            N55.adjacencies[0] = new Edge(N54, CalculationByDistance(CROSS55, CROSS54));

            //https://stackoverflow.com/questions/17480022/java-find-shortest-path-between-2-points-in-a-distance-weighted-map
            Vertex StartV = N28, EndV = N40;
            computePaths(StartV); // run Dijkstra
            List<Vertex> path = getShortestPathTo(EndV);
            FinalPath = path;
            Toast toast = Toast.makeText(getApplicationContext(), path.toString(), Toast.LENGTH_LONG);
            toast.show();

        }
    }
    @Override
    protected void onStart() {
        super.onStart();

        //if (mGoogleApiClient != null)
        // mGoogleApiClient.connect();
    }

    @Override
    public void onResume() {
        super.onResume();

        if (mGoogleApiClient!=null)
            mGoogleApiClient.connect();

        //앱 정보에서 퍼미션을 허가했는지를 다시 검사해봐야 한다.
        if (askPermissionOnceAgain) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                askPermissionOnceAgain = false;

                checkPermissions();
            }
        }
    }

    @Override
    protected void onStop() {

        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
        super.onStop();
    }


    @Override
    public void onPause() {

        //위치 업데이트 중지
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {

            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);

            mGoogleApiClient.disconnect();
        }

        super.onPause();
    }

    @Override
    protected void onDestroy() {

        if (mGoogleApiClient != null) {
            mGoogleApiClient.unregisterConnectionCallbacks(this);
            mGoogleApiClient.unregisterConnectionFailedListener(this);

            if (mGoogleApiClient.isConnected()) {
                LocationServices.FusedLocationApi
                        .removeLocationUpdates(mGoogleApiClient, this);
                mGoogleApiClient.disconnect();
            }
        }
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getFragmentManager();
        MapFragment mapFragment = (MapFragment) fragmentManager.findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Dijkstra MyDijkstra = new Dijkstra();
        MyDijkstra.VertexEdge("A", "B");
    }

    public double CalculationByDistance(LatLng StartP, LatLng EndP) {
        int Radius = 6371;// radius of earth in Km
        double lat1 = StartP.latitude;
        double lat2 = EndP.latitude;
        double lon1 = StartP.longitude;
        double lon2 = EndP.longitude;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2)
                * Math.sin(dLon / 2);
        double c = 2 * Math.asin(Math.sqrt(a));
        double valueResult = Radius * c;
        double km = valueResult / 1;
        DecimalFormat newFormat = new DecimalFormat("####");
        int kmInDec = Integer.valueOf(newFormat.format(km));
        double meter = valueResult % 1000;
        int meterInDec = Integer.valueOf(newFormat.format(meter));

        return Radius * c;
    }

    @Override
    public void onMapReady(final GoogleMap map) {
        Log.d(TAG, "onMapReady");
        mGoogleMap = map;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //API 23 이상이면 런타임 퍼미션 처리 필요
            int hasFineLocationPermission = ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION);
            if (hasFineLocationPermission == PackageManager.PERMISSION_DENIED) {
                ActivityCompat.requestPermissions( mActivity,
                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                        PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
            } else {
                if (mGoogleApiClient == null) {
                    buildGoogleApiClient();
                }
                if (ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {
                    mGoogleMap.setMyLocationEnabled(true);
                }
            }
        } else {
            if (mGoogleApiClient == null) {
                buildGoogleApiClient();
            }
            mGoogleMap.setMyLocationEnabled(true);
        }
        LatLng MAINDOOR = new LatLng(35.231602, 129.084156); // 정문
        LatLng RAINBOWDOOR = new LatLng(35.230112, 129.082836); //구 정문
        LatLng PNU105 = new LatLng(35.231199, 129.083490); // Engineering3
        LatLng PNU108 = new LatLng(35.233156, 129.084072); // Engineering9

        LatLng PNU201 = new LatLng(35.231007, 129.082273); // Engineering6
        LatLng PNU206 = new LatLng(35.230673, 129.080571); // Engineering11
        LatLng PNU207 = new LatLng(35.233279, 129.082838); // Engineering10
        LatLng PNU208 = new LatLng(35.232595, 129.082860); // Main Administration Bldg.
        LatLng NUKTU = new LatLng(35.231824, 129.082570); // 넉터
        LatLng PNU209 = new LatLng(35.235965, 129.084114); // Sangnam International House
        LatLng PNU210 = new LatLng(35.235947, 129.083588); // International Language Institute
        LatLng PNU211 = new LatLng(35.236455, 129.083277); // Child Educare Comprehensive Center

        LatLng PNU306 = new LatLng(35.232249, 129.081249); // Humanities Bldg
        LatLng PNU308 = new LatLng(35.233604, 129.081556); // Physics1
        LatLng PNU309 = new LatLng(35.233669, 129.081152); // Physics2
        LatLng PNU310 = new LatLng(35.233939, 129.082331); // Moonchang
        LatLng PNU312 = new LatLng(35.234482, 129.082744); // Central Laboratory
        LatLng PNU313 = new LatLng(35.235139, 129.082728); // Research Lab
        LatLng PNU315 = new LatLng(35.236024, 129.082444); // Jayoo Hall A
        LatLng PNU316 = new LatLng(35.236024, 129.082444); // Jayoo Hall B

        LatLng PNU401 = new LatLng(35.231333, 129.080009); // Building of Construction
        LatLng PNU405 = new LatLng(35.232197, 129.080154); // Engineering2
        LatLng PNU408 = new LatLng(35.232557, 129.079274); // Engineering5
        LatLng PNU409 = new LatLng(35.232995, 129.080164); // professor hall
        LatLng PNU412 = new LatLng(35.233819, 129.080411); // Museum A
        LatLng PNU414 = new LatLng(35.233924, 129.079692); // Earth
        LatLng PNU416 = new LatLng(35.234851, 129.081255); // Biology
        LatLng PNU417 = new LatLng(35.234676, 129.080193); // Education1
        LatLng PNU419 = new LatLng(35.235342, 129.080268); // Geumjung Hall
        LatLng PNU420 = new LatLng(35.235728, 129.081373); // Library2
        LatLng PNU421 = new LatLng(35.236341, 129.080568); // Social Science
        LatLng PNU422 = new LatLng(35.236709, 129.081566); // Seonghak Hall

        LatLng PNU502 = new LatLng(35.232257, 129.078465); // Parmacy
        LatLng PNU507 = new LatLng(35.233028, 129.078712); // Induck Hall
        LatLng PNU508 = new LatLng(35.233063, 129.078079); // Samaung
        LatLng PNU510 = new LatLng(35.234535, 129.078648); // Library1
        LatLng PNU514 = new LatLng(35.236498, 129.079624); // Business
        LatLng PNU516 = new LatLng(35.235779, 129.079442); // International Studies Bldg.

        LatLng PNU601 = new LatLng(35.232817, 129.077135); // Arts1
        LatLng PNU602 = new LatLng(35.233501, 129.077468); // Human Ecology
        LatLng PNU605 = new LatLng(35.234763, 129.078058); // ROTC
        LatLng PNU606 = new LatLng(35.235061, 129.077597); // Chemistry
        LatLng PNU607 = new LatLng(35.235797, 129.078316); // Comprehensive Research Bldg.
        LatLng PNU608 = new LatLng(35.236831, 129.078455); // Law1
        LatLng PNU609 = new LatLng(35.236235, 129.078122); // Law2

        LatLng PNU701 = new LatLng(35.232029, 129.075064); // Education2
        LatLng PNU702 = new LatLng(35.233151, 129.075654); // Arts2
        LatLng PNU706 = new LatLng(35.233361, 129.074871); // Gym
        LatLng PNU707 = new LatLng(35.234290, 129.076040); // Music
        LatLng PNU709 = new LatLng(35.235219, 129.076523); // Student Union
        LatLng PNU710 = new LatLng(35.235044, 129.075311); // Sports Complex
        LatLng PNU711 = new LatLng(35.236394, 129.076309); // Hyowonjae
        LatLng PNU712 = new LatLng(35.236447, 129.077178); // Woongbee Hall
        LatLng PNU715 = new LatLng(35.237534, 129.077725); // Jilli Hall

        CameraUpdate zoom = CameraUpdateFactory.zoomTo(16);

        LatLng StartAddress = new LatLng(35.231602, 129.084156);
        map.moveCamera(CameraUpdateFactory.newLatLng(StartAddress));
        map.animateCamera(zoom);


        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(PNU201);
        markerOptions.title("제 6공학관");
        markerOptions.snippet("제도관");
        map.addMarker(markerOptions);

        markerOptions.position(MAINDOOR);
        markerOptions.title("정문");
        markerOptions.snippet("부산대학교 정문");
        map.addMarker(markerOptions);

        markerOptions.position(RAINBOWDOOR);
        markerOptions.title("(구)정문");
        markerOptions.snippet("무지개문");
        map.addMarker(markerOptions);

        markerOptions.position(PNU105);
        markerOptions.title("제3공학관(융합기계관)");
        markerOptions.snippet("105");
        map.addMarker(markerOptions);

        markerOptions.position(PNU108);
        markerOptions.title("제9공학관(기전관)");
        markerOptions.snippet("108");
        map.addMarker(markerOptions);





        markerOptions.position(NUKTU);
        markerOptions.title("넉넉한터");
        markerOptions.snippet("203");
        map.addMarker(markerOptions);


        markerOptions.position(PNU206);
        markerOptions.title("제11공학관(조선해양공학관)");
        markerOptions.snippet("206");
        map.addMarker(markerOptions);

        markerOptions.position(PNU207);
        markerOptions.title("제10공학관(특성화공학관");
        markerOptions.snippet("207");
        map.addMarker(markerOptions);

        markerOptions.position(PNU208);
        markerOptions.title("기계기술연구동");
        markerOptions.snippet("208");
        map.addMarker(markerOptions);

        markerOptions.position(PNU209);
        markerOptions.title("상남국제회관");
        markerOptions.snippet("209");
        map.addMarker(markerOptions);

        markerOptions.position(PNU210);
        markerOptions.title("언어교육원");
        markerOptions.snippet("210");
        map.addMarker(markerOptions);

        markerOptions.position(PNU211);
        markerOptions.title("보육종합센터");
        markerOptions.snippet("211");
        map.addMarker(markerOptions);




        markerOptions.position(PNU306);
        markerOptions.title("인문관");
        markerOptions.snippet("306");
        map.addMarker(markerOptions);

        markerOptions.position(PNU308);
        markerOptions.title("제1물리관");
        markerOptions.snippet("308");
        map.addMarker(markerOptions);

        markerOptions.position(PNU309);
        markerOptions.title("제2물리관");
        markerOptions.snippet("309");
        map.addMarker(markerOptions);

        markerOptions.position(PNU310);
        markerOptions.title("문창회관");
        markerOptions.snippet("310");
        map.addMarker(markerOptions);

        markerOptions.position(PNU312);
        markerOptions.title("공동실험실습관");
        markerOptions.snippet("312");
        map.addMarker(markerOptions);

        markerOptions.position(PNU313);
        markerOptions.title("자연대 연구실험동");
        markerOptions.snippet("313");

        markerOptions.position(PNU315);
        markerOptions.title("자유관 A동(기숙사)");
        markerOptions.snippet("315");
        map.addMarker(markerOptions);

        markerOptions.position(PNU316);
        markerOptions.title("자유관 B동");
        markerOptions.snippet("316");
        map.addMarker(markerOptions);






        markerOptions.position(PNU401);
        markerOptions.title("건설관");
        markerOptions.snippet("401");

        markerOptions.position(PNU405);
        markerOptions.title("제2공학관(재료관)");
        markerOptions.snippet("405");
        map.addMarker(markerOptions);

        markerOptions.position(PNU408);
        markerOptions.title("제5공학관(유기소재관)");
        markerOptions.snippet("408");
        map.addMarker(markerOptions);

        markerOptions.position(PNU409);
        markerOptions.title("교수회관");
        markerOptions.snippet("409");

        markerOptions.position(PNU412);
        markerOptions.title("박물관 A");
        markerOptions.snippet("412");
        map.addMarker(markerOptions);

        markerOptions.position(PNU414);
        markerOptions.title("지구관");
        markerOptions.snippet("414");
        map.addMarker(markerOptions);

        markerOptions.position(PNU416);
        markerOptions.title("생물관");
        markerOptions.snippet("416");

        markerOptions.position(PNU417);
        markerOptions.title("제1사범관");
        markerOptions.snippet("417");
        map.addMarker(markerOptions);

        markerOptions.position(PNU419);
        markerOptions.title("금정회관");
        markerOptions.snippet("419");
        map.addMarker(markerOptions);

        markerOptions.position(PNU420);
        markerOptions.title("제2도서관");
        markerOptions.snippet("420");

        markerOptions.position(PNU421);
        markerOptions.title("사회관");
        markerOptions.snippet("421");
        map.addMarker(markerOptions);

        markerOptions.position(PNU422);
        markerOptions.title("성학관");
        markerOptions.snippet("422");
        map.addMarker(markerOptions);









        markerOptions.position(PNU502);
        markerOptions.title("약학관(구관)");
        markerOptions.snippet("502");

        markerOptions.position(PNU507);
        markerOptions.title("인덕관");
        markerOptions.snippet("507");
        map.addMarker(markerOptions);

        markerOptions.position(PNU508);
        markerOptions.title("산학협동관");
        markerOptions.snippet("508");
        map.addMarker(markerOptions);

        markerOptions.position(PNU510);
        markerOptions.title("제1도서관");
        markerOptions.snippet("510");

        markerOptions.position(PNU514);
        markerOptions.title("경영관");
        markerOptions.snippet("514");
        map.addMarker(markerOptions);

        markerOptions.position(PNU516);
        markerOptions.title("국제관");
        markerOptions.snippet("516");
        map.addMarker(markerOptions);




        markerOptions.position(PNU601);
        markerOptions.title("예술관");
        markerOptions.snippet("601");

        markerOptions.position(PNU602);
        markerOptions.title("생활환경관");
        markerOptions.snippet("602");
        map.addMarker(markerOptions);

        markerOptions.position(PNU605);
        markerOptions.title("학군단");
        markerOptions.snippet("605");
        map.addMarker(markerOptions);

        markerOptions.position(PNU606);
        markerOptions.title("화학관");
        markerOptions.snippet("605");

        markerOptions.position(PNU607);
        markerOptions.title("공동연구소동");
        markerOptions.snippet("607");
        map.addMarker(markerOptions);

        markerOptions.position(PNU608);
        markerOptions.title("제2법학관");
        markerOptions.snippet("608");
        map.addMarker(markerOptions);

        markerOptions.position(PNU609);
        markerOptions.title("법학관");
        markerOptions.snippet("609");
        map.addMarker(markerOptions);





        markerOptions.position(PNU701);
        markerOptions.title("제2사범관");
        markerOptions.snippet("701");
        map.addMarker(markerOptions);

        markerOptions.position(PNU702);
        markerOptions.title("조소실");
        markerOptions.snippet("702");
        map.addMarker(markerOptions);

        markerOptions.position(PNU706);
        markerOptions.title("경암체육관");
        markerOptions.snippet("706");
        map.addMarker(markerOptions);

        markerOptions.position(PNU707);
        markerOptions.title("음악관");
        markerOptions.snippet("707");
        map.addMarker(markerOptions);

        markerOptions.position(PNU709);
        markerOptions.title("과학기술연구동");
        markerOptions.snippet("709");
        map.addMarker(markerOptions);

        markerOptions.position(PNU710);
        markerOptions.title("대운동장");
        markerOptions.snippet("710");
        map.addMarker(markerOptions);

        markerOptions.position(PNU711);
        markerOptions.title("효원재");
        markerOptions.snippet("711");
        map.addMarker(markerOptions);

        markerOptions.position(PNU712);
        markerOptions.title("웅비관 A동(기숙사)");
        markerOptions.snippet("712");
        map.addMarker(markerOptions);

        markerOptions.position(PNU715);
        markerOptions.title("진리관 가동(기숙사)");
        markerOptions.snippet("715");
        map.addMarker(markerOptions);






        List<LatLng> Cross;
        Cross = new ArrayList(60);

        Cross.add(CROSS1);Cross.add(CROSS2);Cross.add(CROSS3);Cross.add(CROSS4);Cross.add(CROSS5);Cross.add(CROSS6);Cross.add(CROSS7);Cross.add(CROSS8);Cross.add(CROSS9);Cross.add(CROSS10);
        Cross.add(CROSS11);Cross.add(CROSS12);Cross.add(CROSS13);Cross.add(CROSS14);Cross.add(CROSS15);Cross.add(CROSS16);Cross.add(CROSS17);Cross.add(CROSS18);Cross.add(CROSS19);Cross.add(CROSS20);
        Cross.add(CROSS21);Cross.add(CROSS22);Cross.add(CROSS23);Cross.add(CROSS24);Cross.add(CROSS25);Cross.add(CROSS26);Cross.add(CROSS27);Cross.add(CROSS28);Cross.add(CROSS29);Cross.add(CROSS30);
        Cross.add(CROSS31);Cross.add(CROSS32);Cross.add(CROSS33);Cross.add(CROSS34);Cross.add(CROSS35);Cross.add(CROSS36);Cross.add(CROSS37);Cross.add(CROSS38);Cross.add(CROSS39);Cross.add(CROSS40);
        Cross.add(CROSS41);Cross.add(CROSS42);Cross.add(CROSS43);Cross.add(CROSS44);Cross.add(CROSS45);Cross.add(CROSS46);Cross.add(CROSS47);Cross.add(CROSS48);Cross.add(CROSS49);Cross.add(CROSS50);
        Cross.add(CROSS51);Cross.add(CROSS52);Cross.add(CROSS53);Cross.add(CROSS54);Cross.add(CROSS55);
        LatLng StartDraw = CROSS1;
        LatLng EndDraw = CROSS1;
        for (int j = 1; j < Cross.size() + 1; j++) {
            if (Integer.parseInt(FinalPath.get(0).toString()) == j) {
                StartDraw = Cross.get(j-1);
                EndDraw = Cross.get(j-1);
            }
        }
        int tempj = 0;
        for (int i = 0; i < FinalPath.size(); i++) {
            for (int j = 1; j < Cross.size() + 1; j++) {
                if (Integer.parseInt(FinalPath.get(i).toString()) == j) {
                    StartDraw = Cross.get(j-1);

                }
            }

            Polyline line = map.addPolyline(new PolylineOptions().add(StartDraw, EndDraw).width(5).color(Color.RED));
            EndDraw = StartDraw;
        }

    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d(TAG, "onLocationChanged");
        String markerTitle = getCurrentAddress(location);
        String markerSnippet = "위도:"+String.valueOf(location.getLatitude())
                + " 경도:"+String.valueOf(location.getLongitude());

        //현재 위치에 마커 생성
        setCurrentLocation(location, markerTitle, markerSnippet );
    }

    protected synchronized void buildGoogleApiClient() {

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();

    }

    @Override
    public void onConnected(Bundle connectionHint) {

        Log.d(TAG,"onConnected");
        if (!checkLocationServicesStatus()) {
            showDialogForLocationServiceSetting();
        }

        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(UPDATE_INTERVAL_MS);
        locationRequest.setFastestInterval(FASTEST_UPDATE_INTERVAL_MS);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, locationRequest, this);

            }
        }
        else{
            Log.d(TAG,"onConnected : call FusedLocationApi");
            LocationServices.FusedLocationApi
                    .requestLocationUpdates(mGoogleApiClient, locationRequest, this);

            mGoogleMap.getUiSettings().setCompassEnabled(true);
            //mGoogleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            mGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(15));
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Location location = null;
        location.setLatitude(DEFAULT_LOCATION.latitude);
        location.setLongitude(DEFAULT_LOCATION.longitude);

        setCurrentLocation(location, "위치정보 가져올 수 없음",
                "위치 퍼미션과 GPS 활성 요부 확인하세요");
    }

    @Override
    public void onConnectionSuspended(int cause) {
        if ( cause ==  CAUSE_NETWORK_LOST )
            Log.e(TAG, "onConnectionSuspended(): Google Play services " +
                    "connection lost.  Cause: network lost.");
        else if (cause == CAUSE_SERVICE_DISCONNECTED )
            Log.e(TAG,"onConnectionSuspended():  Google Play services " +
                    "connection lost.  Cause: service disconnected");
    }

    public String getCurrentAddress(Location location){

        //지오코더... GPS를 주소로 변환
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());

        List<Address> addresses;

        try {

            addresses = geocoder.getFromLocation(
                    location.getLatitude(),
                    location.getLongitude(),
                    1);
        } catch (IOException ioException) {
            //네트워크 문제
            Toast.makeText(this, "지오코더 서비스 사용불가", Toast.LENGTH_LONG).show();
            return "지오코더 서비스 사용불가";
        } catch (IllegalArgumentException illegalArgumentException) {
            Toast.makeText(this, "잘못된 GPS 좌표", Toast.LENGTH_LONG).show();
            return "잘못된 GPS 좌표";

        }


        if (addresses == null || addresses.size() == 0) {
            Toast.makeText(this, "주소 미발견", Toast.LENGTH_LONG).show();
            return "주소 미발견";

        } else {
            Address address = addresses.get(0);
            return address.getAddressLine(0).toString();
        }

    }

    public boolean checkLocationServicesStatus(){
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    public void setCurrentLocation(Location location, String markerTitle, String markerSnippet) {

        if (currentMarker != null) currentMarker.remove();


        if (location != null) {
            LatLng currentLocation = new LatLng( location.getLatitude(), location.getLongitude());
            //마커를 원하는 이미지로 변경해줘야함
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(currentLocation);
            markerOptions.title(markerTitle);
            markerOptions.snippet(markerSnippet);
            markerOptions.draggable(true);
            markerOptions.icon(BitmapDescriptorFactory
                    .defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
            currentMarker = mGoogleMap.addMarker(markerOptions);

            mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(currentLocation));
            return;
        }

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(DEFAULT_LOCATION);
        markerOptions.title(markerTitle);
        markerOptions.snippet(markerSnippet);
        markerOptions.draggable(true);
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        currentMarker = mGoogleMap.addMarker(markerOptions);

        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(DEFAULT_LOCATION));

    }


    //여기부터는 런타임 퍼미션 처리을 위한 메소드들
    @TargetApi(Build.VERSION_CODES.M)
    private void checkPermissions() {
        boolean fineLocationRationale = ActivityCompat
                .shouldShowRequestPermissionRationale(this,
                        Manifest.permission.ACCESS_FINE_LOCATION);
        int hasFineLocationPermission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);

        if (hasFineLocationPermission == PackageManager
                .PERMISSION_DENIED && fineLocationRationale )
            showDialogForPermission("앱을 실행하려면 퍼미션을 허가하셔야합니다.");

        else if (hasFineLocationPermission
                == PackageManager.PERMISSION_DENIED && !fineLocationRationale ) {
            showDialogForPermissionSetting("퍼미션 거부 + Don't ask again(다시 묻지 않음) " +
                    "체크 박스를 설정한 경우로 설정에서 퍼미션 허가해야합니다.");
        }

        else if (hasFineLocationPermission == PackageManager.PERMISSION_GRANTED) {

            if (mGoogleApiClient == null) {
                buildGoogleApiClient();
            }

            mGoogleMap.setMyLocationEnabled(true);


        }
    }

    @Override
    public void onRequestPermissionsResult(int permsRequestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {

        if (permsRequestCode
                == PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION && grantResults.length > 0) {

            boolean permissionAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;

            if (permissionAccepted) {

                if (mGoogleApiClient == null) {
                    buildGoogleApiClient();
                }

                if (ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {

                    mGoogleMap.setMyLocationEnabled(true);
                }


            }
            else {

                checkPermissions();
            }
        }
    }


    @TargetApi(Build.VERSION_CODES.M)
    private void showDialogForPermission(String msg) {

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("알림");
        builder.setMessage(msg);
        builder.setCancelable(false);
        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                ActivityCompat.requestPermissions( mActivity,
                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                        PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
            }
        });

        builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                finish();
            }
        });
        builder.create().show();
    }

    private void showDialogForPermissionSetting(String msg) {

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("알림");
        builder.setMessage(msg);
        builder.setCancelable(true);
        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                askPermissionOnceAgain = true;

                Intent myAppSettings = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                        Uri.parse("package:" + mActivity.getPackageName()));
                myAppSettings.addCategory(Intent.CATEGORY_DEFAULT);
                myAppSettings.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mActivity.startActivity(myAppSettings);
            }
        });
        builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                finish();
            }
        });
        builder.create().show();
    }


    //여기부터는 GPS 활성화를 위한 메소드들
    private void showDialogForLocationServiceSetting() {

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("위치 서비스 비활성화");
        builder.setMessage("앱을 사용하기 위해서는 위치 서비스가 필요합니다.\n"
                + "위치 설정을 수정하실래요?" );
        builder.setCancelable(true);
        builder.setPositiveButton("설정", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                Intent callGPSSettingIntent
                        = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivityForResult(callGPSSettingIntent, GPS_ENABLE_REQUEST_CODE);
            }
        });
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id){
                dialog.cancel();
            }
        });
        builder.create().show();
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {

            case GPS_ENABLE_REQUEST_CODE:

                //사용자가 GPS 활성 시켰는지 검사
                if (checkLocationServicesStatus()) {
                    if (checkLocationServicesStatus()) {

                        if (mGoogleApiClient == null) {
                            buildGoogleApiClient();
                        }
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (ActivityCompat.checkSelfPermission(this,
                                    Manifest.permission.ACCESS_FINE_LOCATION)
                                    == PackageManager.PERMISSION_GRANTED) {

                                mGoogleMap.setMyLocationEnabled(true);
                            }
                        }
                        else  mGoogleMap.setMyLocationEnabled(true);

                        return;
                    }
                }
                else{
                    setCurrentLocation(null, "위치정보 가져올 수 없음",
                            "위치 퍼미션과 GPS 활성 요부 확인하세요");
                }

                break;
        }
    }

}
