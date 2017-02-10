package ar.com.adriabe.daos.impl;

import ar.com.adriabe.daos.ColorDao;
import ar.com.adriabe.model.Color;
import ar.com.adriabe.repositories.ColorRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component("colorDao")
public class ColorDaoImpl implements ColorDao {

    final Logger logger = LogManager.getLogger(ColorDaoImpl.class);
    @Autowired
    ColorRepository colorRepository;

    @Override
    public Color findAllColorsById(Long id) {
        return colorRepository.findOne(id);
    }

    @Override
    public List<Color> findAllColorsLikeName(String name) {
        name = '%'+name+'%';
        return colorRepository.findLikeName(name);
    }

    @Override
    public List<Color> findAllColors() {
        return colorRepository.findByDeleted(false);
    }

    @Override
    public void save(Color color) {
        colorRepository.save(color);
    }

    @Override
    public void delete(Color color) {
        colorRepository.delete(color);
    }

    @Override
    public List<Color> findAllColorsLikeCode(String code) {
        return findAllColorsLikeCode(code);
    }
}
