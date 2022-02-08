package DIJTest.providers;

import DIJ.GlobalInstanceProvider;
import DIJ.GlobalInstanceContainer;
import DIJTest.testClasses.NoConstructor;

public class GlobalNoConstructorProvider implements GlobalInstanceProvider {
    private final GlobalInstanceContainer container;
    public GlobalNoConstructorProvider(GlobalInstanceContainer container) {
        this.container = container;
    }

    @Override
    public NoConstructor getGlobalInstance() {
        return container.getGlobalInstance(NoConstructor.class);
    }
}
