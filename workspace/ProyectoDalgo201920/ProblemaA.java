import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStreamReader;
import java.util.StringTokenizer; 

public class ProblemaA  
{ 
	private static int[] arreglo; // Cada entrada menor a 10^6 < 2^32
	private static int[] sufArr;  // Cada entrada menor a N < 10^5 < 2^32
	private static int[] longComPreArr;  // Cada entrada menor a N < 10^5 < 2^32
	
    public static void main(String[] args) throws IOException  
    { 
        BufferedReader reader =  new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st; 
        
        int N = Integer.parseInt(reader.readLine());
        
        while (N != 0) {
        	// Leer arreglo de numeros
        	arreglo = new int[N];
        	st = new StringTokenizer(reader.readLine()); 
        	for (int i = 0; i < N; i++) {
        		arreglo[i] = Integer.parseInt(st.nextToken());
        		System.out.println(arreglo[i]);
        	}
        	
        	// Hallar el Suffix Array
        	
        	// Hallar el Longest Common Prefix Array usando el SA
        	
        	// Hallar el maximo del LCP
        	
        	
        }
  
        
    } 
}