import java.util.Scanner;

public class Input
{
    private static final Scanner CONSOLE = new Scanner(System.in);

    public Input()
    {

    }

    public char acceptCharInput(String displayMessage, int position)
    {
        char input = (acceptStringInput(displayMessage)).charAt(position);
        return input;
    }

    public double acceptDoubleInput(String displayMessage)
    {
        System.out.println(displayMessage);
        double input = Double.parseDouble(CONSOLE.nextLine());
        return input;
    }

    public int acceptIntInput(String displayMessage)
    {
        System.out.println(displayMessage);
        int input = Integer.parseInt(CONSOLE.nextLine());
        return input;
    }

    public String acceptStringInput(String displayMessage)
    {
        System.out.println(displayMessage);
        String input = CONSOLE.nextLine();
        return input;
    }

}
