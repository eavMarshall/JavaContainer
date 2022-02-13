package DIJ;

import DIJ.exceptions.UnsupportedType;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

public class DIJContainer {
    private HashMap<Class, Object> overrideObjects = new HashMap<Class, Object>();
    private HashMap<Class, Object> singleInstances = new HashMap<Class, Object>();
    private HashMap<Class, Class> classOverrides = new HashMap<Class, Class>();

    public DIJContainer() {
    }

    private DIJContainer(HashMap<Class, Object> overrideObjects) {
        this.overrideObjects = overrideObjects;
    }

    public void addInterfaceConfig(HashMap<Class, Class> interfaceMap) {
        for (Class interfaceToInject : interfaceMap.keySet()) {
            Class classToInject = interfaceMap.get(interfaceToInject);
            if (interfaceToInject == null || classToInject == null || !interfaceToInject.isInterface()) {
                throw new UnsupportedType("Override only support interfaces. " + interfaceToInject + " is not an interface");
            }

            classOverrides.put(interfaceToInject, classToInject);
        }
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

    public <T extends Object> T getInstanceOf(Class<T> inputClass) {
        Class<T> clazz = inputClass;
        if (overrideObjects.containsKey(clazz)) return getOverrideObject(clazz);
        if (singleInstances.containsKey(clazz)) return (T) singleInstances.get(clazz);

        if (classOverrides.containsKey(clazz)) {
            clazz = classOverrides.get(clazz);
        }

        Object instance;
        try {
            Constructor constructor = getConstructor(clazz);
            constructor = constructor == null ? clazz.getConstructor() : constructor;
            instance = createInstance(constructor, getParamInstances(constructor));

            if (instance instanceof InitialiseObject)
                ((InitialiseObject) instance).initialise();
            if (instance instanceof SingleInstances)
                singleInstances.put(clazz, instance);
        }
        catch (NoSuchMethodException e) {
            throw new UnsupportedType("Unsupported type:" + clazz.toString() + ", " + e.getMessage());
        }
        catch (Exception e) {
            throw new UnsupportedType("Unexpected error, message: " + e.getMessage());
        }
        return (T) instance;
    }

    private <T extends Object> T getOverrideObject(Class<T> clazz) {
        T overrideInstance = (T) overrideObjects.get(clazz);

        if (overrideInstance instanceof LazyOverride)
            return (T) ((LazyOverride) overrideInstance).getInstance();

        return overrideInstance;
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
            throw new UnsupportedType("Unsupported type:" + clazz.toString()
                    + " this container only supports classes with 1 public constructor. "
                    + "Actual constructor count: " + constructors.length);
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