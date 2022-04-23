package br.com.itb.conversordetemperatura;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Declarar objetos - Declaração Global
    EditText mEditText;
    TextView mTextView;
    Button mButton;
    RadioButton mRadioButtonFahrenheit, mRadioButtonKelvin;

    // Método para verificar se caixa de texto foi preenchida com a temperatura
    private boolean isEmptyValue(){
        return TextUtils.isEmpty(mEditText.getText().toString());
    }

    // Método para calcular conversão de Celsius para Fahrenheit
    private double calculateFahrenheit() {
        double tempC = Double.parseDouble(mEditText.getText().toString());
        double tempF = (tempC * 9/5) + 32;
        return tempF;
    }

    // Método para calcular conversão de Celsius para Kelvin
    private double calculateKelvin() {
        double tempC = Double.parseDouble(mEditText.getText().toString());
        double tempK = tempC + 273.15;
        return tempK;
    }

    //  Método para obter o resultado do calculo escolhido pelo usuário
    public String getResult() {
        String result = "";

        // Estrutura condicional para verificar qual opção foi escolhida
        if(mRadioButtonKelvin.isChecked()){
            result = calculateKelvin() + "K";
        } else if(mRadioButtonFahrenheit.isChecked()) {
            result = calculateFahrenheit() + "F";
        }

        return result;
    }

    // Método para verificar se a temperatura foi inserida e apresentar resultados
    private void showResult() {
        if(isEmptyValue()) {
            Toast.makeText(this, "Digite uma temperatura!", Toast.LENGTH_LONG).show();
            return;
        }

        mTextView.setText(getResult());
        // limpar a caixa de texto
        mEditText.setText("");
    }

    // Classe de implementação do clique do botão
    public class ClickMyButton implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            // Mostrar o resultado do cálculo escolhido
            showResult();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Estabelecer vínculos
        mEditText = findViewById(R.id.edtTextInput);
        mTextView = findViewById(R.id.textViewOutput);
        mButton = findViewById(R.id.buttonCalculate);
        mRadioButtonFahrenheit = findViewById(R.id.radioButtonFahrenheit);
        mRadioButtonKelvin = findViewById(R.id.radioButtonKelvin);

        // Define a opção Fahrenheit como a primeira a ser clicada
        mRadioButtonFahrenheit.setChecked(true);

        // Acionar o botão e disparar métodos criados acima
        mButton.setOnClickListener(new ClickMyButton());
    }
}