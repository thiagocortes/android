package br.com.barbearia5estrelas;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Editar extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editar);
        
        Intent it = getIntent();
        int id = it.getIntExtra("id",0);
        SQLiteDatabase db = openOrCreateDatabase("cliente.db", Context.MODE_PRIVATE, null);
        
        Cursor query = db.rawQuery("SELECT * FROM cliente WHERE _id = ?",new String[]{String.valueOf(id)});
        
        if(query.moveToFirst()){
        	EditText nome = (EditText)findViewById(R.id.nome);
        	EditText email = (EditText)findViewById(R.id.email);
        	EditText niver = (EditText)findViewById(R.id.niver);
        	
        	nome.setText(query.getString(1));
        	email.setText(query.getString(2));
        	niver.setText(query.getString(3));
        }
        
        db.close();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void atualizarCliente(View v){
    	EditText nome = (EditText)findViewById(R.id.nome);
    	EditText email = (EditText)findViewById(R.id.email);
    	EditText niver = (EditText)findViewById(R.id.niver);
    	
    	if(nome.getText().toString().length()<=0){
    		nome.setError("Preencha o campo Nome");
    		nome.requestFocus();
    	}else if(email.getText().toString().length()<=0){
    		email.setError("Preencha o campo Email");
    		email.requestFocus();
    	}
    	else{
    		try{
	            SQLiteDatabase db = openOrCreateDatabase("cliente.db", Context.MODE_PRIVATE, null);
	            ContentValues valores = new ContentValues();
	            valores.put("nome",nome.getText().toString());
	            valores.put("email",email.getText().toString());
	            valores.put("aniversario",niver.getText().toString());
	            
	            Intent it = getIntent();
	            int id = it.getIntExtra("id",0);
	            
	            if (db.update("cliente",valores,"_id=?",new String[]{String.valueOf(id)})> 0){
	            	Toast.makeText(getBaseContext(), "Atualizado com sucesso", Toast.LENGTH_SHORT).show();
	            }else{
	            	Toast.makeText(getBaseContext(), "Não Atualizado", Toast.LENGTH_SHORT).show();
	            }
    		}
	        catch(Exception e){
	           	Toast.makeText(getBaseContext(),e.getMessage(), Toast.LENGTH_SHORT).show();
	        }
            
    	}
    }
    public void apagarCliente(View v){
    		try{
	            final SQLiteDatabase db = openOrCreateDatabase("cliente.db", Context.MODE_PRIVATE, null);	            
	         
	            Intent it = getIntent();
	            final int id = it.getIntExtra("id",0);
	            
	            Builder msg = new Builder(Editar.this);
	            msg.setMessage("Deseja apagar este item?");
	            msg.setNegativeButton("Não", null);
	            msg.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						// TODO Auto-generated method stub
						if (db.delete("cliente","_id=?",new String[]{String.valueOf(id)})> 0){
			            	Toast.makeText(getBaseContext(), "Apagado com sucesso", Toast.LENGTH_SHORT).show();
			            	finish();
			            }else{
			            	Toast.makeText(getBaseContext(), "Não apagado", Toast.LENGTH_SHORT).show();
			            }					
					}
				});
	            msg.show();
    		}
	        catch(Exception e){
	           	Toast.makeText(getBaseContext(),e.getMessage(), Toast.LENGTH_SHORT).show();
	        }
    }
    
}
