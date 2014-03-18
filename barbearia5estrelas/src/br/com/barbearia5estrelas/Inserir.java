package br.com.barbearia5estrelas;

import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Inserir extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inserir);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void CadastrarCliente(View v){
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
	            if (db.insert("cliente", "_id", valores)> 0){
	            	Toast.makeText(getBaseContext(), "Inserido com sucesso", Toast.LENGTH_SHORT).show();
	            }else{
	            	Toast.makeText(getBaseContext(), "Não cadastrado", Toast.LENGTH_SHORT).show();
	            }
    		}
	        catch(Exception e){
	           	Toast.makeText(getBaseContext(),e.getMessage(), Toast.LENGTH_SHORT).show();
	        }
            
    	}
    }
    
}
