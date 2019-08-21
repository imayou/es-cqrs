package com.ayou.live.domain;

import javax.validation.constraints.NotBlank;

/**
 * description
 *
 * @author ysy
 * @date 2019-08-20
 */
public interface ContractInterface {
    @NotBlank
    String getName();

    @NotBlank
    String getPartyA();

    @NotBlank
    String getPartyB();

    @NotBlank
    String getIndustryName();
}
