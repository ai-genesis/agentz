package net.hqhome.ai.agentz.domain.tool;

import com.alibaba.fastjson2.JSONObject;
import lombok.Data;

@Data
public class Tool {
    private String id;
    private JSONObject parameters;

    public String run() {
        return "tool resutl";
    }

}
