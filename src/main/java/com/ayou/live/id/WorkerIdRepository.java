package com.ayou.live.id;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * description
 *
 * @author ysy
 * @date 2019-08-20
 */
@Repository
public interface WorkerIdRepository extends JpaRepository<WorkerId, Long> {
    WorkerId findByServiceKey(String serviceKey);
}