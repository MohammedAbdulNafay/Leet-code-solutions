import java.util.*;

class Solution {
    public boolean isNumber(String s) 
    {
        if(s == null)
        {
            return false;
        }
        
        s = s.trim();
        int len = s.length();
        
        if(len == 0)
        {
            return false;
        }
        
        char first = s.charAt(0);
        
        if(len == 1 && !Character.isDigit(first))
        {
            return false;
        }
        
        if(!Character.isDigit(first) && first != '+' && first != '-' && first != '.')
        {
            return false;
        }
        
        boolean dotOrEFlag = false, dot = false, e = false, plusMinus = false;
        
        char num;
        for(int i = 0; i < len; i++)
        {
            num = s.charAt(i);
            
            if(!Character.isDigit(num) && num != '.' && num != 'e' && num != '+' && num != '-')
            {
                return false;
            }
            
            if(num == '.')
            {
                if(dot)
                {
                    return false;
                }
                else
                {
                    dot = true;
                }
                
                if(dotOrEFlag)
                {
                    return false;
                }
                else
                {
                    dotOrEFlag = true;
                }
                
                if(i+1 >= len)
                {
                    if(Character.isDigit(s.charAt(i-1)))
                    {   
                        return true;
                    }
                    else
                    {
                        return false;
                    }
                }
                
                if(!Character.isDigit(s.charAt(i+1)) && (s.charAt(i+1) != 'e' || (s.charAt(i+1) == 'e' && i == 0)))
                {
                    return false;
                }
            }
            
            
            if(num == 'e')
            {
                dotOrEFlag = true;
                
                if(e)
                {
                    return false;
                }
                else
                {
                    e = true;
                }
                
                if(!Character.isDigit(s.charAt(i-1)) && s.charAt(i-1) != '.')
                {
                    return false;
                }
                
                if(i+1 >= len)
                {
                    return false;
                }
                
                if(!Character.isDigit(s.charAt(i+1)) && s.charAt(i+1) != '+' && s.charAt(i+1) != '-')
                {
                    return false;
                }
            }
            
            if(num == '+' || num == '-')
            {
                if(plusMinus)
                {
                    if(i > 0 && i+1 < len && (s.charAt(i-1) == '+' || s.charAt(i+1) == '+' || s.charAt(i-1) == '-' || s.charAt(i+1) == '-'))
                    {
                        return false;
                    }
                    if(!e || (e && i+1 == len))
                    {
                        return false;
                    }
                }
                else
                {
                    if(i > 0 && i+1 < len && Character.isDigit(s.charAt(i-1)) && Character.isDigit(s.charAt(i+1)))
                    {
                        return false;
                    }
                    if(i+1 >= len)
                    {
                        return false;
                    }
                    plusMinus = true;
                }
            }
        }
        
        return true;
    }
}