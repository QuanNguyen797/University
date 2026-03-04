import java.util.ArrayList;

public class University
{
    private final String INPUT_FILE = "students.txt";
    private final String OUTPUT_FILE = "export.txt";
    private ArrayList<Enrolment> enrolments;

    public University()
    {
        enrolments = new ArrayList<>();
    }

    public University(ArrayList<Enrolment> enrolments)
    {
        this.enrolments = enrolments;
    }

    public void addEnrolment(Enrolment enrolment)
    {
        enrolments.add(enrolment);
    }

    public int chooseStudent()
    {
        Input input = new Input();
        int choice = -2;
        for (int i = 0; i < enrolments.size(); i++)
        {
            System.out.print("\n" + (i+1) + "): ");
            enrolments.get(i).getStudent().display();
        }

        do
        {
            try
            {
                choice = input.acceptIntInput("\nPlease select the student number or 0 to cancel: ") - 1;
                if (choice < -1 || choice >= enrolments.size())
                    System.out.println("Error: Choice input out of range. Please enter number in front of the chosen student or 0 to cancel.\n");
            }
            catch (Exception e)
            {
                System.out.println("Error: invalid input. Please enter the integer number in front of the chosen student or 0 to cancel.\n");
            }
        } while (choice < -1 || choice >= enrolments.size());

        return choice;
    }

    public void display()
    {
        for(Enrolment enrolment : enrolments)
        {
            enrolment.display();
        }
    }

    public void enrollUnenroll()
    {
        Input input = new Input();
        Enrolment enrolment = new Enrolment();
        int choice = chooseStudent();
        int actionChoice = -1;
        int unitChoice = 0;
        boolean quit = false;

        if (choice >= 0 && choice < enrolments.size())
        {
            enrolment = enrolments.get(choice);
            while (!quit)
            {
                do
                {
                    try
                    {
                        actionChoice = input.acceptIntInput("\nPlease choose 1 to enroll, 2 to unenroll a unit, 3 to view currently enrolled units or 0 to cancel.");
                        if (actionChoice < 0 || actionChoice > 3)
                            System.out.println("Choice out of range. Please enter 1 to enroll, 2 to unenroll a unit, 3 to view currently enrolled units.");
                    }
                    catch (Exception e)
                    {
                        System.out.println("Invalid choice. Please enter 1 to enroll, 2 to unenroll a unit, 3 to view currently enrolled units.");
                    }
                } while (actionChoice < 0 || actionChoice > 3);

                switch (actionChoice)
                {
                    case 0:
                        quit = true;
                        break;
                    case 1:
                        if (enrolment.hasFreeUnitSlot() > -1)
                        {
                            
                            this.inputUnitDetailOnce(enrolment.hasFreeUnitSlot(), enrolment);
                        }
                        else
                            System.out.println("Maximum enrolled units reached. Please remove a unit to enroll in another.");    
                        break;
                    case 2:
                        unitChoice = enrolment.chooseUnit();
                        if (unitChoice >= 0 && unitChoice < enrolment.getUnitsSize())
                        {
                            System.out.println("\n" + enrolment.getStudent().getName() + "'s " +  enrolment.getUnits()[unitChoice].getUnitCode() + " enrolment succesfully removed\n");
                            enrolment.getUnits()[unitChoice] = new Unit();
                        }
                        break;
                    case 3:
                        enrolment.viewUnits();
                    default:
                        break;
                }
            }
        }
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
        String studentType = "unknown";
        Student student = null;
        String major = "unknown";
        int year = 0;
        String program = "unknown";
        String advisor = "unknown";

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
            if ((validation.isBlank(address) || !validation.stringLengthInRange(address, 3, 100)))
            {
                System.out.println("Invalid input. Student address must not be blank and between 3 and 100 characters.");
            }
        } while ((validation.isBlank(address) || !validation.stringLengthInRange(address, 3, 100)));

