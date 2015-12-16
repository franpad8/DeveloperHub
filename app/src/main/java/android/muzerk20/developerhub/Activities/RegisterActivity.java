package android.muzerk20.developerhub.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.muzerk20.developerhub.Models.User;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.muzerk20.developerhub.R;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

public class RegisterActivity extends ActionBarActivity {

    private Toolbar toolbar;
    /*private EditText email;
    private EditText username;
    private EditText password;
    private EditText passwordAgain;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        toolbar.setLogo(R.mipmap.ic_launcher_screen);

        findViewById(R.id.buttonRegisterRegister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder validationErrorMsg = new StringBuilder();
                boolean validationErrors = false;
                boolean emptyFields = false;
                boolean isMatchingPasswords = true;
                String email = ((EditText) findViewById(R.id.editTextRegisterEmail)).getText().toString();
                String username = ((EditText) findViewById(R.id.editTextRegisterUserName)).getText().toString();
                String password = ((EditText) findViewById(R.id.editTextRegisterPassword)).getText().toString();
                String passwordAgain = ((EditText) findViewById(R.id.editTextRegisterPasswordAgain)).getText().toString();


                // Check if there are empty fields
                if(email.isEmpty() || username.isEmpty() || password.isEmpty() || passwordAgain.isEmpty()){
                    emptyFields = true;
                    validationErrors = true;
                    validationErrorMsg.append("Rellene TODOS los campos");
                }

                // Check is the 2 passwords match
                if(!password.equals(passwordAgain)){
                    isMatchingPasswords = false;
                    if(validationErrors)
                        validationErrorMsg.append(", e ");
                    else
                        validationErrors = true;
                    validationErrorMsg.append("Introduzca la misma contraseña dos veces");

                }

                if(validationErrors)
                    Toast.makeText(RegisterActivity.this, validationErrorMsg.toString(), Toast.LENGTH_SHORT).show();
                else{
                    final ProgressDialog dig = new ProgressDialog(RegisterActivity.this);
                    dig.setTitle("Por favor espere...");
                    dig.setMessage("Registrando. Por favor, espere.");
                    dig.show();


                    User currentUser = (User) ParseUser.getCurrentUser();

                    if (currentUser != null) {
                        // do stuff with the user
                        currentUser.logOut();
                    }

                    // We add a new user to the backend
                    User newUser = new User();

                    newUser.setEmail(email);
                    newUser.setUsername(username);
                    newUser.setPassword(password);
                    //newUser.put("name", username);

                    newUser.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            dig.dismiss();
                            if (e != null) {
                                // Show the error message
                                Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            } else {
                                // Start an intent for the dispatch activity
                                Intent intent = new Intent(RegisterActivity.this, DispatchActivity.class);
                                intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TASK | intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                finish();

                            }
                        }
                    });


                }



            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
