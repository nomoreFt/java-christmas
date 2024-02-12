package christmas.v1;

import java.util.Locale;
import java.util.ResourceBundle;

public class Messages {

    private static final ResourceBundle messages = ResourceBundle.getBundle("messages", Locale.getDefault());

    public static String get (String key) {
        return messages.getString(key);
    }

}
