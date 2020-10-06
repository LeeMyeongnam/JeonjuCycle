package com.chocopi.jeonjucycle;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FragmentTheme extends AppCompatActivity implements View.OnClickListener {
    private final int FRAGMENT1 = 1;
    private final int FRAGMENT2 = 2;

    private Button bt_tab1, bt_tab2;
    private ThemeActivity themeActivity = null;
    private ThemeActivity2 themeActivity2 = null;
    private FragmentTransaction transaction = null;
    public void back(View v){
        finish();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_theme);
        TextView tv = findViewById(R.id.textView);

        bt_tab1 = findViewById(R.id.viewCard);
        bt_tab2 = findViewById(R.id.viewMap);

        bt_tab1.setOnClickListener(FragmentTheme.this);
        bt_tab2.setOnClickListener(FragmentTheme.this);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/NanumBarunGothicBold.ttf");
        bt_tab1.setTypeface(typeface);
        bt_tab2.setTypeface(typeface);
        tv.setTypeface(typeface);

        callFragment(FRAGMENT1);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.viewCard:
                bt_tab1.setTextColor(Color.parseColor("#fcbe32"));
                bt_tab2.setTextColor(Color.parseColor("#454552"));
                callFragment(1);
                break;

            case R.id.viewMap:
                bt_tab2.setTextColor(Color.parseColor("#fcbe32"));
                bt_tab1.setTextColor(Color.parseColor("#454552"));
                callFragment(2);
                break;
        }
    }

    private void callFragment(int frament_no) {

        transaction = getSupportFragmentManager().beginTransaction();

        switch (frament_no) {
            case 1:
                themeActivity = new ThemeActivity();
                transaction.replace(R.id.fragment_container, themeActivity);
                transaction.commit();
                break;

            case 2:
                themeActivity2 = new ThemeActivity2();
                transaction.replace(R.id.fragment_container, themeActivity2);
                transaction.commit();
                break;
        }

    }
}

