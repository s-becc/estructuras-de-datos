package tp2;
import java.util.*;
class Extension extends LinkedList<Integer> implements Queue<Integer> {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Metodo para devolver solo los impares
    public Extension soloImpares(Queue<Integer> cola) {
        Extension toReturn = new Extension();
        while (!cola.isEmpty()) {
            int elemento = cola.poll();
            if (elemento % 2 == 1) {
                toReturn.add(elemento);
            }
        }
        return toReturn;
    }
}
