package net.hqhome.ai.agentz.domain.tool;

import org.springframework.stereotype.Component;

@Component
public class ToolFactory {

    public Tool getToolById(String id) {
        return new Tool();
    }

}
