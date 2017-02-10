package ar.com.adriabe.repositories;

import ar.com.adriabe.model.OperationLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by AJMILD1 on 23/06/14.
 */

public interface OperationLogRepository extends JpaRepository<OperationLog, Long> {


}
