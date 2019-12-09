import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.StringTokenizer; 

public class ProblemaB  
{ 
	private static Persona[] lista;
	private static int N; 
	
	private static LinkedList<int[]> candidatos; //Guarda el indice correspondiente
	
    public static void main(String[] args) throws IOException  
    { 
        BufferedReader reader =  new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        N = Integer.parseInt(reader.readLine());
        
        while (N != 0) {
        		// Crear arreglo a analizar
        	lista = new Persona[N];
        	for (int i = 0; i < N; i++) {
        		st = new StringTokenizer(reader.readLine());
        		lista[i] = new Persona(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        	}
        	
        	Arrays.sort(lista, new ComparatorA());
        	
        	/* Print array
        	System.out.println(N);
        	for (Persona persona:lista) {
        		System.out.println(persona);
        	}
        	*/
        		// Initialize necessary data structures
        	candidatos = new LinkedList<int[]>();
        	
        		/* 
        		 * ****************************************************************
        		 * 			LOOP PRINCIPAL DE CREACION DE RESPUESTA
        		 * ****************************************************************
        		 */
        	for (Persona persAct : lista) {
        		int t = candidatos.size();
        			
        		    // Caso especial: no hay aun candidatos
        		if (candidatos.size() == 0) { 
        			candidatos.add(new int[] {persAct.index});
        			
        		} else { // Hay al menos un candidato
        			
        		    //########## Buscar indice del primer candidato de arriba hacia abajo en el cual podemos anadir el b actual,
        			// es decir, el primero de arriba a abajo donde el b actual sea ESTRICTAMENTE menor
            		
            		ListIterator<int[]> itCandid = candidatos.listIterator(0);
            		int[] candidAct = new int[0];
            		int tCand = 0;
            		int i = 0;
            		
            		while (itCandid.hasNext()) {
            			candidAct = itCandid.next();
            			tCand = candidAct.length;
            			
            			if (B(candidAct[tCand-1]) > persAct.b) break;
            			i += 1;
            		}
            		
            		//########## Buscar, a partir del indice inicial i, el candidato hacia abajo de la misma longitud que es menor lexicograficamente
            		// pues es este el que me interesa promover y tener en mente
            		int imL = i;
            		int[] candidmL = candidAct;
            		ComparatorLex compLex = new ComparatorLex();
            		
            		while (itCandid.hasNext()) {
            			candidAct = itCandid.next(); i += 1; // Avanzar en 1
            			
            			if (candidAct.length < tCand) {
            				candidAct = itCandid.previous(); i -= 1; // Creo que la puedo borrar
            				break;
            			}
            			
            			if (compLex.compare(candidAct, candidmL) < 0) { // Actualizar candidato menor lexicog
            				imL = i;
            				candidmL = candidAct;
            			}
            		} // Sale cuando candidAct es el de mas abajo entre los de esta longitud
            		
            		//########## Crear el nuevo candidato basado en candidmL
            		int[] nuevoCand;
            		
            		nuevoCand = Arrays.copyOf(candidmL, candidmL.length + 1);
            		nuevoCand[candidmL.length + 1] = persAct.index;
            		
            		//########## Buscar indice donde ira el promovido (i.e. el del primero de su longitud vieja)
            		while (itCandid.hasPrevious()) { // Se que empiezo en uno de la longitud vieja
            			candidAct = itCandid.previous(); i -= 1; // Retroceder en 1
            			
            			if (candidAct.length > tCand) {
            				i += 1; // Si me pase, avanzo 1, pero solo me importa el indice
            				break;
            			}
            		}
            		
            		//########## Borrar los elementos de longitud vieja que no me interesan TODO
            		// i.e. todos los elementos desde i inclusivo (i.e. primero de longitud vieja) hasta imL (exclusivo)
            		for (int j = i; j < imL; j++) candidatos.remove(j);  // THIS MIGHT BE EXPENSIVE
            		
            		
            		//######### Borrar los elementos de la longitud nueva que no me interesan  TODO
            		// (menores lexicograficamente, necesariamente tendran menor potencial de extension pues sus colas son mayores o iguales a la que estoy agregando
            		// Mantener i como indice en el cual se agregara el nuevo candidato
            		
            		
            		//######### Agregar candidato nuevo!
            		candidatos.add(nuevoCand);
        		}
        		
        	}
        	
        		// Restart
        	N = Integer.parseInt(reader.readLine());
        }
        
    }
    
    /* MIGHT BE WORTH IT
    private static int ip(int l) {
    	if (inFirstL.containsKey(l)) {
    		return candidatos.size(); 
    	}
    	return inFirstL.get(l);
    }*/

	private static int A(int i) {
    	return lista[i].a;
    }
    
    private static int B(int i) {
    	return lista[i].b;
    }
    
    /*
    private static void updateFirst(Persona persAct, int l, int i) {
    	if (ip(l) == i+1) {
			inFirstL.put(l, i);
			//
		} else {
			//isMinLex.put(l, 
			//		persAct.index < isMinLex.get(l)? persAct.index : isMinLex.get(l) 
			//				);  
		}
    }*/
    
    public static class Persona {

        int index;
        int a;
        int b;

        Persona(int i, int a, int b) {
            index = i;
            this.a = a;
            this.b = b;
        }
        
        @Override
        public String toString() {
        	return "(" + index +", "+ a + ", "+ b + ")";
        }
    }
    
    public static class ComparatorLex implements Comparator<int[]> {
        @Override
        public int compare(int[] i1, int[] i2) {
            if (i1.length == 0) {
            	if (i2.length == 0) return  0;
            	return 1;
            }
            
            if (i2.length == 0) return -1;
            
        	for (int i = 0; i < Math.min(i1.length, i2.length); i++) {
        		if (i1[1] > i2[i]) return 1;
        		else if (i1[1] < i2[i]) return -1;
        	}
        	// Si todos los indices son iguales
        	return -(i1.length - i2.length);
        }
    }
    
    public static class ComparatorA implements Comparator<Persona> {
        @Override
        public int compare(Persona p1, Persona p2) {
            return (p1.a - p2.a);
        }
    }
}
