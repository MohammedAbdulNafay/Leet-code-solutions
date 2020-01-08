class Solution {
    public int maxPoints(int[][] points) 
    {
        int result = 0;
        Map<Integer, Map<Integer, Integer>> pointsMap = new HashMap();
        for(int i = 0; i < points.length; i++)
        {
            pointsMap.clear();
            int max = 0, overlapped = 0;
            for(int j = i+1; j < points.length; j++)
            {
                int x_diff = points[i][0] - points[j][0];
                int y_diff = points[i][1] - points[j][1];
                
                if(x_diff == 0 && y_diff == 0)
                {
                    overlapped++;
                    continue;
                }
                    
                int gcd = GCDEvaluate(x_diff, y_diff);
                
                if(gcd != 0)
                {
                    x_diff /= gcd;
                    y_diff /= gcd;
                }
                
                if(pointsMap.containsKey(x_diff))
                {
                    if(pointsMap.get(x_diff).containsKey(y_diff))
                    {
                        pointsMap.get(x_diff).put(y_diff, pointsMap.get(x_diff).get(y_diff)+1);
                    }
                    else
                    {
                        pointsMap.get(x_diff).put(y_diff, 1);
                    }
                }
                else
                {
                    Map<Integer, Integer> y_map = new HashMap();
                    y_map.put(y_diff, 1);
                    pointsMap.put(x_diff, y_map);
                }
                
                max = (max > pointsMap.get(x_diff).get(y_diff)) ? max : pointsMap.get(x_diff).get(y_diff);
            }
            int intermediate = max + overlapped + 1;
            result = (result > intermediate) ? result : intermediate;
        }
        return result;
    }
    
    private int GCDEvaluate(int x, int y)
    {
        if(y == 0)
            return x;
        return GCDEvaluate(y, x%y);
    }
}