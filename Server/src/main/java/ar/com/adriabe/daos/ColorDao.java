package ar.com.adriabe.daos;

import ar.com.adriabe.model.Color;

import java.util.List;


public interface ColorDao {
    Color findAllColorsById(Long id);

    List<Color> findAllColorsLikeName(String query);

    List<Color> findAllColors();

    void save(Color color) ;

    void delete(Color color);

    List<Color> findAllColorsLikeCode(String code);
}
