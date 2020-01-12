class Solution {
    public int mySqrt(int x) 
    {
        if (x == 0) return 0;
        
        if (x < 4) return 1;
        
        int start = 0, end = x, mid;
        
        while (start <= end) 
        {
            mid = start + (end - start) / 2;
            if (mid < x/mid) start = mid + 1;
            else if (mid > x/mid) end = mid - 1;
            else return mid;
        }
        
        return start-1;
    }
}