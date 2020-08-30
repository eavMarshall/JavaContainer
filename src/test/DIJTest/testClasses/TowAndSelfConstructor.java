package DIJTest.testClasses;

import DIJ.DIJContainer;

public class TowAndSelfConstructor {
    public NoConstructor p1;
    public OneConstructor p2;
    public DIJContainer p3;
    public TowAndSelfConstructor(NoConstructor p1, OneConstructor p2, DIJContainer p3) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }
}
