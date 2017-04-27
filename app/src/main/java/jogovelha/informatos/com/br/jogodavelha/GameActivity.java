package jogovelha.informatos.com.br.jogodavelha;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity implements android.view.View.OnClickListener {

    private int[][] combinacoes = new int[][]{
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9},
            {1, 4, 7},
            {2, 5, 8},
            {3, 6, 9},
            {1, 5, 9},
            {3, 5, 7}
    };

    Button[] botoes;
    String jogador = "LM" ;
    int total_jogadas = 0;
    MediaPlayer mp = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        ((TextView)findViewById(R.id.txtJogador)).setText("Jogador:" + jogador);

        botoes = new Button[10];

        botoes[1] = (Button) findViewById(R.id.btn1);
        botoes[2] = (Button) findViewById(R.id.btn2);
        botoes[3] = (Button) findViewById(R.id.btn3);
        botoes[4] = (Button) findViewById(R.id.btn4);
        botoes[5] = (Button) findViewById(R.id.btn5);
        botoes[6] = (Button) findViewById(R.id.btn6);
        botoes[7] = (Button) findViewById(R.id.btn7);
        botoes[8] = (Button) findViewById(R.id.btn8);
        botoes[9] = (Button) findViewById(R.id.btn9);

        for (int i = 1; i < 10; i++) {
            botoes[i].setOnClickListener(this);
        }

        mp = MediaPlayer.create(getBaseContext(), R.raw.click);
    }


    @Override
    public void onClick(View v) {

        total_jogadas++;
        if (jogador == "MF") {
            ((Button) v).setText(jogador);
            jogador = "LM";
            ((TextView)findViewById(R.id.txtJogador)).setText("Jogador:" + jogador);
        } else {
            ((Button) v).setText(jogador);
            jogador = "MF";
            ((TextView)findViewById(R.id.txtJogador)).setText("Jogador:" + jogador);
        }

        mp.start();

                ((Button) v).setEnabled(false);
        verificaGanhador();
    }


    public void verificaGanhador() {
        for (int i = 0; i <= 7; i++) {
            if ((botoes[combinacoes[i][0]].getText().equals(botoes[combinacoes[i][1]].getText())) && botoes[combinacoes[i][1]].getText().equals(botoes[combinacoes[i][2]].getText()) && !botoes[combinacoes[i][0]].getText().equals("")){
                Toast.makeText(getBaseContext(),"Ganhou o jogador " + botoes[combinacoes[i][0]].getText(), Toast.LENGTH_LONG).show();
                zeraGame();
                total_jogadas = 0;
            }
        }

        if (total_jogadas == 9){
            Toast.makeText(getBaseContext(),"Empate!", Toast.LENGTH_LONG).show();
            zeraGame();
        }
    }

    public void zeraGame() {
        for (int i = 1; i < 10; i++) {
            botoes[i].setText("");
            botoes[i].setEnabled(true);


            if (jogador == "MF") {
                jogador = "LM";
                ((TextView)findViewById(R.id.txtJogador)).setText("Jogador:" + jogador);
            } else {
                jogador = "MF";
                ((TextView)findViewById(R.id.txtJogador)).setText("Jogador:" + jogador);
            }

            total_jogadas = 0;
        }
    }
}
