package silva.lahass.stephany.email;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnEnviar = findViewById(R.id.btnEnviar);

        // Definicao da acao do click do botao
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtém o texto inserido no campo e-mail
                EditText etEmail = findViewById(R.id.etEmail);
                String email = etEmail.getText().toString();

                // Obtém o texto inserido no campo assunto
                EditText etAssunto = findViewById(R.id.etAssunto);
                String assunto = etAssunto.getText().toString();

                // Obtém o texto inserido no campo de texto
                EditText etTexto = findViewById(R.id.etTexto);
                String texto = etTexto.getText().toString();

                // Cria uma intenção para enviar e-mail
                Intent i = new Intent(Intent.ACTION_SENDTO);

                // Coloca dentro da intenção os dados que eu quero enviar para próxima APP
                i.setData(Uri.parse("mailto:"));
                String[] emails = new String[]{email};
                i.putExtra(Intent.EXTRA_EMAIL, emails);
                i.putExtra(Intent.EXTRA_SUBJECT, assunto);
                i.putExtra(Intent.EXTRA_TEXT, texto);

                // Tenta iniciar uma atividade que possa lidar com o envio de e-mail
                try {
                    startActivity(Intent.createChooser(i, "Escolha o APP"));
                }
                catch (ActivityNotFoundException e) {
                    // Se não houver nenhum aplicativo que possa lidar com o envio de e-mail, exibe uma mensagem de erro
                    Toast.makeText(MainActivity.this, "Não há nenhum app que possa realizar essa operação", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}