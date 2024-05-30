package in.sachinshinde.stack;

import java.util.Stack;

public class OnlineStockSpan {
    Stack<int[]> st;

    public OnlineStockSpan() {
	st = new Stack<>();
    }
    
    public int next(int price) {
        int res = 1;

        while(!st.empty() && st.peek()[0] <= price)
            res += st.pop()[1];
        
        st.add(new int[]{price, res});

        return res;
    }
    
    public static void main(String[] args) {
	OnlineStockSpan stockSpan = new OnlineStockSpan();
	System.out.println(stockSpan.next(100)==1);
	System.out.println(stockSpan.next(80)==1);
	System.out.println(stockSpan.next(60)==1);
	System.out.println(stockSpan.next(70)==2);
	System.out.println(stockSpan.next(75)==3);
	System.out.println(stockSpan.next(85)==5);
    }
}
