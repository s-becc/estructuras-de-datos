package tp3;

public class Pila<E> implements Stack<E>{
	protected int tope;
	protected E[] arreglo;
	public Pila() {
		tope = 0;
		arreglo = (E[]) new Object[10];
	}
	public int size() {
		return tope;
	}
	public boolean isEmpty() {
		return tope == 0;
	}
	public E top() throws EmptyStackException{
		 if (isEmpty())
			 throw new EmptyStackException("no se puede revisar el tope si la pila esta vacia");
		 return arreglo[tope - 1];
	}
    public void push(E elemento) {
	   if (tope == arreglo.length)
		   agrandarArreglo();  
		   arreglo[tope] = elemento;
		   tope++;
    }
    protected void agrandarArreglo(){
    	E[] nuevoArreglo = (E[]) new Object[2 * arreglo.length];
        System.arraycopy(arreglo, 0, nuevoArreglo, 0, tope); // Copiamos los elementos al nuevo arreglo
        arreglo = nuevoArreglo;
    }
    public E pop() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException("No se puede retornar el último elemento si la pila está vacía");
        }
        
        tope--; // Decrementa el índice del tope
        E toReturn = arreglo[tope]; // Obtiene el elemento a retornar
        arreglo[tope] = null; // Limpia la referencia en la pila
        return toReturn; // Retorna el elemento
    }
}