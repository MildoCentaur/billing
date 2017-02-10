package ar.com.adriabe.daos.impl;

import ar.com.adriabe.daos.DaoException;
import ar.com.adriabe.daos.SettingDao;
import ar.com.adriabe.model.Setting;
import ar.com.adriabe.repositories.SettingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Mildo on 1/27/15.
 */
@Component
public class SettingDaoImpl implements SettingDao {

    @Autowired
    SettingRepository settingRepository;

    @Override
    public Setting findByName(String settingName) throws DaoException {

        List<Setting> settings = settingRepository.findLikeName("%" + settingName + "%");
        if (!settings.isEmpty() && settings.get(0) != null && settings.get(0).getValue() != null) {
            return settings.get(0);
        }

        throw new DaoException("Configuración no encontrada");

    }


    @Override
    public String getNextBillANumber() throws DaoException {
        try {
            return findByName(NRO_FACTURA_A).getValue();
        } catch (DaoException e) {
            throw new DaoException("Configuración de número de factura A invalida.");
        }
    }


    @Override
    public String getNextBillBNumber() throws DaoException {
        try {
            return findByName(NRO_FACTURA_B).getValue();
        } catch (DaoException e) {
            throw new DaoException("Configuración de número de factura B invalida.");
        }
    }

    @Override
    public String getNextOrderANumber() throws DaoException {
        try {
            return findByName(NRO_REMITO_A).getValue();
        } catch (DaoException e) {
            throw new DaoException("Configuración de número de remito A invalida.");
        }
    }

    @Override
    public String getNextOrderBNumber() throws DaoException {
        try {
            return findByName(NRO_REMITO_B).getValue();
        } catch (DaoException e) {
            throw new DaoException("Configuración de número de remito B invalida.");
        }
    }

    @Override
    public void setSetting(String name, String value) throws DaoException {
        try {
            Setting setting = findByName(name);
            setting.setValue(value);
            settingRepository.save(setting);
        } catch (DaoException e) {
            throw new DaoException("Configuración '" + name + "' no encontrada.");
        }
    }

    @Override
    public String getNextReceiptNumber() throws DaoException {
        try {
            return findByName(NRO_RECIBO).getValue();
        } catch (DaoException e) {
            throw new DaoException("Configuración de número de recibo invalida.");
        }
    }

    @Override
    public String getTicketPrinterUrl() throws DaoException {
        try {
            String value = findByName(PRINTER_TICKET_URL).getValue();

            return value;
        } catch (DaoException e) {
            throw new DaoException("Erro en la configuración de IP de impresora de ticket.");
        }
    }

    @Override
    public int getTicketPrinterPort() throws DaoException {
        try {
            return Integer.parseInt(findByName(PRINTER_TICKET_PORT).getValue());
        } catch (DaoException e) {
            throw new DaoException("Configuración de puerto de impresora de ticket.");
        }
    }

    @Override
    public String getPrinterNameBillA() throws DaoException {
        try {
            return findByName(PRINTER_NAME_BILL_A).getValue();
        } catch (DaoException e) {
            throw new DaoException("Configuración de nombre de impresora invalida.");
        }
    }

    @Override
    public String getPrinterNameBillB() throws DaoException {
        try {
            return findByName(PRINTER_NAME_BILL_B).getValue();
        } catch (DaoException e) {
            throw new DaoException("Configuración de nombre de impresora invalida.");
        }
    }

    @Override
    public String getPrinterNameReceipt() throws DaoException {
        try {
            return findByName(PRINTER_NAME_RECEIPT).getValue();
        } catch (DaoException e) {
            throw new DaoException("Configuración de nombre de impresora invalida.");
        }
    }

    @Override
    public List<Setting> findAll() {
        return settingRepository.findAll();
    }

    @Override
    public void save(Setting setting) {
        settingRepository.save(setting);
    }


}
