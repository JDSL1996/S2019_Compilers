import org.antlr.v4.runtime.*;
import java.nio.file.*;
import java.io.*;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) throws Exception {
        // read method to get the source code
        File f =  new File(args[0]);
        String file = new String(Files.readAllBytes(f.toPath()));
        scanner lexer = new scanner(new ANTLRInputStream(file));
        
        // printer for output
        PrintWriter writer = new PrintWriter("out.txt", "UTF-8");
        try{
            Token token = lexer.nextToken();
            // while theres more to do
            while(token.getType() != scanner.EOF) {
                // write the type and value found
                writer.println("Token Type: " + lexer.getVocabulary().getSymbolicName(token.getType()));
                writer.println("Value: " + token.getText());
                
                // get more
                token = lexer.nextToken();
            }
        } catch (Exception e) {
                //TODO: handle exception
        } 
        // always close
        finally{
            writer.close();
        }
    }
}