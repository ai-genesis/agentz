package net.hqhome.ai.agentz.domain.agent;

import com.alibaba.fastjson2.JSONObject;
import org.bouncycastle.util.Pack;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReActAgent extends Agent {
    @Override
    public String run(IAgentResource agentResource, List<ChatMessage> messages) {
        messages.addFirst(new ChatMessage(ChatMessage.ROLE_SYSTEM, systemMessage));
        return model.chatCompletion(agentResource, modelParameter, messages);
    }

    @Override
    public List<Thought> parseOutput(String output) {
        Pattern pattern = Pattern.compile("```(.*)```");
        Matcher matcher = pattern.matcher(output);
        boolean found = matcher.find();

        if (!found) {
            throw new RuntimeException("parseOutput failed, output is " + output);
        }

        String json = matcher.group(1);
        JSONObject jsonObject = JSONObject.parseObject(json);
        Thought thought = new Thought(jsonObject.getString("action"), jsonObject.getJSONObject("action_input"), output);

        return Arrays.asList(thought);
    }

    public static void main(String[] args) {
        ReActAgent reActAgent = new ReActAgent();
        var a = reActAgent.parseOutput("```{   \"action\": \"get_weather\",   \"action_input\": {\"city\": \"Hangzhou\", \"date\": \"today\"} }```");
        System.out.println(a);
    }
}
