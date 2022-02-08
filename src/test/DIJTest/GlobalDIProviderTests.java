package DIJTest;

import DIJ.DIJContainer;
import DIJ.GlobalInstanceProvider;
import DIJTest.providers.*;
import DIJTest.testClasses.NoConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class GlobalDIProviderTests {
    private Class clazz;

    public GlobalDIProviderTests(Class clazz) {
        this.clazz = clazz;
    }

    @Parameterized.Parameters(name = "{index}: class={0}")
    public static Collection classProvider() {
        return Arrays.asList(new Object[][]{
                {GlobalNoConstructorProvider.class},
                {GlobalOneConstructorProvider.class},
                {GlobalTwoConstructorProvider.class},
        });
    }

    @Test
    public void testNewInstancesAreCreatedFromDIProvider() {
        DIJContainer container = new DIJContainer();
        GlobalNoConstructorProvider instanceProvider1 = container.getInstanceOf(GlobalNoConstructorProvider.class);
        GlobalNoConstructorProvider instanceProvider2 = container.getInstanceOf(GlobalNoConstructorProvider.class);

        NoConstructor instance1 = instanceProvider1.getGlobalInstance();
        NoConstructor instance2 = instanceProvider2.getGlobalInstance();

        assertNotNull(instance1);
        assertNotNull(instance2);

        assertSame("Only 1 provider should be created per instances of container", instanceProvider1, instanceProvider2);
        assertSame("Global providers should return new instances", instance1, instance2);
    }
}