package ar.com.adriabe.services;

import ar.com.adriabe.daos.DaoException;
import ar.com.adriabe.model.Setting;

import java.util.List;
import java.util.Map;

/**
 * Created by AJMILD1 on 04/02/2015.
 */
public interface SettingService {

    Map<String,List<Setting>> findSettingsGroupedByCategory();

    void update(List<Setting> settings) throws DaoException;
}
