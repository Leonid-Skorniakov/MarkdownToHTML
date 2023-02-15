package md2html;



import java.io.*;
import java.nio.charset.StandardCharsets;

public class Md2Html{
    
    public static void main(String[] args){
        String fileInputName = args[0];
        String fileOutputName = args[1];
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileInputName), StandardCharsets.UTF_8.name()));

            try {
                TextClass resultText = new TextClass();
                String line = reader.readLine();
                StringBuilder buffer = new StringBuilder();

                while (true){
                    if (line == null || line.equals("") ){
                        if (!buffer.isEmpty()) {
                            ParsingLine parsedLine = new ParsingLine(buffer.toString().substring(0, buffer.length() - 1));
                            resultText.append(parsedLine.result());
                            buffer = new StringBuilder();
                        }
                    } else {
                        buffer.append(line).append('\n');
                    }
                    if (line == null) {
                        break;
                    }
                    line = reader.readLine();
                }
                resultText.write(fileOutputName);
            } finally {
                reader.close();
            }
        } catch (IOException e) {
			System.err.println("IOException: problem with opening input file. Error: " + e.getMessage());
		} 
 
    }
}