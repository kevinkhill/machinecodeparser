package mcpGUI;

public class arrayStack {
    protected String head[];
    protected int pointer;

    public arrayStack(int capacity){
        head = new String[capacity];
        pointer = -1;
    }
    public boolean isEmpty(){
        return pointer == -1;
    }
    public void push(String i){
        if(pointer+1 < head.length)
            head[++pointer] = i;
    }
    public String pop(){
        if(isEmpty())
            return "";
        return head[pointer--];
    }
    @Override
    public String toString(){
        String tmp = "";
        while(!isEmpty()){
            tmp += pop();
        }
        return tmp;
    }
}