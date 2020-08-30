package DIJTest;

import DIJ.DIJContainer;
import DIJ.LazyOverride;
import DIJTest.testClasses.NoConstructor;
import DIJTest.testClasses.OneConstructor;
import org.junit.Test;

import static org.junit.Assert.*;

public class LazyOverrideTest {
    private boolean hasCreateInstance = false;
    @Test
    public void testOverride() {
        DIJContainer container = new DIJContainer();
        hasCreateInstance = false;
        DIJContainer overrideContainer = container.addOverrideRule(OneConstructor.class, new LazyOverride() {
            @Override
            public NoConstructor getInstance() {
                hasCreateInstance = true;
                return new NoConstructor();
            }
        });

        assertFalse(hasCreateInstance);

        Object instance = overrideContainer.getInstanceOf(OneConstructor.class);
        assertTrue(NoConstructor.class.isAssignableFrom(instance.getClass()));

        assertTrue(hasCreateInstance);
    }
}
