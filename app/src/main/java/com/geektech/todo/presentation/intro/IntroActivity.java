package com.geektech.todo.presentation.intro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.geektech.todo.data.preference.AppPreference;
import com.geektech.todo.presentation.main.MainActivity;
import com.geektech.todo.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class IntroActivity extends AppCompatActivity {


    @BindView(R.id.tab_layout) TabLayout tabLayout;
    @BindView(R.id.viewPager) ViewPager viewPager;
    @BindView(R.id.btn_next) Button btnNext;
    @BindView(R.id.btn_skip) Button btnSkip;
    int currentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        ButterKnife.bind(this);
        initViewPagerAdapter();
        listeners();

    }

    private void listeners() {
        btnNext.setOnClickListener(v -> {
            if (viewPager.getCurrentItem() != 2) {
                viewPager.setCurrentItem(currentPage + 1);
            } else {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                AppPreference.setLaunched(true);
                finish();
            }

        });

        btnSkip.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            AppPreference.setLaunched(true);
            finish();
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
