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
        TextView txtPhrase = findViewById(R.id.txtPhrase);
        String[] phrases = {
                "Epícuro: \"Cuanto más grande es la dificultad, más gloria hay en superarla\".",
                "Soren Kierkegaard: \"La vida debe ser comprendida hacia atrás. Pero debe ser vivida hacia delante\".",
                "Jean-Paul Sartre: \"El hombre está condenado a ser libre\".",
                "Demócrito: \"El hombre valiente es el que no solo supera a sus enemigos, sino también a sus placeres\".",
                "Erich Fromm: \"La creatividad requiere que la valentía se desprenda de las certezas\".",
                "Edmund Burke: \"Aquellos que no conocen la historia están condenados a repetirla\".",
                "Dante: \"De una pequeña chispa puede prender una llama\".",
                "Gandhi: \"Nadie puede herirme sin mi permiso\".",
                "Spinoza: \"Puedo controlar mis pasiones y emociones si puedo entender su naturaleza\".",
                "Sergio Rodríguez Abitia: \"El turismo tiene como objetivo la construcción de mejores personas y no de mejores fortunas\".",
                "John Maynard Keynes: \"El mercado puede permanecer irracional más tiempo del que usted puede permanecer solvente\".",
                "Gary Vaynerchuk: \"Por favor, piense en su legado, porque lo está escribiendo todos los días\".",
                "Stephen Covey: \"El liderazgo efectivo es poner primero lo primero. La gestión eficaz es la disciplina llevada a cabo\".",
                "John D. Rockefeller: \"Una buena gestión consiste en mostrar a gente promedio cómo hacer el trabajo de gente superior\".",
                "Peter F. Drucker: \"Lo que se mide mejora\".",
                "Jeff Bezos: \"Tu marca es lo que la gente dice de ti cuando no estás en la sala\".",
                "Lao Tsé: \"No hay que ir para atrás ni para darse impulso\".",
                "Charles Baudelaire: \"Para trabajar basta estar convencido de una cosa: que trabajar es menos aburrido que divertirse\".",
                "Jacinto Benavente: \"Lo peor que hacen los malos es obligarnos a dudar de los buenos\".",
                "Confucio: \"Aprende a vivir y sabrás morir bien\".",
                "Albert Einstein: \"Cada día sabemos más y entendemos menos\".",
                "Elizabeth Gilbert: \"El conocimiento es una brújula que te guía en la oscuridad\".",
                "Sócrates: \"La verdadera sabiduría está en reconocer la propia ignorancia\".",
                "Robert Collier: \"El éxito es el resultado de pequeños esfuerzos repetidos día tras día\".",
                "Fyodor Dostoyevski: \"El hombre se complace en enumerar sus pesares, pero no enumera sus alegrías\".",
                "Herman Hesse: \"El pájaro pelea hasta que consigue salir del huevo. El huevo es su mundo. Todo ser viviente debería intentar destruir el mundo\".",
                "Mark Twain: \"No hay una visión más triste que la de un joven pesimista\"."
        };

        // Generar un numero aleatorio del 1 al 28
        int randomNumber = generateRandomNum(1, 28);
        // Mostrar el numero aleatorio y frase en los TextView
        txtRandomNum.setText(String.valueOf(randomNumber));
        txtPhrase.setText(phrases[randomNumber - 1]);
    }

    private int generateRandomNum(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }
}