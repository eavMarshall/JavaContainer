package test;

import DIJ.DIJContainer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import test.testClasses.NoConstructor;
import test.testClasses.OneConstructor;
import test.testClasses.TowAndSelfConstructor;
import test.testClasses.TwoConstructor;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class CreateNewInstanceTest {
    private Class clazz;

    public CreateNewInstanceTest(Class clazz) {
        this.clazz = clazz;
    }

    @Parameterized.Parameters(name = "{index}: class={0}")
    public static Collection classProvider() {
        return Arrays.asList(new Object[][]{
                {NoConstructor.class},
                {OneConstructor.class},
                {TwoConstructor.class},
                {TowAndSelfConstructor.class},
        });
    }

    @Test
    public void createClassWithNoConstructor() {
        Object instance = new DIJContainer().getInstanceOf(this.clazz);

        assertNotNull(instance);
        assertTrue(instance.getClass().isAssignableFrom(this.clazz));
    }
}