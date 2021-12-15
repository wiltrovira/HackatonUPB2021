package Utilities;

import android.content.Context;
import android.content.SharedPreferences;

public class UserPreferences {

    static SharedPreferences preferences;

    public static SharedPreferences getPreferences(Context context) {
        preferences = context.getSharedPreferences("userPreferences",Context.MODE_PRIVATE);
        return preferences;
    }
}
