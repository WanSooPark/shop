package com.allddaom.services.admin.topics.dto.form;

import com.allddaom.models.topics.domain.Topic;
import com.allddaom.models.topics.domain.TopicStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminTopicForm {

    private String code;
    private String name;
    private String status;
    private boolean showMain;

    public static AdminTopicForm of(Topic topic) {
        return AdminTopicForm.builder()
                .code(topic.getCode())
                .name(topic.getName())
                .status(topic.getStatus()
                        .name())
                .showMain(topic.isShowMain())
                .build();
    }

    public static AdminTopicForm empty() {
        return AdminTopicForm.builder()
                .code("")
                .status(TopicStatus.ACTIVATE.name())
                .build();
    }

    public TopicStatus getTopicStatus() {
        return TopicStatus.getStringToEnum(this.status);
    }

    public Topic toEntity() {
        Topic topic = new Topic();
        topic.setCode(this.code);
        topic.setName(this.name);
        topic.setStatus(getTopicStatus());
        topic.setShowMain(this.showMain);
        return topic;
    }
}
