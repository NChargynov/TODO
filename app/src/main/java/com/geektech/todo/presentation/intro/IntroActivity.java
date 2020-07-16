package com.geektech.todo.presentation.intro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.geektech.todo.App;
import com.geektech.todo.data.AppPreference;
import com.geektech.todo.presentation.main.MainActivity;
import com.geektech.todo.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;


public class IntroActivity extends AppCompatActivity {


    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Button btnNext;
    private Button btnSkip;
    int currentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.viewPager);
        btnNext = findViewById(R.id.btn_next);
        btnSkip = findViewById(R.id.btn_skip);


        initViewPagerAdapter();
        listeners();

    }

    private void listeners() {
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewPager.getCurrentItem() != 2) {
                    viewPager.setCurrentItem(currentPage + 1);
                } else {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    AppPreference.setLaunched(true);
                    finish();
                }

            }
        });

        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                AppPreference.setLaunched(true);
                finish();
            }
        });

    }

    private ArrayList<IntroItem> getItems() {
        ArrayList<IntroItem> introItems = new ArrayList<>();
        introItems.add(new IntroItem(R.drawable.girl));
        introItems.add(new IntroItem(R.drawable.update));
        introItems.add(new IntroItem(R.drawable.thankyou));

        return introItems;
    }

    private void initViewPagerAdapter() {

        IntroAdapter adapter = new IntroAdapter();
        viewPager.setAdapter(adapter);
        adapter.update(getItems());
        init();
        tabLayout.setupWithViewPager(viewPager, true);


    }

    private void init() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentPage = position;
                if (position == 2) {
                    btnSkip.setVisibility(View.GONE);
                    btnNext.setText(R.string.start);
                } else {
                    btnNext.setText(R.string.next);
                    btnSkip.setText(R.string.skip);
                    btnSkip.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
}
