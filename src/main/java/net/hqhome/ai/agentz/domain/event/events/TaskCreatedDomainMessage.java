package net.hqhome.ai.agentz.domain.event.events;

import com.alibaba.fastjson2.JSONObject;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.hqhome.ai.agentz.domain.event.AbstractDomainEvent;

@Data
@EqualsAndHashCode(callSuper = true)
public class TaskCreatedDomainMessage extends AbstractDomainEvent {
    private String threadId;
    private String agentId;
    private String toolId;
    private JSONObject parameters;
}
