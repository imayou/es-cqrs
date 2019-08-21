package com.ayou.live.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * description
 *
 * @author ysy
 * @date 2019-08-20
 */
@NoArgsConstructor
@Getter
@Setter
public class DeleteContractCommand extends AbstractCommand {
    public DeleteContractCommand(Long identifier) {
        super(identifier);
    }
}
