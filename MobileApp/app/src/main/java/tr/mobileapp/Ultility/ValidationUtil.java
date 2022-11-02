package tr.mobileapp.Ultility;

import android.text.TextUtils;
import android.util.Patterns;
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

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    public static boolean isValidVNPhone(String phone) {
        return phone.matches("(\\+84|0[3|5|7|8|9])+([0-9]{8})\\b");
    }
}
