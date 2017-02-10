package ar.com.adriabe.web.model.forms;

import ar.com.adriabe.model.Setting;

import java.util.ArrayList;

/**
 * Created by Mildo on 2/18/15.
 */
public class SettingUpdateForm {

    private ArrayList<Setting> settings;

    public ArrayList<Setting> getSettings() {
        return settings;
    }

    public void setSettings(ArrayList<Setting> settings) {
        this.settings = settings;
    }
}
