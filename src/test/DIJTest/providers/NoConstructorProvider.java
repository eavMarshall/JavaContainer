package DIJTest.providers;

import DIJ.DIJContainer;
import DIJ.DIProvider;
import DIJTest.testClasses.NoConstructor;

public class NoConstructorProvider implements DIProvider {
    private final DIJContainer container;
    public NoConstructorProvider(DIJContainer container) {
        this.container = container;
    }

    @Override
    public NoConstructor getInstance() {
        return container.getInstanceOf(NoConstructor.class);
    }
}
