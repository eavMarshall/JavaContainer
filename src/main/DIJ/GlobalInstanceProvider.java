package DIJ;

public interface GlobalInstanceProvider extends SingleInstances {
    <T extends Object> T getGlobalInstance();
}
