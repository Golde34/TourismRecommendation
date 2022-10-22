package tr.mobileapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import android.os.Bundle;
import android.util.Log;

import tr.mobileapp.Database.AppDatabase;

public class MainActivity extends AppCompatActivity {

    private static final String DB_NAME = "tourismRecommendation.db";
  
    private void bindingView(){
    }

    private void bindingAction(){
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindingView();
        bindingAction();

        AppDatabase database = Room.databaseBuilder(this, AppDatabase.class, "Sample.db")
                .addMigrations(MIGRATION_1_2)
                .build();
        Log.d("DATABASE", "Successful create db: " + database.isOpen());
    }

//    public void Sync(String url, Context context) {
//        JSONObject jsonObjectRequest = new JSONObject();
//
//        try {
//            Account accountE = new Account();
//            JSONObject account = accountE.toJson();
//
//            jsonObjectRequest.put("walletId", account);
//            jsonObjectRequest.put("destination", null);
//            jsonObjectRequest.put("budget", null);
//            jsonObjectRequest.put("startDate", "2022-08-23");
//            jsonObjectRequest.put("endDate", "2022-08-28");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        Log.d("TEST", "-------------");
//        Log.d("TEST", jsonObjectRequest.toString());
//        // Make request for JSONObject
//        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
//                Request.Method.POST, url, jsonObjectRequest,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        Intent i = new Intent(context, TripPlanActivity.class);
//                        i.putExtra("response", response.toString());
//
//                        startActivity(i);
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                VolleyLog.d("TAG", "Error: " + error.getMessage());
//            }
//        }) {
//
//            /**
//             * Passing some request headers
//             */
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                HashMap<String, String> headers = new HashMap<String, String>();
//                headers.put("Content-Type", "application/json; charset=utf-8");
//                return headers;
//            }
//
//        };
////        Volley.newRequestQueue(this).add(jsonObjReq);
//        VolleySingleton.getmInstance(getApplicationContext()).addToRequestQueue(jsonObjReq);
////        VolleySingleton.getmInstance(getApplicationContext());
//
//    }
//
//    private Map<String, String> setHeaderData() {
//        Map<String, String> headers = new HashMap<>();
//        headers.put("header", "Sample header");
//        return headers;
//    }
//
    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
       public void migrate(SupportSQLiteDatabase database) {
           // Empty implementation, because the schema isn't changing.
        }
   };
}