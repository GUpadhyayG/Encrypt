package com.example.encryption_app;

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

public class MainActivity extends AppCompatActivity {

    EditText et;
    TextView txt;

    Button btn1,btn2,btn3,btn4,btn5;

    private EncryptionManager encryptionManager;
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

        et=findViewById(R.id.et1);
        txt=findViewById(R.id.txt1);

        btn1=findViewById(R.id.btnBase64);
        btn2=findViewById(R.id.btnDecodeBase64);
        btn3=findViewById(R.id.btnSHA);
        btn4=findViewById(R.id.btnEncrypt);
        btn5=findViewById(R.id.btnDecrypt);

        encryptionManager =EncryptionManager.getInstance();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data =et.getText().toString();
                if(!TextUtils.isEmpty(data))
                {
                    result=encryptionManager.encodeBase64(data);
                    txt.setText(result);
                }
                else{
                    Toast.makeText(MainActivity.this,"Field is empty.",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(result))
                {
                    result=encryptionManager.decodeBase64(result);
                    txt.setText((result));
                }
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data=et.getText().toString();
                if(!TextUtils.isEmpty(data)) {
                    result = encryptionManager.getSHA256(data);
                    txt.setText(result);

                    if (result.equals(encryptionManager.getSHA256(data))) {
                        Log.d("TAG", "SAME");
                    } else {
                        Log.d("TAG", "same");
                    }
                }
                else {
                    Toast.makeText(MainActivity.this,"Field is empty",Toast.LENGTH_SHORT).show();
                }
            }

        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // encrypt logic
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Decrypt logic
            }
        });



    }
}