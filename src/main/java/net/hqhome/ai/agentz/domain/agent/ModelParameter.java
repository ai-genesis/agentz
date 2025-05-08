package net.hqhome.ai.agentz.domain.agent;

import com.alibaba.fastjson2.JSONObject;
import lombok.Data;

import java.util.List;

@Data
public class ModelParameter {
//    private String model;
    private Double temperature;
    private List<String> stop;
    private Boolean stream;
    private List<Tool> tools;

    @Data
    public static class Tool {
        private String type;
        private List<Function> function;
    }

    @Data
    public static class Function {
        private String name;
        private String description;
        private JSONObject parameters;
        private Boolean strict;
    }
}
