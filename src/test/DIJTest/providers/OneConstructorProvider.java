package DIJTest.providers;

import DIJ.DIJContainer;
import DIJ.DIProvider;
import DIJTest.testClasses.OneConstructor;

public class OneConstructorProvider implements DIProvider {
    private final DIJContainer container;
    public OneConstructorProvider(DIJContainer container) {
        this.container = container;
    }

    @Override
    public OneConstructor getInstance() {
        return container.getInstanceOf(OneConstructor.class);
    }
}
