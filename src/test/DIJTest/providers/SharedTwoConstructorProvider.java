package DIJTest.providers;

import DIJ.SharedInstanceProvider;
import DIJ.SharedInstanceContainer;
import DIJTest.testClasses.TwoConstructor;

public class SharedTwoConstructorProvider implements SharedInstanceProvider {
    private final SharedInstanceContainer container;
    public SharedTwoConstructorProvider(SharedInstanceContainer container) {
        this.container = container;
    }

    @Override
    public TwoConstructor getSharedInstance() {
        return container.getSharedInstance(TwoConstructor.class);
    }
}
