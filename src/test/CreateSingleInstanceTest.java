package test;

import DIJ.DIJContainer;
import org.junit.Test;
import test.testClasses.SingleInstanceClass;

import static org.junit.Assert.assertSame;

public class CreateSingleInstanceTest {
    @Test
    public void testContainerKeepsInstances() {
        DIJContainer container = new DIJContainer();
        SingleInstanceClass call1 = container.getInstanceOf(SingleInstanceClass.class);
        SingleInstanceClass call2 = container.getInstanceOf(SingleInstanceClass.class);

        assertSame(call1, call2);
    }
}
