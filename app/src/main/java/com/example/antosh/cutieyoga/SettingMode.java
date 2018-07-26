package com.example.antosh.cutieyoga;

/**
 * Created by Antosh on 6/29/2018.
 */

public class SettingMode {
    public static void setSetting_mode(int setting_mode) {
        SettingMode.setting_mode = setting_mode;
    }

    static int setting_mode = 10;

    public static int getSetting_mode() {
        return setting_mode;
    }
}
