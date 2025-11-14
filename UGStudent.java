public class UGStudent extends Student
{
    private String major;
    private int year;

    public UGStudent()
    {
        super();
        major = "unknown";
        year = 0;
    }

    public UGStudent(String name, String address, String phoneNo, String email, String major, int year)
    {
        super(name, address, phoneNo, email);
        this.major = major;
        this.year = year;
    }

    public void display()
    {
        System.out.println("Name: " + super.getName());
        System.out.println("Address: " + super.getAddress()); 
        System.out.println("PhoneNo: " + super.getPhoneNo());
        System.out.println("Email: " + super.getEmail());
        System.out.println("Major: " + major);
        System.out.println("Year: " + year);
    }

    public String getMajor()
    {
        return major;
    }

    public String getStudentType()
    {
        return "Undergraduate";
    }

    public int getYear()
    {
        return year;
    }

    public void setMajor(String major)
    {
        this.major = major;
    }

    public void setYear(int year)
    {
        this.year = year;
    }

    public String toString()
    {
        return this.getName() + "," + this.getAddress() + ","
         + this.getPhoneNo() + ","
         + this.getEmail() + "," + major + "," + year;
    }
}
