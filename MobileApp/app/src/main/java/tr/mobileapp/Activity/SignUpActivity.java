package tr.mobileapp.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import tr.mobileapp.Entity.Account;
import tr.mobileapp.R;
import tr.mobileapp.Ultility.ValidationUtil;
import tr.mobileapp.VolleySingleton;

public class SignUpActivity extends AppCompatActivity {
    private LinearLayout llToLogin;
    private Button btnRegister;
    private EditText idEDTUserNameSignUp;
    private EditText idEDTPassword;
    private EditText idEDTEmail;
    private EditText idEDTPhone;

    private void bindingView(){
        llToLogin = findViewById(R.id.idLLtoLogin);
        btnRegister = findViewById(R.id.idButtonRegister);
        idEDTUserNameSignUp = findViewById(R.id.idEDTUserNameSignUp);
        idEDTPassword = findViewById(R.id.idEDTPassSignUp);
        idEDTEmail = findViewById(R.id.idEDTEmail);
        idEDTPhone = findViewById(R.id.idEDTPhone);
    }

    private void bindingAction(){
        llToLogin.setOnClickListener(this::onLLToLogin);
        btnRegister.setOnClickListener(this::onBtnRegister);
    }

    private void onBtnRegister(View view) {

        String username = ValidationUtil.getTrim(idEDTUserNameSignUp);
        String password = ValidationUtil.getTrim(idEDTPassword);
        String email = ValidationUtil.getTrim(idEDTEmail);
        String phone = ValidationUtil.getTrim(idEDTPhone);

        if (ValidationUtil.isInputBlank(username, idEDTUserNameSignUp) ||
                ValidationUtil.isInputBlank(password, idEDTPassword) ||
                ValidationUtil.isInputBlank(email, idEDTEmail) ||
                ValidationUtil.isInputBlank(phone, idEDTPhone)) {
            Toast.makeText(this, "Input cannot be blank", Toast.LENGTH_LONG).show();
            return;
        }

        Account account = new Account(username, password, email, phone);

        Sync("http://10.0.2.2:8080/account/signup", account, this);
    }

    private void Sync(String url, Account account, Context context) {
        Log.d("Signup", "Start to send signup request");
        JSONObject jsonObjectRequest = new JSONObject();

        try {

            jsonObjectRequest.put("username", account.getUsername());
            jsonObjectRequest.put("password", account.getPassword());
            jsonObjectRequest.put("email", account.getEmail());
            jsonObjectRequest.put("phoneNumber", account.getPhoneNumber());

            // Make request for JSONObject
            JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                    Request.Method.POST, url, jsonObjectRequest,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            String errorMess = "Signup failed";
                            Log.d("Signup", response.toString());
                            try {
                                errorMess = response.getString("message");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(errorMess.equals("Signup successfully")) {
                                Intent i = new Intent(context, LoginActivity.class);
                                finish();
                                startActivity(i);
                            } else {
                                Toast.makeText(context, errorMess, Toast.LENGTH_LONG).show();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context,"Signup failed", Toast.LENGTH_LONG).show();
                    VolleyLog.d("TAG", "Error: " + error.getMessage());
                }
            }) {

                /**
                 * Passing some request headers
                 */
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put("Content-Type", "application/json; charset=utf-8");
                    return headers;
                }

            };

            VolleySingleton.getmInstance(getApplicationContext()).addToRequestQueue(jsonObjReq);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void showError() {

    }

    private void onLLToLogin(View view) {
        onBackPressed();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        bindingView();
        bindingAction();
    }
}
