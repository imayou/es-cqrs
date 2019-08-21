package com.ayou.live.id;

import com.ayou.live.common.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * description
 *
 * @author ysy
 * @date 2019-08-20
 */
@Component
public class UIDGenerator {
    private final WorkerIdService workerIdService;
    private SnowFlake flake;

    @Autowired
    public UIDGenerator(WorkerIdService workerIdService) {
        this.workerIdService = workerIdService;
    }

    public Long getId() {
        return flake.nextId();
    }

    @PostConstruct
    public void init() {
        this.flake = new SnowFlake(workerIdService.getWorkerId());
    }
}
