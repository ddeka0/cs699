package com.debashishdeka.newsapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Debashish on 10/21/2017.
 */

public class SignUp extends Activity{

    private dbclass dbobject = new dbclass(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);
        // Toast.makeText(getApplicationContext(), getIntent().getStringExtra("tag"), Toast.LENGTH_SHORT).show();
    }
    public void OnSignUpBtnClick(View view) {
        if(view.getId() == R.id.signup_btn) {

            EditText name = (EditText)findViewById(R.id.reg_uname);
            EditText email = (EditText)findViewById(R.id.reg_email);
            EditText password = (EditText)findViewById(R.id.reg_pass);

            String _name = "";
            String _email = "";
            String _pass = "";

            _name = name.getText().toString().trim();
            _email = email.getText().toString().trim();
            _pass = password.getText().toString().trim();


            Boolean name_entred = !_name.isEmpty();
            Boolean email_entered = !_email.isEmpty();
            Boolean pass_entered = !_pass.isEmpty();



            // Toast.makeText(getApplicationContext(), "OK ! Go back and LogIn again", Toast.LENGTH_SHORT).show();
            if(name_entred && email_entered && pass_entered) {

                Boolean user_name_availibility = dbobject.confirm_user_name(_name);

                if(user_name_availibility) {

                    UserInfo userInfo = new UserInfo();
                    userInfo.setName(_name);
                    userInfo.setEmail(_email);
                    userInfo.setPassword(_pass);

                    dbobject.insertUser(userInfo);
                    Toast.makeText(getApplicationContext(), "OK ! Go back and LogIn again", Toast.LENGTH_SHORT).show();

                }else {

                    Toast.makeText(getApplicationContext(),"user_name = " + _name + " is in use ! try another one", Toast.LENGTH_SHORT).show();

                }
            }else {

                Toast.makeText(getApplicationContext(), "Fill the complete form !", Toast.LENGTH_SHORT).show();

            }
        }
    }
}
