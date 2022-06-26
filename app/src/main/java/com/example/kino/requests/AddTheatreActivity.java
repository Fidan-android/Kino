package com.example.kino.requests;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kino.databinding.ActivityAddTheatreBinding;
import com.example.kino.tasks.TheatreGetTask;
import com.example.kino.tasks.TheatrePostTask;
import com.google.android.material.snackbar.Snackbar;

public class AddTheatreActivity extends AppCompatActivity {

    private ActivityAddTheatreBinding binding = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddTheatreBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    protected void onStart() {
        super.onStart();
        binding.btnCreate.setOnClickListener(view -> {
            startAnimation();
            if (binding.etName.getText().toString().equals("")
                    || binding.etAddress.getText().toString().equals("")) {
                stopAnimation();
                Snackbar.make(view, "Вы не заполнили все поля", Snackbar.LENGTH_SHORT).show();
            } else {
                try {
                    TheatrePostTask theatreTask = new TheatrePostTask();
                    theatreTask.execute(binding.etName.getText().toString(), binding.etAddress.getText().toString());
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    new Handler().postDelayed(() -> {
                        stopAnimation();
                        finish();
                    }, 3000);
                }
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

    @Override
    protected void onDestroy() {
        binding = null;
        super.onDestroy();
    }
}