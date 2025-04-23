package com.example.medicamentos;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MedicamentoAdapter medicamentoAdapter;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseHelper(this);
        recyclerView = findViewById(R.id.medicamentosRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadMedicamentos();

        findViewById(R.id.addMedicamentoButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CadastroActivity.class);
                startActivity(intent);
            }
        });
    }

    private void loadMedicamentos() {
        Cursor cursor = dbHelper.getAllMedicamentos();
        medicamentoAdapter = new MedicamentoAdapter(cursor, new MedicamentoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int id) {
                // Marcar como tomado
                dbHelper.marcarComoTomado(id);
                loadMedicamentos();
            }

            @Override
            public void onItemLongClick(int id) {
                // Excluir medicamento
                dbHelper.deleteMedicamento(id);
                loadMedicamentos();
            }
        });
        recyclerView.setAdapter(medicamentoAdapter);
    }
}
