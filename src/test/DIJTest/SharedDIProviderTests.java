package DIJTest;

import DIJ.DIJContainer;
import DIJTest.providers.*;
import DIJTest.testClasses.NoConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class SharedDIProviderTests {
    private Class clazz;

    public SharedDIProviderTests(Class clazz) {
        this.clazz = clazz;
    }

    @Parameterized.Parameters(name = "{index}: class={0}")
    public static Collection classProvider() {
        return Arrays.asList(new Object[][]{
                {SharedNoConstructorProvider.class},
                {SharedOneConstructorProvider.class},
                {SharedTwoConstructorProvider.class},
        });
    }

    @Test
    public void testNewInstancesAreCreatedFromDIProvider() {
        DIJContainer container = new DIJContainer();
        SharedNoConstructorProvider instanceProvider1 = container.getInstanceOf(SharedNoConstructorProvider.class);
        SharedNoConstructorProvider instanceProvider2 = container.getInstanceOf(SharedNoConstructorProvider.class);

        NoConstructor instance1 = instanceProvider1.getSharedInstance();
        NoConstructor instance2 = instanceProvider2.getSharedInstance();

        assertNotNull(instance1);
        assertNotNull(instance2);

        assertSame("Only 1 provider should be created per instances of container", instanceProvider1, instanceProvider2);
        assertSame("Global providers should return new instances", instance1, instance2);
    }
}