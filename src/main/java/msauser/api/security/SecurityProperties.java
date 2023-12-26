package msauser.api.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "cust.security")
public class SecurityProperties {

    private IgnoreProperties ignore;

    public IgnoreProperties getIgnore() {
        return ignore;
    }

    public void setIgnore(IgnoreProperties ignore) {
        this.ignore = ignore;
    }
}
