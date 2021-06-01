package com.example.cfvirtual;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

import com.example.cfvirtual.ui.RatingGeneratorFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment fragment = new RatingGeneratorFragment();

        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().add(android.R.id.content, fragment, "RATING_GENERATER")
                .addToBackStack(null).commit();
    }

}
