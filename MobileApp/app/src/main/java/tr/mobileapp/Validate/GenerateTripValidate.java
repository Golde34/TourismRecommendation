package tr.mobileapp.Validate;

import android.annotation.SuppressLint;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

public class GenerateTripValidate {
    public GenerateTripValidate() {
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public HashMap<Integer, String> generateTripValidate(String location, String budget, String startDate, String endDate) {
        HashMap<Integer, String> validation = new HashMap<>();
        try {
            if (location == null || location.equals("")) {
                validation.put(1, "Location must not be null.");
            }
            if (Objects.equals(budget, "") || budget == null) {
                validation.put(2, "Budget must not be null.");
            } else {
                if (Integer.parseInt(budget) == 0) {
                    validation.put(5, "Budget must be larger than 0.");
                }
            }
            validateDate(startDate, endDate, validation);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (validation.size() == 0) {
            validation.put(0, "Validated success.");
        }
        return validation;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void validateDate(String startDate, String endDate, HashMap<Integer, String> validation) throws ParseException {
        if (startDate == null || startDate.equals("")) {
            validation.put(3, "Start date must not be null");
        }
        if (endDate == null || endDate.equals("")) {
            validation.put(4, "End date must not be null");
        }
        if ((startDate != null || !startDate.equals(""))
                && (endDate != null || !endDate.equals(""))) {
            @SuppressLint("SimpleDateFormat")
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date start = dateFormat.parse(startDate);
            Date end = dateFormat.parse(endDate);
            LocalDateTime now = LocalDateTime.now();
            Date today = dateFormat.parse(String.valueOf(now));
            if (start.compareTo(today) <= 0) {
                validation.put(6, "The start date must be in the future.");
            }
            if (start.compareTo(end) >= 0) {
                validation.put(7, "The end date must be larger than the start date.");
            }
        }
    }
}
