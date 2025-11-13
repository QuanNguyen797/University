public class PGStudent extends Student
{
    private String program;
    private String advisor;

    public PGStudent()
    {
        super();
        program = "unknown";
        advisor = "unknown";
    }

    public PGStudent(String name, String address, String phoneNo, String email, String program, String advisor)
    {
        super(name, address, phoneNo, email);
        this.program = program;
        this.advisor = advisor;
    }

    public void display()
    {
        System.out.println(this.getName() + "," + this.getAddress() + "," 
                + this.getPhoneNo() + "," + this.getEmail() + "," 
                + program + "," + advisor);
    }

    public String getMajor()
    {
        return program;
    }

    public String getStudentType()
    {
        return "Graduate";
    }

    public String getYear()
    {
        return advisor;
    }

    public void setMajor(String program)
    {
        this.program = program;
    }

    public void setYear(String advisor)
    {
        this.advisor = advisor;
    }

    public String toString()
    {
        return this.getName() + "," + this.getAddress() + ","
         + this.getPhoneNo() + ","
         + this.getEmail() + "," + program + "," + advisor;
    }
}

