import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.nio.file.*;
import java.util.Hashtable;
import java.util.Stack;
import java.io.*;

public class Driver3 {
    public static void main(String[] args) {
        // read method to get the source code
        File f;
        String file;
        scannerLexer lexer;
        boolean done = false;
        try {
            f = new File(args[0]);
            file = new String(Files.readAllBytes(f.toPath()));
            lexer = new scannerLexer(new ANTLRInputStream(file));

            CommonTokenStream cts = new CommonTokenStream(lexer);
            scannerParser parser = new scannerParser(cts);

            parser.setErrorHandler(new BailErrorStrategy());

            Listener listener = new Listener();
            new ParseTreeWalker().walk(listener, parser.program());

            Hashtable<String, String[][]> s = listener.getSymbolTable();
            Stack<String> stack = listener.getStack();

            // while (stack.peek() != null) {
            for (String key : s.keySet()) {

                // String key = stack.poll();
                System.out.println("Symbol table " + key);
                for (String[] entry : s.get(key)) {

                    System.out.print("name " + entry[0] + " type " + entry[1]);
                    if (entry.length == 3) {
                        System.out.println(" value " + entry[3]);
                    }

                    System.out.println();
                }
            }

            // try {
            // parser.program();
            // System.out.println("Accepted");
            // } catch (Exception e) {
            // System.out.println("Not accepted");
            // }
            // // always close
            // finally {
            // done = true;
            // }
        } catch (NullPointerException e) {
            System.out.println("No file found");
        } catch (FileNotFoundException fnfe) {
            System.out.println("No file found");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}