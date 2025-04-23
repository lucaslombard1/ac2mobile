package com.example.medicamentos;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class CadastroActivity extends AppCompatActivity {

    private EditText nomeEditText, descricaoEditText, horaEditText;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        nomeEditText = findViewById(R.id.medicamentoNome);
        descricaoEditText = findViewById(R.id.medicamentoDescricao);
        horaEditText = findViewById(R.id.medicamentoHora);
        dbHelper = new DatabaseHelper(this);

        findViewById(R.id.salvarMedicamentoButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = nomeEditText.getText().toString();
                String descricao = descricaoEditText.getText().toString();
                String hora = horaEditText.getText().toString();

                if (nome.isEmpty() || descricao.isEmpty() || hora.isEmpty()) {
                    Toast.makeText(CadastroActivity.this, "Todos os campos são obrigatórios", Toast.LENGTH_SHORT).show();
                } else {
                    dbHelper.addMedicamento(nome, descricao, hora);
                    Toast.makeText(CadastroActivity.this, "Medicamento salvo!", Toast.LENGTH_SHORT).show();
                    finish(); // Fecha a atividade e volta para a tela anterior
                }
            }
        });
    }
}
