package com.example.oratoriarandomizer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;

import java.util.Random;
import android.content.Context;
import android.content.SharedPreferences;

public class FraseFragment extends Fragment {

    Phrase[] phrases = new Phrase[28];
    TextView txtPhrase1;
    TextView txtPhrase2;
    TextView txtPhrase3;
    //TextView txtRandomNum;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_frase, container, false);

        txtPhrase1 = view.findViewById(R.id.txtPhraseFrase1);
        txtPhrase2 = view.findViewById(R.id.txtPhraseFrase2);
        txtPhrase3 = view.findViewById(R.id.txtPhraseFrase3);
        txtPhrase1.setOnClickListener(v -> selectPhrase(txtPhrase1));
        txtPhrase2.setOnClickListener(v -> selectPhrase(txtPhrase2));
        txtPhrase3.setOnClickListener(v -> selectPhrase(txtPhrase3));

        Button btnRandom = view.findViewById(R.id.btnRandomFrase);
        Button btnReset = view.findViewById(R.id.btnResetFrase);
        btnRandom.setOnClickListener(this::randomPhrase);
        btnReset.setOnClickListener(this::resetPhrases);

        initializePhrases();

        return view;
    }

    private SharedPreferences getSharedPreferences() {
        return requireActivity().getSharedPreferences("FraseFragmentPreferences", Context.MODE_PRIVATE);
    }

    public void initializePhrases() {
        phrases[0] = new Phrase(1,"Epícuro:\n \"Cuanto más grande es la dificultad, más gloria hay en superarla\".",true);
        phrases[1] = new Phrase(2,"Soren Kierkegaard:\n \"La vida debe ser comprendida hacia atrás. Pero debe ser vivida hacia delante\".",true);
        phrases[2] = new Phrase(3,"Jean-Paul Sartre:\n \"El hombre está condenado a ser libre\".",true);
        phrases[3] = new Phrase(4,"Anaxágoras:\n \"En todo hay una parte de todo\".",true);
        phrases[4] = new Phrase(5,"Demócrito:\n \"El hombre valiente es el que no solo supera a sus enemigos, sino también a sus placeres\".",true);
        phrases[5] = new Phrase(6,"Erich Fromm:\n \"La creatividad requiere que la valentía se desprenda de las certezas\".",true);
        phrases[6] = new Phrase(7,"Edmund Burke:\n \"Aquellos que no conocen la historia están condenados a repetirla\".",true);
        phrases[7] = new Phrase(8,"Dante:\n \"De una pequeña chispa puede prender una llama\".",true);
        phrases[8] = new Phrase(9,"Gandhi:\n \"Nadie puede herirme sin mi permiso\".",true);
        phrases[9] = new Phrase(10,"Spinoza:\n \"Puedo controlar mis pasiones y emociones si puedo entender su naturaleza\".",true);
        phrases[10] = new Phrase(11,"Sergio Rodríguez Abitia:\n \"El turismo tiene como objetivo la construcción de mejores personas y no de mejores fortunas\".",true);
        phrases[11] = new Phrase(12,"John Maynard Keynes:\n \"El mercado puede permanecer irracional más tiempo del que usted puede permanecer solvente\".",true);
        phrases[12] = new Phrase(13,"Gary Vaynerchuk:\n \"Por favor, piense en su legado, porque lo está escribiendo todos los días\".",true);
        phrases[13] = new Phrase(14,"Stephen Covey:\n \"El liderazgo efectivo es poner primero lo primero. La gestión eficaz es la disciplina llevada a cabo\".",true);
        phrases[14] = new Phrase(15,"John D. Rockefeller:\n \"Una buena gestión consiste en mostrar a gente promedio cómo hacer el trabajo de gente superior\".",true);
        phrases[15] = new Phrase(16,"Peter F. Drucker:\n \"Lo que se mide mejora\".",true);
        phrases[16] = new Phrase(17,"Jeff Bezos:\n \"Tu marca es lo que la gente dice de ti cuando no estás en la sala\".",true);
        phrases[17] = new Phrase(18,"Lao Tsé:\n \"No hay que ir para atrás ni para darse impulso\".",true);
        phrases[18] = new Phrase(19,"Charles Baudelaire:\n \"Para trabajar basta estar convencido de una cosa: que trabajar es menos aburrido que divertirse\".",true);
        phrases[19] = new Phrase(20,"Jacinto Benavente:\n \"Lo peor que hacen los malos es obligarnos a dudar de los buenos\".",true);
        phrases[20] = new Phrase(21,"Confucio:\n \"Aprende a vivir y sabrás morir bien\".",true);
        phrases[21] = new Phrase(22,"Albert Einstein:\n \"Cada día sabemos más y entendemos menos\".",true);
        phrases[22] = new Phrase(23,"Elizabeth Gilbert:\n \"El conocimiento es una brújula que te guía en la oscuridad\".",true);
        phrases[23] = new Phrase(24,"Sócrates:\n \"La verdadera sabiduría está en reconocer la propia ignorancia\".",true);
        phrases[24] = new Phrase(25,"Robert Collier:\n \"El éxito es el resultado de pequeños esfuerzos repetidos día tras día\".",true);
        phrases[25] = new Phrase(26,"Fyodor Dostoyevski:\n \"El hombre se complace en enumerar sus pesares, pero no enumera sus alegrías\".",true);
        phrases[26] = new Phrase(27,"Herman Hesse:\n \"El pájaro pelea hasta que consigue salir del huevo. El huevo es su mundo. Todo ser viviente debería intentar destruir el mundo\".",true);
        phrases[27] = new Phrase(28,"Mark Twain:\n \"No hay una visión más triste que la de un joven pesimista\".",true);

        SharedPreferences sharedPreferences = getSharedPreferences();
        for (Phrase phrase : phrases) {
            boolean isHab = sharedPreferences.getBoolean("phrase_" + phrase.getId(), true);
            phrase.setHab(isHab);
        }
    }
    private int generateRandomNum(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }
    public void randomPhrase(View view) {

        int[] randomNumber = new int[3];
        int[] posArray = new int[3];
        boolean anyPhraseHab = false;
        int habCount = 0;

        for (Phrase phrase : phrases) {
            if (phrase.isHab()) {
                anyPhraseHab = true;
                habCount++;
            }
        }
        if (habCount < 3) {
            txtPhrase1.setText("No hay suficientes frases disponibles.");
            txtPhrase2.setText("");
            txtPhrase3.setText("");
            return;
        }
        if (anyPhraseHab) {
            for (int i = 0; i < 3; i++) {
                do {
                    randomNumber[i] = generateRandomNum(1, 28);
                } while (contains(randomNumber, randomNumber[i], i) || !phrases[randomNumber[i] - 1].isHab());
                posArray[i] = randomNumber[i] - 1;
            }
            for (int i = 0; i < 3; i++) {
                int textViewId = getResources().getIdentifier("txtPhraseFrase" + (i + 1), "id", requireContext().getPackageName());
                TextView textView = requireView().findViewById(textViewId);
                textView.setText(phrases[posArray[i]].getPhrase());
            }
        }
    }

    private boolean contains(int[] array, int number, int endIndex) {
        for (int i = 0; i < endIndex; i++) {
            if (array[i] == number) {
                return true;
            }
        }
        return false;
    }

    public void selectPhrase(View view) {

        TextView selectedTextView = (TextView) view;
        String selectedPhrase = selectedTextView.getText().toString();
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());

        builder.setTitle("Confirmación");
        builder.setMessage("¿Desea seleccionar esta frase?\n\n" + selectedPhrase);
        builder.setPositiveButton("Confirmar", ((dialog, which) -> {
            for (Phrase phrase : phrases) {
                if (phrase.getPhrase().equals(selectedPhrase)) {
                    phrase.setHab(false);
                    saveFraseState(phrase);
                    break;
                }
            }

            txtPhrase1.setText("");
            txtPhrase2.setText("");
            txtPhrase3.setText("");

            Toast.makeText(getContext(), "Frase seleccionada: " + selectedPhrase, Toast.LENGTH_SHORT).show();
        }));
        builder.setNegativeButton("Cancelar", ((dialog, which) -> dialog.dismiss()));

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void saveFraseState(Phrase phrase) {
        SharedPreferences sharedPreferences = getSharedPreferences();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("phrase_" + phrase.getId(), phrase.isHab());
        editor.apply();
    }

    public void resetPhrases(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences();
        SharedPreferences.Editor editor = sharedPreferences.edit();

        for (Phrase phrase : phrases) {
            phrase.setHab(true);
            editor.putBoolean("phrase_" + phrase.getId(), true);
        }
        editor.apply();

        txtPhrase1.setText("Las frases han sido refrescadas");
        txtPhrase2.setText("");
        txtPhrase3.setText("");
    }
}