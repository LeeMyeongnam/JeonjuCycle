package com.chocopi.jeonjucycle;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.skp.Tmap.TMapData;
import com.skp.Tmap.TMapGpsManager;
import com.skp.Tmap.TMapMarkerItem;
import com.skp.Tmap.TMapPOIItem;
import com.skp.Tmap.TMapPoint;
import com.skp.Tmap.TMapPolyLine;
import com.skp.Tmap.TMapView;

import org.xml.sax.SAXException;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

public class Course3 extends AppCompatActivity implements TMapGpsManager.onLocationChangedCallback{
    TMapView tmapview = null;
    int marker = 1;
    int loc=1;
    private TMapGpsManager tmapgps = null;
    TMapMarkerItem start = null;
    TMapMarkerItem end= null;
    TMapPoint Startpoint = new TMapPoint(35.834570, 127.129185);
    TMapPoint Endpoint = new TMapPoint(35.870813, 127.053770);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course3);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("코스안내")
                .setMessage("선택하신 코스는 약 9.63km 입니다.\n주요 경유지는\n◎전주고속버스터미널\n◎전주월드컵경기장\n◎전주수목원")
                .setPositiveButton("확인", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int whichButton){

                    }
                });

        AlertDialog dialog = builder.create();    // 알림창 객체 생성
        dialog.show();    // 알림창 띄우기

        RelativeLayout relativeLayout = findViewById(R.id.course3);
        tmapview = new TMapView(this);
        relativeLayout.addView(tmapview);

        TextView tv = findViewById(R.id.textView);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/NanumBarunGothicBold.ttf");
        tv.setTypeface(typeface);
        Bitmap bike = BitmapFactory.decodeResource(this.getResources(), R.drawable.bicycle);
        tmapview.setIcon(bike);
        tmapview.setCenterPoint(127.093353, 35.850504);
        tmapview.setLocationPoint(127.093353, 35.850504);
        tmapview.setSKPMapApiKey("6043d8dd-b18e-3a24-9893-acb9bbc43822");
        tmapview.setLanguage(TMapView.LANGUAGE_KOREAN);
        tmapview.setZoomLevel(12);
        tmapview.setMapType(TMapView.MAPTYPE_STANDARD);
        tmapview.setCompassMode(true);
        tmapview.setTrackingMode(true);

        tmapgps = new TMapGpsManager(Course3.this);
        tmapgps.setMinTime(1000);
        tmapgps.setMinDistance(5);
        tmapgps.setProvider(tmapgps.NETWORK_PROVIDER);
        tmapgps.OpenGps();

        ImageView location = findViewById(R.id.location);
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tmapview.setIconVisibility(true);
                loc=2;
                TMapPoint tpoint = tmapgps.getLocation();
                tmapview.setLocationPoint(tpoint.getLongitude(), tpoint.getLatitude());
                tmapview.setCenterPoint(tpoint.getLongitude(), tpoint.getLatitude());
            }
        });
        ImageView change = findViewById(R.id.change);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(marker==1){
                    end.setTMapPoint(Startpoint);
                    start.setTMapPoint(Endpoint);
                    marker = 2;
                }
                else if(marker==2){
                    start.setTMapPoint(Startpoint);
                    end.setTMapPoint(Endpoint);
                    marker = 1;
                }
            }
        });

        addMarker();
        new Thread() {
            public void run() {

                TMapPOIItem tMapPOIItem1 = new TMapPOIItem();
                TMapData tmapdata1 = new TMapData();

                TMapPoint startpoint1 = new TMapPoint(35.834570, 127.129185); //버스 터미널
                TMapPoint midpoint2 = new TMapPoint(35.856089, 127.068260); //백제교
                TMapPoint midpoint3 = new TMapPoint(35.863339, 127.069096); //백제교
                TMapPoint midpoint4 = new TMapPoint(35.866106, 127.062141); //백제교
                TMapPoint midpoint5 = new TMapPoint(35.872853, 127.061544); //백제교
                TMapPoint midpoint6 = new TMapPoint(35.870813, 127.053770); //백제교


                TMapPolyLine polyline1 = null;
                TMapPolyLine polyline2 = null;
                TMapPolyLine polyline3 = null;
                TMapPolyLine polyline4 = null;
                TMapPolyLine polyline5 = null;
                TMapPolyLine polyline6 = null;

                try {
                    polyline1 = tmapdata1.findPathData(startpoint1, midpoint2);
                    polyline1.setLineColor(Color.parseColor("#20AB32"));
                    polyline1.setOutLineColor(Color.parseColor("#20AB32"));
                    polyline1.setLineWidth(20);
                    tmapview.addTMapPolyLine("polyline1", polyline1);
                  /*  polyline2 = tmapdata1.findPathData(midpoint1, midpoint2);
                    polyline2.setLineColor(Color.BLUE);
                    polyline2.setLineWidth(40);
                    tmapview.addTMapPolyLine("polyline2", polyline2);*/
                    polyline3 = tmapdata1.findPathData(midpoint2, midpoint3);
                    polyline3.setLineColor(Color.parseColor("#20AB32"));
                    polyline3.setOutLineColor(Color.parseColor("#20AB32"));
                    polyline3.setLineWidth(20);
                    tmapview.addTMapPolyLine("polyline3", polyline3);
                    polyline4 = tmapdata1.findPathData(midpoint3, midpoint4);
                    polyline4.setLineColor(Color.parseColor("#20AB32"));
                    polyline4.setOutLineColor(Color.parseColor("#20AB32"));
                    polyline4.setLineWidth(20);
                    tmapview.addTMapPolyLine("polyline4", polyline4);
                    polyline5 = tmapdata1.findPathData(midpoint4, midpoint5);
                    polyline5.setLineColor(Color.parseColor("#20AB32"));
                    polyline5.setOutLineColor(Color.parseColor("#20AB32"));
                    polyline5.setLineWidth(20);
                    tmapview.addTMapPolyLine("polyline5", polyline5);
                    polyline6 = tmapdata1.findPathData(midpoint5, midpoint6);
                    polyline6.setLineColor(Color.parseColor("#20AB32"));
                    polyline6.setOutLineColor(Color.parseColor("#20AB32"));
                    polyline6.setLineWidth(20);
                    tmapview.addTMapPolyLine("polyline6", polyline6);

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                } catch (SAXException e) {
                    e.printStackTrace();
                }

            }
            }.start();
        }
    public void back(View v){
        finish();
    }
    private void addMarker(){
        start = new TMapMarkerItem();
        end= new TMapMarkerItem();

        Bitmap bitmap1 = null;
        Bitmap bitmap2 = null;
        bitmap1 = BitmapFactory.decodeResource(this.getResources(), R.drawable.pin);
        bitmap2 = BitmapFactory.decodeResource(this.getResources(), R.drawable.pin1);

        start.setTMapPoint(Startpoint);
        start.setIcon(bitmap1);
        start.setPosition((float) 0.5, 1);
        end.setTMapPoint(Endpoint);
        end.setIcon(bitmap2);
        end.setPosition((float) 0.5, 1);
        tmapview.addMarkerItem("start1",start);
        tmapview.addMarkerItem("end1",end);
    }


    @Override
    public void onLocationChange(Location location) {
        if(loc==2)
        tmapview.setLocationPoint(location.getLongitude(), location.getLatitude());

        LocationDistance startloc = new LocationDistance();

        TMapPoint tpoint = tmapgps.getLocation();
        double startA = startloc.distance(tpoint.getLatitude(), tpoint.getLongitude(), Startpoint.getLatitude(), Startpoint.getLongitude(),  "meter");
        if(startA<100)
            Toast.makeText(Course3.this, "출발지에 근접하였습니다.", Toast.LENGTH_SHORT).show();
        double endA = startloc.distance(tpoint.getLatitude(), tpoint.getLongitude(), Startpoint.getLatitude(), Startpoint.getLongitude(),  "meter");
        if(endA<100)
            Toast.makeText(Course3.this, "도착지에 근접하였습니다.", Toast.LENGTH_SHORT).show();
    }
}
