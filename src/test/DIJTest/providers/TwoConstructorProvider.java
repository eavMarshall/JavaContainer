package DIJTest.providers;

import DIJ.DIJContainer;
import DIJ.DIProvider;
import DIJTest.testClasses.TwoConstructor;

public class TwoConstructorProvider implements DIProvider {
    private final DIJContainer container;
    public TwoConstructorProvider(DIJContainer container) {
        this.container = container;
    }

    @Override
    public TwoConstructor getInstance() {
        return container.getInstanceOf(TwoConstructor.class);
    }
}
