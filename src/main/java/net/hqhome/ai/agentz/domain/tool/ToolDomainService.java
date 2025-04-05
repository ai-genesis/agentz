package net.hqhome.ai.agentz.domain.tool;

import com.alibaba.fastjson2.JSON;
import jakarta.annotation.PostConstruct;
import net.hqhome.ai.agentz.domain.AbstractDomainService;
import net.hqhome.ai.agentz.domain.event.AbstractDomainEvent;
import net.hqhome.ai.agentz.domain.event.events.AgentInternalMessageAddedDomainEvent;
import net.hqhome.ai.agentz.domain.event.events.TaskCreatedDomainMessage;
import net.hqhome.ai.agentz.domain.event.events.ToolResultDomainEvent;
import net.hqhome.ai.agentz.domain.event.events.UserMessageAddedDomainEvent;
import net.hqhome.ai.agentz.domain.thread.Message;
import net.hqhome.ai.agentz.domain.thread.Thread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ToolDomainService extends AbstractDomainService {

  @Autowired
  private ToolFactory toolFactory;

  @PostConstruct
  public void init() {
    super.register(this, UserMessageAddedDomainEvent.class);
    super.register(this, TaskCreatedDomainMessage.class);
  }

  @Override
  public void handle(AbstractDomainEvent event) {
    if (event instanceof TaskCreatedDomainMessage taskCreated) {
      Tool tool = toolFactory.getToolById(taskCreated.getToolId());
      tool.setParameters(taskCreated.getParameters());
      String res = tool.run();

      var toolResultDomainEvent = new ToolResultDomainEvent();
      toolResultDomainEvent.setThreadId(taskCreated.getThreadId());
      toolResultDomainEvent.setAgentId(taskCreated.getAgentId());
      toolResultDomainEvent.setResult(res);

      publishEvent(toolResultDomainEvent);
    }
//    ToolEvent ;
//    String res = event.getToolId.run();
//    MessageAddedDomainEvent messageAddedDomainEvent;
//
//    publishEvent(messageAddedDomainEvent);
  }

  public void addMessage(Thread thread, Message message) {
    thread.addMessage(message);

    UserMessageAddedDomainEvent userMessageAddedDomainEvent = new UserMessageAddedDomainEvent();
    userMessageAddedDomainEvent.setThreadId(thread.getId());
    userMessageAddedDomainEvent.setAgentId(thread.getAgentId());
    userMessageAddedDomainEvent.setMessages(JSON.toJSONString(thread.getMessages()));

    publishEvent(userMessageAddedDomainEvent);
  }

}
