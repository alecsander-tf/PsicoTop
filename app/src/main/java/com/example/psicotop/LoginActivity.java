package com.example.psicotop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.psicotop.banco.Post;
import com.example.psicotop.modal.Usuario;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Post p = new Post();

                Usuario u = new Usuario("barbara", "15");
                p.postUser(u);

                //startActivity(new Intent(getApplicationContext(), MenuActivity.class));
            }
        });
    }
}
