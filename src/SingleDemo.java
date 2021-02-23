import java.util.HashMap;

public class SingleDemo {

    private static SingleDemo sSingleDemo;
    private SingleDemo() {
    }

    public static SingleDemo getInstance(){
        if (sSingleDemo == null){
            synchronized ((SingleDemo.class)){
                if (sSingleDemo ==null){
                    sSingleDemo = new SingleDemo();
                }
            }
        }
        return SingleHolder.sSingleDemo;
    }

    private static class SingleHolder{
        private static SingleDemo sSingleDemo = new SingleDemo();
    }

}
