package com.chocopi.jeonjucycle;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.skp.Tmap.TMapGpsManager;
import com.skp.Tmap.TMapMarkerItem;
import com.skp.Tmap.TMapPoint;
import com.skp.Tmap.TMapView;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class MapActivity extends AppCompatActivity {
    TMapView tmapview;
    TMapGpsManager tmapgps;

    String[] n = null;
    String[] x = null;
    String[] y = null;
    String[] p = null;
    String[] a = null;

    ImageView wifi;
    ImageView bike;
    ImageView toilet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        wifi = findViewById(R.id.imageView3);
        bike = findViewById(R.id.imageView4);
        toilet = findViewById(R.id.imageView5);

        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.mapview);

        tmapview = new TMapView(this);
        tmapview.setSKPMapApiKey("79756655-a3bb-3153-8d13-b93c09fd2977");
        tmapview.setLocationPoint(127.130064, 35.826920);
        tmapview.setLanguage(TMapView.LANGUAGE_KOREAN);
        tmapview.setMapType(TMapView.MAPTYPE_STANDARD);
        tmapview.setTrackingMode(true);
        tmapview.setZoomLevel(13);
        //   tmapview.setCompassMode(true);
        tmapview.setBicycleInfo(true);
        tmapview.setBicycleFacilityInfo(true);

        frameLayout.addView(tmapview);

        tmapgps = new TMapGpsManager(this);
        tmapgps.setMinDistance(5);
        tmapgps.setMinTime(1000);
        tmapgps.setProvider(TMapGpsManager.NETWORK_PROVIDER);
        tmapgps.OpenGps();


        tmapview.setOnCalloutRightButtonClickListener(new TMapView.OnCalloutRightButtonClickCallback() {
            @Override
            public void onCalloutRightButton(TMapMarkerItem MarkerItem) {
                Intent intent = new Intent(MapActivity.this, popupActivity.class);
                intent.putExtra("name",MarkerItem.getName().toString());
                intent.putExtra("phonenumber",MarkerItem.getID().toString());
                startActivity(intent);
            }
        });
    }

    public void back(View v)
    {
        finish();
    }

    public void mylocation(View v)
    {
        TMapPoint point = tmapgps.getLocation();
        tmapview.setIconVisibility(true);
        tmapview.setLocationPoint(point.getLongitude(), point.getLatitude());
        tmapview.setCenterPoint(point.getLongitude(), point.getLatitude());
    }

    public void wifi(View v)
    {
        Bitmap marker = BitmapFactory.decodeResource(this.getResources(), R.drawable.marker);

        wifi.setImageResource(R.drawable.ic_wifi_orange_24dp);
        bike.setImageResource(R.drawable.ic_directions_bike_black_24dp);
        toilet.setImageResource(R.drawable.ic_wc_black_24dp);

        tmapview.removeAllMarkerItem();

        xmlParsingWifi();

        double cLatitude[] = new double[n.length];
        double cLongitude[] = new double[n.length];

        for(int i = 0; i < n.length; i++) {
            cLatitude[i] = Double.parseDouble(x[i]);
            cLongitude[i] = Double.parseDouble(y[i]);
        }

        TMapPoint Cpoint[] = new TMapPoint[n.length];

        for(int i = 0; i < n.length; i++) {
            Cpoint[i] = new TMapPoint(cLatitude[i],  cLongitude[i]);
        }

        TMapMarkerItem cMarkerItem[] = new TMapMarkerItem[n.length];

        for(int i = 0; i < n.length; i++) {
            cMarkerItem[i] = new TMapMarkerItem();
            cMarkerItem[i].setTMapPoint(Cpoint[i]);

            cMarkerItem[i].setVisible(TMapMarkerItem.VISIBLE);
            cMarkerItem[i].setCalloutTitle("와이파이존");
            cMarkerItem[i].setName(n[i]);
            cMarkerItem[i].setCalloutSubTitle(n[i]);

            cMarkerItem[i].setCanShowCallout(true);
            cMarkerItem[i].setPosition((float) 0.5, 1);
            cMarkerItem[i].setIcon(marker);

            tmapview.addMarkerItem("a"+i, cMarkerItem[i]);
        }
    }

    public void Bikestore(View v)
    {
        wifi.setImageResource(R.drawable.ic_wifi_black_24dp);
        bike.setImageResource(R.drawable.ic_directions_bike_orange_24dp);
        toilet.setImageResource(R.drawable.ic_wc_black_24dp);

        Bitmap marker = BitmapFactory.decodeResource(this.getResources(), R.drawable.marker);
        Bitmap arrow = BitmapFactory.decodeResource(this.getResources(), R.drawable.arrow);

        tmapview.removeAllMarkerItem();

        xmlParsingBicycle();

        double bLatitude[] = new double[n.length];
        double bLongitude[] = new double[n.length];

        TMapPoint Bpoint[] = new TMapPoint[n.length];
        TMapMarkerItem bMarkerItem[] = new TMapMarkerItem[n.length];

        for(int i = 0; i < n.length; i++) {
            bLatitude[i] = Double.parseDouble(x[i]);
            bLongitude[i] = Double.parseDouble(y[i]);
            Bpoint[i] = new TMapPoint(bLatitude[i],  bLongitude[i]);

            bMarkerItem[i] = new TMapMarkerItem();
            bMarkerItem[i].setTMapPoint(Bpoint[i]);
            bMarkerItem[i].setVisible(TMapMarkerItem.VISIBLE);
            bMarkerItem[i].setIcon(marker);
            bMarkerItem[i].setCalloutTitle("자전거판매소");
            bMarkerItem[i].setCalloutSubTitle(n[i]);
            bMarkerItem[i].setName(n[i]);
            bMarkerItem[i].setCanShowCallout(true);
            bMarkerItem[i].setPosition((float) 0.5, 1);
            bMarkerItem[i].setCalloutRightButtonImage(arrow);
            tmapview.addMarkerItem(p[i], bMarkerItem[i]);
        }
    }

    public void Toilet(View v)
    {

        wifi.setImageResource(R.drawable.ic_wifi_black_24dp);
        bike.setImageResource(R.drawable.ic_directions_bike_black_24dp);
        toilet.setImageResource(R.drawable.ic_wc_orange_24dp);

        Bitmap marker = BitmapFactory.decodeResource(this.getResources(), R.drawable.marker);
        Bitmap arrow = BitmapFactory.decodeResource(this.getResources(), R.drawable.arrow);

        tmapview.removeAllMarkerItem();

        xmlParsingToilet();

        double tLatitude[]  = new double[n.length];
        double tLongitude[] = new double[n.length];

        for(int i = 0; i < n.length; i++) {
            tLatitude[i]   = Double.parseDouble(x[i]);
            tLongitude[i]  = Double.parseDouble(y[i]);
        }

        TMapPoint Tpoint[] = new TMapPoint[n.length];

        for(int i = 0; i < n.length; i++) {
            Tpoint[i]      = new TMapPoint(tLatitude[i],  tLongitude[i]);
        }

        TMapMarkerItem tMarkerItem[] = new TMapMarkerItem[n.length];

        for(int i = 0; i < n.length; i++) {
            tMarkerItem[i] = new TMapMarkerItem();
            tMarkerItem[i].setTMapPoint(Tpoint[i]);
            tMarkerItem[i].setVisible(TMapMarkerItem.VISIBLE);
            tMarkerItem[i].setIcon(marker);
            tMarkerItem[i].setCalloutTitle("화장실");
            tMarkerItem[i].setCalloutSubTitle(n[i]);
            tMarkerItem[i].setName(n[i]);
            tMarkerItem[i].setCanShowCallout(true);
            tMarkerItem[i].setPosition((float) 0.5, 1);
            tMarkerItem[i].setCalloutRightButtonImage(arrow);
            tmapview.addMarkerItem(p[i], tMarkerItem[i]);
        }
    }


    public void xmlParsingWifi () {

        try {
            InputStream culture = getResources().openRawResource(R.raw.wifi);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(culture);

            NodeList rowList = doc.getElementsByTagName("Row");
            NodeList nameList = doc.getElementsByTagName("name");
            NodeList latitudeList = doc.getElementsByTagName("latitude");
            NodeList longitudeList = doc.getElementsByTagName("longitude");
            NodeList numberList = doc.getElementsByTagName("phonenumber");
            NodeList addressList = doc.getElementsByTagName("address");

            n = new String[rowList.getLength()];
            x = new String[rowList.getLength()];
            y = new String[rowList.getLength()];
            p = new String[rowList.getLength()];
            a = new String[rowList.getLength()];

            for (int i = 0; i < rowList.getLength(); i++) {
                n[i] = nameList.item(i).getFirstChild().getNodeValue();
                x[i] = latitudeList.item(i).getFirstChild().getNodeValue();
                y[i] = longitudeList.item(i).getFirstChild().getNodeValue();
                p[i] = numberList.item(i).getFirstChild().getNodeValue();
                a[i] = addressList.item(i).getFirstChild().getNodeValue();
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void xmlParsingBicycle(){

        try {
            InputStream bicycle = getResources().openRawResource(R.raw.bicycle);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(bicycle);

            NodeList rowList = doc.getElementsByTagName("Row");
            NodeList nameList = doc.getElementsByTagName("name");
            NodeList latitudeList = doc.getElementsByTagName("latitude");
            NodeList longitudeList = doc.getElementsByTagName("longitude");
            NodeList phonenumberList = doc.getElementsByTagName("phonenumber");
            NodeList addressList = doc.getElementsByTagName("address");

            n = new String[rowList.getLength()];
            x = new String[rowList.getLength()];
            y = new String[rowList.getLength()];
            p = new String[rowList.getLength()];
            a = new String[rowList.getLength()];

            for(int i = 0; i < rowList.getLength(); i++) {
                n[i] = nameList.item(i).getFirstChild().getNodeValue();
                x[i] = latitudeList.item(i).getFirstChild().getNodeValue();
                y[i] = longitudeList.item(i).getFirstChild().getNodeValue();
                p[i] = phonenumberList.item(i).getFirstChild().getNodeValue();
                a[i] = addressList.item(i).getFirstChild().getNodeValue();
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void xmlParsingToilet(){

        try {
            InputStream toilet = getResources().openRawResource(R.raw.toilet);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(toilet));

            NodeList rowList = doc.getElementsByTagName("Row");
            NodeList nameList = doc.getElementsByTagName("name");
            NodeList latitudeList = doc.getElementsByTagName("latitude");
            NodeList longitudeList = doc.getElementsByTagName("longitude");
            NodeList phonenumberList = doc.getElementsByTagName("phonenumber");
            NodeList addressList = doc.getElementsByTagName("address");

            n = new String[rowList.getLength()];
            x = new String[rowList.getLength()];
            y = new String[rowList.getLength()];
            p = new String[rowList.getLength()];
            a = new String[rowList.getLength()];

            for(int i = 0; i < rowList.getLength(); i++) {
                n[i] = nameList.item(i).getFirstChild().getNodeValue();
                x[i] = latitudeList.item(i).getFirstChild().getNodeValue();
                y[i] = longitudeList.item(i).getFirstChild().getNodeValue();
                p[i] = phonenumberList.item(i).getFirstChild().getNodeValue();
                a[i] = addressList.item(i).getFirstChild().getNodeValue();
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
