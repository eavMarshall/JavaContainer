package DIJ;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

public class DIJContainer {
    private HashMap<Class, Object> overrideObjects = new HashMap<Class, Object>();
    private HashMap<Class, Object> singleInstances = new HashMap<Class, Object>();

    public DIJContainer() {
    }

    private DIJContainer(HashMap<Class, Object> overrideObjects) {
        this.overrideObjects = overrideObjects;
    }

    /**
     * Each override returns a new immutable container instance
     * @param clazz
     * @param replacementObject
     * @return
     */
    public DIJContainer addOverrideRule(Class clazz, Object replacementObject) {
        HashMap<Class, Object> newOverrideRules = new HashMap<Class, Object>();
        for (Class key : overrideObjects.keySet()) {
            newOverrideRules.put(key, overrideObjects.get(key));
        }
        newOverrideRules.put(clazz, replacementObject);
        return new DIJContainer(newOverrideRules);
    }

    public <T extends Object> T getInstanceOf(Class clazz) {
        if (overrideObjects.containsKey(clazz)) {
            T overrideInstance = (T) overrideObjects.get(clazz);

            if (overrideInstance instanceof LazyOverride)
                return (T) ((LazyOverride) overrideInstance).getInstance();

            return overrideInstance;
        }

        if (singleInstances.containsKey(clazz))
            return (T) singleInstances.get(clazz);

        Object instance;
        try {
            Constructor constructor = getConstructor(clazz);
            constructor = constructor == null ? clazz.getConstructor() : constructor;
            instance = createInstance(constructor, getParamInstances(constructor));

            if (instance instanceof InitialiseObject)
                ((InitialiseObject) instance).initialise();
            if (instance instanceof SingleInstances)
                singleInstances.put(clazz, instance);
        } catch (Exception e) {
            if (e instanceof NoSuchMethodException)
                throw new RuntimeException("Unsupported type:" + clazz.toString() + " NoSuchMethodException");
            if (e instanceof InvocationTargetException)
                throw new RuntimeException("error creating object, message: " + e.getCause());

            throw new RuntimeException("Unexpected error, message: " + e.getMessage());
        }
        return (T) instance;
    }

    private Object createInstance(Constructor constructor, ArrayList<Object> params) {
        try {
            return constructor.newInstance(params.toArray());
        } catch (Exception e) {
            if (e instanceof InvocationTargetException)
                throw new RuntimeException("error creating object, message: " + e.getCause());
            throw new RuntimeException(e.getMessage());
        }
    }

    private Constructor getConstructor(Class clazz) {
        Constructor[] constructors = clazz.getConstructors();
        if (constructors.length > 1) {
            for (Constructor c : constructors) {
                if (c.getParameterTypes().length == 0) {
                    return c;
                }
            }
            throw new RuntimeException("Unsupported type:" + clazz.toString()
                    + " this container only supports classes with 1 constructor. Actual constructor count: " + constructors.length);
        }

        return constructors.length == 0 ? null : constructors[0];
    }

    private ArrayList<Object> getParamInstances(Constructor constructor) {
        ArrayList<Object> params = new ArrayList<Object>();
        Class<?>[] paramTypes = constructor.getParameterTypes();
        for (Class<?> paramType : paramTypes) {
            if (paramType.isAssignableFrom(this.getClass())) {
                params.add(this);
                continue;
            }
            params.add(getInstanceOf(paramType));
        }
        return params;
    }
}