import java.util.Scanner;

public class Validation{

    public Validation()
    {

    }

    public boolean isBlank(String string)
    {
        if (string.trim().length() == 0)
            return true;
        else
            return false;
    }

    public boolean stringLengthInRange(String string, int min, int max)
    {
        if (string.trim().length() >= min && string.trim().length() <= max)
            return true;
        else
            return false;
    }

    public boolean isInt(String string)
    {
        for(int i =0; i < string.length(); i++)
        {
            if (string.charAt(i) < '0' || string.charAt(i) > '9')
                return false;
        }
        return true;
    }
}
