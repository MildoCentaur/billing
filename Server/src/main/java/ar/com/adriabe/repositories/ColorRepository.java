package ar.com.adriabe.repositories;

import ar.com.adriabe.model.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


/**
 * Created by AJMILD1 on 04/09/14.
 */
public interface ColorRepository extends JpaRepository<Color, Long> {

    @Query(value = "select col from Color col where col.name like :name")
    List<Color> findLikeName(@Param("name") String name);

    List<Color> findByDeleted(boolean b);
}