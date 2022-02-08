# DIJContainer
## _A super simple dependency injection container for java 8_

DIJContainer is a autowiring container, with the ability to use data providers for lazy loading. No XML configuration, no annotations and no extra build steps

## Features
- Immutable
- Container will always inject itself, rather than create a new container
- Overridable configuration for testing
- Data providers for lazy loading and global instances
- Configuration free production code

## How to use
Just call the getInstanceOf at the root of your application. All dependencies will be injected.
```java
class ApplicationMain {
    public static void main(String[] args) {
        DIJContainer container = new DIJContainer();
        MyApplication app = container.getInstanceOf(MyApplication.class);
        ...
    }
}
```

To improve start up performance lazy load your classes with instances providers
```java
public class MyDependencyProvider implements DIProvider {
    private final DIJContainer container;
    public NoConstructorProvider(DIJContainer container) {
        this.container = container;
    }

    @Override
    public MyDependency getInstance() {
        return container.getInstanceOf(MyDependency.class);
    }
}


public class MyApplication {
    private MyDependencyProvider myDependencyProvider;
    public MyApplication(MyDependencyProvider myDependencyProvider) {
        this.myDependencyProvider = myDependencyProvider;
    }
    
    public void whenReadyToWork() {
        // creates new MyDependency instance when I need it
        MyDependency myDependency = myDependencyProvider.getInstance();
        ...
    }
}
```
Container can keep share instances across your application
```java
public class GlobalMyDependencyProvider implements GlobalInstanceProvider {
    private final GlobalMyDependencyProvider container;
    public GlobalNoConstructorProvider(GlobalInstanceContainer container) {
        this.container = container;
    }

    @Override
    public MyDependency getGlobalInstance() {
        return container.getGlobalInstance(MyDependency.class);
    }
}

public class MyApplication {
    private GlobalMyDependencyProvider globalMyDependencyProvider;
    public MyApplication(GlobalMyDependencyProvider globalMyDependencyProvider) {
        this.globalMyDependencyProvider = globalMyDependencyProvider;
    }
    
    public void whenReadyToWork() {
        //same instance return every time getGlobalInstance is called
        MyDependency myDependency = globalMyDependencyProvider.getGlobalInstance(); 
        ...
    }
}
```

License
MIT