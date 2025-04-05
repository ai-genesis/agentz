package net.hqhome.ai.agentz.domain.event.events;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.hqhome.ai.agentz.domain.event.AbstractDomainEvent;

@Data
@EqualsAndHashCode(callSuper = true)
public class ToolResultDomainEvent extends AbstractDomainEvent {
    private String threadId;
    private String agentId;
    private String result;
}
