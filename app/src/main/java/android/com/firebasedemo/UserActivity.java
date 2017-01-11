package android.com.firebasedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textViewEmail;
    private Button buttonLogOut;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        textViewEmail = (TextView) findViewById(R.id.textViewEmail);
        buttonLogOut = (Button) findViewById(R.id.buttonLogout);

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() == null) {
            finish();
            Intent intent = new Intent(UserActivity.this, LoginActivity.class);
            startActivity(intent);
        }

        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        textViewEmail.setText(firebaseUser.getEmail());

        buttonLogOut.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == buttonLogOut) {
            firebaseAuth.signOut();
            finish();
            Intent intent = new Intent(UserActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }
}
