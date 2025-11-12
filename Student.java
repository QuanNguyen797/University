public class Student
{
    private String name;
    private String address;
    private String phoneNo;
    private String email;

    public Student()
    {
        name = "unknown";
        address = "unknown";
        phoneNo = "unknown";
        email = "unknown";
    }

    public Student(String name, String address, String phoneNo, String email)
    {
        this.name = name;
        this.address = address;
        this.phoneNo = phoneNo;
        this.email = email;
    }

    public void display()
    {
        System.out.println("Name: " + name);
        System.out.println("Address: " + address); 
        System.out.println("PhoneNo: " + phoneNo);
        System.out.println("Email: " + email);
    }

    public String getAddress()
    {
        return address;
    }

    public String getEmail()
    {
        return email;
    }

    public String getName()
    {
        return name;
    }

    public String getPhoneNo()
    {
        return phoneNo;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setPhoneNo(String phoneNo)
    {
        this.phoneNo = phoneNo;
    }

    public String toString()
    {
        return this.getName() + "," + this.getAddress() + "," 
                + this.getPhoneNo() + "," + this.getEmail();
    }
}
