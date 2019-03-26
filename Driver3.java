import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.nio.file.*;
import java.util.LinkedHashMap;
import java.util.Set;
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

            LinkedHashMap<String, String[][]> s = listener.getSymbolTable();
            // Stack<String> stack = listener.getStack();

            // while (stack.peek() != null) {
            String[] keySet = s.keySet().toArray(new String[0]);
            for (int x = 0 ; x<keySet.length ; x++){
                // String key = stack.poll();
                System.out.println("Symbol table " + keySet[x]);
                for (String[] entry: s.get(keySet[x])) {

                    if (entry != null) {
                        for(int y = 0;y<entry.length;y++){
                            if(y == 0){
                                System.out.print("name " + entry[y]);
                            }else if(y==1){
                                System.out.print(" type " + entry[y]);
                            }
                        }
                        if (entry.length == 3) {
                            System.out.print(" value " + entry[2]);
                        }
                        System.out.println();
                    }
                }
                System.out.println();
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            System.out.println("No file found");
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
            System.out.println("No file found");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}