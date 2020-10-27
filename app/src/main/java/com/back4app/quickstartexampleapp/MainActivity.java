package com.back4app.quickstartexampleapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnKeyListener {

        public boolean isSignUp = true;
        public TextView loginView;

        public void goToDoLists(){
            Intent intent = new Intent(getApplicationContext(), listToDos.class);
            startActivity(intent);
        }

        public void signingUp(String username, String password){
            ParseUser user = new ParseUser();
            user.setUsername(username);
            user.setPassword(password);

            user.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {
                    if(e == null){
                        Toast.makeText(MainActivity.this,"Sign up Successful", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(MainActivity.this,e.getMessage(), Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                }
            });

        }

        public void loggingIn(String username, String password){

            ParseUser.logInInBackground(username,password, new LogInCallback() {
                @Override
                public void done(ParseUser user, ParseException e) {
                    if(user != null){
                        //String[] arr = {"hello"};
                        Toast.makeText(MainActivity.this,user.get("strings").toString(), Toast.LENGTH_SHORT).show();


                        //Toast.makeText(MainActivity.this,"Logged in", Toast.LENGTH_SHORT).show();
                    }
                        else {
                            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                }
            });
        }


    public void onClickedSignedUp(View view){
        EditText usernameText = findViewById(R.id.email_address);
        EditText passwordText = findViewById(R.id.password);
        boolean  empty_user_pass = usernameText.getText().toString().matches("") || passwordText.getText().toString().matches("");

        // if the password or username is empty give a toast message otherwise proceed to login and sign up
        if(empty_user_pass){
            Toast.makeText(this, "username and password are required", Toast.LENGTH_SHORT).show();
        }
        else {
                if(isSignUp){
                    signingUp(usernameText.getText().toString(),passwordText.getText().toString());
                }

                else {
                    loggingIn(usernameText.getText().toString(), passwordText.getText().toString());
                }
        }
    }

    public void onClick(View view){
        if(view.getId() == R.id.logInSwitch){
                Button signUpButton = findViewById(R.id.signUp);

                //switiching text between login and signup

                if(!isSignUp){
                   isSignUp = true;
//                    Intent intent = new Intent(getApplicationContext(), sign_up.class);
//                    startActivity(intent);
//                    signUpButton.setText("Sign Up");
//                    loginView.setText("Login here");
                }
                else {
                    isSignUp = false;
//                    signUpButton.setText("Login");
//                    loginView.setText("Signup here");
                }
        }
    }

    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {

        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginView = findViewById(R.id.logInSwitch);
        loginView.setOnClickListener(this);

        if(ParseUser.getCurrentUser() != null){
            goToDoLists();
        }

        // Save the current Installation to Back4App
        ParseInstallation.getCurrentInstallation().saveInBackground();


// create a score class with username and score rows

/*

        ParseObject score = new ParseObject("Score");
        score.put("username", "bob");
        score.put("score", 66);
        score.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e == null){
                    Log.i("Success", "We saved the score");
                }
                else{

                }
            }
        });

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Score");
        query.getInBackground("3KfzoIbtdt", new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if(e == null && object != null){

                    Log.i("username", object.getString("username"));

                }
            }
        });
*/

// iterating all

/*
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Score");
        query.whereEqualTo("username", "john");

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e == null){
                    if (objects.size() > 0){
                        for(ParseObject object :objects){
                            Log.i("username", object.getString("username"));
                        }
                    }
                }
            }
        });
*/

//sign up

/*
        ParseUser user = new ParseUser();
        user.setUsername("john");
        user.setPassword("password");

        user.signUpInBackground(new SignUpCallback() {
             @Override
             public void done(ParseException e) {
                 if(e == null){
                     Log.i("sign", "we did it");
                 }
                 else{
                     e.printStackTrace();
                 }
             }
         });
*/

//login

/*

        ParseUser.logInInBackground("john", "passsword", new LogInCallback() {
        @Override
        public void done(ParseUser user, ParseException e) {
            if(user != null){
                Log.i("success", "logged in");
            }
            else {
                e.printStackTrace();
            }
        }
    });

 */

//log out
//ParseUser.logOut();

//checking if it is logged in
 /*   if(ParseUser.getCurrentUser() != null){
        Log.i("Signed in", ParseUser.getCurrentUser().getUsername());
    }
    else {
        Log.i("not signed", "not signed in");
    }
*/

    }
}
