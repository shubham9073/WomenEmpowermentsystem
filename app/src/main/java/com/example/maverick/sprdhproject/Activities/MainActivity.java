package com.example.maverick.sprdhproject.Activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maverick.sprdhproject.R;


public class MainActivity extends Activity {
    TextView tvShow;
    Button btLogout,but1,but2,but3,but4,but5;
    ImageButton btMore,btRoot;
    SharedPreferences prefs;
    DatabaseHandler databaseHandler = new DatabaseHandler(this);
    String SaveUName, SavePassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvShow = (TextView) findViewById(R.id.tvShow);
        btMore = (ImageButton) findViewById(R.id.btMore);

        but1 = (Button) findViewById(R.id.but1);
        but2 = (Button) findViewById(R.id.but2);
        but3 = (Button) findViewById(R.id.but3);
        but4 = (Button) findViewById(R.id.but4);
        but5 = (Button) findViewById(R.id.but5);
        btLogout = (Button) findViewById(R.id.btLogout);


        prefs = getSharedPreferences("inUser", MODE_PRIVATE);
        SaveUName = String.valueOf(prefs.getString("uName", "a"));
        SavePassword = String.valueOf(prefs.getString("pass", "a"));
        tvShow.setText("Welcome . . " + SaveUName);
        if (SaveUName.equals("a") || SavePassword.equals("a")) {
            Intent intent = new Intent(MainActivity.this, Register.class);
            startActivity(intent);
        }

        User user = databaseHandler.getUser(SaveUName);

        btMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuDialog();
            }
        });


        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,Activitymain.class);
                startActivity(i);
            }
        });

        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,jobs.class);
                startActivity(i);
            }
        });

        but3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,NGO.class);
                startActivity(i);
            }
        });
        but4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,education.class);
                startActivity(i);
            }
        });

        but5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url="https://www.paypal.com/in/home";
                Intent e = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(e);
            }
        });

        btLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                logMeOut();
            }
        });
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            logMeOut();
        }
        return super.onKeyDown(keyCode, event);
    }

    private void logMeOut() {
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.custom_exit_dialog);
        Button btYes = (Button) dialog.findViewById(R.id.btYes);
        Button btNo = (Button) dialog.findViewById(R.id.btNo);
        Button btExit = (Button) dialog.findViewById(R.id.btExit);
        btYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("uName", "a");
                editor.putString("pass", "a");
                editor.commit();
                Intent intent = new Intent(MainActivity.this, Register.class);
                startActivity(intent);
                dialog.dismiss();
            }
        });
        btNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btExit.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View v) {
                MainActivity.this.finishAffinity();
            }
        });
        dialog.show();
    }

    private void deleteAccount(String email) {
        SharedPreferences.Editor editor = prefs.edit();
        databaseHandler.deleteUser(email);
        editor.putString("uName", "a");
        editor.putString("pass", "a");
        editor.commit();
        Intent intent = new Intent(MainActivity.this, Register.class);
        startActivity(intent);
        Toast.makeText(MainActivity.this, "Account Deleted", Toast.LENGTH_SHORT).show();

    }

    private void updateAccount(final String email) {
        final User user = databaseHandler.getUser(email);
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.update);
        final EditText etName = (EditText) dialog.findViewById(R.id.etNameChange);
        final EditText etEmail = (EditText) dialog.findViewById(R.id.etEmailChange);
        final EditText etUName = (EditText) dialog.findViewById(R.id.etUNameChange);
        final EditText etPhone = (EditText) dialog.findViewById(R.id.etPhoneChange);
        Button btDone = (Button) dialog.findViewById(R.id.btDone);
        //EditText etPassword = (EditText) dialog.findViewById(R.id.etPasswordChange);

        etName.setText(user.getName());
        etEmail.setText(user.getEmail());
        etUName.setText(user.getuName());
        etPhone.setText(user.getPhone());
        //etEmail.setText(user.getPassword());
        btDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setName(etName.getText().toString().trim());
                user.setEmail(etEmail.getText().toString().trim());
                user.setuName(etUName.getText().toString().trim());
                user.setPhone(etPhone.getText().toString().trim());
                databaseHandler.updateUser(user);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("uName", etEmail.getText().toString());
                editor.commit();
                Toast.makeText(MainActivity.this,"Changes Saved",Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                MainActivity.this.recreate();
            }
        });


        dialog.show();
    }

    private void menuDialog() {
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.menu);
        Button btDel = (Button) dialog.findViewById(R.id.btDel);
        Button btUpdate = (Button) dialog.findViewById(R.id.btUpdate);
        btDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAccount(SaveUName);
                dialog.dismiss();
            }
        });
        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateAccount(SaveUName);
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}
