package com.ayou.live.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * description
 *
 * @author ysy
 * @date 2019-08-20
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AbstractCommand {
    @TargetAggregateIdentifier
    private Long identifier;
}
