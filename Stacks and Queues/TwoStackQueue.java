import java.util.Stack;
public class TwoStackQueue<T> implements TwoStackQueueInterface<T>{

    private Stack<T> s1;
    private Stack<T> s2;

    TwoStackQueue() { //constructor 
        s1 = new Stack<>();
        s2 = new Stack<>();
    } 
    
    public void enqueue(T x){ // constant time method 
        s1.push(x);
    }

    public T dequeue(){
        //we should check if stacks are empty or not to pop
        if(isEmpty()){
            System.out.println("Queue is empty."); // or return null or throw exception
            
        }else if(s2.size()==0){ 
            while(s1.size()!=0){ // or !s1.isEmpty()
                s2.push(s1.pop());
            }
        }
        return s2.pop();

    }

    public int size(){
        int theSize;
        theSize=(s1.size() + s2.size());
        return theSize;
    }

    public boolean isEmpty(){
        return (size()==0);
    }
    //I left/wrote this main method below is to see if the queue works, do not count for the runtime of the queue class
    /*public static void main(String[] args){
        int[] trial ={1,2,3,4,5,6,7,8};
        TwoStackQueue<Integer> trialQ = new TwoStackQueue<Integer>();

        for(int i=1 ; i <trial.length; i++){
            trialQ.enqueue(i);
        }
        while(!trialQ.isEmpty()){
            System.out.println(trialQ.dequeue());
        }
            
        
        
    } */


}