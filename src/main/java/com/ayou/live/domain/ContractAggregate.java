package com.ayou.live.domain;

import com.ayou.live.commands.CreateContractCommand;
import com.ayou.live.commands.DeleteContractCommand;
import com.ayou.live.commands.UpdateContractCommand;
import com.ayou.live.event.ContractCreatedEvent;
import com.ayou.live.event.ContractDeletedEvent;
import com.ayou.live.event.ContractUpdatedEvent;
import com.ayou.live.id.UIDGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.messaging.MetaData;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

/**
 * description
 * 在这里我们实现 Event Soucing 的模式，把各个 Handler 都放在 aggregate 里面。
 *
 * @author ysy
 * @date 2019-08-20
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Aggregate
public class ContractAggregate implements ContractInterface {

    @AggregateIdentifier
    private Long identifier;

    private String name;

    private String partyA;

    private String partyB;

    private String industryName;

    private boolean deleted = false;

    @CommandHandler
    public ContractAggregate(CreateContractCommand command, MetaData metaData, UIDGenerator generator) {
        if (null == command.getIdentifier()) {
            command.setIdentifier(generator.getId());
        }
        AggregateLifecycle.apply(new ContractCreatedEvent(command.getIdentifier(),
                command.getName(),
                command.getPartyA(),
                command.getPartyB(),
                command.getIndustryName()), metaData);
    }

    @CommandHandler
    private void on(UpdateContractCommand command, MetaData metaData) {
        AggregateLifecycle.apply(new ContractUpdatedEvent(command.getIdentifier(),
                        command.getName(),
                        command.getPartyA(),
                        command.getPartyB(),
                        command.getIndustryName()),
                metaData);
    }

    @CommandHandler
    private void on(DeleteContractCommand command, MetaData metaData) {
        AggregateLifecycle.apply(new ContractDeletedEvent(command.getIdentifier()), metaData);
    }

    @EventSourcingHandler
    private void on(ContractCreatedEvent event) {
        this.setIdentifier(event.getIdentifier());
        this.onUpdate(event);
    }

    @EventSourcingHandler
    private void onUpdate(ContractUpdatedEvent event) {
        this.setName(event.getName());
        this.setPartyA(event.getPartyA());
        this.setPartyB(event.getPartyB());
        this.setIndustryName(event.getIndustryName());
    }

    @EventSourcingHandler(payloadType = ContractDeletedEvent.class)
    private void on() {
        this.setDeleted(true);
    }
}