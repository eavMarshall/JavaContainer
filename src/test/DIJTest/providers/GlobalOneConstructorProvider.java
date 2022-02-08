package DIJTest.providers;

import DIJ.GlobalInstanceProvider;
import DIJ.GlobalInstanceContainer;
import DIJTest.testClasses.OneConstructor;

public class GlobalOneConstructorProvider implements GlobalInstanceProvider {
    private final GlobalInstanceContainer container;
    public GlobalOneConstructorProvider(GlobalInstanceContainer container) {
        this.container = container;
    }

    @Override
    public OneConstructor getGlobalInstance() {
        return container.getGlobalInstance(OneConstructor.class);
    }
}
