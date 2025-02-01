package TDAArbolBinario;

import java.util.Iterator;

import TDAArbol.EmptyTreeException;
import TDAArbol.InvalidOperationException;
import TDAArbol.NodoArbol;
import TDADiccionario.Diccionario;
import TDADiccionario.Dictionary;
import TDALista.BoundaryViolationException;
import TDALista.InvalidPositionException;
import TDALista.ListaDobleEnlazada;
import TDALista.Position;
import TDALista.PositionList;
import TDAMapeo.InvalidKeyException;
import TDAMapeo.Map;
import TDAMapeo.MapeoHash;

public class ArbolBinario<E> implements BinaryTree<E> {
    BTNodo<E> raiz;
    int size;

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size == 0;
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return new ArbolIterator();
	}

	@Override
	public Iterable<Position<E>> positions() {
		// TODO Auto-generated method stub
		PositionList<Position<E>> lista = new ListaDobleEnlazada<>();
		preOrdenRec(raiz, lista);
		return lista;
	}
	 
	private void preOrdenRec(Position<E> p, PositionList<Position<E>> l) {
		l.addLast(p);
		try {
		if (hasLeft(p))
			preOrdenRec(left(p), l);
		if (hasRight(p))
			preOrdenRec(right(p), l);
		}catch (InvalidPositionException | BoundaryViolationException e) {e.printStackTrace();}
	}

	@Override
	public E replace(Position<E> v, E e) throws InvalidPositionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position<E> root() throws EmptyTreeException {
		// TODO Auto-generated method stub
		if (size == 0)
			throw new EmptyTreeException("No se puede pedir la raiz de un arbol vacio");
		return raiz;
	}

	@Override
	public Position<E> parent(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Position<E>> children(Position<E> v) throws InvalidPositionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isInternal(Position<E> v) throws InvalidPositionException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isExternal(Position<E> v) throws InvalidPositionException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isRoot(Position<E> v) throws InvalidPositionException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void createRoot(E e) throws InvalidOperationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Position<E> addFirstChild(Position<E> p, E e) throws InvalidPositionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position<E> addLastChild(Position<E> p, E e) throws InvalidPositionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position<E> addBefore(Position<E> p, Position<E> rb, E e) throws InvalidPositionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position<E> addAfter(Position<E> p, Position<E> lb, E e) throws InvalidPositionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeExternalNode(Position<E> p) throws InvalidPositionException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeInternalNode(Position<E> p) throws InvalidPositionException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeNode(Position<E> p) throws InvalidPositionException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Position<E> left(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position<E> right(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasLeft(Position<E> v) throws InvalidPositionException {
		// TODO Auto-generated method stub
		if (v == null)
			throw new InvalidPositionException("Posicion invalida");
		return checkPosition(v).getHijoL() != null;
	}

	@Override
	public boolean hasRight(Position<E> v) throws InvalidPositionException {
		// TODO Auto-generated method stub
		if (v == null)
			throw new InvalidPositionException("Posicion invalida");
		return checkPosition(v).getHijoD() != null;
	}

	@Override
	public Position<E> addLeft(Position<E> v, E r) throws InvalidOperationException, InvalidPositionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position<E> addRight(Position<E> v, E r) throws InvalidOperationException, InvalidPositionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void attach(Position<E> r, BinaryTree<E> T1, BinaryTree<E> T2) throws InvalidPositionException {
		// TODO Auto-generated method stub
		
	}
    public Dictionary<E,E> diccionarioArbol(){
    	Dictionary<E,E> dic = new Diccionario<>();
    	try {
			dicRec(dic, root());
		} catch (EmptyTreeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return dic;
    	
    }
    public void dicRec(Dictionary<E,E> d, Position<E> p) {
    	try {
			if (!isRoot(p))
				d.insert(checkPosition(p).getPadre().element(), p.element());
		} catch (InvalidPositionException | InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	try {
    		if (isInternal(p)) {
    	if (hasLeft(p))
    	  dicRec(d, left(p));
    	if (hasRight(p))
      	  dicRec(d, right(p));
    		}
    	}catch(InvalidPositionException | BoundaryViolationException e) { e.printStackTrace();}
    	
    }
	public void eliminiarSubArbol(Position<E> p) throws InvalidPositionException{
		if (p == null)
			throw new InvalidPositionException("pos invalida");
		BTNodo<E> nodo = checkPosition(p);
		if (nodo.getPadre() == null) {
			raiz = null; 
			size = 0;
		       }
		int nuevoSize = size - contarSubArbol(p);
		if (nodo.getPadre().getHijoD() == nodo) {
		    nodo.getPadre().setHijoD(null);
		    nodo.setPadre(null);
		    size = nuevoSize;
		}
		else {
			nodo.getPadre().setHijoL(null);
		nodo.setPadre(null);
		size = nuevoSize;
		}
	}
	public int contarSubArbol(Position<E> p) {
	    int count = 1; // Contamos el nodo actual
	    try {
	        // Contar nodos en el subárbol izquierdo
	        if (this.hasLeft(p)) {
	            count += contarSubArbol(this.left(p));
	        }
	        // Contar nodos en el subárbol derecho
	        if (this.hasRight(p)) {
	            count += contarSubArbol(this.right(p));
	        }
	    } catch (BoundaryViolationException | InvalidPositionException e) {
	        e.printStackTrace();
	    }
	    return count;
	}
	private BTNodo<E> checkPosition(Position<E> p) throws InvalidPositionException{
		if (p == null)
			throw new InvalidPositionException("Posicion no valida");
		BTNodo<E> toRet = null;
		try {
			toRet = (BTNodo<E>) p;
		}catch (ClassCastException e) {throw new InvalidPositionException("Fallo el casteo");}
		
		return toRet;
	}
	 private class ArbolIterator implements Iterator<E> {
	        // Assuming positions() returns an iterable of Position<E>
	        Iterator<Position<E>> posIterator = positions().iterator();

	        @Override
	        public boolean hasNext() {
	            return posIterator.hasNext();
	        }

	        @Override
	        public E next() {
	            return posIterator.next().element(); // Return the element of the position
	        }

	        @Override
	        public void remove() {
	            posIterator.remove();
	        }
	    }

}
