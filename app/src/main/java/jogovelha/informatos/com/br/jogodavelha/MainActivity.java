package jogovelha.informatos.com.br.jogodavelha;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements android.view.View.OnClickListener {


    private EditText edt_jogador1;
    private EditText edt_jogador2;
    private Button btn_jogar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edt_jogador1 = (EditText) findViewById(R.id.edtJogador1);
        edt_jogador2 = (EditText) findViewById(R.id.edtJogador2);
        btn_jogar = (Button) findViewById(R.id.btnJogar);


        btn_jogar.setOnClickListener(this);

    }

    public void onClick(View v) {
        if (edt_jogador1.getText().toString().trim().equals("") || edt_jogador2.getText().toString().trim().equals("")) {
            Toast.makeText(getBaseContext(), "Prencha o nome do jogador", Toast.LENGTH_LONG).show();
        } else {
            //Toast.makeText(getBaseContext(),"Passou Buceta" + edt_jogador1 + edt_jogador2, Toast.LENGTH_LONG).show();
            Intent it = new Intent(MainActivity.this, GameActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("jogador1", edt_jogador1.getText().toString());
            bundle.putString("jogador2", edt_jogador2.getText().toString());

            it.putExtras(bundle);

            startActivity(it);

        }

    }
}
