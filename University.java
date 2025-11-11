import java.util.ArrayList;

public class University
{
    private ArrayList<Enrolment> enrolments;

    public University()
    {
        enrolments = new ArrayList<Enrolment>();
    }

    public University(ArrayList<Enrolment> enrolments)
    {
        this.enrolments = enrolments;
    }

    public void display()
    {
        for(Enrolment enrolment : enrolments)
        {
            enrolment.display();
        }
    }

    public void addEnrolment(Enrolment enrolment)
    {
        enrolments.add(enrolment);
    }

    public ArrayList<Enrolment> getEnrolments()
    {
        return enrolments;
    }

    public int getEnrolmentSize()
    {
        return enrolments.size();
    }

    public Enrolment getSpecificEnrolment(int index)
    {
        return enrolments.get(index);
    }


    public void inputStudentDetails(Enrolment enrolment)
    {
        Input input = new Input();
        Validation validation = new Validation();
        String name = "unknown";
        String address = "unknown";
        String phone = "unknown";
        String email = "uknown";

        do
        {
            name = input.acceptStringInput("Please enter student name: ");
            if ((validation.isBlank(name) || !validation.stringLengthInRange(name, 3, 12)))
            {
                System.out.println("Invalid input. Student name must not be blank and between 3 and 12 characters.");
            }
        } while ((validation.isBlank(name) || !validation.stringLengthInRange(name, 3, 12)));

        do
        {
            address = input.acceptStringInput("Please enter student address: ");
            if ((validation.isBlank(address) || !validation.stringLengthInRange(address, 25, 100)))
            {
                System.out.println("Invalid input. Student address must not be blank and between 25 and 100 characters.");
            }
        } while ((validation.isBlank(address) || !validation.stringLengthInRange(address, 25, 100)));

        do
        {
            phone = input.acceptStringInput("Please enter student phone number: ");
            if ((validation.isBlank(phone) || !validation.stringLengthInRange(phone, 10, 10)))
            {
                System.out.println("Invalid input. Student phone number must not be blank and exactly 10 numbers.");
            }
        } while ((validation.isBlank(phone) || !validation.stringLengthInRange(phone, 10, 10)));

        do
        {
            email = input.acceptStringInput("Please enter student email: ");
            if (validation.isBlank(email))
            {
                System.out.println("Invalid input. Student email must not be blank.");
            }
        } while (validation.isBlank(email));

        Student student = new Student(name, address, phone, email);
        enrolment.setStudent(student);
    }

    public void inputUnitDetails(Enrolment enrolment)
    {
        Input console = new Input();
        int unitNumber = -1;

        do
        {
            try
            {
                unitNumber = console.acceptIntInput("Please enter number of units: ");
                if (unitNumber < 0 || unitNumber > 4)
                    System.out.println("Please enter an integer larger than 0 and less than 4");
            }
            catch (Exception e)
            {
                System.out.println("Please enter an integer larger than 0 and less than 4");
            }
        } while (unitNumber < 0 || unitNumber > 4);

        for (int index = 0; index < unitNumber; index++)
        {
            inputUnitDetailOnce(index, enrolment);
        }
    }

    public void inputUnitDetailOnce(int index, Enrolment enrolment)
    {
        Input input = new Input();
        Validation validation = new Validation();
        String uCode = "unknown";
        String uDesc = "unknown";
        String cPointsNumber = "unknown";
        do
            {
                uCode = input.acceptStringInput("\nPlease enter units code: ");
                if (!(!validation.isBlank(uCode) && validation.stringLengthInRange(uCode, 7, 7)))
                {
                    System.out.println("Invalid input. Unit code must not be blank and exactly 7 characters.");
                }
            } while (!(!validation.isBlank(uCode) && validation.stringLengthInRange(uCode, 7, 7)));

            do
            {
                uDesc = input.acceptStringInput("Please enter units description: ");
                if (!(!validation.isBlank(uDesc) && validation.stringLengthInRange(uDesc, 1, 250)))
                {
                    System.out.println("Invalid input. Unit description must not be blank and between 1 and 250 characters.");
                }
            } while (!(!validation.isBlank(uDesc) && validation.stringLengthInRange(uDesc, 1, 250)));

            do
            {
                cPointsNumber = input.acceptStringInput("Please enter credit points: ");
                if (!validation.isInt(cPointsNumber))
                {
                    System.out.println("Invalid input. Please enter an interger number for credit points");
                }
            } while (!validation.isInt(cPointsNumber));
            int cPoints = Integer.parseInt(cPointsNumber);
            
            enrolment.setSpecificUnits(index, uCode, uDesc, cPoints);
    }

    public static void main(String[] args)
    {
        University university = new University();

        university.startProgram();
        university.display();
    }

    public void removeEnrolment(int index)
    {
        enrolments.remove(index);
    }

    public void setEnrolments(ArrayList<Enrolment> enrolments)
    {
        this.enrolments = enrolments;
    }

    public void setSpecificEnrolment(int index, Enrolment enrolment)
    {
        enrolments.set(index, enrolment);
    }

    public void startProgram()
    {
        Input console = new Input();
        int choice = 0;
        String choiceString = "unknown";
        Validation validation = new Validation();
        boolean quit = false;

        do
        {   
            do
            {
                try
                {
                    choiceString = console.acceptStringInput("Please enter your choice: " 
                + "\n1: Enrol a student\n2: Exit the program");
                    choice = Integer.parseInt(choiceString);
                }
                catch (Exception e)
                {
                    System.out.println("Invalid choice. Please enter an integer.\n");
                }
            } while(validation.isBlank(choiceString) || !validation.isInt(choiceString));

            switch(choice)
            {
                case 1:
                    Enrolment enrolment = new Enrolment();
                    do
                    {
                        choiceString = console.acceptStringInput("Please enter enrolment date: ");
                        if (validation.isBlank(choiceString))
                        {
                            System.out.println("Date cannot be empty");
                        }
                    } while (validation.isBlank(choiceString));
                    enrolment.setDate(choiceString);

                    System.out.println("\nPlease input student details: ");
                    inputStudentDetails(enrolment);

                    System.out.println("\nPlease input unit(s) details: ");
                    inputUnitDetails(enrolment);
                    
                    enrolments.add(enrolment);
                    break;
                case 2:
                    quit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please choose from the available menu numbers.\n");
                    break;
            }

        } while (quit == false);   
    }

    public String toString()
    {
        String enrolmentString = "";
        for (Enrolment enrolment : enrolments)
        {
            enrolmentString += enrolment.toString() + "\n";
        }
        return enrolmentString;
    }
}
