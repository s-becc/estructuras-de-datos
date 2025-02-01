package tp1;


	public class ConjuntoArreglo<E> implements Conjunto<E> {
	    protected E[] arreglo;

	 
	    public ConjuntoArreglo(E[] arreglo) {
	        this.arreglo = arreglo;
	    }

	    @Override
	    public int size() {
	    	int cont = 0;

	       for (E element : arreglo) {
			if (element != null) {
				cont++;
			}
		}
	        return cont ;
	    }

	    @Override
	    public int capacity() {
	        return arreglo.length ;
	    }

	    @Override
	    public boolean isEmpty() {
	    boolean vacio = true;
	    	for (int i = 0; i<arreglo.length && vacio; i++) {
				if (arreglo[i] != null) {
					vacio = false;
				}
			}
	    	return vacio;
	    }

	    @Override
	    public E get(int index) {         
	        return arreglo[index];
	    }

		@Override
		public boolean pertenece(E elem) {
			boolean esta = false;
			for (int i = 0; i < capacity() && !esta;i++)
				if (arreglo[i] != null && arreglo[i].equals(elem))
					esta = true;
			return esta;
		}
		public boolean perteneceRecursivo(E elem, int index) {
		    // Caso base: si hemos revisado todos los elementos
		    if (index < 0) {
		        return false;
		    }

		    // Verificar el elemento en la posición actual
		    if (get(index) != null && get(index).equals(elem)) {
		        return true;
		    }

		    // Llamada recursiva para el siguiente índice
		    return perteneceRecursivo(elem, index - 1);
		}
		
	}
