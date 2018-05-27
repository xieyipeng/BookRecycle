package com.example.englishplay.bookrecycle.sign_in;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.englishplay.bookrecycle.MainActivity;
import com.example.englishplay.bookrecycle.R;

public class SignInActivity extends AppCompatActivity {

    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        initViews();
        initClick();
    }

    private void initClick() {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toMain = new Intent(SignInActivity.this, MainActivity.class);
                startActivity(toMain);
            }
        });
    }

    private void initViews() {
        loginButton = findViewById(R.id.login_button);
    }
}
