package net.hqhome.ai.agentz.domain.agent;

import com.alibaba.fastjson2.JSONObject;
import lombok.Data;

@Data
public class Task {
    private String name;
    private String description;
    private JSONObject parameters;
    private String toolId;

    public Task(String name, JSONObject parameters) {
        this.name = name;
        this.parameters = parameters;
    }

    public String run() {
        return null;
    }
}
