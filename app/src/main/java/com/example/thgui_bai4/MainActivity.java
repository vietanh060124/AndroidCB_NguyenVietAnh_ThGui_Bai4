package com.example.thgui_bai4;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etName, etHeight, etWeight;
    private Button btnCalculateBMI;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etHeight = findViewById(R.id.etHeight);
        etWeight = findViewById(R.id.etWeight);
        btnCalculateBMI = findViewById(R.id.btnCalculateBMI);
        tvResult = findViewById(R.id.tvResult);

        btnCalculateBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String heightStr = etHeight.getText().toString();
                String weightStr = etWeight.getText().toString();

                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(heightStr) || TextUtils.isEmpty(weightStr)) {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        double height = Double.parseDouble(heightStr);
                        double weight = Double.parseDouble(weightStr);
                        double bmi = weight / (height * height);

                        String diagnosis = getBMIDiagnosis(bmi);
                        tvResult.setText(String.format("Họ tên: %s\nBMI: %.2f\nChẩn đoán: %s", name, bmi, diagnosis));
                    } catch (NumberFormatException e) {
                        Toast.makeText(MainActivity.this, "Dữ liệu không hợp lệ", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private String getBMIDiagnosis(double bmi) {
        if (bmi < 18.5) {
            return "Thiếu cân";
        } else if (bmi >= 18.5 && bmi < 24.9) {
            return "Bình thường";
        } else if (bmi >= 25 && bmi < 29.9) {
            return "Thừa cân";
        } else {
            return "Béo phì";
        }
    }
}