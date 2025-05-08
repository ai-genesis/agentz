package net.hqhome.ai.agentz.domain.agent;

import lombok.Data;

import java.util.List;

@Data
public class ModelResponse {
    private String content;
    private List<Tool> tools;

    @Data
    public static class Tool {
        private String id;
        private Function function;
    }

    @Data
    public static class Function {
        private String name;
        private String arguments;
    }
}
