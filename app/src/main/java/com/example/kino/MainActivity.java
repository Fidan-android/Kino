package com.example.kino;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.kino.databinding.ActivityMainBinding;
import com.example.kino.requests.AddFilmActivity;
import com.example.kino.requests.FilmActivity;
import com.example.kino.requests.TheatreActivity;
import com.example.kino.requests.AddTheatreActivity;

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
        binding.theatres.setOnClickListener(view -> {
            startActivity(new Intent(this, TheatreActivity.class));
        });
        binding.films.setOnClickListener(view -> {
            startActivity(new Intent(this, FilmActivity.class));
        });
        binding.addTheatre.setOnClickListener(view -> {
            startActivity(new Intent(this, AddTheatreActivity.class));
        });
        binding.addFilm.setOnClickListener(view -> {
            startActivity(new Intent(this, AddFilmActivity.class));
        });
    }

    @Override
    protected void onStop() {
        binding.theatres.setOnClickListener(null);
        binding.films.setOnClickListener(null);
        binding.addTheatre.setOnClickListener(null);
        binding.addFilm.setOnClickListener(null);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        binding = null;
        super.onDestroy();
    }
}