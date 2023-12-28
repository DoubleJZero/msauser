package msauser.api.kafka;

public class KafkaSendMessageException extends RuntimeException {
    public KafkaSendMessageException(){
        super();
    }

    public KafkaSendMessageException(String message, Throwable cause){
        super(message, cause);
    }

    public KafkaSendMessageException(String message){
        super(message);
    }

    public KafkaSendMessageException(Throwable cause){
        super(cause);
    }
}
