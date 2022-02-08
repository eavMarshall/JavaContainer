package DIJTest;

import DIJ.DIJContainer;
import DIJTest.providers.NoConstructorProvider;
import DIJTest.providers.OneConstructorProvider;
import DIJTest.providers.TwoConstructorProvider;
import DIJTest.testClasses.NoConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class DIProviderTests {
    private Class clazz;

    public DIProviderTests(Class clazz) {
        this.clazz = clazz;
    }

    @Parameterized.Parameters(name = "{index}: class={0}")
    public static Collection classProvider() {
        return Arrays.asList(new Object[][]{
                {NoConstructorProvider.class},
                {OneConstructorProvider.class},
                {TwoConstructorProvider.class},
        });
    }

    @Test
    public void testNewInstancesAreCreatedFromDIProvider() {
        DIJContainer container = new DIJContainer();
        NoConstructorProvider instanceProvider1 = container.getInstanceOf(NoConstructorProvider.class);
        NoConstructorProvider instanceProvider2 = container.getInstanceOf(NoConstructorProvider.class);

        NoConstructor instance1 = instanceProvider1.getInstance();
        NoConstructor instance2 = instanceProvider2.getInstance();

        assertNotNull(instance1);
        assertNotNull(instance2);

        assertSame("Only 1 provider should be created per instances of container", instanceProvider1, instanceProvider2);
        assertNotSame("Providers should return new instances", instance1, instance2);
    }
}