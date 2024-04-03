import java.util.Stack;
import java.io.*;
import java.util.*;
import java.io.File;
public class SymbolBalance implements SymbolBalanceInterface {

private File file;
private boolean ignore;
/*public SymbolBalance(){
    file =null;
    Stack<Character> stack = new Stack<>();
    ignore= false;
}*/

public void setFile(String filename){
   
    file = new File(filename);
    ignore = false;

}
public BalanceError checkFile(){
    try{
    Scanner scan = new Scanner(file);
    Stack<Character> stack = new Stack<>();
    int lineNumber=0;

    while(scan.hasNextLine()){
    lineNumber++;
    String line = scan.nextLine();

    for(int i=0; i< line.length(); i++){
        //char c= line.charAt(i);
        if(ignore == false){ // if i am not inside a string
            if (openSymbol(line.charAt(i))){
                stack.push(line.charAt(i));
            }else if(closedSymbol(line.charAt(i))){ 
                if(stack.size()>0){
                    char popped= stack.pop();
                    if(popped == '(' && line.charAt(i) != ')' ||popped == '{' && line.charAt(i) != '}'|| popped == '[' && line.charAt(i) != ']' ){
                        char x= line.charAt(i);
                        MismatchError notMatch = new MismatchError(lineNumber, x, popped);
                        return notMatch;
                      
                    }
                }else if(stack.size()==0){
                    EmptyStackError noOpenSymbol = new EmptyStackError(lineNumber);
                    return noOpenSymbol;
                }

            }
            else if(line.charAt(i) == '"' && line.charAt(i) != stack.peek()){
                ignore=true;
                stack.push(line.charAt(i));
            }
            else if(line.charAt(i)== '/' && i < (line.length()-1) && line.charAt(i+1)=='*'){
                ignore= true;
                stack.push(line.charAt(i+1));
            }
        }
        else if(line.charAt(i) == '"' && !(stack.isEmpty()) && line.charAt(i) == stack.peek()){
                System.out.println("yooooo");
                ignore=false;
                stack.pop();
            }
        else if(line.charAt(i)== '*' && i < (line.length()-1) && line.charAt(i+1)=='/' && !(stack.isEmpty()) && line.charAt(i) == stack.peek()){
            ignore= false;
            char h = stack.pop();
            if(line.charAt(i)!= h){
                char y = line.charAt(i);
               EmptyStackError noOpenSymbol1 = new EmptyStackError(lineNumber);
                return noOpenSymbol1;
                
            }
        }
    }

   } 
    if(!scan.hasNextLine() && stack.size()>0){
        char top_stack = stack.peek();
        int size = stack.size();
        NonEmptyStackError closeSymboll = new NonEmptyStackError(top_stack, size);
        if (closeSymboll!=null){
            return closeSymboll;
        }
    }
    }
    catch (Exception e){
        e.printStackTrace();
    }

    return null;
      
}
    public boolean closedSymbol(Character d){
         if (d== ']' || d== ')' || d== '}' ){
        return true;
        }else{
            return false;
        }
    }   
    public boolean openSymbol(Character a){
        if (a== '[' || a== '(' || a== '{' ){
        return true;
        }else{
            return false;
        }
        
    }
    //my tester method 
/*public static void main(String args[]){
    SymbolBalance sym =new SymbolBalance();
    sym.setFile("TestFiles/Test6.java");
    BalanceError hi = sym.checkFile();
    if (hi == null){
        System.out.println("null");
    }
    else{
        System.out.println(hi.toString());
    }
}*/
}

