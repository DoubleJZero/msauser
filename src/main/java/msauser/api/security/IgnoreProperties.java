package msauser.api.security;

import java.util.List;

public class IgnoreProperties {
    private List<String> path;

    private List<String> resource;

    public List<String> getPath() {
        return path;
    }

    public void setPath(List<String> path) {
        this.path = path;
    }

    public List<String> getResource() {
        return resource;
    }

    public void setResource(List<String> resource) {
        this.resource = resource;
    }
}
