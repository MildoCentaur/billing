package ar.com.adriabe.services.impl;

import ar.com.adriabe.daos.ColorDao;
import ar.com.adriabe.model.Color;
import ar.com.adriabe.model.common.annotation.ILogableOperation;
import ar.com.adriabe.model.constant.ACTION_TYPE;
import ar.com.adriabe.model.constant.COLOR_TYPE;
import ar.com.adriabe.services.ColorService;
import ar.com.adriabe.services.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service("colorService")
public class ColorServiceImpl implements ColorService {

    final Logger logger = LogManager.getLogger(ColorServiceImpl.class);
    @Autowired
    ColorDao colorDao;

    @Override
    public List<Color> findAllColors(String query) {
        if (query==null || query.isEmpty()){
            return colorDao.findAllColors();
        }
        return colorDao.findAllColorsLikeName(query);
    }

    @Override
    public Color findColorById(Long id) {
        return colorDao.findAllColorsById(id);
    }

    @Override
    public Map<Integer, String> findAllColorTypesLists() {

        Map<Integer,String> map= new LinkedHashMap<Integer, String>();
        map.put(COLOR_TYPE.BLANCO.getValue(), COLOR_TYPE.BLANCO.getLabel());
        map.put(COLOR_TYPE.CLARO.getValue(), COLOR_TYPE.CLARO.getLabel());
        map.put(COLOR_TYPE.OSCURO.getValue(), COLOR_TYPE.OSCURO.getLabel());
        map.put(COLOR_TYPE.ESPECIAL.getValue(), COLOR_TYPE.ESPECIAL.getLabel());
        return map;
    }

    @Override
    @ILogableOperation(desc = "Registra un nuevo color",type = ACTION_TYPE.CREATE)
    @Transactional()
    public void save(Color color) {
        colorDao.save(color);

    }

    @Override
    public void delete(Long id) throws ServiceException {
        try {
            Color color = colorDao.findAllColorsById(id);
            color.setDeleted(true);
            colorDao.delete(color);
        }catch(Exception ex){
            logger.error(ex.getMessage());
            ex.printStackTrace();
            //Si falla al marcarlo como borrado que no haga nada
        }
    }

    @Override
    public List<Color> findColorLikeCode(String code){
        return colorDao.findAllColorsLikeCode(code);
    }
}
