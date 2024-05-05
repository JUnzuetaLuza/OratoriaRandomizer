package com.example.oratoriarandomizer;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void randomNum(View view) {

        // Declarar variables
        Button btnGenerate = findViewById(R.id.btnGenerate);
        TextView txtRandomNum = findViewById(R.id.txtRandomNum);

        // Generar un numero aleatorio del 1 al 28
        int randomNumber = generateRandomNum(1, 28);
        // Mostrar el numero aleatorio en el TextView
        txtRandomNum.setText(String.valueOf(randomNumber));
    }

    private int generateRandomNum(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }
}