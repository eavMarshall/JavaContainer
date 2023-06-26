package DIJ;

import java.util.HashMap;

public class SharedInstanceContainer implements SingleInstances {
    private final DIJContainer container;
    private HashMap<Class, Object> instances = new HashMap<Class, Object>();

    public SharedInstanceContainer(DIJContainer container) {
        this.container = container;
    }

    public <T extends Object> T getSharedInstance(Class clazz) {
        if (!instances.containsKey(clazz)) {
            instances.put(clazz, container.getInstanceOf(clazz));
        }

        return (T) instances.get(clazz);
    }
}
