package DIJTest.providers;

import DIJ.GlobalInstanceProvider;
import DIJ.GlobalInstanceContainer;
import DIJTest.testClasses.TwoConstructor;

public class GlobalTwoConstructorProvider implements GlobalInstanceProvider {
    private final GlobalInstanceContainer container;
    public GlobalTwoConstructorProvider(GlobalInstanceContainer container) {
        this.container = container;
    }

    @Override
    public TwoConstructor getGlobalInstance() {
        return container.getGlobalInstance(TwoConstructor.class);
    }
}
