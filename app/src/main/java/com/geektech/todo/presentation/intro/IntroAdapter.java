package com.geektech.todo.presentation.intro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.geektech.todo.R;

import java.util.ArrayList;

public class IntroAdapter extends PagerAdapter {

    ArrayList<IntroItem> introItems;

    public IntroAdapter() {
        introItems = new ArrayList<>();
    }

    public void update(ArrayList<IntroItem> introItems){
        this.introItems = introItems;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return introItems.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        ImageView imageView;

        LayoutInflater inflater = (LayoutInflater) container.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.item_intro, container, false);

        imageView = itemView.findViewById(R.id.img_item);
        imageView.setImageResource(introItems.get(position).getImage());

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
