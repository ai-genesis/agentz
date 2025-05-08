package net.hqhome.ai.agentz.domain.thread;

import com.alibaba.fastjson2.JSON;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import net.hqhome.ai.agentz.domain.event.AbstractDomainEvent;
import net.hqhome.ai.agentz.domain.event.ISubscriber;
import net.hqhome.ai.agentz.domain.event.events.AgentFinishedDomainEvent;
import net.hqhome.ai.agentz.domain.event.events.AgentInternalMessageAddedDomainEvent;
import net.hqhome.ai.agentz.domain.event.events.ToolResultDomainEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.hqhome.ai.agentz.domain.AbstractDomainService;
import net.hqhome.ai.agentz.domain.event.events.UserMessageAddedDomainEvent;

@Slf4j
@Service
public class ThreadDomainService extends AbstractDomainService {

    @Autowired
    private IThreadRepository threadRepository;

    @Autowired
    private ISubscriber subscriberAdapter;

    @PostConstruct
    public void init() {
        subscriberAdapter.register(this, AgentInternalMessageAddedDomainEvent.class);
//        subscriberAdapter.register(this, ToolResultDomainEvent.class);
    }

    @Override
    public void handle(AbstractDomainEvent event) {
        if (event instanceof AgentInternalMessageAddedDomainEvent agentInternalMessageAdded) {
            // TODO extract to factory
            Thread thread = new Thread();
            thread.setId(agentInternalMessageAdded.getThreadId());
            thread.setStatus(ThreadStatus.RUNNING);
            Message message = new Message(Message.ROLE_MODEL, agentInternalMessageAdded.getMessage());
            updateStatusAndAddMessage(thread, message);
        }
    }

    public void updateStatusAndAddMessage(Thread thread, Message message) {
        if (message.getRole().equals(Message.ROLE_USER)) {
//            if (thread.isRunning()) {
//                log.warn("add message failed, because thread {} is running", thread.getId());
//                return;
//            }

//            synchronized (thread) {
                // todo
//                if (thread.isRunning()) {
//                    log.warn("add message failed, because thread {} is running", thread.getId());
//                    return;
//                }
            thread.setStatus(ThreadStatus.RUNNING);
            thread.addMessage(message);
            threadRepository.update(thread, message);

            UserMessageAddedDomainEvent userMessageAddedDomainEvent = new UserMessageAddedDomainEvent();
            userMessageAddedDomainEvent.setThreadId(thread.getId());
            userMessageAddedDomainEvent.setAgentId(thread.getAgentId());
            userMessageAddedDomainEvent.setMessages(JSON.toJSONString(thread.getMessages()));

            publishEvent(userMessageAddedDomainEvent);
//            }

        } else if (message.getRole().equals(Message.ROLE_ASSISTANT)
            || message.getRole().equals(Message.ROLE_MODEL)
            || message.getRole().equals(Message.ROLE_TOOL)) {
            thread.addMessage(message);
            threadRepository.update(thread, message);
        }
    }

    public void updateStatus(Thread thread, ThreadStatus status) {
        thread.setStatus(status);
        threadRepository.update(thread);
    }
}
