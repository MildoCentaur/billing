package ar.com.adriabe.services.impl;

import ar.com.adriabe.daos.DaoException;
import ar.com.adriabe.daos.SettingDao;
import ar.com.adriabe.model.Setting;
import ar.com.adriabe.services.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Mildo on 2/5/15.
 */
@Service
public class SettingServiceImpl implements SettingService {
    @Autowired
    SettingDao settingDao;

    @Override
    public Map<String, List<Setting>> findSettingsGroupedByCategory() {
        Map<String, List<Setting>> map = new HashMap<String, List<Setting>>();
        List<Setting> allSettings = settingDao.findAll();
        List<Setting> categoryList = new ArrayList<Setting>();
        String category = allSettings.get(0).getCategory();
        for (Setting setting : allSettings) {
            if(category.compareToIgnoreCase(setting.getCategory())!=0){
                map.put(category,categoryList);
                categoryList = new ArrayList<Setting>();
                category = setting.getCategory();
            }
            categoryList.add(setting);
        }
        map.put(category,categoryList);

        return map;
    }

    @Override
    public void update(List<Setting> settings) throws DaoException {
        Setting old;
        for (Setting setting : settings) {
            old = settingDao.findByName(setting.getName());
            old.setValue(setting.getValue());
            settingDao.save(old);
        }
    }
}
