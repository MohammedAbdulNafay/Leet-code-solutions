class Solution {
    public boolean isPerfectSquare(int num) 
    {
        long start = 1, end = num;
        
        while(start < end)
        {
            long mid = (start+end)/2;
            
            long square = mid*mid;
            
            if(square == num) return true;
            
            else if(square > num) end = mid-1;
            
            else start = mid+1;
        }
        
        return start*start == num;
    }
}