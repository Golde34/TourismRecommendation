package tr.mobileapp.Helper;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import tr.mobileapp.Entity.Account;

public class SharedPreferenceHelper {

    public static void storeDataToPref(Context context, String prefName, String key, String data) {
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
//        Gson gson = new Gson();
//        String json = gson.toJson(data);
        editor.putString(key, data);
        editor.apply();
    }

    public static String getDataFromPref(Context context, String prefName, String key) {
        SharedPreferences pref = context.getSharedPreferences(prefName, MODE_PRIVATE);
        String json = pref.getString(key, null);
        return json;
    }
}
