package in.sachinshinde.recursion;

//	https://leetcode.com/problems/find-the-winner-of-the-circular-game/

class JosephusProblem {
	
	private int getTheWinner(int n, int k){
		int res = 0;
        for (int i = 1; i <= n; ++i)
            res = (res + k) % i;
        return res + 1;
	}
	
	public static void main(String[] args) {
		JosephusProblem josephusProblem = new JosephusProblem();
		System.out.println(josephusProblem.getTheWinner(5, 2));
	}
}
