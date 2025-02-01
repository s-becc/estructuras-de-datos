package TDAArbol;


import java.util.Iterator;

import TDALista.BoundaryViolationException;
import TDALista.EmptyListException;
import TDALista.InvalidPositionException;
import TDALista.ListaDobleEnlazada;
import TDALista.Nodo;
import TDALista.Position;
import TDALista.PositionList;

public class Arbol<E> implements Tree<E> {
	private NodoArbol<E> raiz;
	private int n;
	//Constructor
	public Arbol() {
		raiz = null;
		n = 0;
	}
	
	
    public void eliminarRoot() throws InvalidOperationException{
    	if (size() == 0) 
    		throw new InvalidOperationException("arbol vacio");
    	int hijos = raiz.getHijos().size();
    	if (hijos == 0)
    		raiz = null;
    	else
    		if (hijos == 1) {
    			try {
    			raiz = raiz.getHijos().first().element();
    			raiz.setPadre(null);
    			}catch(EmptyListException e) { throw new InvalidOperationException("arbol corrupto");}
    		}
    		else 
    			throw new InvalidOperationException("la raiz tiene mas de un hijo");
    }
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return n;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return n == 0;
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return new ArbolIterator();
	}
	private void preorderSubtree(Position<E> p, PositionList<Position<E>> snapshot) {
	    try {
	        snapshot.addLast(p);  // En preorden, primero se agrega la posición actual
	        for (Position<E> c : children(p)) {
	            preorderSubtree(c, snapshot);  // Luego, se procesan los hijos
	        }
	    } catch (InvalidPositionException e) {
	        e.printStackTrace();
	    }
	}
	@Override
	public Iterable<Position<E>> positions() {
		// TODO Auto-generated method stub
	 
			  PositionList<Position<E>> snapshot = new ListaDobleEnlazada<>();
		  if (!isEmpty())
			try {
				preorderSubtree(root(), snapshot);
			} catch (EmptyTreeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // fill the snapshot recursively
			  return snapshot;
	 
	}

	@Override
	public E replace(Position<E> v, E e) throws InvalidPositionException {
		// TODO Auto-generated method stub
		if (v == null)
			throw new InvalidPositionException("Posicion Invalida");
		E toRet = v.element();
		checkPosition(v).setElement(e);
		return toRet;
	}

	@Override
	public Position<E> root() throws EmptyTreeException {
		// TODO Auto-generated method stub
		if (n == 0)
			throw new  EmptyTreeException("El arbol esta vacio");
		return raiz;
	}

	@Override
	public Position<E> parent(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
		// TODO Auto-generated method stub
		if (v == null)
			throw new InvalidPositionException("Posicion Invalida");
		if (checkPosition(v) == raiz)
			throw new BoundaryViolationException("La raiz no tiene padre");
		return checkPosition(v).getPadre();
	}

	@Override
	public Iterable<Position<E>> children(Position<E> v) throws InvalidPositionException {
		// TODO Auto-generated method stub
		if (v == null)
			throw new InvalidPositionException("Posicion Invalida");
		NodoArbol<E> padre = checkPosition(v);
		PositionList<Position<E>> toRet = new ListaDobleEnlazada<Position<E>>();
		for (Position<E> hijo : padre.getHijos())
			toRet.addLast(hijo);
		return toRet;
	}

	@Override
	public boolean isInternal(Position<E> v) throws InvalidPositionException {
		// TODO Auto-generated method stub
		if (v == null)
			throw new InvalidPositionException("Posicion Invalida");
		return !(checkPosition(v).getHijos().isEmpty());
	}

	@Override
	public boolean isExternal(Position<E> v) throws InvalidPositionException {
		// TODO Auto-generated method stub
		if (v == null)
			throw new InvalidPositionException("Posicion Invalida");
		return (checkPosition(v).getHijos().isEmpty());
	}

	@Override
	public boolean isRoot(Position<E> v) throws InvalidPositionException {
		// TODO Auto-generated method stub
		if (v == null)
			throw new InvalidPositionException("Posicion Invalida");
		return (checkPosition(v) == raiz);
	}

	@Override
	public void createRoot(E e) throws InvalidOperationException {
		// TODO Auto-generated method stub
		if (raiz != null)
			throw new InvalidOperationException("El arbol ya tiene raiz");
		raiz = new NodoArbol<E>(e, null);
		n++;
	}

	@Override
	public Position<E> addFirstChild(Position<E> p, E e) throws InvalidPositionException {
		// TODO Auto-generated method stub
		// Verifica si la posición es válida y si el árbol no está vacío
	    if (p == null || n == 0) {
	        throw new InvalidPositionException("Posición inválida");
	    }

	    // Convierte la posición a NodoArbol
	    NodoArbol<E> nodoPadre = checkPosition(p);
	    
	    // Crea un nuevo nodo con el elemento e y lo vincula al padre
	    NodoArbol<E> nuevoNodo = new NodoArbol<>(e, nodoPadre);
	    
	    // Agrega el nuevo nodo como primer hijo
	    nodoPadre.getHijos().addFirst(nuevoNodo);
	    
	    // Incrementa el tamaño del árbol
	    n++;
	    
	    // Retorna la posición del nuevo nodo
	    return nuevoNodo;

	}

	@Override
	public Position<E> addLastChild(Position<E> p, E e) throws InvalidPositionException {
		// TODO Auto-generated method stub
		if (p == null || n == 0)
			throw new InvalidPositionException("Posicion Invalida");
		Position<E> toRet = new NodoArbol<E>(e,checkPosition(p));
		checkPosition(p).getHijos().addLast(checkPosition(toRet));
		n++;
		return toRet;
	}

	@Override
	public Position<E> addBefore(Position<E> p, Position<E> rb, E e) throws InvalidPositionException {
		// TODO Auto-generated method stub
		if (p == null || rb == null || !(checkPosition(rb).isHijo(checkPosition(p))) || n == 0)
			throw new InvalidPositionException("Posicion Invalida");

	    NodoArbol<E> padre = checkPosition(p); // Obtenemos el nodo padre
	    NodoArbol<E> toRet = new NodoArbol<>(e, padre); // Creamos el nuevo nodo con su padre

	    // Obtenemos la lista de hijos del nodo padre
	    PositionList<NodoArbol<E>> hijos = padre.getHijos();

	    // Convertimos lb de tipo Position<E> a su nodo correspondiente
	    NodoArbol<E> nodoLb = checkPosition(rb);

	    // Ahora necesitamos obtener la posición de `nodoLb` en la lista `hijos`
	    Position<NodoArbol<E>> posicionLb = null;
	    for (Position<NodoArbol<E>> pos : hijos.positions()) {
	        if (pos.element().equals(nodoLb)) {
	            posicionLb = pos;
	            break;
	        }
	    }

	    // Verificamos que se haya encontrado la posición de lb
	    if (posicionLb == null) {
	        throw new InvalidPositionException("No se encontró la posición de lb en la lista de hijos");
	    }

	    // Insertamos el nuevo nodo antes de `posicionLb`
	    hijos.addBefore(posicionLb, toRet);

	    n++; // Aumentamos el tamaño del árbol
	    return toRet;
	}
	

	@Override
	public Position<E> addAfter(Position<E> p, Position<E> lb, E e) throws InvalidPositionException {
		// TODO Auto-generated method stub
		if (p == null || lb == null || !(checkPosition(lb).isHijo(checkPosition(p))) || n == 0)
			throw new InvalidPositionException("Posicion Invalida");

	    NodoArbol<E> padre = checkPosition(p); // Obtenemos el nodo padre
	    NodoArbol<E> toRet = new NodoArbol<>(e, padre); // Creamos el nuevo nodo con su padre

	    // Obtenemos la lista de hijos del nodo padre
	    PositionList<NodoArbol<E>> hijos = padre.getHijos();

	    // Convertimos lb de tipo Position<E> a su nodo correspondiente
	    NodoArbol<E> nodoLb = checkPosition(lb);

	    // Ahora necesitamos obtener la posición de `nodoLb` en la lista `hijos`
	    Position<NodoArbol<E>> posicionLb = null;
	    for (Position<NodoArbol<E>> pos : hijos.positions()) {
	        if (pos.element().equals(nodoLb)) {
	            posicionLb = pos;
	            break;
	        }
	    }

	    // Verificamos que se haya encontrado la posición de lb
	    if (posicionLb == null) {
	        throw new InvalidPositionException("No se encontró la posición de lb en la lista de hijos");
	    }

	    // Insertamos el nuevo nodo después de `posicionLb`
	    hijos.addAfter(posicionLb, toRet);

	    n++; // Aumentamos el tamaño del árbol
	    return toRet;
	}

	@Override
	public void removeExternalNode(Position<E> p) throws InvalidPositionException {
		// TODO Auto-generated method stub
		if (p == null || isInternal(p) || n == 0)
			throw new InvalidPositionException("Posicion Invalida");
		NodoArbol<E> nodo = checkPosition(p);
		if (nodo.getPadre() == null) {
			raiz = null;
			n--;
		}
		else {
		PositionList<NodoArbol<E>> hijosPadre = nodo.getPadre().getHijos();
		Position<NodoArbol<E>> posicionNodo = null;
        for (Position<NodoArbol<E>> hijoPos : hijosPadre.positions()) {
            if (hijoPos.element() == nodo) {
                posicionNodo = hijoPos;
                break;
            }
        }
        if (posicionNodo != null) {
            hijosPadre.remove(posicionNodo);
        }

	    nodo.setPadre(null);
	    n--;}
		
	}

	@Override
	public void removeInternalNode(Position<E> p) throws InvalidPositionException {
		// TODO Auto-generated method stub
		if (p == null || isExternal(p) || n == 0)
			throw new InvalidPositionException("Posicion Invalida");
		NodoArbol<E> nodo = checkPosition(p);  // Convertimos la posición a NodoArbol

	    if (nodo.getPadre() != null) {
	        NodoArbol<E> padre = nodo.getPadre();  // Obtenemos el padre
	        PositionList<NodoArbol<E>> hijosPadre = padre.getHijos();  // Lista de hijos del padre

	        // Asignamos los hijos del nodo a su padre
	        for (NodoArbol<E> hijo : nodo.getHijos()) {
	            hijo.setPadre(padre);  // Actualizamos el padre de cada hijo
	            hijosPadre.addLast(hijo);  // Añadimos cada hijo al final de la lista del padre
	        }

	        // Eliminamos el nodo de la lista de hijos del padre
	        Position<NodoArbol<E>> posicionNodo = null;
	        for (Position<NodoArbol<E>> hijoPos : hijosPadre.positions()) {
	            if (hijoPos.element() == nodo) {
	                posicionNodo = hijoPos;
	                break;
	            }
	        }
	        if (posicionNodo != null) {
	            hijosPadre.remove(posicionNodo);
	        }
	        n--;

	    } else {
	        // Si es la raíz
	        if (nodo.getHijos().size() == 1) {
	            try {
	                NodoArbol<E> nuevaRaiz = nodo.getHijos().first().element();  // Obtenemos el único hijo
	                nuevaRaiz.setPadre(null);  // El nuevo nodo raíz no tiene padre
	                raiz = nuevaRaiz;  // Actualizamos la raíz del árbol
	                n--;
	            } catch (EmptyListException e) {
	                throw new InvalidPositionException("El nodo no tiene hijos, posición inválida");
	            }
	        } else
	   
	            throw new InvalidPositionException("No se puede eliminar la raíz con múltiples hijos");
	        }
	    }





	@Override
	public void removeNode(Position<E> p) throws InvalidPositionException {
		// TODO Auto-generated method stub
		if (p == null || (isRoot(p) && checkPosition(p).getHijos().size() > 1) || n == 0)
			throw new InvalidPositionException("Posicion Invalida");
		NodoArbol<E> nodo = checkPosition(p);

	    if (isExternal(p)) {
	        // Caso 1: El nodo es una hoja
	        if (nodo.getPadre() != null) {
	            Position<NodoArbol<E>> hijoEliminar = null;
	            for (Position<NodoArbol<E>> pos : nodo.getPadre().getHijos().positions()) {
	                if (pos.element() == nodo) {
	                    hijoEliminar = pos;
	                    break;
	                }
	            }
	            if (hijoEliminar != null) {
	                nodo.getPadre().getHijos().remove(hijoEliminar);  // Elimina el nodo hoja
	            }
	        } else {
	            // El nodo es la raíz del árbol
	            raiz = null;  // Si es la única hoja, el árbol queda vacío
	        }

	    } else {
	        // Caso 2: El nodo es interno
	        if (nodo.getPadre() != null) {
	            NodoArbol<E> padre = nodo.getPadre();
	            PositionList<NodoArbol<E>> hijosPadre = padre.getHijos();
	            
	            // Asigna los hijos del nodo a su padre
	            for (NodoArbol<E> hijo : nodo.getHijos()) {
	                hijo.setPadre(padre);
	                hijosPadre.addLast(hijo);  // Agrega los hijos del nodo eliminado al final
	            }
	            
	            // Elimina el nodo de la lista de hijos del padre
	            Position<NodoArbol<E>> hijoEliminar = null;
	            for (Position<NodoArbol<E>> pos : hijosPadre.positions()) {
	                if (pos.element() == nodo) {
	                    hijoEliminar = pos;
	                    break;
	                }
	            }
	            if (hijoEliminar != null) {
	                hijosPadre.remove(hijoEliminar);
	            }

	        } else {
	            // Caso especial: Si es la raíz y tiene un solo hijo
	            if (nodo.getHijos().size() == 1) {
	                try {
	                    NodoArbol<E> nuevaRaiz = nodo.getHijos().first().element();
	                    nuevaRaiz.setPadre(null);
	                    raiz = nuevaRaiz;  // Actualiza la raíz del árbol
	                } catch (EmptyListException e) {
	                    throw new InvalidPositionException("El nodo no tiene hijos, posición inválida");
	                }
	            } else {
	                throw new InvalidPositionException("No se puede eliminar la raíz con múltiples hijos");
	            }
	        }
	    }

	    n--;  // Disminuye el tamaño del árbol
	}
	 
					
	}
	public int sizeSubArbol(Position<E> p) throws InvalidPositionException{
		if (p == null)
			throw new InvalidPositionException("posicion invalida");
		int cont = 1;
		for (Position<E> h : children(p))
			cont += sizeSubArbol(h);
		return cont;
	}
			
	private NodoArbol<E> checkPosition(Position<E> p) throws InvalidPositionException{
		if (p == null)
			throw new InvalidPositionException("Posicion no valida");
		NodoArbol<E> toRet = null;
		try {
			toRet = (NodoArbol<E>) p;
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
	  public Iterable<E> convertirAHoja(Position<E> p) throws InvalidPositionException{
		  if (p == null)
			  throw new InvalidPositionException("posicion no valida");
		  PositionList<E> lista = new ListaDobleEnlazada<>();
		  preOrdElim(p,lista);
		  return lista;
	  } 
	  public void preOrdElim(Position<E> p, PositionList<E> pl) {
		  try {
		  for (Position<E> hijo : children(p)) {
			  preOrdElim(hijo, pl);
			  pl.addLast(hijo.element());
			  removeNode(p);
		  }
		  }catch (InvalidPositionException e) {e.printStackTrace();}
	  }
}
