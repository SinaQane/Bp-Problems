public class P2
{
    public static void main(String[] args) { }
}
interface SearchInterface
{
    int indexOf(String str);
    int indexOf(String str, int fromIndex);
    int lastIndexOf(String str);
    int lastIndexOf(String str, int fromIndex);
}
class NewBuilder implements SearchInterface
{
    String newBuilder;
    public NewBuilder()
    {
        this.newBuilder = "";
    }
    public NewBuilder append(char[] str)
    {
        for (char character : str)
        {
            this.insert(this.newBuilder.length(), character+"");
        }
        return this;
    }
    public NewBuilder append(boolean b)
    {
        this.insert(this.newBuilder.length(), b);
        return this;
    }
    public NewBuilder append(double d)
    {
        this.insert(this.newBuilder.length(), d);
        return this;
    }
    public NewBuilder delete(int start, int end)
    {
        for (int i=start; i<end; i++)
        {
            this.deleteCharAt(start);
        }
        return this;
    }
    public NewBuilder deleteCharAt(int index)
    {
        String backUp = this.newBuilder;
        String partOne = "";
        String partTwo = "";
        try
        {
            partOne = backUp.substring(0, index);
            partTwo = backUp.substring(index+1);
        }
        catch (Exception e)
        {
            partOne = backUp;
        }
        this.newBuilder = "";
        this.newBuilder += partOne;
        this.newBuilder += partTwo;
        return this;
    }
    public NewBuilder insert(int offset, String str)
    {
        String backUp = this.newBuilder;
        String partOne = "";
        String partTwo = "";
        try
        {
            partOne = backUp.substring(0, offset);
            partTwo = backUp.substring(offset);
        }
        catch (StringIndexOutOfBoundsException ignored)
        { }
        this.newBuilder = "";
        this.newBuilder += partOne;
        this.newBuilder += str;
        this.newBuilder += partTwo;
        return this;
    }
    public NewBuilder insert(int offset, boolean b)
    {
        this.insert(offset, Boolean.toString(b));
        return this;
    }
    public NewBuilder insert(int offset, double d)
    {
        String str = "";
        if ((d == Math.floor(d)) && !Double.isInfinite(d))
        {
            str = Integer.toString((int)Math.floor(d));
        }
        else
        {
            str = Double.toString(d);
        }
        this.insert(offset, str);
        return this;
    }
    public NewBuilder reverse()
    {
        String reversed = "";
        for (int i = this.newBuilder.length()-1; i>=0; i--)
        {
            reversed += this.newBuilder.substring(i, i+1);
        }
        this.newBuilder = reversed;
        return this;
    }
    public String toString()
    {
        return this.newBuilder;
    }

    @Override
    public int indexOf(String str)
    {
        return this.indexOf(str, 0);
    }

    @Override
    public int indexOf(String str, int fromIndex)
    {
        int answer = -1;
        for (int i=fromIndex; i<=this.newBuilder.length()-str.length(); i++)
        {
            if (str.equals(this.newBuilder.substring(i, i+str.length())))
            {
                answer = i;
                break;
            }
        }
        return answer;
    }

    @Override
    public int lastIndexOf(String str)
    {
        return this.lastIndexOf(str, 0);
    }

    @Override
    public int lastIndexOf(String str, int fromIndex)
    {
        int answer = -1;
        for (int i=fromIndex; i<=this.newBuilder.length()-str.length(); i++)
        {
            if (str.equals(this.newBuilder.substring(i, i+str.length())))
            {
                answer = i;
            }
        }
        return answer;
    }
}
