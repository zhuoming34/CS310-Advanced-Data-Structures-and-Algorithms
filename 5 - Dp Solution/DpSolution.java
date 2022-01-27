//Implement three functions

public class DpSolution{

    int uniquePaths(int m, int n) {
        if(m <= 0 || n<= 0) return 0;
        return recur(m-1,n-1); // counting backward
    }
    int recur(int i, int j) {
        if (i < 0 || j < 0) return 0;
        if (i == 0 || j == 0) return 1;
        return recur(i-1,j) + recur(i,j-1);
    }
    
    int uniquePathsDP(int m, int n) {
		int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) dp[i][0] = 1; // initialize the first col
        for (int j = 0; j < n; j++) dp[0][j] = 1; // initialize the first row
        for (int i = 1; i < m; i++)
            for (int j = 1; j < n; j++) dp[i][j] = dp[i-1][j] + dp[i][j-1];
    return dp[m-1][n-1];
    }
    
    int uniquePathsWithObstacles(int[][] A) {
        if (A[0][0] == 1) return 0; // obstacle at starting pos, cannot start
        int m = A.length;
        int n = A[0].length;
        int[][] dp = new int[m][n]; // default 0's
        dp[0][0] = 1;
        
        for (int i = 1; i < m; i++) 
	        if (A[i][0] == 0) dp[i][0] = dp[i-1][0];
        for (int j = 1; j < n; j++)  
            if (A[0][j] == 0) dp[0][j] = dp[0][j-1];
        for (int i = 1; i < m; i++)
            for (int j = 1; j < n; j++)
            	if (A[i][j] == 0) dp[i][j] = dp[i-1][j] + dp[i][j-1];
        return dp[m-1][n-1];
    }

    public static void main(String []args){
        DpSolution test = new DpSolution();
        int n = 3;
        System.out.println("n = " + n);
        System.out.println("Test uniquePaths");
        System.out.println(test.uniquePaths(n,n));
        System.out.println("Test uniquePathsDP");
        System.out.println(test.uniquePathsDP(n,n));
        System.out.println("Test uniquePathsWithObstacles");
        int[][] A1 = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        System.out.println(test.uniquePathsWithObstacles(A1));
        int[][] A2 = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        System.out.println(test.uniquePathsWithObstacles(A2));
        
        System.out.println("\ncheck results among methods:");
        int[][] B1 = {{0,0,0,0},
                      {0,0,0,0},
                      {0,0,0,0},
                      {0,0,0,0}};
        int M = B1.length, N = B1[0].length;
        int n1 = test.uniquePaths(M,N);
        int n2 = test.uniquePathsDP(M,N);
        int n3 = test.uniquePathsWithObstacles(B1);
        System.out.println("\nm=" + M + " and n=" + N + ":");
        System.out.println("Naive recursive way:  " + n1);
        System.out.println("DP without obstacles: " + n2);
        System.out.println("DP with obstacles:    " + n3);
        int[][] B2 = {{0,0,0,0},
                      {0,1,0,0},
                      {0,0,1,0},
                      {0,0,0,0}};
        int n4 = test.uniquePathsWithObstacles(B2);
        System.out.println("DP with obstacles @B2: " + n4);
        
    }
}