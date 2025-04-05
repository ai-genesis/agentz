package net.hqhome.ai.agentz.domain.thread;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {
  // message的作用是给用户的视角看自己发了什么和大模型回来什么，那其实可以进行扩展哦
  public static String ROLE_USER = "user";
  public static String ROLE_ASSISTANT = "assistant";

  public static String ROLE_SYSTEM = "system";
  public static String ROLE_MODEL = "model";
  public static String ROLE_TOOL = "tool";

  private Boolean internal = false;
  private String role;
  private String content;


  public Message(String role, String content) {
    this.role = role;
    this.content = content;

    if (!role.equals(ROLE_USER) && !role.equals(ROLE_ASSISTANT)) {
      internal = true;
    }
  }
}
