package ar.com.adriabe.repositories;

import ar.com.adriabe.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by Mildo on 12/29/14.
 */
public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("select event from Event event where event.date>:date order by event.date")
    List<Event> findByDateFrom(@Param("date") Date date);
}
