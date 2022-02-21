package br.com.mrgsoft.calculadoradegorjetas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editValor;
    private TextView textPorcentagem;
    private TextView textGorgeja;
    private TextView textTotal;
    private SeekBar seekBarGorjeta;

    private double porcentagem = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editValor       = findViewById(R.id.editValor);
        textPorcentagem = findViewById(R.id.textPorcentagem);
        textGorgeja     = findViewById(R.id.textGorjeta);
        textTotal       = findViewById(R.id.textTotal);
        seekBarGorjeta  = findViewById(R.id.seekBarGorgeta);

        //Listener SeekBar
        seekBarGorjeta.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                porcentagem = i;
                textPorcentagem.setText( Math.round( porcentagem ) + " %" );
                calcular();

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void calcular(){

        String valorRecuperado = editValor.getText().toString();
        if( valorRecuperado == null || valorRecuperado.equals("")){

            Toast.makeText(
                    getApplicationContext(),
                    "Digite um valor primeiro!",
                    Toast.LENGTH_SHORT
            ).show();

        }else {

            //Converter string para double
            double valorDigitado = Double.parseDouble( valorRecuperado );

            //calcular a gorjeta e o total
            double gorjeta = valorDigitado * ( porcentagem / 100 );
            double total = gorjeta + valorDigitado;

            //Exibir gorjeta e o total com centevaos
            textGorgeja.setText("R$ " + (gorjeta));
            textTotal.setText("R$ " + (total));

            //Exibir gorjeta e o total com arredondamento
            //textGorgeja.setText("R$ " + Math.round(gorjeta));
            //textTotal.setText("R$ " + Math.round(total));

        }
    }
}