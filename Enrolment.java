public class Enrolment
{
    private String date;
    private Student student;
    private Unit[] units;

    public Enrolment()
    {
        date = "unknown";
        student = new Student();
        units = new Unit[4];
        for (int i = 0; i < units.length; i++)
        {
            units[i] = new Unit();
        }
    }

    public Enrolment(String date, Student student, Unit[] units)
    {
        this.date = date;
        this.student = student;
        this.units = units;
    }

    public void display()
    {
        System.out.println("Enrolment date: " + date);
        student.display();
        System.out.println("");
        for (int index = 0; index < units.length; index++)
        {
            if (units[index] != null && !units[index].getUnitCode().equals("unknown"))
            {
                System.out.println(student.getName() + "'s enrolled unit number " + (index + 1));
                units[index].display();
                System.out.println("");
            }
        }
    }

    public void displayUnits()
    {
        for (int index = 0; index < units.length; index++)
        {
            if (units[index] != null)
            {
                units[index].display();
                System.out.println("");
            }
        }
    }

    public String getDate()
    {
        return date;
    }

    public Unit getSpecificUnit(int index)
    {
        if (index >= units.length || index < 0)
        {
            return null;
        }
        else
        {
            return units[index];
        }
    }

    public Student getStudent()
    {
        return student;
    }

    public Unit[] getUnits()
    {
        return units;
    }

    public int getUnitsSize()
    {
        return units.length;
    }

    public void removeUnit()
    {
        Input input = new Input();
        Validation validation = new Validation();
        String uCode = "unknown";

        this.displayUnits();
        do
        {
            uCode = input.acceptStringInput("Please enter units code to remove: ");
            if (!(!validation.isBlank(uCode) && validation.stringLengthInRange(uCode, 7, 7)))
            {
                System.out.println("Invalid input. Unit code must not be blank and exactly 7 characters.");
            }
        } while (!(!validation.isBlank(uCode) && validation.stringLengthInRange(uCode, 7, 7)));

        boolean removed = false;
        for (int i = 0; i < units.length; i++)
        {
            if (units[i] != null)
            {
                if (units[i].getUnitCode().toLowerCase().equals(uCode.toLowerCase()))
                {
                    System.out.println("\n" + units[i].getUnitCode() + " removed.");
                    units[i] = null;
                    removed = true;
                }
            }
        }

        if (removed == false)
            System.out.println("\nUnit does not exist or already removed.");
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public void setSpecificUnits(int index, String uCode, String uDesc, int cPoints)
    {
        if (index < units.length && index >= 0)
        {
            units[index].setUnitCode(uCode);
            units[index].setUnitDescription(uDesc);
            units[index].setCreditPoints(cPoints);
        }
    }

    public void setStudent(Student student)
    {
        this.student = student;
    }

    public void setUnits(Unit[] units)
    {
        this.units = units;
    }

    public void setUnitsSize(int size)
    {
        this.units = new Unit[size];
        for (int index = 0; index < size; index++)
        {
            units[index] = new Unit();
        }
    }

    public String toString()
    {
        return "date: " + date + "\n"+ student.toString() + "\n" + 
        units.toString();
    }
}
