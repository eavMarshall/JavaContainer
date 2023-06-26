package DIJTest.providers;

import DIJ.SharedInstanceProvider;
import DIJ.SharedInstanceContainer;
import DIJTest.testClasses.OneConstructor;

public class SharedOneConstructorProvider implements SharedInstanceProvider {
    private final SharedInstanceContainer container;
    public SharedOneConstructorProvider(SharedInstanceContainer container) {
        this.container = container;
    }

    @Override
    public OneConstructor getSharedInstance() {
        return container.getSharedInstance(OneConstructor.class);
    }
}
