class Solution 
{
    public double findMedianSortedArrays(int[] nums1, int[] nums2) 
    {
        int length1 = nums1.length, length2 = nums2.length;
        if(length1 < length2) 
        {
            return findMedian(nums1, length1, nums2, length2);
        }
        else
        {
            return findMedian(nums2, length2, nums1, length1);
        }   
    }
    
    private double findMedian(int[] smallArray, int smallLength, int[] bigArray, int bigLength)
    {
        int min_index = 0, max_index = smallLength, i = 0, j = 0, median = 0;
        
        while (min_index <= max_index)
        {
            i = (min_index + max_index) / 2;
            j = ((smallLength + bigLength + 1) / 2) - i;
            
            if(i < smallLength && j > 0 && bigArray[j-1] > smallArray[i])
            {
                min_index = i + 1;
            }
            else if(i > 0 && j < bigLength && bigArray[j] < smallArray[i-1])
            {
                max_index = i - 1;
            }
            else
            {
                if (i == 0)
                {
                    median = bigArray[j-1];
                }
                else if (j == 0) {
                    median = smallArray[i-1];
                }
                else
                {
                    median = maximum(smallArray[i-1], bigArray[j-1]);
                }
                break;
            }
        }
        
        if ((smallLength + bigLength) % 2 == 1)
        {
            return (double) median;
        }
        
        if(i == smallLength) 
        {
            return (bigArray[j]+median)/2.0;
        }
        
        if(j == bigLength)
        {
            return (smallArray[i]+median)/2.0;
        }
        
        return (median + minimum(smallArray[i],bigArray[j])) / 2.0;
    }
    
    private int maximum(int a, int b)  
    { 
        return a > b ? a : b; 
    } 
      
    private int minimum(int a, int b)  
    { 
        return a < b ? a : b;  
    } 
}
