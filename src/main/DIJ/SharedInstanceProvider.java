package DIJ;

public interface SharedInstanceProvider extends SingleInstances {
    <T extends Object> T getSharedInstance();
}
