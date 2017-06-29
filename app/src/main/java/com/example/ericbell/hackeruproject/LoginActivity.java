package com.example.ericbell.hackeruproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends Activity {
    private static final int RC_SIGN_IN = 1;
    @BindView(R.id.ivLogin)
    ImageView ivLogin;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;


    @BindView(R.id.etEmail)
    EditText etEmail;
    @BindView(R.id.etpassword)
    EditText etpassword;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.btnRegister)
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mAuth = FirebaseAuth.getInstance();


    }


    @OnClick(R.id.btnLogin)
    public void onBtnLoginClicked() {
        if (!validEmail() | !validPassword())
            return;

        mAuth.signInWithEmailAndPassword(getEmail(), getPassword()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }

        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @OnClick(R.id.btnRegister)
    public void onBtnRegisterClicked() {

        if (!validEmail() | !validPassword())
            return;
        String email = getEmail();
        String password = getPassword();

        mAuth.createUserWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {


                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }


    private String getEmail() {
        return etEmail.getText().toString();
    }

    private String getPassword() {
        return etpassword.getText().toString();
    }

    private boolean validEmail() {
        boolean valid;
        String email = getEmail();
        valid = email.length() > 6 && email.contains("@") && email.contains(".") && (email.contains("com") || email.contains("il"));
        if (!valid) {
            etEmail.setError("inViled Email");
            return valid = false;
        } else {
            valid = true;
        }
        return valid;
    }

    private boolean validPassword() {

        String password = getPassword();
        boolean valid;
        valid = password.length() > 6;

        if (!valid) {
            etpassword.setError("you must at least 6 laters");
            valid = false;
        } else {
            valid = true;
        }
        return valid;
    }
}
