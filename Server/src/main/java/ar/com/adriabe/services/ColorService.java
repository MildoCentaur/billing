package ar.com.adriabe.services;

import ar.com.adriabe.model.Color;
import ar.com.adriabe.model.common.annotation.ILogableOperation;

import java.util.List;
import java.util.Map;


public interface ColorService {

    List<Color> findAllColors(String query);

    Color findColorById(Long id);

    Map<Integer, String> findAllColorTypesLists();


    void save(Color color) ;

    @ILogableOperation(desc = "Borrado logico del color")
    void delete(Long idColor) throws ServiceException;

    List<Color> findColorLikeCode(String code);
}
