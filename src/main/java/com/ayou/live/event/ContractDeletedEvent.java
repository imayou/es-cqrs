package com.ayou.live.event;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * description
 *
 * @author ysy
 * @date 2019-08-20
 */
@Getter
@Setter
@NoArgsConstructor
public class ContractDeletedEvent extends AbstractEvent {
    public ContractDeletedEvent(Long identifier) {
        super(identifier);
    }
}
