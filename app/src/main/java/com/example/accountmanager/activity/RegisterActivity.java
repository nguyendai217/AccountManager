package com.example.accountmanager.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.accountmanager.R;
import com.example.accountmanager.database.DataBaseHelper;
import com.example.accountmanager.model.User;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edtName, edtEmail, edtPassword, edtRepassword;
    Button btnRegister;
    TextView tvLogin;
    private DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        dataBaseHelper = new DataBaseHelper(this);
        init();
        controls();
    }

    private void init() {
        edtName = findViewById(R.id.edt_name);
        edtEmail = findViewById(R.id.edt_email);
        edtPassword = findViewById(R.id.edt_password);
        edtRepassword = findViewById(R.id.edt_repassword);
        btnRegister = findViewById(R.id.btn_register);
        tvLogin = findViewById(R.id.tv_login);

    }

    private void controls() {
        btnRegister.setOnClickListener(this);
        tvLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_register:
                veryfyRegister();
                clearEditext();
                break;
            case R.id.tv_login:
                Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(i);
                break;
        }
    }
    private void veryfyRegister() {
        String email=edtEmail.getText().toString().trim();
        String pass= edtPassword.getText().toString().trim();
        String repass= edtRepassword.getText().toString().trim();
        String name= edtName.getText().toString();

        if (!emptyValidation()&& !dataBaseHelper.checkEmail(email)&& pass.equals(repass)) {
            User user= new User(name,email,pass);
            dataBaseHelper.addUser(user);

            clearEditext();
            Intent intent= new Intent(RegisterActivity.this,LoginActivity.class);
            startActivity(intent);
            Toast.makeText(this, "Đăng kí thành công ", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Đăng kí thất bại !", Toast.LENGTH_SHORT).show();
        }
//        if (emptyValidation()){
//            Toast.makeText(this, "Không được để trống !", Toast.LENGTH_SHORT).show();
//        }
    }
    private void clearEditext() {
        edtName.setText(null);
        edtPassword.setText(null);
        edtEmail.setText(null);
        edtRepassword.setText(null);
    }
    private boolean emptyValidation() {
        if (TextUtils.isEmpty(edtEmail.getText().toString()) || TextUtils.isEmpty(edtPassword.getText().toString())
                || TextUtils.isEmpty(edtName.getText().toString()) || TextUtils.isEmpty(edtRepassword.getText().toString())) {
            return true;
        } else {
            return false;
        }
    }
}
