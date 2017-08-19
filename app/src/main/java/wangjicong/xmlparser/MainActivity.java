package wangjicong.xmlparser;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.RingtonePreference;
import android.util.DisplayMetrics;

import java.util.Locale;
import android.os.Handler;

public class MainActivity extends PreferenceActivity {
    private RingtonePreference ringtone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.main);
    }
}
