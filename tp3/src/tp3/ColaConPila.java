package tp3;

public class ColaConPila<E> implements Queue<E> {
   private Pila<E> pila;
   
   public ColaConPila(){
	  pila = new Pila<>();
    }
   public int size() {
	   return pila.size();
   }
   public boolean isEmpty() {
	   return pila.isEmpty();
   }
   public E front() throws EmptyQueueException{
	   if (pila.isEmpty())
            throw new EmptyQueueException("No se puede consultar el frente de una cola vacia");
		E aux = null;
	   try {
			aux = pila.top();
		} catch (EmptyStackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   return aux;
	
   }
   public void enqueue(E elem) {
	   Pila<E> aux = new Pila<>();
	   while (!pila.isEmpty())
		try {
			aux.push(pila.pop());
		} catch (EmptyStackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   pila.push(elem);
	   while (!aux.isEmpty())
		try {
			pila.push(aux.pop());
		} catch (EmptyStackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   
   }
@Override
    public E dequeue() throws EmptyQueueException {
	E aux = null;
	if (isEmpty())
		   throw new EmptyQueueException("no se puede desencolar una cola vacia");
	  
	try {
		aux = pila.pop();
	} catch (EmptyStackException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return aux;
       }
}
