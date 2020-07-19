package com.geektech.todo.presentation.main;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.geektech.todo.App;
import com.geektech.todo.R;
import com.geektech.todo.data.TodoApiClient;
import com.geektech.todo.model.TodoAction;
import com.geektech.todo.ui.FavoritesFragment;
import com.geektech.todo.ui.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.like.LikeButton;
import com.like.OnLikeListener;
import com.yahoo.mobile.client.android.util.rangeseekbar.RangeSeekBar;

import java.text.MessageFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.spinner)
    Spinner spinner;
    @BindView(R.id.seek_bar1)
    RangeSeekBar seekBar1;
    @BindView(R.id.seek_bar2)
    RangeSeekBar seekBar2;
    @BindView(R.id.tv_category)
    TextView tvCategory;
    @BindView(R.id.tv_explore)
    TextView tvExplore;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_pay_free)
    TextView tvPayFree;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    private String valueOfSpinner;
    private Float minPrice, maxPrice, minAccessibility, maxAccessibility;
    @BindView(R.id.one_user)
    ImageView icOnePerson;
    @BindView(R.id.two_users)
    ImageView icTwoPerson;
    @BindView(R.id.three_users)
    ImageView icThreePerson;
    @BindView(R.id.four_users)
    ImageView icFourPerson;
    @BindView(R.id.for_icon_center)
    ImageView forCenterIcon;
    private List<Float> price, accessibility;
    @BindView(R.id.btn_next_main)
    Button btnNext;
    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigationView;
    @BindView(R.id.favorite_button)
    LikeButton favoriteButton;
    TodoAction todoAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        listeners();
        onSpinnerClick();

        Log.d("ololo", "Stored " + todoAction);
    }

    public void listeners() {

        favoriteButton.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                Log.d("ololo", "liked");
                App.todoStorage.saveTodoAction(todoAction);
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                Log.d("ololo", "unLiked");

            }
        });


        btnNext.setOnClickListener(v -> {
            App.todoApiClient.getAction(valueOfSpinner, null, null,
                    maxPrice, minPrice, null, minAccessibility,
                    maxAccessibility, new TodoApiClient.TodoActionCallback() {
                        @RequiresApi(api = Build.VERSION_CODES.N)
                        @Override
                        public void onSuccess(TodoAction todoAction) {
                            tvExplore.setText(todoAction.getActivity());
                            tvPrice.setText(MessageFormat.format("{0} $ ", todoAction.getPrice().toString()));
                            participantsQuantity(todoAction);
                            progressBar.setProgress((int) (todoAction.getAccessibility() * 100), true);
                            progressBar.setProgress((int) seekBar1.getAbsoluteMinValue());
                            progressBar.setProgress((int) seekBar1.getAbsoluteMaxValue());
                            progressBar.setProgress((int) seekBar2.getAbsoluteMaxValue());
                            progressBar.setProgress((int) seekBar2.getAbsoluteMaxValue());
                            Log.d("ololo", "Receive + " + todoAction.toString());

                            for (TodoAction action: App.todoStorage.getAllActions()) {
                                Log.d("ololo", action.toString());
                            }

                        }

                        @Override
                        public void onFailure(Exception exception) {
                            Log.d("ololo", exception.getMessage());
                        }
                    });
            Log.d("ololo", "click btn next ");
        });


        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    Log.d("ololo", "home is click");
                    showFragment(new HomeFragment());
                    break;
                case R.id.favorites:
                    Log.d("ololo", "favorites is click");
                    showFragment(new FavoritesFragment());
                    break;
            }

            return false;
        });
    }

    public void showFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container_favorites, fragment);
        transaction.commit();
    }

//    private void initViews() {
//        seekBar1 = findViewById(R.id.seek_bar1);
//        seekBar2 = findViewById(R.id.seek_bar2);
//        tvCategory = findViewById(R.id.tv_category);
//        tvExplore = findViewById(R.id.tv_explore);
//        tvPrice = findViewById(R.id.tv_price);
//        tvPayFree = findViewById(R.id.tv_pay_free);
//        progressBar = findViewById(R.id.progress_bar);
//        icOnePerson = findViewById(R.id.one_user);
//        icTwoPerson = findViewById(R.id.two_users);
//        icThreePerson = findViewById(R.id.three_users);
//        icFourPerson = findViewById(R.id.four_users);
//        forCenterIcon = findViewById(R.id.for_icon_center);
//        btnNext = findViewById(R.id.btn_next_main);
//    }

    public void onSpinnerClick() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.types, android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                valueOfSpinner = spinner.getSelectedItem().toString();
                tvCategory.setText(valueOfSpinner);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(MainActivity.this, "No item is selected", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setVisibilityOfImages() {
        forCenterIcon.setVisibility(View.VISIBLE);
        icOnePerson.setVisibility(View.GONE);
        icTwoPerson.setVisibility(View.GONE);
        icThreePerson.setVisibility(View.GONE);
        icFourPerson.setVisibility(View.GONE);

    }

    public void participantsQuantity(TodoAction todoAction) {
        switch (todoAction.getParticipants()) {
            case 1:
                setVisibilityOfImages();
                if (todoAction.getParticipants() == 1) {
                    forCenterIcon.setImageResource(R.drawable.ic_one_user);
                }
                break;
            case 2:
                if (todoAction.getParticipants() == 2) {
                    forCenterIcon.setImageResource(R.drawable.ic_two_users);
                }
                break;
            case 3:
                if (todoAction.getParticipants() == 3)
                    forCenterIcon.setImageResource(R.drawable.ic_three_persons);
                break;
            case 4:
                if (todoAction.getParticipants() == 4)
                    forCenterIcon.setImageResource(R.drawable.ic_four_users);
                break;
        }
    }

}
