package br.com.barbearia5estrelas;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class ListarActivity extends Activity{	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.listaractivity);
	}
    public void onResume() {
        super.onResume();
        
        SQLiteDatabase db = openOrCreateDatabase("cliente.db", Context.MODE_PRIVATE, null);
        //tabela de clientes
        StringBuilder sqlCliente = new StringBuilder();
        //criando tabela de clientes
        sqlCliente.append("CREATE TABLE IF NOT EXISTS cliente(");
        sqlCliente.append("_id INTEGER PRIMARY KEY, ");
        sqlCliente.append("nome VARCHAR (50), ");
        sqlCliente.append("email VARCHAR (100), ");
        sqlCliente.append("aniversario VARCHAR (100) );");
        db.execSQL(sqlCliente.toString());
        
        Cursor query = db.rawQuery("SELECT * FROM cliente",null);
        
        String [] from ={"_id","nome","email","aniversario"};
        int [] to = {R.id.tv_id, R.id.tv_nome, R.id.tv_email, R.id.tv_niver};
        SimpleCursorAdapter ad = new SimpleCursorAdapter(getBaseContext(),
        		R.layout.listar_model,query,from,to);
        
        ListView lt = (ListView)findViewById(R.id.lt_dados);
        lt.setAdapter(ad);
        
        lt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView adapter, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				SQLiteCursor c = (SQLiteCursor) adapter.getAdapter().getItem(position);
				Intent it = new Intent(getBaseContext(),Editar.class);
				it.putExtra("id", c.getInt(0));
				startActivity(it);			
			}
		});       
        db.close();
    }
}
