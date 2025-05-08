package net.hqhome.ai.agentz.domain.agent;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import net.hqhome.ai.agentz.domain.event.events.*;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import net.hqhome.ai.agentz.domain.AbstractDomainService;
import net.hqhome.ai.agentz.domain.event.AbstractDomainEvent;
// import net.hqhome.ai.agentz.domain.event.EventType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AgentDomainService extends AbstractDomainService {

    @Autowired
    private AgentFactory agentFactory;

    @Autowired
    private IAgentResource agentResource;

    private Map<String, List<CompletableFuture<String>>> runningTasks = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        super.register(this, UserMessageAddedDomainEvent.class);
        super.register(this, ToolResultDomainEvent.class);
    }

    @Override
    public void handle(AbstractDomainEvent event) {
        if (event instanceof UserMessageAddedDomainEvent msgAddedEvent) {
            Agent agent = agentFactory.get(msgAddedEvent.getAgentId());

            if (agent instanceof ChatAgent) {
                try {
                    ModelResponse res = agent.run(agentResource, JSON.parseArray(msgAddedEvent.getMessages(), ChatMessage.class));
                    AgentFinishedDomainEvent agentFinishedDomainEvent = new AgentFinishedDomainEvent();
                    agentFinishedDomainEvent.setThreadId(msgAddedEvent.getThreadId());
                    agentFinishedDomainEvent.setIsError(false);
                    agentFinishedDomainEvent.setResult(res.getContent());
                    publishEvent(agentFinishedDomainEvent);
                } catch (Exception e) {
                    AgentFinishedDomainEvent agentFinishedDomainEvent = new AgentFinishedDomainEvent();
                    agentFinishedDomainEvent.setThreadId(msgAddedEvent.getThreadId());
                    agentFinishedDomainEvent.setIsError(true);
                    agentFinishedDomainEvent.setError(e);
                    publishEvent(agentFinishedDomainEvent);
                }
            } else if (agent instanceof ReActAgent) {


                fun(agent, JSON.parseArray(msgAddedEvent.getMessages(), ChatMessage.class), msgAddedEvent.getThreadId());
//                String res = agent.run(agentResource, JSON.parseArray(msgAddedEvent.getMessages(), ChatMessage.class));
//                List<Task> tasks = agent.parseOutput(res);
//
//                List<?> futures =  tasks.stream().map(task -> {
////                    CompletableFuture<String> future = new CompletableFuture<>();
//                    var msg = new TaskCreatedDomainMessage();
//                    msg.setId("test");
//                    publishEvent(msg);
//                    return new CompletableFuture<>();
//                }).collect(Collectors.toList());
//
//                CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]))
//                        .thenRun(() -> {
//
//                        });
//
//
//
//                List<String> result = tasks.stream().map(task -> task.run()).collect(Collectors.toList());

//                publishEvent();
//        List<Task> tasks = agent.parseOutput(res);
//        while (tasks  != FinishTask) {
//          if (tasks is finishtask){
//            msgAddedEvent.getCallback().accept(res);
//          } else {
//            tasks.run("", (rs) -> {
//              tasks = agent.parseOutput(rs);
//            })
//          }

//                List<String> result = new ArrayList<>();

//          Task task = agent.parseOutput(res);
            }
        } else if (event instanceof ToolResultDomainEvent toolResultDomainEvent) {
            String agentId = toolResultDomainEvent.getAgentId();
            List<CompletableFuture<String>> futures =  runningTasks.get(agentId);
            int count = 0;
            for (CompletableFuture<String> future : futures) {
                if (future.isDone()) {
                    count++;
                    continue;
                }
//                if (count == futures.size() - 1) {
//                    runningTasks.remove(agentId);
//                }
                future.complete(toolResultDomainEvent.getResult());
            }

        }

//    MessageAddedDomainEvent e = (MessageAddedDomainEvent) event;
//
//    Agent agent = agentFactory.get(e.getAgentId());
//
//    List<ChatMessage> messages = JSON.parseArray(e.getMessages(), ChatMessage.class);
//    AgentFinishedDomainEvent agentFinishedDomainEvent = new AgentFinishedDomainEvent();
//    agentFinishedDomainEvent.setThreadId(e.getThreadId());
//
//    try {
////      Task result = agent.run();
////      if (result is FinishedTask) {
////        AgentFinishedDomainEvent agentFinishedDomainEvent
////      } else {
////        ToolEvent tool;
////        publishEvent(tool);
////      }
//      String res = agent.getModel().chatCompletion(agentResource, messages);
//      // TODO agent parse res base on agent type
//      // rag agent.run();
//
//      agentFinishedDomainEvent.setIsError(false);
//      agentFinishedDomainEvent.setResult(res);
//    } catch (Exception ex) {
//      log.error("seeeeee", ex);
//      agentFinishedDomainEvent.setIsError(true);
//      agentFinishedDomainEvent.setError(ex);
//    }
//
//    // agent parse res
////    AgentFinishedDomainEvent agentFinishedDomainEvent = new AgentFinishedDomainEvent();
////    agentFinishedDomainEvent.setThreadId(e.getThreadId());
////    agentFinishedDomainEvent.setResult(res);
//    publishEvent(agentFinishedDomainEvent);


//    event = (MessageAddedDomainEvent) event;
//    List<ChatMessage> messages = new ArrayList<>();
//    ChatMessage message = new ChatMessage();
//    message.setRole("user");
//    message.setContent("hello");
//    messages.add(message);
//    OpenAIModel model = new OpenAIModel();
//    model.setUrl("https://api.openai.com/v1/chat/completions");
//    model.setModel("gpt-3.5-turbo");
//    model.setEncodedApiKey("");
//    String a = model.chatCompletion(agentResource, messages);
//    System.out.println(a);
//    AgentFinishedDomainEvent agentFinishedDomainEvent = new AgentFinishedDomainEvent();
//    agentFinishedDomainEvent.setThreadId(((MessageAddedDomainEvent) event).getThreadId());
//    agentFinishedDomainEvent.setResult(a);
//    publish(agentFinishedDomainEvent);
//    model.set("https://api.openai.com/v1/chat/completions");
//    model.setUrl("https://api.openai.com/v1/chat/completions");
//    String a = agentResource.chatCompletions("https://api.openai.com/v1/chat/completions",
//            "",
//            "gpt-3.5-turbo",
//            messages,
//            null,
//            1.);
        // TODO call agent run or debug based on event
//    throw new UnsupportedOperationException("12323232Unimplemented method 'handle'");
    }

    public void fun(Agent agent, List<ChatMessage> messages, String threadId) {
//        String res = agent.run(agentResource, messages);
        String res = "```{\"action\":\"test\"}```";
        List<Thought> thoughts = agent.parseOutput(res);
        var agentInternalMessageAddedDomainEvent = new AgentInternalMessageAddedDomainEvent();
        agentInternalMessageAddedDomainEvent.setThreadId(threadId);
        agentInternalMessageAddedDomainEvent.setMessage(res);
        publishEvent(agentInternalMessageAddedDomainEvent);  // record model's output

//        Object monitor = new Object();
        AtomicBoolean functionEnded = new AtomicBoolean(false);
//        Boolean functionEnded = false;

        List<CompletableFuture<String>> futures = thoughts.stream().map(thought -> {
            var msg = new TaskCreatedDomainMessage();
            msg.setToolId("001");  // TODO get toolid from task
            msg.setAgentId(agent.getId());
            msg.setParameters(thought.getParameters());



            return CompletableFuture.supplyAsync(() -> {
                synchronized (functionEnded) {
                    try {
                        log.info("in async");
                        if (!functionEnded.get()) {
                            functionEnded.wait();
                        }
                        log.info("actually called async");
                        publishEvent(msg);

                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                return null;
            }).thenCompose(pre -> new CompletableFuture<String>());
//            CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
//                log.info("called async");
//                publishEvent(msg);
//                return "";
//            });
//
//            return future.<String>newIncompleteFuture();
        }).collect(Collectors.toList());

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]))
                .thenRun(() -> {
                    runningTasks.remove(agent.getId());
                    List<ChatMessage> msgs = futures.stream().map(future -> {
                        String result = future.join();
                        ChatMessage message = new ChatMessage(ChatMessage.ROLE_TOOL, result);
                        return message;
                    }).collect(Collectors.toList());

                    fun(agent, msgs, threadId);
                });
        runningTasks.put(agent.getId(), futures);
        log.info("task submitted");
        synchronized (functionEnded) {
            functionEnded.notify(); // 这还有可能在in async之前。。。
            functionEnded.set(true);
        }
    }
}
