package com.example.medicamentos;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class MedicamentoAdapter extends CursorAdapter {

    public interface OnItemClickListener {
        void onItemClick(int id);
        void onItemLongClick(int id);
    }

    private OnItemClickListener listener;

    public MedicamentoAdapter(Context context, Cursor cursor, OnItemClickListener listener) {
        super(context, cursor, 0);
        this.listener = listener;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_medicamento, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView nomeTextView = view.findViewById(R.id.nomeMedicamentoTextView);
        TextView descricaoTextView = view.findViewById(R.id.descricaoMedicamentoTextView);
        TextView horaTextView = view.findViewById(R.id.horaMedicamentoTextView);

        final int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
        String nome = cursor.getString(cursor.getColumnIndexOrThrow("nome"));
        String descricao = cursor.getString(cursor.getColumnIndexOrThrow("descricao"));
        String hora = cursor.getString(cursor.getColumnIndexOrThrow("hora_consumo"));

        nomeTextView.setText(nome);
        descricaoTextView.setText(descricao);
        horaTextView.setText(hora);

        view.setOnClickListener(v -> listener.onItemClick(id));
        view.setOnLongClickListener(v -> {
            listener.onItemLongClick(id);
            return true;
        });
    }
}
