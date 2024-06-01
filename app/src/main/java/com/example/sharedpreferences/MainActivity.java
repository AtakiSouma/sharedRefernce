package com.example.sharedpreferences;

import android.content.SharedPreferences;
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

public class MainActivity extends AppCompatActivity {
    EditText edta, edtb, edtKQ;
    Button btnTong, btnClearr;
    TextView txt_lishSU;
    String lichsu = "";

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
        edta = findViewById(R.id.edta);
        edtb = findViewById(R.id.edtb);
        edtKQ = findViewById(R.id.edtKQ);
        txt_lishSU = findViewById(R.id.txtLishSu);
        btnTong = findViewById(R.id.btnTong);
        btnClearr = findViewById(R.id.btnClear);
        SharedPreferences mypref = getSharedPreferences("mysave" , MODE_PRIVATE);
        lichsu = mypref.getString("ls" , "");
        txt_lishSU.setText(lichsu);
        btnTong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = Integer.parseInt(edta.getText().toString());
                int  b = Integer.parseInt(edtb.getText().toString());
                int c = a + b;
                edtKQ.setText(c+"");
                lichsu += a + " + "  + b + " = " + c;
                txt_lishSU.setText(lichsu);
                lichsu +="\n";

            }
        });
        btnClearr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lichsu="";
                txt_lishSU.setText(lichsu);
                edta.setText("");
                edtb.setText("");
                edtKQ.setText("");
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences myPref = getSharedPreferences("mysave", MODE_PRIVATE);
        SharedPreferences.Editor myedit = myPref.edit();
        myedit.putString("ls", lichsu);
        myedit.commit();
    }
}