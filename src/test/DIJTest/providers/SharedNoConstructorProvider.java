package DIJTest.providers;

import DIJ.SharedInstanceProvider;
import DIJ.SharedInstanceContainer;
import DIJTest.testClasses.NoConstructor;

public class SharedNoConstructorProvider implements SharedInstanceProvider {
    private final SharedInstanceContainer container;
    public SharedNoConstructorProvider(SharedInstanceContainer container) {
        this.container = container;
    }

    @Override
    public NoConstructor getSharedInstance() {
        return container.getSharedInstance(NoConstructor.class);
    }
}
