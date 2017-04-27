package jogovelha.informatos.com.br.jogodavelha;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity implements android.view.View.OnClickListener {

    Button[] botoes;
    String jogador1;
    String jogador2;
    String jogador;
    int total_jogadas = 0;
    MediaPlayer mp = null;
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
    private Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent it = getIntent();
        Bundle bundle = it.getExtras();
        jogador1 = bundle.getString("jogador1").toUpperCase();
        jogador2 = bundle.getString("jogador2").toUpperCase();
        jogador = jogador1;
        Toast.makeText(getBaseContext(), "Jogador " + jogador, Toast.LENGTH_LONG).show();



        ((TextView)findViewById(R.id.txtJogador)).setText("Jogador:" + jogador);

        botoes = new Button[10];

        animation = new AlphaAnimation(1, 0);
        animation.setDuration(3000);
        animation.setInterpolator(new LinearInterpolator());
        animation.setRepeatCount(Animation.INFINITE);
        animation.setRepeatMode(Animation.REVERSE);


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
            botoes[i].startAnimation(animation);

        }

        mp = MediaPlayer.create(getBaseContext(), R.raw.click);

    }


    @Override
    public void onClick(View v) {

        total_jogadas++;
        if (jogador == jogador1) {
            ((Button) v).setText(jogador1);
            jogador = jogador2;
            ((TextView)findViewById(R.id.txtJogador)).setText("Jogador:" + jogador);
        } else {
            ((Button) v).setText(jogador2);
            jogador = jogador1;
            ((TextView)findViewById(R.id.txtJogador)).setText("Jogador:" + jogador);
        }

        mp.start();
        v.setEnabled(false);
        verificaGanhador();

        v.clearAnimation();
    }


    public void verificaGanhador() {
        for (int i = 0; i <= 7; i++) {
            if ((botoes[combinacoes[i][0]].getText().equals(botoes[combinacoes[i][1]].getText())) &&
                    botoes[combinacoes[i][1]].getText().equals(botoes[combinacoes[i][2]].getText()) && !botoes[combinacoes[i][0]].getText().equals("")) {
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
            botoes[i].startAnimation(animation);
            total_jogadas = 0;
        }

        if (jogador == jogador2) {
            ((TextView) findViewById(R.id.txtJogador)).setText("Jogador:" + jogador);
        } else {
            ((TextView) findViewById(R.id.txtJogador)).setText("Jogador:" + jogador);
        }
    }
}
