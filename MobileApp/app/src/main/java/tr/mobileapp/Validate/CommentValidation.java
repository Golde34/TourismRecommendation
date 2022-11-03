package tr.mobileapp.Validate;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Objects;

public class CommentValidation {
    public CommentValidation() {
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static HashMap<Integer, String> commentValidation(String title, String comment) {
        HashMap<Integer, String> validation = new HashMap<>();
        if (title == null || title.equals("")) {
            validation.put(1, "title must not be null.");
        }
        if (comment == null || comment.equals("")) {
            validation.put(2, "Comment must not be null.");
        }
        if (validation.size() == 0) {
            validation.put(0, "Validated success.");
        }
        return validation;
    }
}
