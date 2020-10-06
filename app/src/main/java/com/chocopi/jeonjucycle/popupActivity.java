package com.chocopi.jeonjucycle;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * Created by 이명남 on 2017-11-14.
 */

public class popupActivity extends Activity {

    private TextView NameTextview;
    private TextView PhonenumberTextview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        WindowManager.LayoutParams layoutParams= new WindowManager.LayoutParams();
        layoutParams.flags= WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        layoutParams.dimAmount= 0.9f;
        getWindow().setAttributes(layoutParams);

        setContentView(R.layout.activity_popup);

        NameTextview = (TextView)findViewById(R.id.textView2);
        PhonenumberTextview = (TextView)findViewById(R.id.textView7);

        Intent intent1 = getIntent();
        String name = intent1.getStringExtra("name");
        NameTextview.setText(name);

        Intent intent2 = getIntent();
        String phonenumber = intent2.getStringExtra("phonenumber");
        PhonenumberTextview.setText(phonenumber);


        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/NanumBarunGothicBold.ttf");
        NameTextview.setTypeface(typeface);
        PhonenumberTextview.setTypeface(typeface);
    }

    public void call(View v) {
        Intent intent = new Intent(Intent.ACTION_DIAL,
                Uri.parse("tel:"+ PhonenumberTextview.getText().toString()));
        startActivity(intent);
    }

    public void cancel(View v) {
        finish();
    }
}
