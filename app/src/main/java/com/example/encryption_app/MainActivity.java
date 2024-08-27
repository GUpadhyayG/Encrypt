package com.example.encryption_app;

import static android.widget.Toast.*;
import static com.example.encryption_app.AES.generateKey;
import static com.example.encryption_app.AES.encrypt;
//import static com.example.encrypt.AES.generateKey;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.security.InvalidAlgorithmParameterException;
import java.util.Base64;

import javax.crypto.SecretKey;

public class MainActivity extends AppCompatActivity {

    EditText et;
    EditText txt;

    Button btn1,btn2,btn3,btn4,btn5;

    private String result;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        et = findViewById(R.id.et1);
        txt = findViewById(R.id.txt1);

        btn1 = findViewById(R.id.btnEncrypt);
        btn2 = findViewById(R.id.btnDecrypt);
        btn3 = findViewById(R.id.btnSHA);
        btn4 = findViewById(R.id.btnBase64);
        btn5 = findViewById(R.id.btnDecodeBase64);



        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //SecretKey secretKey = null;

                Intent intent=new Intent(MainActivity.this,Activity2.class);
                intent.putExtra("text",et.getText().toString());

                startActivity(intent);


            }


        });


        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String inputText = et.getText().toString();
                String encodedText = BASE64encodeDecode.encodeToBase64(inputText);
                txt.setText(encodedText);

            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String encodedText = et.getText().toString();
                String decodedText = BASE64encodeDecode.decodeFromBase64(encodedText);
                txt.setText(decodedText);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputText = et.getText().toString();
                if (!TextUtils.isEmpty(inputText)) {
                    // Generate SHA-256 hash using the HashUtils class
                    String hashedText = HashUtils.hashWithSHA256(inputText);
                    if (hashedText != null) {
                        txt.setText(hashedText); // Display the hashed text
                    } else {
                        Toast.makeText(MainActivity.this, "Hashing failed!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Please enter text to hash!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}