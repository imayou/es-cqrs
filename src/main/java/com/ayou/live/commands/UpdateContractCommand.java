package com.ayou.live.commands;

import com.ayou.live.domain.ContractInterface;
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
public class UpdateContractCommand extends AbstractCommand implements ContractInterface {

    private String name;

    private String partyA;

    private String partyB;

    private String industryName;

    public UpdateContractCommand(Long identifier, String name, String partyA, String partyB, String industryName) {
        super(identifier);
        this.name = name;
        this.partyA = partyA;
        this.partyB = partyB;
        this.industryName = industryName;
    }
}
