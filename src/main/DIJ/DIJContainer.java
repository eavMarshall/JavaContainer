package DIJ;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DIJContainer {
    private HashMap<Class, Object> overrideObjects = new HashMap<>();
    private HashMap<Class, Object> singleInstances = new HashMap<>();

    public DIJContainer() {}
    private DIJContainer(HashMap<Class, Object> overrideObjects) {
        this.overrideObjects = overrideObjects;
    }

    public DIJContainer addOverrideRule(Class clazz, Object replacementObject) {
        HashMap<Class, Object> newOverrideRules = new HashMap<>();
        for (Class key : overrideObjects.keySet()) {
            newOverrideRules.put(key, overrideObjects.get(key));
        }
        newOverrideRules.put(clazz, replacementObject);
        return new DIJContainer(newOverrideRules);
    }

    public <T extends Object> T getInstanceOf(Class clazz) {
        if (overrideObjects.containsKey(clazz)) {
            T overrideInstance = (T) overrideObjects.get(clazz);

            if (overrideInstance instanceof LazyOverride) {
                return (T) ((LazyOverride) overrideInstance).getInstance();
            }

            return overrideInstance;
        }

        if (singleInstances.containsKey(clazz))
            return (T) singleInstances.get(clazz);

        Object instance = null;
        try {
            Constructor constructor = getConstructor(clazz);
            ArrayList<Object> params = getParamInstances(constructor);
            instance = createInstance(constructor, params);

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

    private Constructor getConstructor(Class clazz) {
        Constructor[] constructors = clazz.getConstructors();
        if (constructors.length > 1)
            throw new RuntimeException("Unsupported type:" + clazz.toString() + " this container only supports classes with 1 constructor. Actual constructor count: " + constructors.length);

        return constructors[0];
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

    /*
    this is dumb but I don't think there's any other way, generated with:

    int lines = 40;
    System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
    System.out.println("case 0: return c0(constructor);");
    for(int i = 1; i <= lines; i++) {
        System.out.println("case "+i+": return c"+i+"(constructor, params);");
    }
    System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
    System.out.println("private Object c0(Constructor c) throws Exception { return c.newInstance(); }");
    for(int i = 1; i <= lines; i++) {
        System.out.print("private Object c"+i+"(Constructor c, List p) throws Exception { return c.newInstance(");
        for(int j = 0; j < i; j++) {
            String prefix = ", ";
            if (j == 0) prefix = "";
            System.out.print(prefix + "p.get("+j+")");
        }
        System.out.println("); }");
    }
    System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
 */
    private Object createInstance(Constructor constructor, ArrayList<Object> params) {
        try {
            switch (params.size()) {
                case 0: return c0(constructor);
                case 1: return c1(constructor, params);
                case 2: return c2(constructor, params);
                case 3: return c3(constructor, params);
                case 4: return c4(constructor, params);
                case 5: return c5(constructor, params);
                case 6: return c6(constructor, params);
                case 7: return c7(constructor, params);
                case 8: return c8(constructor, params);
                case 9: return c9(constructor, params);
                case 10: return c10(constructor, params);
                case 11: return c11(constructor, params);
                case 12: return c12(constructor, params);
                case 13: return c13(constructor, params);
                case 14: return c14(constructor, params);
                case 15: return c15(constructor, params);
                case 16: return c16(constructor, params);
                case 17: return c17(constructor, params);
                case 18: return c18(constructor, params);
                case 19: return c19(constructor, params);
                case 20: return c20(constructor, params);
                case 21: return c21(constructor, params);
                case 22: return c22(constructor, params);
                case 23: return c23(constructor, params);
                case 24: return c24(constructor, params);
                case 25: return c25(constructor, params);
                case 26: return c26(constructor, params);
                case 27: return c27(constructor, params);
                case 28: return c28(constructor, params);
                case 29: return c29(constructor, params);
                case 30: return c30(constructor, params);
                case 31: return c31(constructor, params);
                case 32: return c32(constructor, params);
                case 33: return c33(constructor, params);
                case 34: return c34(constructor, params);
                case 35: return c35(constructor, params);
                case 36: return c36(constructor, params);
                case 37: return c37(constructor, params);
                case 38: return c38(constructor, params);
                case 39: return c39(constructor, params);
                case 40: return c40(constructor, params);
            }
            throw new RuntimeException("Too many arguments");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private Object c0(Constructor c) throws Exception { return c.newInstance(); }
    private Object c1(Constructor c, List p) throws Exception { return c.newInstance(p.get(0)); }
    private Object c2(Constructor c, List p) throws Exception { return c.newInstance(p.get(0), p.get(1)); }
    private Object c3(Constructor c, List p) throws Exception { return c.newInstance(p.get(0), p.get(1), p.get(2)); }
    private Object c4(Constructor c, List p) throws Exception { return c.newInstance(p.get(0), p.get(1), p.get(2), p.get(3)); }
    private Object c5(Constructor c, List p) throws Exception { return c.newInstance(p.get(0), p.get(1), p.get(2), p.get(3), p.get(4)); }
    private Object c6(Constructor c, List p) throws Exception { return c.newInstance(p.get(0), p.get(1), p.get(2), p.get(3), p.get(4), p.get(5)); }
    private Object c7(Constructor c, List p) throws Exception { return c.newInstance(p.get(0), p.get(1), p.get(2), p.get(3), p.get(4), p.get(5), p.get(6)); }
    private Object c8(Constructor c, List p) throws Exception { return c.newInstance(p.get(0), p.get(1), p.get(2), p.get(3), p.get(4), p.get(5), p.get(6), p.get(7)); }
    private Object c9(Constructor c, List p) throws Exception { return c.newInstance(p.get(0), p.get(1), p.get(2), p.get(3), p.get(4), p.get(5), p.get(6), p.get(7), p.get(8)); }
    private Object c10(Constructor c, List p) throws Exception { return c.newInstance(p.get(0), p.get(1), p.get(2), p.get(3), p.get(4), p.get(5), p.get(6), p.get(7), p.get(8), p.get(9)); }
    private Object c11(Constructor c, List p) throws Exception { return c.newInstance(p.get(0), p.get(1), p.get(2), p.get(3), p.get(4), p.get(5), p.get(6), p.get(7), p.get(8), p.get(9), p.get(10)); }
    private Object c12(Constructor c, List p) throws Exception { return c.newInstance(p.get(0), p.get(1), p.get(2), p.get(3), p.get(4), p.get(5), p.get(6), p.get(7), p.get(8), p.get(9), p.get(10), p.get(11)); }
    private Object c13(Constructor c, List p) throws Exception { return c.newInstance(p.get(0), p.get(1), p.get(2), p.get(3), p.get(4), p.get(5), p.get(6), p.get(7), p.get(8), p.get(9), p.get(10), p.get(11), p.get(12)); }
    private Object c14(Constructor c, List p) throws Exception { return c.newInstance(p.get(0), p.get(1), p.get(2), p.get(3), p.get(4), p.get(5), p.get(6), p.get(7), p.get(8), p.get(9), p.get(10), p.get(11), p.get(12), p.get(13)); }
    private Object c15(Constructor c, List p) throws Exception { return c.newInstance(p.get(0), p.get(1), p.get(2), p.get(3), p.get(4), p.get(5), p.get(6), p.get(7), p.get(8), p.get(9), p.get(10), p.get(11), p.get(12), p.get(13), p.get(14)); }
    private Object c16(Constructor c, List p) throws Exception { return c.newInstance(p.get(0), p.get(1), p.get(2), p.get(3), p.get(4), p.get(5), p.get(6), p.get(7), p.get(8), p.get(9), p.get(10), p.get(11), p.get(12), p.get(13), p.get(14), p.get(15)); }
    private Object c17(Constructor c, List p) throws Exception { return c.newInstance(p.get(0), p.get(1), p.get(2), p.get(3), p.get(4), p.get(5), p.get(6), p.get(7), p.get(8), p.get(9), p.get(10), p.get(11), p.get(12), p.get(13), p.get(14), p.get(15), p.get(16)); }
    private Object c18(Constructor c, List p) throws Exception { return c.newInstance(p.get(0), p.get(1), p.get(2), p.get(3), p.get(4), p.get(5), p.get(6), p.get(7), p.get(8), p.get(9), p.get(10), p.get(11), p.get(12), p.get(13), p.get(14), p.get(15), p.get(16), p.get(17)); }
    private Object c19(Constructor c, List p) throws Exception { return c.newInstance(p.get(0), p.get(1), p.get(2), p.get(3), p.get(4), p.get(5), p.get(6), p.get(7), p.get(8), p.get(9), p.get(10), p.get(11), p.get(12), p.get(13), p.get(14), p.get(15), p.get(16), p.get(17), p.get(18)); }
    private Object c20(Constructor c, List p) throws Exception { return c.newInstance(p.get(0), p.get(1), p.get(2), p.get(3), p.get(4), p.get(5), p.get(6), p.get(7), p.get(8), p.get(9), p.get(10), p.get(11), p.get(12), p.get(13), p.get(14), p.get(15), p.get(16), p.get(17), p.get(18), p.get(19)); }
    private Object c21(Constructor c, List p) throws Exception { return c.newInstance(p.get(0), p.get(1), p.get(2), p.get(3), p.get(4), p.get(5), p.get(6), p.get(7), p.get(8), p.get(9), p.get(10), p.get(11), p.get(12), p.get(13), p.get(14), p.get(15), p.get(16), p.get(17), p.get(18), p.get(19), p.get(20)); }
    private Object c22(Constructor c, List p) throws Exception { return c.newInstance(p.get(0), p.get(1), p.get(2), p.get(3), p.get(4), p.get(5), p.get(6), p.get(7), p.get(8), p.get(9), p.get(10), p.get(11), p.get(12), p.get(13), p.get(14), p.get(15), p.get(16), p.get(17), p.get(18), p.get(19), p.get(20), p.get(21)); }
    private Object c23(Constructor c, List p) throws Exception { return c.newInstance(p.get(0), p.get(1), p.get(2), p.get(3), p.get(4), p.get(5), p.get(6), p.get(7), p.get(8), p.get(9), p.get(10), p.get(11), p.get(12), p.get(13), p.get(14), p.get(15), p.get(16), p.get(17), p.get(18), p.get(19), p.get(20), p.get(21), p.get(22)); }
    private Object c24(Constructor c, List p) throws Exception { return c.newInstance(p.get(0), p.get(1), p.get(2), p.get(3), p.get(4), p.get(5), p.get(6), p.get(7), p.get(8), p.get(9), p.get(10), p.get(11), p.get(12), p.get(13), p.get(14), p.get(15), p.get(16), p.get(17), p.get(18), p.get(19), p.get(20), p.get(21), p.get(22), p.get(23)); }
    private Object c25(Constructor c, List p) throws Exception { return c.newInstance(p.get(0), p.get(1), p.get(2), p.get(3), p.get(4), p.get(5), p.get(6), p.get(7), p.get(8), p.get(9), p.get(10), p.get(11), p.get(12), p.get(13), p.get(14), p.get(15), p.get(16), p.get(17), p.get(18), p.get(19), p.get(20), p.get(21), p.get(22), p.get(23), p.get(24)); }
    private Object c26(Constructor c, List p) throws Exception { return c.newInstance(p.get(0), p.get(1), p.get(2), p.get(3), p.get(4), p.get(5), p.get(6), p.get(7), p.get(8), p.get(9), p.get(10), p.get(11), p.get(12), p.get(13), p.get(14), p.get(15), p.get(16), p.get(17), p.get(18), p.get(19), p.get(20), p.get(21), p.get(22), p.get(23), p.get(24), p.get(25)); }
    private Object c27(Constructor c, List p) throws Exception { return c.newInstance(p.get(0), p.get(1), p.get(2), p.get(3), p.get(4), p.get(5), p.get(6), p.get(7), p.get(8), p.get(9), p.get(10), p.get(11), p.get(12), p.get(13), p.get(14), p.get(15), p.get(16), p.get(17), p.get(18), p.get(19), p.get(20), p.get(21), p.get(22), p.get(23), p.get(24), p.get(25), p.get(26)); }
    private Object c28(Constructor c, List p) throws Exception { return c.newInstance(p.get(0), p.get(1), p.get(2), p.get(3), p.get(4), p.get(5), p.get(6), p.get(7), p.get(8), p.get(9), p.get(10), p.get(11), p.get(12), p.get(13), p.get(14), p.get(15), p.get(16), p.get(17), p.get(18), p.get(19), p.get(20), p.get(21), p.get(22), p.get(23), p.get(24), p.get(25), p.get(26), p.get(27)); }
    private Object c29(Constructor c, List p) throws Exception { return c.newInstance(p.get(0), p.get(1), p.get(2), p.get(3), p.get(4), p.get(5), p.get(6), p.get(7), p.get(8), p.get(9), p.get(10), p.get(11), p.get(12), p.get(13), p.get(14), p.get(15), p.get(16), p.get(17), p.get(18), p.get(19), p.get(20), p.get(21), p.get(22), p.get(23), p.get(24), p.get(25), p.get(26), p.get(27), p.get(28)); }
    private Object c30(Constructor c, List p) throws Exception { return c.newInstance(p.get(0), p.get(1), p.get(2), p.get(3), p.get(4), p.get(5), p.get(6), p.get(7), p.get(8), p.get(9), p.get(10), p.get(11), p.get(12), p.get(13), p.get(14), p.get(15), p.get(16), p.get(17), p.get(18), p.get(19), p.get(20), p.get(21), p.get(22), p.get(23), p.get(24), p.get(25), p.get(26), p.get(27), p.get(28), p.get(29)); }
    private Object c31(Constructor c, List p) throws Exception { return c.newInstance(p.get(0), p.get(1), p.get(2), p.get(3), p.get(4), p.get(5), p.get(6), p.get(7), p.get(8), p.get(9), p.get(10), p.get(11), p.get(12), p.get(13), p.get(14), p.get(15), p.get(16), p.get(17), p.get(18), p.get(19), p.get(20), p.get(21), p.get(22), p.get(23), p.get(24), p.get(25), p.get(26), p.get(27), p.get(28), p.get(29), p.get(30)); }
    private Object c32(Constructor c, List p) throws Exception { return c.newInstance(p.get(0), p.get(1), p.get(2), p.get(3), p.get(4), p.get(5), p.get(6), p.get(7), p.get(8), p.get(9), p.get(10), p.get(11), p.get(12), p.get(13), p.get(14), p.get(15), p.get(16), p.get(17), p.get(18), p.get(19), p.get(20), p.get(21), p.get(22), p.get(23), p.get(24), p.get(25), p.get(26), p.get(27), p.get(28), p.get(29), p.get(30), p.get(31)); }
    private Object c33(Constructor c, List p) throws Exception { return c.newInstance(p.get(0), p.get(1), p.get(2), p.get(3), p.get(4), p.get(5), p.get(6), p.get(7), p.get(8), p.get(9), p.get(10), p.get(11), p.get(12), p.get(13), p.get(14), p.get(15), p.get(16), p.get(17), p.get(18), p.get(19), p.get(20), p.get(21), p.get(22), p.get(23), p.get(24), p.get(25), p.get(26), p.get(27), p.get(28), p.get(29), p.get(30), p.get(31), p.get(32)); }
    private Object c34(Constructor c, List p) throws Exception { return c.newInstance(p.get(0), p.get(1), p.get(2), p.get(3), p.get(4), p.get(5), p.get(6), p.get(7), p.get(8), p.get(9), p.get(10), p.get(11), p.get(12), p.get(13), p.get(14), p.get(15), p.get(16), p.get(17), p.get(18), p.get(19), p.get(20), p.get(21), p.get(22), p.get(23), p.get(24), p.get(25), p.get(26), p.get(27), p.get(28), p.get(29), p.get(30), p.get(31), p.get(32), p.get(33)); }
    private Object c35(Constructor c, List p) throws Exception { return c.newInstance(p.get(0), p.get(1), p.get(2), p.get(3), p.get(4), p.get(5), p.get(6), p.get(7), p.get(8), p.get(9), p.get(10), p.get(11), p.get(12), p.get(13), p.get(14), p.get(15), p.get(16), p.get(17), p.get(18), p.get(19), p.get(20), p.get(21), p.get(22), p.get(23), p.get(24), p.get(25), p.get(26), p.get(27), p.get(28), p.get(29), p.get(30), p.get(31), p.get(32), p.get(33), p.get(34)); }
    private Object c36(Constructor c, List p) throws Exception { return c.newInstance(p.get(0), p.get(1), p.get(2), p.get(3), p.get(4), p.get(5), p.get(6), p.get(7), p.get(8), p.get(9), p.get(10), p.get(11), p.get(12), p.get(13), p.get(14), p.get(15), p.get(16), p.get(17), p.get(18), p.get(19), p.get(20), p.get(21), p.get(22), p.get(23), p.get(24), p.get(25), p.get(26), p.get(27), p.get(28), p.get(29), p.get(30), p.get(31), p.get(32), p.get(33), p.get(34), p.get(35)); }
    private Object c37(Constructor c, List p) throws Exception { return c.newInstance(p.get(0), p.get(1), p.get(2), p.get(3), p.get(4), p.get(5), p.get(6), p.get(7), p.get(8), p.get(9), p.get(10), p.get(11), p.get(12), p.get(13), p.get(14), p.get(15), p.get(16), p.get(17), p.get(18), p.get(19), p.get(20), p.get(21), p.get(22), p.get(23), p.get(24), p.get(25), p.get(26), p.get(27), p.get(28), p.get(29), p.get(30), p.get(31), p.get(32), p.get(33), p.get(34), p.get(35), p.get(36)); }
    private Object c38(Constructor c, List p) throws Exception { return c.newInstance(p.get(0), p.get(1), p.get(2), p.get(3), p.get(4), p.get(5), p.get(6), p.get(7), p.get(8), p.get(9), p.get(10), p.get(11), p.get(12), p.get(13), p.get(14), p.get(15), p.get(16), p.get(17), p.get(18), p.get(19), p.get(20), p.get(21), p.get(22), p.get(23), p.get(24), p.get(25), p.get(26), p.get(27), p.get(28), p.get(29), p.get(30), p.get(31), p.get(32), p.get(33), p.get(34), p.get(35), p.get(36), p.get(37)); }
    private Object c39(Constructor c, List p) throws Exception { return c.newInstance(p.get(0), p.get(1), p.get(2), p.get(3), p.get(4), p.get(5), p.get(6), p.get(7), p.get(8), p.get(9), p.get(10), p.get(11), p.get(12), p.get(13), p.get(14), p.get(15), p.get(16), p.get(17), p.get(18), p.get(19), p.get(20), p.get(21), p.get(22), p.get(23), p.get(24), p.get(25), p.get(26), p.get(27), p.get(28), p.get(29), p.get(30), p.get(31), p.get(32), p.get(33), p.get(34), p.get(35), p.get(36), p.get(37), p.get(38)); }
    private Object c40(Constructor c, List p) throws Exception { return c.newInstance(p.get(0), p.get(1), p.get(2), p.get(3), p.get(4), p.get(5), p.get(6), p.get(7), p.get(8), p.get(9), p.get(10), p.get(11), p.get(12), p.get(13), p.get(14), p.get(15), p.get(16), p.get(17), p.get(18), p.get(19), p.get(20), p.get(21), p.get(22), p.get(23), p.get(24), p.get(25), p.get(26), p.get(27), p.get(28), p.get(29), p.get(30), p.get(31), p.get(32), p.get(33), p.get(34), p.get(35), p.get(36), p.get(37), p.get(38), p.get(39)); }
}
