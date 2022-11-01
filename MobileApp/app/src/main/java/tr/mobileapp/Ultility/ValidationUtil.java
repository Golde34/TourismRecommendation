package tr.mobileapp.Ultility;

import android.widget.EditText;

public class ValidationUtil {

    public static String getTrim(EditText idEDT) {
        return idEDT.getText().toString().trim();
    }

    public static boolean isInputBlank(String input, EditText edt) {
        if (input.trim().equalsIgnoreCase("")) {
            edt.setError("This field can not be blank");
            return true;
        } else {
            return false;
        }
    }
}
