import java.util.ArrayList;

public class University
{
    private ArrayList<Enrolment> enrolments;

    public University()
    {
        enrolments = new ArrayList<Enrolment>();
        enrolments.add(new Enrolment());
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

    public Student inputStudentDetails()
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
            if (!(validation.isBlank(name) && validation.stringLengthInRange(name, 3, 12)))
            {
                System.out.println("Invalid input. Student name must not be blank and between 3 and 12 characters.");
            }
        } while (!(validation.isBlank(name) && validation.stringLengthInRange(name, 3, 12)));

        do
        {
            address = input.acceptStringInput("Please enter student address: ");
            if (!(validation.isBlank(address) && validation.stringLengthInRange(address, 25, 100)))
            {
                System.out.println("Invalid input. Student address must not be blank and between 25 and 100 characters.");
            }
        } while (!(validation.isBlank(address) && validation.stringLengthInRange(address, 25, 100)));

        do
        {
            phone = input.acceptStringInput("Please enter student phone number: ");
            if (!(validation.isBlank(phone) && validation.stringLengthInRange(phone, 10, 10)))
            {
                System.out.println("Invalid input. Student phone number must not be blank and exactly 10 numbers.");
            }
        } while (!(validation.isBlank(phone) && validation.stringLengthInRange(phone, 10, 10)));

        do
        {
            email = input.acceptStringInput("Please enter student email: ");
            if (!validation.isBlank(email))
            {
                System.out.println("Invalid input. Student email must not be blank.");
            }
        } while (!validation.isBlank(email));

        this.student = new Student(name, address, phone, email);
        return student;
    }

    public void inputUnitDetails()
    {
        boolean null_flag = false;
        for (int index = 0; index < units.length; index++)
        {
            if (units[index] == null)
            {
                null_flag = true;
            }
        }

        if (!null_flag)
        {
            System.out.println("Maximum 4 units enrolled. Please remove existing units to enroll in new ones.");
            return;
        }       

        Scanner console = new Scanner(System.in);
        Input input = new Input();
        Validation validation = new Validation();

        String uCode = "unknown";
        String uDesc = "unknown";
        String cPointsNumber = "unknown";

        do
        {
            uCode = input.acceptStringInput("Please enter units code: ");
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
        
        int index = 0;
        while (index < units.length && units[index] != null)
            {
                index++;
            }
        if (index < units.length)
        {
            units[index] = new Unit(uCode, uDesc, cPoints);
        }
        this.displayUnits();
    }

    public static void main(String[] args)
    {
        Input console = new Input();
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
        String choice_string = "unknown";
        Validation validation = new Validation();

        do
        {   
            do
            {
                if(!validation.isInt(choice_string))
                {
                    System.out.println("Invalid choice. Please choose from the available menu numbers.");
                }
                choice_string = console.acceptStringInput("Please enter your choice: \n1: Input student detail\n2: Input unit details\n3: Remove a current unit\n4: Exit");
            } while(!validation.isInt(choice_string));

            choice = Integer.parseInt(choice_string);
            switch(choice)
            {
                case 1: 
                    student = inputStudentDetails();
                    break;
                case 2:
                    this.inputUnitDetails();
                    break;
                case 3:
                    this.removeUnit();
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Invalid choice. Please choose from the available menu numbers.");
                    continue;
            }

        } while (choice != 4);   
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
