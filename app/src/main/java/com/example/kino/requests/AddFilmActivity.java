package com.example.kino.requests;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;

import com.example.kino.adapters.AllTheatreAdapter;
import com.example.kino.databinding.ActivityAddFilmBinding;
import com.example.kino.tasks.AllTheatreGetTask;
import com.example.kino.tasks.FilmPostTask;
import com.example.kino.tasks.TheatresFilmPostTask;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class AddFilmActivity extends AppCompatActivity {

    private ActivityAddFilmBinding binding = null;
    private ArrayList<String[]> theatres = null;
    private String filmId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddFilmBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    protected void onStart() {
        super.onStart();
        binding.btnCreate.setOnClickListener(view -> {
            startAnimation();
            if (binding.etName.getText().toString().equals("")) {
                stopAnimation();
                Snackbar.make(view, "Вы не заполнили все поля", Snackbar.LENGTH_SHORT).show();
            } else {
                try {
                    FilmPostTask filmPostTask = new FilmPostTask();
                    filmPostTask.execute(binding.etName.getText().toString());
                    filmId = filmPostTask.get();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    new Handler().postDelayed(() -> {
                        stopAnimation();
                        configureList();
                    }, 1500);
                }
            }
        });

        binding.btnAdd.setOnClickListener(view -> {
            startAnimation();
            try {
                int n = binding.theatresList.getChildCount();

                for (int i = 0; i < n; i++) {
                    String[] st = (String[]) binding.theatresList.getAdapter().getItem(i);
                    LinearLayout ll = (LinearLayout) binding.theatresList.getChildAt(i);
                    AppCompatCheckBox ch = (AppCompatCheckBox) ll.getChildAt(2);
                    System.out.println(filmId);
                    if (ch.isChecked()) {
                        TheatresFilmPostTask theatresFilmPostTask = new TheatresFilmPostTask();
                        theatresFilmPostTask.execute(st[1], filmId);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                new Handler().postDelayed(() -> {
                    stopAnimation();
                    Snackbar.make(binding.getRoot(), "Фильм успешно прикреплен к кинотеатрам", Snackbar.LENGTH_SHORT).show();
                    finish();
                }, 1500);
            }
        });
    }

    private void startAnimation() {
        binding.btnCreate.setVisibility(View.GONE);
        binding.prgCreate.setVisibility(View.VISIBLE);
    }

    private void stopAnimation() {
        binding.btnCreate.setVisibility(View.VISIBLE);
        binding.prgCreate.setVisibility(View.GONE);
    }

    private void configureList() {
        try {
            AllTheatreGetTask allTheatreGetTask = new AllTheatreGetTask();
            theatres = allTheatreGetTask.execute().get();
            binding.theatresList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
            binding.theatresList.setAdapter(new AllTheatreAdapter(this, theatres));
        } catch (Exception e) {
            e.printStackTrace();
        }

        binding.btnAdd.setVisibility(View.VISIBLE);
        Snackbar.make(binding.getRoot(), "Фильм успешно добавлен", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        binding = null;
        super.onDestroy();
    }


}