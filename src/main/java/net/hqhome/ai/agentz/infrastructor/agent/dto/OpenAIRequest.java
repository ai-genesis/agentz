package net.hqhome.ai.agentz.infrastructor.agent.dto;

import com.alibaba.fastjson2.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class OpenAIRequest {
    private String model;
    private List<?> messages;
    private List<?> stop;
    private Double temperature;
    private Boolean stream;
    private List<Tool> tools;

    @Data
    public static class Tool {
        private String type;
        private List<Function> function;

        @Data
        public static class Function {
            private String name;
            private String description;
            private JSONObject parameters;
            private Boolean strict;
        }
    }
}
