package tr.mobileapp.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import tr.mobileapp.VolleySingleton;

public class LoginActivity extends AppCompatActivity {
    private LinearLayout llToSignUp;
    private EditText idEDTUserNameLogin;
    private EditText idEDTPassLogin;
    private Button idButtonLogin;

    private void bindingView() {
        llToSignUp = findViewById(R.id.idLLtoSignUp);
        idEDTUserNameLogin = findViewById(R.id.idEDTUserNameLogin);
        idEDTPassLogin = findViewById(R.id.idEDTPassLogin);
        idButtonLogin = findViewById(R.id.idButtonLogin);
    }

    private void bindingAction() {
        llToSignUp.setOnClickListener(this::onLLToSignup);
        idButtonLogin.setOnClickListener(this::onBtnLoginClick);
    }

    private void onBtnLoginClick(View view) {

        Log.d("TEST", "start to login");

        String username = idEDTUserNameLogin.getText().toString();
        String password = idEDTPassLogin.getText().toString();

        Sync("http://10.0.2.2:8080/account/login", username, password, this);
    }

    private void onLLToSignup(View view) {
        Intent i = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bindingView();
        bindingAction();
    }


    private void Sync(String url, String username, String password, Context context) {
        JSONObject jsonObjectRequest = new JSONObject();
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        try {
            Log.d("TEST", "put object");
            jsonObjectRequest.put("username", username);
            jsonObjectRequest.put("password", password);

            Account account = new Account();
            Log.d("TEST", "request code");
            // Make request for JSONObject
            JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                    Request.Method.POST, url, jsonObjectRequest,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.i("response", response.toString());
                            editor.putString("account", response.toString());
                            editor.apply();

                            if(!response.toString().isEmpty()) {
                                Intent i = new Intent(context, HomeActivity.class);
                                startActivity(i);
                            } else {
                                Toast.makeText(context,"Login failed", Toast.LENGTH_LONG).show();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context,"Login failed", Toast.LENGTH_LONG).show();
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
}
