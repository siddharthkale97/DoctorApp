package me.siddharthkale.doctorapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private Button btnLogOut;
    private FirebaseAuth auth;
    private ProgressBar progressbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        auth  = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() == null) {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        }

        setContentView(R.layout.activity_main);

        btnLogOut = findViewById(R.id.logout_button);
        progressbar = findViewById(R.id.progressBar);

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressbar.setVisibility(View.VISIBLE);
                FirebaseAuth.getInstance().signOut();

                auth  = FirebaseAuth.getInstance();

                if(auth.getCurrentUser() == null){
                    progressbar.setVisibility(View.GONE);
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    finish();
                }

                // this listener will be called when there is change in firebase user session
                /*
                final FirebaseAuth.AuthStateListener authListener = new FirebaseAuth.AuthStateListener() {
                    @Override
                    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                        FirebaseUser user_cur = firebaseAuth.getCurrentUser();
                        if (user_cur == null){
                            // user auth state is changed - user is null
                            // launch login activity
                            startActivity(new Intent(MainActivity.this, ResetPasswordActivity.class));
                            finish();

                        }
                        if(user_cur!= null){
                            Toast.makeText(MainActivity.this, "Logout failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                };*/

            }
        });

    }
}
