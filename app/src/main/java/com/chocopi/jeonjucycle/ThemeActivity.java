package com.chocopi.jeonjucycle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ThemeActivity extends Fragment {
    public ThemeActivity(){}
    private View view = null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view =inflater.inflate(R.layout.activity_theme, container, false);

        Button theme1 = view.findViewById(R.id.c1);
        theme1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Course6.class);
                startActivity(intent);
            }
        });
        Button theme2 = view.findViewById(R.id.c2);
        theme2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Course2.class);
                startActivity(intent);
            }
        });
        Button theme3 = view.findViewById(R.id.c3);
        theme3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Course3.class);
                startActivity(intent);
            }
        });
        Button theme4 = view.findViewById(R.id.c4);
        theme4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Course4.class);
                startActivity(intent);
            }
        });
        Button theme5 = view.findViewById(R.id.c5);
        theme5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Course5.class);
                startActivity(intent);
            }
        });
        Button theme6 = view.findViewById(R.id.c6);
        theme6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Course1.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
