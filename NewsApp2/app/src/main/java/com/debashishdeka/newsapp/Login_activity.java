package com.debashishdeka.newsapp;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login_activity extends AppCompatActivity {
    private dbclass dbobject = new dbclass(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activity);
    }

    public void OnBtnClick(View view) {

        if(view.getId() == R.id.login_btn) {

            EditText editText_1 = (EditText)findViewById(R.id.user_field);
            String user_name = editText_1.getText().toString().trim();

            EditText editText_2 = (EditText)findViewById(R.id.pass_field);
            String password = editText_2.getText().toString().trim();

            Boolean user_name_entered = false,password_entred = false;



            // checking for empty string entered //

            if(!user_name.isEmpty()) {
                user_name_entered = true;
            }
            if(!password.isEmpty()) {
                password_entred = true;
            }


            if(user_name_entered && password_entred) {

                String found_password = dbobject.get_password(user_name);
                if (found_password.equals(password)) {
                    Toast.makeText(getApplicationContext(), " OK ! you are logged in !", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Login_activity.this, Final_activity.class);
                    intent.putExtra("user_name", user_name);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), " password not matched !", Toast.LENGTH_SHORT).show();
                }

            }else {

                if(!user_name_entered)
                    Toast.makeText(getApplicationContext(), "Please enter username", Toast.LENGTH_SHORT).show();
                else if(!password_entred)
                    Toast.makeText(getApplicationContext(), "Please enter password", Toast.LENGTH_SHORT).show();

            }


        }else if(view.getId() == R.id.register_field) {

            Intent intent = new Intent(Login_activity.this,SignUp.class);
            intent.putExtra("tag","Deka");
            startActivity(intent);
        }
    }
}
