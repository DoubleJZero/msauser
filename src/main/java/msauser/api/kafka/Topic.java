package msauser.api.kafka;

import lombok.Getter;

@Getter
public enum Topic {
    USER_TOPIC("user-service-topic");

    private String name;

    Topic(String name){
        this.name = name;
    }
}
