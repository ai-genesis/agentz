package net.hqhome.ai.agentz.domain.agent;

import com.alibaba.fastjson2.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Thought {
    private String taskName;
    private JSONObject parameters;
    private String raw;
}
