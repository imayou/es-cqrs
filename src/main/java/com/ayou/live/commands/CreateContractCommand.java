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
@Getter
@Setter
@NoArgsConstructor
public class CreateContractCommand extends UpdateContractCommand {
    public CreateContractCommand(Long identifier, String name, String partyA, String partyB, String industryName) {
        super(identifier, name, partyA, partyB, industryName);
    }
}
