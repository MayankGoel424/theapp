package com.practice.mayank.theapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.practice.mayank.theapp.Common.Common;
import com.practice.mayank.theapp.Model.user;
import com.rengwuxian.materialedittext.MaterialEditText;

public class MainActivity extends AppCompatActivity {
    MaterialEditText edtnewusr, edtnewpswrd, edtnewemail; // Sign up
    MaterialEditText edtusr,edtpsd;  // Login

    Button btnsignup,btnlogin;

    FirebaseDatabase databse;
    DatabaseReference users;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Firebase
        databse = FirebaseDatabase.getInstance();
        users = databse.getReference("Users");

        edtusr = (MaterialEditText)findViewById(R.id.editusrlgn);
        edtpsd = (MaterialEditText)findViewById(R.id.editpswrdlgn);

        btnsignup = (Button)findViewById(R.id.sgnupbtn);
        btnlogin = (Button)findViewById(R.id.loginpbtn);


        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showdialog();
            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(edtusr.getText().toString(),edtpsd.getText().toString());
            }
        });

    }

    private void login(final String user, final String pwd) {
        users.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(user).exists())
                {
                    if(!user.isEmpty()){
                        user lgin = dataSnapshot.child(user).getValue(user.class);
                        if(lgin.getPassword().equals(pwd)) {
                            Common.username=user;
                            Intent i = new Intent(MainActivity.this,Home.class);
                            startActivity(i);
                            finish();

                        }
                            else{
                            Toast.makeText(MainActivity.this,"Wrong Password",Toast.LENGTH_LONG).show();
                        }

                    }
                    else {
                        Toast.makeText(MainActivity.this,"Please Enter The User Name",Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    Toast.makeText(MainActivity.this,"User do not exists",Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void showdialog(){
        AlertDialog.Builder alertdialog = new AlertDialog.Builder(MainActivity.this);
        alertdialog.setTitle("Sign Up");
        alertdialog.setMessage("Enter The Information");

        LayoutInflater inflate = this.getLayoutInflater();
        View sign_up = inflate.inflate(R.layout.sign_up,null);

        edtnewusr = (MaterialEditText)sign_up.findViewById(R.id.editusr);
        edtnewpswrd = (MaterialEditText)sign_up.findViewById(R.id.editpswrd);
        edtnewemail=(MaterialEditText)sign_up.findViewById(R.id.editemail);


        alertdialog.setView(sign_up);
        alertdialog.setIcon(R.drawable.ic_account);

        alertdialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alertdialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final user usr = new user(edtnewusr.getText().toString(),edtnewpswrd.getText().toString(),edtnewemail.getText().toString(),10);
                users.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child(usr.getUsername()).exists())
                            Toast.makeText(MainActivity.this,"USER ALREADY EXISTS",Toast.LENGTH_LONG).show();
                        else{
                            users.child(usr.getUsername()).setValue(usr);
                            Toast.makeText(MainActivity.this,"SUCCESSFULLY REGISTERED",Toast.LENGTH_LONG).show();

                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            dialog.dismiss();
            }
        });
     alertdialog.show();
    }

}
