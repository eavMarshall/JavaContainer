package DIJTest;

import DIJ.DIJContainer;
import org.junit.Test;
import test.testClasses.NoConstructor;
import test.testClasses.SingleInstanceClass;

import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;

public class CreateSingleInstanceTest {
    @Test
    public void testContainerKeepsInstances() {
        DIJContainer container = new DIJContainer();
        SingleInstanceClass call1 = container.getInstanceOf(SingleInstanceClass.class);
        SingleInstanceClass call2 = container.getInstanceOf(SingleInstanceClass.class);

        assertSame("Classes that implement SingleInstance should be kept by the contianer", call1, call2);
    }

    @Test
    public void testContianerReturnsNewInstances() {
        DIJContainer container = new DIJContainer();
        NoConstructor call1 = container.getInstanceOf(NoConstructor.class);
        NoConstructor call2 = container.getInstanceOf(NoConstructor.class);

        assertNotSame("Classes that don't implement SingleInstances should be new instances", call1, call2);
    }
}
