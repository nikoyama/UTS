package com.nix.nikoyama;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Tanggal Pengerjaan   : 4 Juni 2021
 * NIM  : 10118048
 * Nama : Niko Yama
 * Kelas    : IF2
 */

public class WalkthroughActivity extends AppCompatActivity {
    private WalkthroughAdapter walkthroughAdapter;
    private LinearLayout layoutWalkthroughIndicator;
    private MaterialButton buttonWalkthroughAction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getSupportActionBar()!=null) {
            getSupportActionBar().hide();
        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_walkthrough);
        layoutWalkthroughIndicator = findViewById(R.id.layoutOnboardingIndicators);
        buttonWalkthroughAction = findViewById(R.id.buttonOnBoardingAction);

        setOnboardingItem();

        ViewPager2 onboardingViewPager = findViewById(R.id.onboardingViewPager);
        onboardingViewPager.setAdapter(walkthroughAdapter);

        setOnboadingIndicator();
        setCurrentOnboardingIndicators(0);

        onboardingViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentOnboardingIndicators(position);
            }
        });

        buttonWalkthroughAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onboardingViewPager.getCurrentItem() + 1 < walkthroughAdapter.getItemCount()) {
                    onboardingViewPager.setCurrentItem(onboardingViewPager.getCurrentItem() + 1);
                } else {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }
            }
        });
    }

    private void setOnboadingIndicator() {
        ImageView[] indicators = new ImageView[walkthroughAdapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(8, 0, 8, 0);
        for (int i = 0; i < indicators.length; i++) {
            indicators[i] = new ImageView(getApplicationContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(
                    getApplicationContext(), R.drawable.indicator_inactive
            ));
            indicators[i].setLayoutParams(layoutParams);
            layoutWalkthroughIndicator.addView(indicators[i]);
        }
    }

    @SuppressLint("SetTextI18n")
    private void setCurrentOnboardingIndicators(int index) {
        int childCount = layoutWalkthroughIndicator.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ImageView imageView = (ImageView) layoutWalkthroughIndicator.getChildAt(i);
            if (i == index) {
                imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.indicator_active));
            } else {
                imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.indicator_inactive));
            }
        }
        if (index == walkthroughAdapter.getItemCount() - 1){
            buttonWalkthroughAction.setText("Start");
        }else {
            buttonWalkthroughAction.setText("Next");
        }
    }

    private void setOnboardingItem() {
        List<WalkthroughItem> walkthroughItems = new ArrayList<>();

        WalkthroughItem item = new WalkthroughItem();
        item.setTitle("Lorem Ipsum");
        item.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
        item.setImage(R.drawable.ic_baseline_image);

        WalkthroughItem item2 = new WalkthroughItem();
        item2.setTitle("");
        item2.setDescription("");
        item2.setImage(R.drawable.ic_baseline_image);

        WalkthroughItem item3 = new WalkthroughItem();
        item3.setTitle("");
        item3.setDescription("");
        item3.setImage(R.drawable.ic_baseline_image);

        walkthroughItems.add(item);
        walkthroughItems.add(item);
        walkthroughItems.add(item);

        walkthroughAdapter = new WalkthroughAdapter(walkthroughItems);

    }


}