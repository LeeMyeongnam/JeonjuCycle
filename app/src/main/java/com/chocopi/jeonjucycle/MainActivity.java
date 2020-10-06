package com.chocopi.jeonjucycle;

import android.Manifest;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout gomap = findViewById(R.id.gomap);
        LinearLayout gotheme = findViewById(R.id.gotheme);


        gomap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity.this,MapActivity.class);
                startActivity(intent1);
            }
        });
        gotheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity.this, FragmentTheme.class);
                 startActivity(intent1);
            }
        });
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/NanumBarunGothicBold.ttf");
        TextView t1 = findViewById(R.id.t1);
        TextView t2 = findViewById(R.id.t2);
        TextView t3 = findViewById(R.id.mainTitle);
        t1.setTypeface(typeface);
        t2.setTypeface(typeface);
        t3.setTypeface(typeface);
        PermissionListener permissionlistener = new PermissionListener(){
            @Override
            public void onPermissionGranted() {
                Toast.makeText(MainActivity.this, "위치권한 허용됨", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPermissionDenied(ArrayList<String> arrayList) {
                Toast.makeText(MainActivity.this, "위치권한 거부됨", Toast.LENGTH_SHORT).show();
            }
        };

        new TedPermission(this)
                .setPermissionListener(permissionlistener)
                .setDeniedMessage("위치정보 접근 권한을 허용하지 않으면 \n전주한바퀴 앱 이용이 불가능합니다.\n\n권한을 설정해주십시오. [설정] > [권한]")
                .setRationaleMessage("전주한바퀴 앱을 이용하시려면\n위치정보 접근 권한이 필요합니다.")
                .setPermissions(Manifest.permission.ACCESS_FINE_LOCATION)
                .check();
    }

}
