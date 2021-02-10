import java.util.ArrayList;
public class P3
{
    public static void main(String[] args){}
}
class A
{
    protected int firstField;
    private int secondField;
    public final int thirdField = calledTimes;
    private static int calledTimes = 0;
    private static final ArrayList<A> createdObjects = new ArrayList<>();

    private A()
    {
        A.resetSecondFields();
        createdObjects.add(this);
    }

    A(int firstField) {}

    private A(int firstField, int secondField)
    {
        this();
        this.firstField = firstField;
        this.secondField = secondField;
    }

    public void changeFirst(int a)
    {
        firstField = a;
    }

    final protected A getlastObj()
    {
        calledTimes++;
        return createdObjects.size() == 0 ? null : createdObjects.get(createdObjects.size() - 1);
    }

    private static void resetSecondFields()
    {
        for(A a : createdObjects)
            a.secondField = 1;
    }
}

class B extends A
{
    private B()
    {
        super(0);
    }
}
final class C
{
    private C(){}
}
