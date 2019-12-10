/******************************************************************************

                            Online Java Compiler.
                Code, Compile, Run and Debug java program online.
Write your code in this editor and press "Run" button to execute it.

*******************************************************************************/
import java.util.Collections;
import java.util.Arrays;

public class Main
{
	public static void main(String[] args) {
		System.out.println("Hello World");
		
		String text = "acaaacatat";
		
		int[] POS = new int[text.length()];         //Suffix array (output)
        int[] PRM = new int[text.length()];         //Inverse Suffix Array (PRM[POS[i]] = i)
        Boolean[] BH = new Boolean[text.length()];  //Points to the leftmost suffix of a H-bucket
        Boolean[] B2H = new Boolean[text.length()]; //Marks moved suffixes; After checking, points to the leftmost suffix in 2H-bucket
        int[] Count = new int[text.length()];       //Internal array
        int[] next = new int[text.length()];        //Internal array
        
        /*
        !!!First stage sort!!!!
        This section sets all arrays according to the first stage
        Sets all the suffixes according to their first letter (Sets POS, PRM and BH arrays)
        */
        long start =System.nanoTime();              //for time measuring
        
        java.util.HashMap<Character, Integer> Alphabet = new java.util.HashMap<Character, Integer>();
        int t = 0;
        for (char x : text.toCharArray())
            {
                if (Alphabet.keySet().contains(x))
                    {
                        int tmp=Alphabet.get(x);
                        tmp++;     
                        Alphabet.put(x, tmp);
                    }
                else
                    {
                        Alphabet.put(x, 1);
                    }
                POS[t] = -1;
                BH[t] = false;
                B2H[t] = false;
                Count[t] = 0;
                t++;
            }
        
        java.util.ArrayList<Character> Letters = new java.util.ArrayList(Alphabet.keySet());
        Collections.sort(Letters);
        
        int letterFirstposition;
        int letterOffset;
        int letterposition = 0;
        BH[0] = true;
        
        for (char x : text.toCharArray())
        {
            letterFirstposition = 0;
            for (char y : Letters)
            {
                if (x != y)
                {
                    int tmp = Alphabet.get(y);
                    letterFirstposition += tmp;
                }
                else
                {
                    break;
                }
            }
            
            letterOffset = letterFirstposition;
            BH[letterFirstposition] = true;
            while (POS[letterOffset] != -1)
            {
                letterOffset++;
            }
            
            POS[letterOffset] = letterposition;
            PRM[letterposition] = letterOffset;
            letterOffset = 0;
            letterposition++;
        }
        /*
        End of first stage sort!
        */
        
        /*
        Algorithm by H=1,2,4,8,16...H<N
        POS array and time elapsed are written in console after the sort is done
        */
        for (int h=1; h<text.length(); h=h*2)
        {
            int buckets = 0;
            for (int i=0, j; i<text.length(); i=j)
            {
                j=i+1;
                while(j<text.length() && !BH[j])
                    j++;
                next[i]=j;
                buckets++;
            }
            
            if (buckets == text.length())               //Algorithm is done after every suffix is in its own bucket
                break;
            
            for (int i=0; i<text.length(); i=next[i])   //Sets PRM array
            {
                Count[i]=0;
                for(int j=i; j<next[i]; j++)
                    PRM[POS[j]]=i;
            }
            
            Count[PRM[text.length() - h]]++;
            B2H[PRM[text.length() - h]] = true;
            
            for (int i=0; i<text.length(); i=next[i])   //Scan all buckets and update PRM, Count and B2H arrays
            {
                for (int j=i; j<next[i]; j++)           //Update arrays
                {
                    int s = POS[j] -h;
                    if (s>=0)
                    {
                        int tmp = PRM[s];
                        PRM[s] = tmp + Count[tmp]++;
                        B2H[PRM[s]] = true;
                    }
                }
                for (int j=i; j<next[i]; j++)           //Reset B2H array such that only the leftmost of them in each
                {                                       //2H-bucket is set to 1, and rest are reset to 0
                    int s = POS[j] - h;
                    if (s>=0 && B2H[PRM[s]])
                    {
                        for (int k=PRM[s] + 1; k<FindNextBH(k, BH, text.length()); k++)
                            B2H[k]=false;
                    }
                }
            }
            
            for (int i=0; i<text.length(); i++)         //Updating POS and BH arrays
            {
                POS[PRM[i]]=i;
                BH[i] |= B2H[i];
            }
        }
        
        for (int i=0; i<text.length(); i++)             //Updating PRM array
        {
            PRM[POS[i]]=i;
        }
        
        
        
        //###########################################################
        System.out.println(Arrays.toString(POS));
	}
	
	/*
    Finds position of next true value in BH array starting from x.
    If none is found, returns last possible position in BH array
    */
    static int FindNextBH(int x, Boolean[] BH, int end)
    {
        for (int i=x; i<end; i++)
        {
            if (BH[i] == true)
                return i;
        }
        return end;
    }
}
