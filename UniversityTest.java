public class UniversityTest
{
    public static void main(String[] args)
    {
        testParseLegacyLineDefaultsToUG();
        testParseExportUGLine();
        testParseExportPGLine();
        testEnrolmentToStringNoTrailingSemicolon();
        testValidationIsInt();
        System.out.println("All tests passed.");
    }

    private static void testParseLegacyLineDefaultsToUG()
    {
        University university = new University();
        String line = "3 Sep 2018,Lucy,Caulfield,12345678,lucy@monash.edu,FIT9131-Java Programming-6;FIT9132-Databases-6";
        Enrolment enrolment = university.parseEnrolmentLine(line);

        assert enrolment != null : "Expected enrolment to parse";
        assert "3 Sep 2018".equals(enrolment.getDate()) : "Date mismatch";
        assert enrolment.getStudent() instanceof UGStudent : "Expected UG student for legacy line";
        UGStudent student = (UGStudent) enrolment.getStudent();
        assert "Lucy".equals(student.getName()) : "Name mismatch";
        assert "Information Technology".equals(student.getMajor()) : "Default major mismatch";
        assert student.getYear() == 1 : "Default year mismatch";
        assert "FIT9131".equals(enrolment.getSpecificUnit(0).getUnitCode()) : "First unit mismatch";
        assert "FIT9132".equals(enrolment.getSpecificUnit(1).getUnitCode()) : "Second unit mismatch";
    }

    private static void testParseExportUGLine()
    {
        University university = new University();
        String line = "4 Mar 2026,Alex,Clayton,0411223344,alex@uni.edu,Computer Science,2,FIT9131-Java Programming-6";
        Enrolment enrolment = university.parseEnrolmentLine(line);

        assert enrolment != null : "Expected enrolment to parse";
        assert enrolment.getStudent() instanceof UGStudent : "Expected UG student";
        UGStudent student = (UGStudent) enrolment.getStudent();
        assert "Computer Science".equals(student.getMajor()) : "Major mismatch";
        assert student.getYear() == 2 : "Year mismatch";
        assert "FIT9131".equals(enrolment.getSpecificUnit(0).getUnitCode()) : "Unit mismatch";
    }

    private static void testParseExportPGLine()
    {
        University university = new University();
        String line = "4 Mar 2026,Sam,Richmond,0400000000,sam@uni.edu,Data Science,Dr Lee,FIT9999-Research Methods-12";
        Enrolment enrolment = university.parseEnrolmentLine(line);

        assert enrolment != null : "Expected enrolment to parse";
        assert enrolment.getStudent() instanceof PGStudent : "Expected PG student";
        PGStudent student = (PGStudent) enrolment.getStudent();
        assert "Data Science".equals(student.getMajor()) : "Program mismatch";
        assert "Dr Lee".equals(student.getYear()) : "Advisor mismatch";
    }

    private static void testEnrolmentToStringNoTrailingSemicolon()
    {
        Unit[] units = new Unit[4];
        for (int i = 0; i < units.length; i++)
        {
            units[i] = new Unit();
        }
        units[0] = new Unit("FIT1001", "Unit One", 6);
        units[2] = new Unit("FIT1002", "Unit Two", 6);

        Enrolment enrolment = new Enrolment("1 Jan 2026", new UGStudent("Ava", "Caulfield", "12345678", "ava@uni.edu", "IT", 1), units);
        String line = enrolment.toString();

        assert !line.endsWith(";") : "Export line should not end with semicolon";
        assert line.contains("FIT1001-Unit One-6;FIT1002-Unit Two-6") : "Units should be joined by one semicolon";
    }

    private static void testValidationIsInt()
    {
        Validation validation = new Validation();
        assert validation.isInt("1234") : "Digits should be valid integers";
        assert !validation.isInt("12a4") : "Alpha chars should be invalid";
        assert !validation.isInt("  ") : "Blank should be invalid";
    }
}
