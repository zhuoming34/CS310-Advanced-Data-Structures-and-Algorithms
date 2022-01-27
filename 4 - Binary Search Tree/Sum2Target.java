// One sample solution for p6. There could be multiple different approaches.
// The solution is not in a complete class. Just key functions are implemented.
// The solution assumes all elements are non-negative.
// Backtracking technique is used.

public static ArrayList<ArrayList<Integer>> combinationSum(int[] candidates, int target) {           
    ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();  
    ArrayList<Integer> comb = new ArrayList<Integer>(); // for a single combination
    if(candidates == null || candidates.length==0)  
        return res; 
              
    Arrays.sort(candidates); // sort the input array first
    boolean [] visited = new boolean[candidates.length];
    helper(candidates,target, 0, comb, res, visited);  
    return res;  
}  
     
private static void helper(int[] candidates, int target, int start, ArrayList<Integer> comb,   
    ArrayList<ArrayList<Integer>> res, boolean[] visited){  
        // If the target < 0, it means the sum is greater than it and then it's time to stop
        if(target < 0)  
            return;  
        // If the target == 0, combination found
        if(target == 0){  
            res.add(new ArrayList<Integer>(comb));  
            return;  
        }
         
        for(int i = start; i < candidates.length; i++){
            if(!visited[i]){
                // check to prevent duplicates
                if(i>0 && candidates[i] == candidates[i-1] && visited[i-1]==false)
                    continue;  
                comb.add(candidates[i]);
                visited[i] = true;
                // update target for the next recursive call
                int newtarget = target - candidates[i];
                helper(candidates, newtarget, i+1, comb, res,visited);  
                visited[i] = false;
                comb.remove(comb.size() - 1);  // backtrack
             }
         }  
     } 