class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        
        List<List<Integer>> combinationsList = new ArrayList<>();
        List<Integer> intermediateList = new ArrayList<>();
        
        combinationCheckHelper(candidates, 0, 0, target, intermediateList, combinationsList);
        
        return combinationsList;
    }
    
    private void combinationCheckHelper(int[] candidates, int startIndex, int total, int target, List<Integer> intermediateList, List<List<Integer>> combinationsList) {
        
        if (target < total)
            return;
        else if (target == total)
        {
            combinationsList.add(new ArrayList<>(intermediateList));
            return;
        }
        
        for (int index = startIndex; index < candidates.length; index++) 
        {
            intermediateList.add(candidates[index]);
            combinationCheckHelper(candidates, index, total+candidates[index], target, intermediateList, combinationsList);
            intermediateList.remove(intermediateList.size()-1);
            
        }
        
    }
}
