package DIJTest;

import DIJ.DIJContainer;
import DIJ.LazyOverride;
import org.junit.Test;
import test.testClasses.NoConstructor;
import test.testClasses.OneConstructor;

import static org.junit.Assert.*;

public class LazyOverrideTest {
    private boolean hasCreateInstance = false;
    @Test
    public void testOverride() {
        DIJContainer container = new DIJContainer();
        hasCreateInstance = false;
        DIJContainer overrideContainer = container.addOverrideRule(OneConstructor.class, new LazyOverride<NoConstructor>() {
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
