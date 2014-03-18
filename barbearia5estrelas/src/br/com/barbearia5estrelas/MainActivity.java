package br.com.barbearia5estrelas;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }    
    public void listarCliente(View v){
    	Intent it = new Intent(getBaseContext(),ListarActivity.class);
    	startActivity(it);
    }
    public void cadastroCliente(View v){
    	Intent it = new Intent(getBaseContext(),Inserir.class);
    	startActivity(it);
    }
    
}
