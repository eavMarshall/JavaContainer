package test;

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

        assertNotSame("Container should be immutable, new container per override rule", container, overrideContainer);

        Object instance = overrideContainer.getInstanceOf(OneConstructor.class);

        assertTrue(NoConstructor.class.isAssignableFrom(instance.getClass()));
    }
}
