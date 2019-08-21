package com.ayou.live.controller;

import com.ayou.live.commands.CreateContractCommand;
import com.ayou.live.commands.DeleteContractCommand;
import com.ayou.live.commands.UpdateContractCommand;
import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.*;

/**
 * description
 *
 * @author ysy
 * @date 2019-08-20
 */
@RestController
@RequestMapping("/contracts")
@AllArgsConstructor
public class ContractController {
    private final CommandGateway commandGateway;

    @PostMapping
    public void createContract(CreateContractCommand command) {
        commandGateway.sendAndWait(command);
    }

    @PutMapping("/{id}")
    public void updateContract(@PathVariable("id") Long id, UpdateContractCommand command) {
        command.setIdentifier(id);
        commandGateway.send(command);
    }

    @DeleteMapping("/{id}")
    public void deleteContract(@PathVariable("id") Long id) {
        commandGateway.send(new DeleteContractCommand(id));
    }
}
