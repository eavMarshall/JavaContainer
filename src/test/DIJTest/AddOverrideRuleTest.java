package DIJTest;

import DIJ.DIJContainer;
import org.junit.Test;
import test.testClasses.NoConstructor;
import test.testClasses.OneConstructor;

import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

public class AddOverrideRuleTest {
    @Test
    public void testOverride() {
        DIJContainer container = new DIJContainer();
        DIJContainer overrideContainer = container.addOverrideRule(OneConstructor.class, new NoConstructor());
        DIJContainer overrideContainer2 = container.addOverrideRule(OneConstructor.class, new NoConstructor());

        assertNotSame("Container should be immutable, new container per override rule", container, overrideContainer);
        assertNotSame("Container should be immutable, new container per override rule", container, overrideContainer2);
        assertNotSame("Container should be immutable, new container per override rule", overrideContainer, overrideContainer2);

        Object instance = overrideContainer.getInstanceOf(OneConstructor.class);
        assertTrue(NoConstructor.class.isAssignableFrom(instance.getClass()));

        Object instance2 = overrideContainer2.getInstanceOf(OneConstructor.class);
        assertTrue(NoConstructor.class.isAssignableFrom(instance2.getClass()));

        assertNotSame(instance, instance2);
    }
}