        do
        {
            phone = input.acceptStringInput("Please enter student phone number: ");
            if ((validation.isBlank(phone) || !validation.stringLengthInRange(phone, 8, 10) || !validation.isInt(phone)))
            {
                System.out.println("Invalid input. Student phone number must not be blank, digits only, and 8 to 10 characters long.");
            }
        } while ((validation.isBlank(phone) || !validation.stringLengthInRange(phone, 8, 10) || !validation.isInt(phone)));

        do
        {
            email = input.acceptStringInput("Please enter student email: ");
            if (validation.isBlank(email))
            {
                System.out.println("Invalid input. Student email must not be blank.");
            }
        } while (validation.isBlank(email));

        do
        {
            studentType = input.acceptStringInput("Please enter student type: U (Undergraduate) / P (Postgraduate)");
            if (validation.isBlank(studentType) || !studentType.toLowerCase().equals("u") 
            && !studentType.toLowerCase().equals("p"))
                System.out.println("Invalid input. Please enter U or P");
        } while (validation.isBlank(studentType) || !studentType.toLowerCase().equals("u") 
        && !studentType.toLowerCase().equals("p"));

        switch (studentType.toLowerCase())
        {
            case "u":
                do
                {
                    major = input.acceptStringInput("Please enter student major: ");
                    if (validation.isBlank(major))
                        System.out.println("Error: Major cannot be blank.");
                } while (validation.isBlank(major));

                do
                {
                    try
                    {
                        year = input.acceptIntInput("Please enter current student year");
                        if (year <= 0)
                            System.out.println("Year must be larger than 0");
                    }
                    catch (Exception e)
                    {
                        System.out.println("Error: Please enter an integer number larger than 0");
                    }
                } while (year <= 0);
                student = new UGStudent(name, address, phone, email, major, year);
                break;
            case "p":
                do
                {
                    program = input.acceptStringInput("Please enter student program: ");
                    if (validation.isBlank(program))
                        System.out.println("Error: Program cannot be blank.");
                } while (validation.isBlank(program));

                do
                {
                    advisor = input.acceptStringInput("Please enter student advisor: ");
                    if (validation.isBlank(advisor))
                        System.out.println("Error: Advisor cannot be blank.");
                } while (validation.isBlank(advisor));

                student = new PGStudent(name, address, phone, email, program, advisor);
                break;
            default:
                break;
        }

