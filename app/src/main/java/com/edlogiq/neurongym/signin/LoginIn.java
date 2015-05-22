package com.edlogiq.neurongym.signin;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.edlogiq.neurongym.R;
import com.edlogiq.neurongym.neurongym.HomePage;
import com.parse.FunctionCallback;
import com.parse.ParseCloud;
import com.parse.ParseException;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LoginIn extends ActionBarActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_in);

        ((Button)findViewById(R.id.registtation)).setOnClickListener(this);
        ((Button)findViewById(R.id.sighin)).setOnClickListener(this);
    }

    final boolean accepted=true;
    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.registtation){
            Intent intent=new Intent(this,Form.class);
            startActivity(intent);
        }else if(v.getId()==R.id.sighin){
            Intent intent=new Intent(this,HomePage.class);
            startActivity(intent);
        }
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
        this.finish();
    }
}
