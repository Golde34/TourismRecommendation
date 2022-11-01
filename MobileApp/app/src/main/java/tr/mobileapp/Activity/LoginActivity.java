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
import tr.mobileapp.Helper.SharedPreferenceHelper;
import tr.mobileapp.R;
import tr.mobileapp.Ultility.ValidationUtil;
import tr.mobileapp.VolleySingleton;

public class LoginActivity extends AppCompatActivity {


    SharedPreferenceHelper sharedPreferenceHelper = new SharedPreferenceHelper();

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

        String username = ValidationUtil.getTrim(idEDTUserNameLogin);
        String password = ValidationUtil.getTrim(idEDTPassLogin);

        if(ValidationUtil.isInputBlank(username, idEDTUserNameLogin) ||
        ValidationUtil.isInputBlank(password, idEDTPassLogin)){
            Toast.makeText(this, "Input cannot be blank", Toast.LENGTH_LONG).show();
            return;
        }

        Sync("http://10.0.2.2:8080/account/login", username, password, this);
    }

    private void onLLToSignup(View view) {
        Intent i = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("TEST", "CREATE NE");
        setContentView(R.layout.activity_login);

        String jsonAccount = SharedPreferenceHelper.getDataFromPref(this, "MyPref", "account");
        if(null != jsonAccount){
            Intent i = new Intent(this, HomeActivity.class);
            finish();
            startActivity(i);
        }

        bindingView();
        bindingAction();

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("TEST", "START NE");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("TEST", "RESTART NE");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("TEST", "PAUSE NE");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("TEST", "STOP NE");
    }

    private void Sync(String url, String username, String password, Context context) {
        JSONObject jsonObjectRequest = new JSONObject();

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
                            if(!response.toString().isEmpty()) {
                                Log.i("response", response.toString());
                                SharedPreferenceHelper.storeDataToPref(context, "MyPref", "account", response.toString());
                                Intent i = new Intent(context, HomeActivity.class);
                                finish();
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