        if (student != null)
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
                if (unitNumber < 1 || unitNumber > 4)
                    System.out.println("Please enter an integer between 1 and 4.");
            }
            catch (Exception e)
            {
                System.out.println("Please enter an integer between 1 and 4.");
            }
        } while (unitNumber < 1 || unitNumber > 4);

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
        int cPoints = -1;
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
                try
                {
                    cPointsNumber = input.acceptStringInput("Please enter credit points: ");
                    cPoints = Integer.parseInt(cPointsNumber);
                    if (cPoints < 0)
                        System.out.println("Credit points must be 0 at minimum");
                }
                catch (Exception e)
                {
                    System.out.println("Invalid input. Please enter an interger number for credit points");
                }
            } while (!validation.isInt(cPointsNumber) || cPoints < 0);
            
            
            enrolment.setSpecificUnits(index, uCode, uDesc, cPoints);
    }

    public static void main(String[] args)
    {
        University university = new University();
        System.out.println("Welcome to University enrolment managing program.");
        university.startProgram();
    }

    public void removeEnrolment(int index)
    {
        enrolments.remove(index);
    }

    public void readFile()
    {
        FileIO fileIO = new FileIO(INPUT_FILE);
        String content = fileIO.readFile();
        if (content.trim().length() == 0)
            return;

        String[] lines = content.split("/");
        int lineNumber = 0;

        for (String line : lines)
        {
            lineNumber++;
            if (line.trim().length() == 0)
                continue;

            try
            {
                Enrolment parsed = parseEnrolmentLine(line);
                if (parsed != null)
                    enrolments.add(parsed);
                else
                    System.out.println("Error reading line " + lineNumber);
            }
            catch (Exception e)
            {
                System.out.println("Error reading line " + lineNumber);
            }   
        }
    }

    Enrolment parseEnrolmentLine(String line)
    {
        String[] items = line.split(",");
        if (items.length < 6)
            return null;

        String date = items[0].trim();
        String name = items[1].trim();
        String address = items[2].trim();
        String phone = items[3].trim();
        String email = items[4].trim();
        String unitField = items[items.length - 1].trim();
        Student student = buildStudentFromItems(items, name, address, phone, email);
        Unit[] units = parseUnits(unitField);

        return new Enrolment(date, student, units);
    }

    private Student buildStudentFromItems(String[] items, String name, String address, String phone, String email)
    {
        if (items.length >= 8)
        {
            String field5 = items[5].trim();
            String field6 = items[6].trim();
            Validation validation = new Validation();
            if (validation.isInt(field6))
                return new UGStudent(name, address, phone, email, field5, Integer.parseInt(field6));
            return new PGStudent(name, address, phone, email, field5, field6);
        }

        return new UGStudent(name, address, phone, email, "Information Technology", 1);
    }

    private Unit[] parseUnits(String unitField)
    {
        Unit[] units = new Unit[4];
        for (int i = 0; i < units.length; i++)
        {
            units[i] = new Unit();
        }

        if (unitField.length() == 0)
            return units;

        String[] unitList = unitField.split(";");
        for (int index = 0; index < unitList.length && index < units.length; index++)
        {
            Unit parsedUnit = parseUnit(unitList[index].trim());
            if (parsedUnit != null)
                units[index] = parsedUnit;
        }
        return units;
    }

    private Unit parseUnit(String unitRecord)
    {
        if (unitRecord.length() == 0)
            return null;

        String[] unitItems = unitRecord.split("-");
        if (unitItems.length != 3)
            return null;

        String uCode = unitItems[0].trim();
        String uDesc = unitItems[1].trim();
        int cPoint = Integer.parseInt(unitItems[2].trim());
        return new Unit(uCode, uDesc, cPoint);
    }

     public void removeEnrolment()
    {
        Enrolment enrolment = new Enrolment();
        System.out.println("\nChoose a student to remove");
        int choice = chooseStudent();
        if (choice >= 0 && choice < enrolments.size())
        {
            enrolment = enrolments.get(choice);
            System.out.println("\n" + enrolment.getStudent().getName() + " removed succesfully.\n");
            enrolments.remove(choice);
        }
        else if (choice == -1)
        {
            System.out.println("\nNo student removed.\n");
        }     
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

        this.readFile();

        do
        {   
            do
            {
                try
                {
                    choiceString = console.acceptStringInput("\nPlease enter your choice: " 
                    + "\n1: Enrol a student\n2: View current students\n3: Unit enrolment and unenrolment\n4: Remove student\n5: Exit the program");
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
                    for (Enrolment studentEnrolment : enrolments)
                    {
                        System.out.println("");
                        studentEnrolment.getStudent().display();
                    }
                    System.out.println("");
                    break;
                case 3:
                    this.enrollUnenroll();
                    break;
                case 4:
                    this.removeEnrolment();
                    break;
                case 5:
                    quit = true;
                    this.writeFile();
                    System.out.println("\nGoodbye!\n");
                    break;
                default:
                    System.out.println("Invalid choice. Please choose from the available menu numbers.\n");
                    break;
            }

        } while (quit == false);   
    }

    public String toString()
    {
        String content = "";
        
        for (int i = 0; i < enrolments.size(); i++)
        {
            content += enrolments.get(i).toString();
            if (i != enrolments.size() -1)
                content += "\n";
        }
        return content;
    }

    public void writeFile()
    {
        FileIO fileIO = new FileIO(OUTPUT_FILE);
        fileIO.writeFile(this.toString());
    }
}
