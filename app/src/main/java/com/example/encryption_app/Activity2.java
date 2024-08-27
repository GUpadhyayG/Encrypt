package com.example.encryption_app;

import static com.example.encryption_app.AES.generateKey;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import javax.crypto.SecretKey;

public class Activity2 extends AppCompatActivity {

    TextView txt2;
    Button btn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txt2=findViewById(R.id.text2);

        btn=findViewById(R.id.btn2);
        Intent intent= getIntent();
        String plainText = intent.getStringExtra("text");
        if (plainText == null || plainText.isEmpty()) {
            txt2.setText("No text to encrypt");
            return; // Exit if no text to encrypt
        }
        try {
           SecretKey secretKey = generateKey();
            String encryptedText = AES.encrypt(plainText,secretKey);
            txt2.setText(encryptedText);

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    try {

                     String Decrypted = AES.decrypt(encryptedText,secretKey);
                        txt2.setText(Decrypted);

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

                }
            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}