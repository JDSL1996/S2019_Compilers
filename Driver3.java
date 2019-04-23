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

            // make the listener
            Listener listener = new Listener();
            // walk the parsetree using the listener
            new ParseTreeWalker().walk(listener, parser.program());

            // Stack<ASTNode> treeStack = listener.getAST();

            // while (!treeStack.empty()) {
            // ASTNode current = treeStack.pop();

            // System.out.print(current.getLeft().getPay());
            // System.out.print(current.getPay());
            // System.out.print(current.getRight().getPay());

            // System.out.println();
            // }

            // get the table
            // LinkedHashMap<String, String[][]> s = listener.getSymbolTable();
            // String[] keySet = s.keySet().toArray(new String[0]);

            // pretty print out
            // for (int x = 0; x < keySet.length; x++) {
            // System.out.println("Symbol table " + keySet[x]);
            // for (String[] entry : s.get(keySet[x])) {

            // if (entry != null) {
            // for (int y = 0; y < entry.length; y++) {
            // if (y == 0) {
            // System.out.print("name " + entry[y]);
            // } else if (y == 1) {
            // System.out.print(" type " + entry[y]);
            // }
            // }
            // // if it has a value print it out (only strings)
            // if (entry.length == 3) {
            // System.out.print(" value " + entry[2]);
            // }
            // System.out.println();
            // }
            // }
            // System.out.println();
            // }
        } catch (

        NullPointerException e) {
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