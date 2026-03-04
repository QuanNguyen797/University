import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileIO 
{
    private String fileName;

    public FileIO()
    {
        fileName = "unknown";
    }

    public FileIO(String fileName)
    {
        this.fileName = fileName;
    }

    public String getFileName()
    {
        return fileName;
    }

    public String readFile()
    {
        String content = "";
        try (FileReader reader = new FileReader(fileName); Scanner scanner = new Scanner(reader))
        {
            if (scanner.hasNextLine())
            {
                content += scanner.nextLine();
            }
            while (scanner.hasNextLine())
            {
                content += "/" + scanner.nextLine() ;
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Error reading file ");
        }
        catch (IOException e)
        {
            System.out.println("Error closing file.");
        }
        return content;
    }

    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    public void writeFile(String content)
    {
        try (FileWriter writer = new FileWriter(fileName))
        {
            writer.write(content);
        }
        catch (IOException e)
        {
            System.out.println("Error writing to file.");
        }
    }
}
