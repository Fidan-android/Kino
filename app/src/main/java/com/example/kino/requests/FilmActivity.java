package com.example.kino.requests;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kino.adapters.FilmAdapter;
import com.example.kino.databinding.ActivityFilmBinding;
import com.example.kino.tasks.FilmGetTask;
import com.example.kino.tasks.TheatreGetTask;

import java.util.ArrayList;

public class FilmActivity extends AppCompatActivity {

    private ActivityFilmBinding binding = null;
    private ArrayList<String[]> films = new ArrayList<>();
    private FilmAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFilmBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void configureView() {
        // создаем адаптер
        adapter = new FilmAdapter(this, films);
        binding.findResultList.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        binding.btnFind.setOnClickListener(view -> {
            this.startAnimation();
            new Handler().postDelayed(() -> {
                if (!binding.etFind.getText().toString().equals("")) {
                    try {
                        FilmGetTask filmGetTask = new FilmGetTask();
                        filmGetTask.execute(binding.etFind.getText().toString());
                        films = filmGetTask.get();
                        configureView();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        stopAnimation();
                    }
                } else {
                    stopAnimation();
                }
            }, 3000);
        });
    }

    private void startAnimation() {
        binding.btnFind.setVisibility(View.GONE);
        binding.prgFind.setVisibility(View.VISIBLE);
    }

    private void stopAnimation() {
        binding.btnFind.setVisibility(View.VISIBLE);
        binding.prgFind.setVisibility(View.GONE);
    }

    @Override
    protected void onStop() {
        binding.btnFind.setOnClickListener(null);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        binding = null;
        super.onDestroy();
    }
}