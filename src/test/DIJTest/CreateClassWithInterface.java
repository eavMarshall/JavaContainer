package DIJTest;

import DIJ.DIJContainer;
import DIJ.exceptions.UnsupportedType;
import DIJTest.testClasses.ClassIServiceDependency;
import DIJTest.testClasses.IService;
import DIJTest.testClasses.Service;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.HashMap;

import static org.junit.Assert.*;

public class CreateClassWithInterface {
    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test(expected = UnsupportedType.class)
    public void testInterfaceError() {
        DIJContainer container = new DIJContainer();
        IService service = container.getInstanceOf(IService.class);

        assertNotNull(service);
    }

    @Test
    public void testInterfaceConstructorError() {
        expectedEx.expect(UnsupportedType.class);
        expectedEx.expectMessage("Unsupported type:interface DIJTest.testClasses.IService");
        DIJContainer container = new DIJContainer();
        ClassIServiceDependency testObject = container.getInstanceOf(ClassIServiceDependency.class);

        fail("These did not throw expected exception");
    }

    @Test
    public void testBadOverrideRuleForInterface() {
        expectedEx.expect(UnsupportedType.class);
        expectedEx.expectMessage("Override only support interfaces. class DIJTest.testClasses.ClassIServiceDependency is not an interface");
        DIJContainer container = new DIJContainer();
        container.addInterfaceConfig(new HashMap<Class, Class>() {{
            put(IService.class, Service.class);
            put(ClassIServiceDependency.class, ClassIServiceDependency.class);
        }});

        fail("These did not throw expected exception");
    }

    @Test
    public void testInterfaceOverride() {
        DIJContainer container = new DIJContainer();
        container.addInterfaceConfig(new HashMap<Class, Class>() {{
            put(IService.class, Service.class);
        }});
        IService testObject = container.getInstanceOf(IService.class);

        assertTrue(testObject instanceof Service);
        assertTrue(testObject instanceof IService);
    }
}
