package msauser.api.kafka;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import({KafkaProducerConfig.class})
@Configuration
public class MessagingConfig implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception { }
}
