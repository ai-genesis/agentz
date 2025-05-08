package net.hqhome.ai.agentz.domain.agent;

import java.util.List;

public class ChatAgent extends Agent {
    @Override
    public ModelResponse run(IAgentResource agentResource, List<ChatMessage> messages) {
        return model.chatCompletion(agentResource, modelParameter, messages);
    }
}
