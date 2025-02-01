package TDAArbol;

import TDAArbolBinario.BinaryTree;
import TDALista.BoundaryViolationException;
import TDALista.EmptyListException;
import TDALista.InvalidPositionException;
import TDALista.ListaDobleEnlazada;
import TDALista.Position;
import TDALista.PositionList;
import TDAMapeo.InvalidKeyException;
import TDAMapeo.Map;
import TDAMapeo.MapeoHash;

public class ejercicios {
	public static <E> void completarDerechos(E r, BinaryTree<E> t) throws EmptyTreeException{
		if (t.isEmpty())
			throw new EmptyTreeException("arbol vacio");
		completarDerechosRec(r, t.root(), t);
		
	}
	public static <E> void completarDerechosRec(E r, Position<E> nodo, BinaryTree<E> t) {
		try {
			if (t.hasLeft(nodo) && !t.hasRight(nodo))
				t.addRight(nodo, r);
		} catch (InvalidPositionException | InvalidOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 try {
		if (t.hasLeft(nodo))
			completarDerechosRec(r, t.left(nodo), t);
		if (t.hasRight(nodo))
			completarDerechosRec(r, t.right(nodo), t);
		 }catch (InvalidPositionException | BoundaryViolationException e) { e.printStackTrace();}
	}
	public void insertarCharEnXNivel(Tree<Character> a, int x, Character c) {
		try {
		Position<Character> actual = a.root();
        int nivel = 0;
        PositionList<Position<Character>> lista= new ListaDobleEnlazada<>();
        lista.addLast(actual);
        while (!lista.isEmpty()) {
        	lista.remove(lista.first());
        	for (Position<Character> h : a.children(actual))
        		lista.addLast(h);
        	nivel++;
        	if (nivel == x)
        		for (Position<Position<Character>> p : lista.positions()) {
        			if (a.isExternal(p.element()))
        			  a.addFirstChild(p.element(), c);
        			}
      }
        
	}catch (InvalidPositionException | EmptyTreeException | EmptyListException e) {e.printStackTrace();}
	}
	public Map<Character, Integer> cantOperadores(BinaryTree<Character> a){
		Map<Character,Integer> mapa = new MapeoHash<>();
		if (!a.isEmpty())
			try {
				cantRec(mapa, a, a.root());
			} catch (EmptyTreeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return mapa;
	}
	private void cantRec(Map<Character, Integer> m, BinaryTree<Character> a, Position<Character> p) {
		if (a.hasLeft(p))
			cantRec(m, a, a.left(p));
		Character ch = p.element();
		Integer actual = m.get(ch);
		if (actual == null)
			m.put(ch, 1);
		else
			m.put(ch, actual + 1);
		if (a.hasRight(p))
			cantRec(m, a, a.right(p));
	}
	public static Map<Character, Integer> aparicionesEnN(Tree<Character> t, int n){
		PositionList<Position<Character>> pl = new ListaDobleEnlazada<>();
		Map<Character,Integer> m = new MapeoHash<>();
		int eliminar = 0;
		int nivel = 0;
		try {
		pl.addFirst(t.root());
		while (pl != null && nivel != n) {
			eliminar = pl.size();
		  for (Position<Character> p : pl)
			for (Position<Character> h : t.children(p))
				pl.addLast(h);
		  for (int i = 0; i<eliminar; i++)
			 
				pl.remove(pl.first());
			 
		  nivel++;
		        }
		 
		if (nivel == n)
			for (Position<Character> p : pl) 
				if (m.get(p.element()) == null)
					m.put(p.element(), 1);
					else
						m.put(p.element(), m.get(p.element()) + 1);
	} catch (InvalidPositionException | EmptyListException | InvalidKeyException | EmptyTreeException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return m;
			
	}
}