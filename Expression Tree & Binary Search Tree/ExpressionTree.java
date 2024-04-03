import java.util.LinkedList;
//import java.util.Scanner;
//import java.util.Stack;

public class ExpressionTree implements ExpressionTreeInterface{

    //constructor 
   //reads the expression char by char and creates node for each 
    public ExpressionTree(String expression) throws Exception{ 
        String [] expression1 = expression.split(" "); // check how to use split 
        LinkedList<ExpressionNode> stack = new LinkedList<>();

        // create a node for each expression 
        for( int i = 0; i< expression1.length; i ++) {
            ExpressionNode forStack = new ExpressionNode(expression1[i]);// scanner?

            //if element is an operator pop the stack and check for underflow prior 
            if (forStack.isOperator){
                if (stack.size()<2){
                    throw new Exception("Stack underflow: error in expression- should include more integers before operator");
                }
                forStack.right= stack.pop(); //first popped is right child 
                forStack.left= stack.pop(); // second popped is left child
            }
            stack.push(forStack);

        }
        //last one to be pushed onto the stack is the root
        root = stack.pop();

        if(stack.size()!=0){
            throw new Exception ("Stack is not empty: there should be less operands in expression.");
        }
        
    }

    //private internal evaluate method for integer answer of expression-recursive
    private int eval(ExpressionNode root){
        if(root.data.equals("*")){
            int a = eval(root.left) * eval(root.right);
            return a;
        }else if(root.data.equals("+")){
            int b = eval(root.left) + eval(root.right);
            return b;
        }else if(root.data.equals("-")){
            int c = eval(root.left) - eval(root.right);
            return c;
        }else if(root.data.equals("/")){
            int d = eval(root.left) / eval(root.right);
            return d;
        }else if(!root.isOperator){
            int rootInt = Integer.parseInt(root.data);
            return rootInt;
        }
        return 0;

    }
    //prefix expression private internal method: returns string expression -recursive
    private String prefix(ExpressionNode root){
        if (!root.isOperator){
            return root.data;
        }
        if (root == null){
            return "";
        }
        return root.data + " " + prefix(root.left) + " " + prefix(root.right);
    }

    //private internal postfix expression method: return string expression - recursive
    private String postfix(ExpressionNode root){
        if (root == null){
            return "";
        }
        if (!root.isOperator){
            return root.data;
        }
        return prefix(root.left) + " " + prefix(root.right) + " " + root.data;

    }
    //private internal infix expression method
    private String infix(ExpressionNode root){
        if (!root.isOperator){
            return root.data;
        }
        if (root == null){
            return "";
        }
        return "(" + infix(root.left) + " " + root.data + " " + infix(root.right) + ")";
    }

    //the public methods to call for all private expression and evalute methods are below
    public int eval(){
        return eval(root);
    }
    public String infix(){
        return infix(root);
    }
    public String postfix(){
        return postfix(root);
    }
    public String prefix(){
        return prefix(root);
    }

    //to start a tree
    ExpressionNode root;
    public ExpressionTree(){
        root = null;
    } 

    //nested class for the node
    public static class ExpressionNode<AnyType>{

        //constructor for the nodes for when there is a parameter or not given 
        public ExpressionNode(){
            data = null;
            left = null;
            right = null;
            isOperator= false;
        }

        public ExpressionNode(String d, ExpressionNode l, ExpressionNode r){
            data = d;
            left = l;
            right = r;
            isOperator= typeOfChar(data);
        }
        public ExpressionNode(String d){
            data = d;
            left = null;
            right = null;
            isOperator= typeOfChar(data);
        }
        //boolean to see if an operator
        public boolean typeOfChar (String data){
           if (data.equals("*") || data.equals("+") || data.equals("-") || data.equals("/") ){
               return true;
           }else 
           return false;
        }

        public String data;
        public ExpressionNode left;
        public ExpressionNode right;
        public boolean isOperator;
    }


}