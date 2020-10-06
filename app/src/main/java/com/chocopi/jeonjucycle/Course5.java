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

public class Course5 extends AppCompatActivity implements TMapGpsManager.onLocationChangedCallback {
    TMapView tmapview = null;
    int marker = 1;
    int loc=1;
    private TMapGpsManager tmapgps = null;
    TMapMarkerItem start = null;
    TMapMarkerItem end= null;
    TMapPoint Startpoint = new TMapPoint(35.815039, 127.153937);
    TMapPoint Endpoint = new TMapPoint(35.814050, 127.154491);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course5);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("코스안내")
                .setMessage("선택하신 코스는 약 3.71km 입니다.\n주요 경유지는\n◎전주한옥마을\n◎경기전\n◎남부시장\n◎전주천변\n◎오목대이목대")
                .setPositiveButton("확인", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int whichButton){

                    }
                });

        AlertDialog dialog = builder.create();    // 알림창 객체 생성
        dialog.show();    // 알림창 띄우기


        RelativeLayout relativeLayout = findViewById(R.id.course5);
        tmapview = new TMapView(this);
        relativeLayout.addView(tmapview);

        TextView tv = findViewById(R.id.textView);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/NanumBarunGothicBold.ttf");
        tv.setTypeface(typeface);
        Bitmap bike = BitmapFactory.decodeResource(this.getResources(), R.drawable.bicycle);
        tmapview.setIcon(bike);
        tmapview.setCenterPoint(127.153937, 35.815039);
        tmapview.setLocationPoint(127.153937, 35.815039);
        tmapview.setSKPMapApiKey("6043d8dd-b18e-3a24-9893-acb9bbc43822");
        tmapview.setLanguage(TMapView.LANGUAGE_KOREAN);
        tmapview.setZoomLevel(15);
        tmapview.setMapType(TMapView.MAPTYPE_STANDARD);
        tmapview.setCompassMode(true);
        tmapview.setTrackingMode(true);

        tmapgps = new TMapGpsManager(Course5.this);
        tmapgps.setMinTime(1000);
        tmapgps.setMinDistance(5);
        tmapgps.setProvider(tmapgps.NETWORK_PROVIDER);
        tmapgps.OpenGps();

        ImageView location = findViewById(R.id.location);
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loc=2;
                tmapview.setIconVisibility(true);
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

                TMapPoint startpoint1 = new TMapPoint(35.815039, 127.153937); //한옥마을 관광안내소
                TMapPoint midpoint1 = new TMapPoint(35.817506, 127.152034); //한옥마을 길
                TMapPoint midpoint2 = new TMapPoint(35.814535, 127.149633); //경기전
                TMapPoint midpoint3 = new TMapPoint(35.812645, 127.146965); //남부시장
                TMapPoint midpoint4 = new TMapPoint(35.813252, 127.149315); //전동성당
                TMapPoint midpoint5 = new TMapPoint(35.810933, 127.151522); //천변 길
                TMapPoint midpoint6 = new TMapPoint(35.812144, 127.160280); //기린대로
                TMapPoint midpoint7 = new TMapPoint(35.814050, 127.154491); //오목대 이목대


                TMapPolyLine polyline1 = null;
                TMapPolyLine polyline2 = null;
                TMapPolyLine polyline3 = null;
                TMapPolyLine polyline4 = null;
                TMapPolyLine polyline5 = null;
                TMapPolyLine polyline6 = null;
                TMapPolyLine polyline7 = null;
                try {
                    polyline1 = tmapdata1.findPathData(startpoint1, midpoint1);
                    polyline1.setLineColor(Color.parseColor("#C700DD"));
                    polyline1.setOutLineColor(Color.parseColor("#C700DD"));
                    polyline1.setLineWidth(20);
                    tmapview.addTMapPolyLine("polyline1", polyline1);
                    polyline2 = tmapdata1.findPathData(midpoint1, midpoint2);
                    polyline2.setLineColor(Color.parseColor("#C700DD"));
                    polyline2.setOutLineColor(Color.parseColor("#C700DD"));
                    polyline2.setLineWidth(20);
                    tmapview.addTMapPolyLine("polyline2", polyline2);
                    polyline3 = tmapdata1.findPathData(midpoint2, midpoint3);
                    polyline3.setLineColor(Color.parseColor("#C700DD"));
                    polyline3.setOutLineColor(Color.parseColor("#C700DD"));
                    polyline3.setLineWidth(20);
                    tmapview.addTMapPolyLine("polyline3", polyline3);
                    polyline4 = tmapdata1.findPathData(midpoint3, midpoint4);
                    polyline4.setLineColor(Color.parseColor("#C700DD"));
                    polyline4.setOutLineColor(Color.parseColor("#C700DD"));
                    polyline4.setLineWidth(20);
                    tmapview.addTMapPolyLine("polyline4", polyline4);
                    polyline5 = tmapdata1.findPathData(midpoint4, midpoint5);
                    polyline5.setLineColor(Color.parseColor("#C700DD"));
                    polyline5.setOutLineColor(Color.parseColor("#C700DD"));
                    polyline5.setLineWidth(20);
                    tmapview.addTMapPolyLine("polyline5", polyline5);
                    polyline6 = tmapdata1.findPathData(midpoint5, midpoint6);
                    polyline6.setLineColor(Color.parseColor("#C700DD"));
                    polyline6.setOutLineColor(Color.parseColor("#C700DD"));
                    polyline6.setLineWidth(20);
                    tmapview.addTMapPolyLine("polyline6", polyline6);
                    polyline7 = tmapdata1.findPathData(midpoint6, midpoint7);
                    polyline7.setLineColor(Color.parseColor("#C700DD"));
                    polyline7.setOutLineColor(Color.parseColor("#C700DD"));
                    polyline7.setLineWidth(20);
                    tmapview.addTMapPolyLine("polyline7", polyline7);

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
    private void addMarker() {
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
            Toast.makeText(Course5.this, "출발지에 근접하였습니다.", Toast.LENGTH_SHORT).show();
        double endA = startloc.distance(tpoint.getLatitude(), tpoint.getLongitude(), Startpoint.getLatitude(), Startpoint.getLongitude(),  "meter");
        if(endA<100)
            Toast.makeText(Course5.this, "도착지에 근접하였습니다.", Toast.LENGTH_SHORT).show();
    }
}