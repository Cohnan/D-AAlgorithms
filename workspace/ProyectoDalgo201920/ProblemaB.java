import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer; 

public class ProblemaB  
{ 
	private static ArrayList<Persona> lista;
	
    public static void main(String[] args) throws IOException  
    { 
        BufferedReader reader =  new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        
    }
    
    class Persona {

        int index;
        int a;
        int b;

        Persona(int i, int a, int b) {
            index = i;
            this.a = a;
            this.b = b;
        }
    }
    
    class ComparatorLex implements Comparator<Persona> {
        @Override
        public int compare(Persona p1, Persona p2) {
            return p1.index - p2.index;
        }
    }
    
    class ComparatorA implements Comparator<Persona> {
        @Override
        public int compare(Persona p1, Persona p2) {
            return p1.a - p2.a;
        }
    }
    
    class ComparatorB implements Comparator<Persona> {
        @Override
        public int compare(Persona p1, Persona p2) {
            return -(p1.b - p2.b);
        }
    }
}
