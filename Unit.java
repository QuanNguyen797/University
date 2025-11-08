    public class Unit
{
    private String unitCode;
    private String unitDescription;
    private int creditPoints;

    public Unit()
    {
        unitCode = "unknown";
        unitDescription = "unknown";
        creditPoints = 0;
    }

    public Unit(String uCode, String uDescription, int cPoints)
    {
        unitCode = uCode;
        unitDescription = uDescription;
        creditPoints = cPoints;
    }
    
    public Unit(String unitCode)
    {
        this.unitCode = unitCode;
        unitDescription = "unknown";
        creditPoints = 0;
    }

    public void display()
    {
        System.out.println("Unit Code: " + unitCode);
        System.out.println("Unit Description: " + unitDescription);
        System.out.println("Credit Points: " + creditPoints);
    }

    public int getCreditPoints()
    {
        return creditPoints;
    }

    public String getUnitCode()
    {
        return unitCode;
    }

    public String getUnitDescription()
    {
        return unitDescription;
    }

    public void setCreditPoints(int creditPoints)
    {
        this.creditPoints = creditPoints;
    }

    public void setUnitCode(String unitCode)
    {
        this.unitCode = unitCode;
    }

    public void setUnitDescription(String unitDescription)
    {
        this.unitDescription = unitDescription;
    }

     public String toString()
     {
        return "Unit Code: " + unitCode + "\n" + "Unit Description: " + 
            unitDescription + "\n" + "Credit Points: " + creditPoints;
     }   

}
