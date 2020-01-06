import java.util.regex.*;
class Solution {
    private Pattern pattern = Pattern.compile("[a-zA-Z0-9]+");
    public int strongPasswordChecker(String s) 
    {
        if(s == null)
            return 6;
        int len = s.length();
        
        if(len <= 1)
            return (6 - len);
        
        boolean upper = false, lower = false, num = false;
        
        int remove, add, required = 0, repeatremoval = 0, repeated = 1, consecutive = 0, presentConsecutive = 1, equalConsecutive = 0, threeConsecutive = 0;
        
        add = (len < 6) ? (6 - len) : 0;
        
        remove = (len > 20) ? (len - 20) : 0;
        
        if(!pattern.matcher(s).matches())
        {
            if(remove > 0)
                return (6 + remove);
            else
                return add;
        }
        
        char presentChar, previousChar;
        
        for(int i = 0; i < len; i++)
        {
            presentChar = s.charAt(i);
            previousChar = (i > 0) ? s.charAt(i-1) : '\0';
            
            if(presentChar == previousChar)
            {
                if(repeated < 3)
                    repeated++;
                else
                {
                    repeatremoval++;
                    repeated = 1;
                }
                presentConsecutive++;
            }
            else
            {
                if(repeated >= 3)
                    repeatremoval++;
                
                if(repeated == 3)
                    threeConsecutive++;
                
                if(consecutive < presentConsecutive)
                    consecutive = presentConsecutive;
                else if(consecutive == presentConsecutive)
                    equalConsecutive++;
                
                presentConsecutive = 1;
                repeated = 1;
            }
            
            if(Character.isDigit(presentChar))
            {
                num = true;
            }
            
            if(Character.isUpperCase(presentChar))
            {
                upper = true;
            }
            
            if(Character.isLowerCase(presentChar))
            {
                lower = true;
            }
        }
        
        consecutive = (consecutive > presentConsecutive) ? consecutive : presentConsecutive;
        
        if(repeated >= 3)
            repeatremoval++;
        
        if(!num)
            required++;
        
        if(!upper)
            required++;
        
        if(!lower)
            required++;
        
        if(add == 0 && remove == 0)
        {
            if(repeatremoval == 0)
                return required;
            else
                return repeatremoval;
        }
        
        
        if(add > 0)
            return (add > required) ? ((add > repeatremoval) ? add : repeatremoval) : ((repeatremoval > required) ? repeatremoval : required);
        
        if(remove > 0)
        {
            int result = 0;
            
            while(threeConsecutive > 0 && remove > 0 && repeatremoval > 0)
            {
                result++;
                remove--;
                repeatremoval--;
                threeConsecutive--;
            }
            
            if(consecutive > 5)
            {
                if(remove > 1)
                    repeatremoval -= (remove/2);
                else if(remove == 1)
                    repeatremoval--;
                
                if(equalConsecutive > 0 || required > 0)
                    return (required > repeatremoval) ? (result + remove + required) : (result + remove + repeatremoval);
                else
                    return (result + remove + repeatremoval);
            }
            else if(consecutive > 3)
            {
                if(remove > 1 && (consecutive < 5 || (consecutive == 5 && repeatremoval != 1)))
                    repeatremoval -= (remove/2);
                
                return (required > repeatremoval) ? (result + remove + required) : (result + remove + repeatremoval);
            }
            else
                return ((required + remove) > repeatremoval) ? (result + required + remove) : result + repeatremoval;
        }
            
        
        return 0;
    }
}