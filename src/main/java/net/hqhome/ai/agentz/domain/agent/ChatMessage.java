package net.hqhome.ai.agentz.domain.agent;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChatMessage {
    public static String ROLE_SYSTEM = "system";
    public static String ROLE_TOOL = "tool";

    private String role;
    private String content;

}
