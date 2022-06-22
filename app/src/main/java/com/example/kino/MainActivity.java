package com.example.kino;

import android.app.Activity;
import android.os.Bundle;

import com.example.kino.databinding.ActivityMainBinding;

public class MainActivity extends Activity {

    private ActivityMainBinding binding = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    protected void onStart() {
        super.onStart();

    }
}