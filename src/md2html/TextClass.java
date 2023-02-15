package md2html;



import java.io.IOException;
import java.util.*;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class TextClass {
    private List<String> list = new ArrayList<>();

    public void append(String line) {
        list.add(line);
    }

    public void write(String fileOutputName) {
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter
                (new FileOutputStream(fileOutputName),
                StandardCharsets.UTF_8.name())
            );
            try{
                for (String line : list) {
                    writer.write(line + '\n');
                }
            } finally {
                writer.close();
            }
        } catch (IOException e) {
            System.err.println("IOException: problem with opening ouput file. Error: " + e.getMessage());  
        }

        
    }
}
