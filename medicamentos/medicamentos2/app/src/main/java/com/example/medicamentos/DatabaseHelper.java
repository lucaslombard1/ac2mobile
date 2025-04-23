package com.example.medicamentos;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Nome do banco de dados e versão
    private static final String DATABASE_NAME = "medicamentos.db";
    private static final int DATABASE_VERSION = 1;

    // Tabela Medicamentos
    private static final String TABLE_NAME = "medicamentos";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "nome";
    private static final String COLUMN_DESCRIPTION = "descricao";
    private static final String COLUMN_TIME = "hora_consumo";
    private static final String COLUMN_TAKEN = "tomado";  // Para indicar se o medicamento foi tomado

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Método que cria a tabela
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_MEDICAMENTOS_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NAME + " TEXT, "
                + COLUMN_DESCRIPTION + " TEXT, "
                + COLUMN_TIME + " TEXT, "
                + COLUMN_TAKEN + " INTEGER DEFAULT 0);";  // 0 para não tomado, 1 para tomado

        db.execSQL(CREATE_MEDICAMENTOS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Inserir medicamento
    public long addMedicamento(String nome, String descricao, String horaConsumo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, nome);
        values.put(COLUMN_DESCRIPTION, descricao);
        values.put(COLUMN_TIME, horaConsumo);
        values.put(COLUMN_TAKEN, 0); // Marca como não tomado inicialmente

        return db.insert(TABLE_NAME, null, values);
    }

    // Obter todos os medicamentos
    public Cursor getAllMedicamentos() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_NAME, null, null, null, null, null, COLUMN_TIME);
    }

    // Marcar medicamento como tomado
    public int marcarComoTomado(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TAKEN, 1);  // Marca como tomado

        return db.update(TABLE_NAME, values, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
    }

    // Excluir medicamento
    public void deleteMedicamento(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
    }
}
