import java.util.Hashtable;

public class Test {
    static class Animal
    {
        public void eat()
        {
            System.out.println("父类的 eating...");
        }
    }

    static class Bird extends Animal{
        private int aa=10;

        public Bird(int aa) {
            this.aa = aa;
        }
        @Override
        public void eat()
        {
            System.out.println("子类重写的父类的  eatting..."+aa);
        }
        public void fly()
        {
            System.out.println("子类新方法  flying..."+aa);
        }
    }

    public static class Human
    {
        public void sleep()
        {
            System.out.println("父类人类   sleep..");
        }
    }
    static class Male extends Human
    {
        @Override
        public void sleep()
        {
            System.out.println("男人 sleep..");
        }
    }
    static class Female extends Human
    {
        @Override
        public void sleep()
        {
            System.out.println("女人 sleep..");
        }
    }

    /**
     * 描述:
     */
    public static void eat(Animal a){
        a.eat();
        eat2(a);
    }

    public static void eat2(Animal a){
        a.eat();
    }


    public static void sleep(Human h)//方法的参数是父类------！！！
    {
        h.sleep();
    }


    public static void main(String[] args)
    {
        Animal b=new Bird(2); //向上转型
        System.out.println(b instanceof  Bird);
        b.eat();
        eat(b);
        ((Bird) b).fly();
        Bird b2 = ((Bird) b);
        eat(b2);
        b2.fly();
//          b.fly(); //b虽指向子类对象，但此时子类作为向上的代价丢失和父类不同的fly()方法
        sleep(new Male());
        sleep(new Female());//传入的参数是子类-----！！

        AA aa = new BB();
        aa.show();
    }


    abstract static class AA {
        public static void show() {
            System.out.println("aa");
        }
    }

    static class BB extends AA {
        public static void show() {
            System.out.println("bb");
        }
    }
}
