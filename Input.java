import java.util.Scanner;

public class Input
{

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
        Scanner console = new Scanner(System.in);
        double input = Double.parseDouble(console.nextLine());
        return input;
    }

    public int acceptIntInput(String displayMessage)
    {
        System.out.println(displayMessage);
        Scanner console = new Scanner(System.in);
        int input = Integer.parseInt(console.nextLine());
        return input;
    }

    public String acceptStringInput(String displayMessage)
    {
        System.out.println(displayMessage);
        Scanner console = new Scanner(System.in);
        String input = console.nextLine();
        return input;
    }

}
