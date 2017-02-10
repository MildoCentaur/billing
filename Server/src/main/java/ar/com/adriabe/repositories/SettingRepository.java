package ar.com.adriabe.repositories;

import ar.com.adriabe.model.Setting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Mildo on 12/29/14.
 */
public interface SettingRepository extends JpaRepository<Setting, Long> {

    @Query(value = "select distinct setting from Setting setting where lower(setting.name) like lower(:name)")
    List<Setting> findLikeName(@Param("name") String name);
}
