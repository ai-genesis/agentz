package net.hqhome.ai.agentz.infrastructor.thread.dataobject;

import java.util.Date;

public class MessageDO {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column message.id
     *
     * @mbg.generated Mon Mar 25 17:32:10 CST 2024
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column message.thread_id
     *
     * @mbg.generated Mon Mar 25 17:32:10 CST 2024
     */
    private String threadId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column message.role
     *
     * @mbg.generated Mon Mar 25 17:32:10 CST 2024
     */
    private String role;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column message.content
     *
     * @mbg.generated Mon Mar 25 17:32:10 CST 2024
     */
    private String content;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column message.create_time
     *
     * @mbg.generated Mon Mar 25 17:32:10 CST 2024
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column message.modified_time
     *
     * @mbg.generated Mon Mar 25 17:32:10 CST 2024
     */
    private Date modifiedTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column message.is_deleted
     *
     * @mbg.generated Mon Mar 25 17:32:10 CST 2024
     */
    private Boolean isDeleted;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column message.id
     *
     * @return the value of message.id
     *
     * @mbg.generated Mon Mar 25 17:32:10 CST 2024
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column message.id
     *
     * @param id the value for message.id
     *
     * @mbg.generated Mon Mar 25 17:32:10 CST 2024
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column message.thread_id
     *
     * @return the value of message.thread_id
     *
     * @mbg.generated Mon Mar 25 17:32:10 CST 2024
     */
    public String getThreadId() {
        return threadId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column message.thread_id
     *
     * @param threadId the value for message.thread_id
     *
     * @mbg.generated Mon Mar 25 17:32:10 CST 2024
     */
    public void setThreadId(String threadId) {
        this.threadId = threadId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column message.role
     *
     * @return the value of message.role
     *
     * @mbg.generated Mon Mar 25 17:32:10 CST 2024
     */
    public String getRole() {
        return role;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column message.role
     *
     * @param role the value for message.role
     *
     * @mbg.generated Mon Mar 25 17:32:10 CST 2024
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column message.content
     *
     * @return the value of message.content
     *
     * @mbg.generated Mon Mar 25 17:32:10 CST 2024
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column message.content
     *
     * @param content the value for message.content
     *
     * @mbg.generated Mon Mar 25 17:32:10 CST 2024
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column message.create_time
     *
     * @return the value of message.create_time
     *
     * @mbg.generated Mon Mar 25 17:32:10 CST 2024
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column message.create_time
     *
     * @param createTime the value for message.create_time
     *
     * @mbg.generated Mon Mar 25 17:32:10 CST 2024
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column message.modified_time
     *
     * @return the value of message.modified_time
     *
     * @mbg.generated Mon Mar 25 17:32:10 CST 2024
     */
    public Date getModifiedTime() {
        return modifiedTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column message.modified_time
     *
     * @param modifiedTime the value for message.modified_time
     *
     * @mbg.generated Mon Mar 25 17:32:10 CST 2024
     */
    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column message.is_deleted
     *
     * @return the value of message.is_deleted
     *
     * @mbg.generated Mon Mar 25 17:32:10 CST 2024
     */
    public Boolean getIsDeleted() {
        return isDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column message.is_deleted
     *
     * @param isDeleted the value for message.is_deleted
     *
     * @mbg.generated Mon Mar 25 17:32:10 CST 2024
     */
    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}