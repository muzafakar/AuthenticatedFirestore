package com.muzadev.authenticatedfirestore.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.muzadev.authenticatedfirestore.R;
import com.muzadev.authenticatedfirestore.model.Customer;
import com.muzadev.authenticatedfirestore.presenter.Contract;
import com.muzadev.authenticatedfirestore.presenter.Presenter;

import java.util.List;

public class LoginActivity extends AppCompatActivity implements Contract.View {
    private Contract.Presenter presenter;
    private EditText etEmail, etPassword;
    private Button btnLogin;
    private ProgressBar progressBar;
    private View mParentLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initUi();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                presenter.login(email, password);
                progressBar.setVisibility(View.VISIBLE);
            }
        });

    }

    @Override
    public void onLogin(boolean isSuccess) {
        if (isSuccess) {
            Snackbar.make(mParentLayout, "Success", Snackbar.LENGTH_SHORT).show();
            startActivity(new Intent(this, CustomerActivity.class));
            finish();
        } else {
            Snackbar.make(mParentLayout, "Failed", Snackbar.LENGTH_SHORT).show();
        }
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onGetCustomer(List<Customer> customers) {

    }


    private void initUi() {
        presenter = new Presenter(this);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        progressBar = findViewById(R.id.pb);
        mParentLayout = findViewById(android.R.id.content);
    }
}
