import org.antlr.v4.runtime.*;
import java.nio.file.*;
import java.io.*;

public class Driver {
    public static void main(String[] args) throws Exception {
        // read method to get the source code
        try{
            File f =  new File(args[0]);
            String file = new String(Files.readAllBytes(f.toPath()));
            scanner1 lexer = new scanner1(new ANTLRInputStream(file));

            Token token = lexer.nextToken();
            // while theres more to do
            while(token.getType() != scanner1.EOF) {
                // write the type and value found
                System.out.println("Token Type: " + lexer.getVocabulary().getSymbolicName(token.getType()));
                System.out.println("Value: " + token.getText());
                
                // get more
                token = lexer.nextToken();
            }

        } catch (NullPointerException e) {
            System.out.println("No file found");
        } catch (FileNotFoundException fnfe) {
            System.out.println("No file found");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}