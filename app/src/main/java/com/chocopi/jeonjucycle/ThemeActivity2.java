package com.chocopi.jeonjucycle;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ThemeActivity2 extends Fragment {
    public ThemeActivity2(){}
    ImageView imageview = null;
    int coursenum;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.activity_theme2, container, false);
        super.onCreate(savedInstanceState);

        imageview = view.findViewById(R.id.courseimage);
        final int [] ImageId = {R.drawable.course2, R.drawable.course1, R.drawable.course3, R.drawable.course4, R.drawable.course5, R.drawable.course6};
        final int [] buttonId = {R.id.imageb2, R.id.imageb1, R.id.imageb3, R.id.imageb4, R.id.imageb5, R.id.imageb6};

        final Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/NanumBarunGothicBold.ttf");

        final TextView img[] = new TextView[6];

        for(int i=0; i<buttonId.length; i++){
           final int index = i;
            img[i]=view.findViewById(buttonId[i]);
            img[i].setTypeface(typeface);
            img[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imageview.setImageResource(ImageId[index]);
                    img[index].setTextColor(Color.parseColor("#ff5f2e"));

                    coursenum=index;
                    for(int j=0; j<buttonId.length; j++){
                        if(j!=index)
                            img[j].setTextColor(Color.parseColor("#e1eef6"));
                    }
                }
            });
        }
        imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                {
                    switch (coursenum){
                        case 0:
                            Intent intent1 = new Intent(getActivity(), Course2.class);
                            startActivity(intent1);
                            break;
                        case 1:
                            Intent intent2 = new Intent(getActivity(), Course1.class);
                            startActivity(intent2);
                            break;
                        case 2:
                            Intent intent3 = new Intent(getActivity(), Course3.class);
                            startActivity(intent3);
                            break;
                        case 3:
                            Intent intent4 = new Intent(getActivity(), Course4.class);
                            startActivity(intent4);
                            break;
                        case 4:
                            Intent intent5 = new Intent(getActivity(), Course5.class);
                            startActivity(intent5);
                            break;
                        case 5:
                            Intent intent6 = new Intent(getActivity(), Course6.class);
                            startActivity(intent6);
                            break;
                    }
                }
            }
        });

        return view;
    }

}
