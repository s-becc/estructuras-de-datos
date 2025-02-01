package TDAArbol;

import TDALista.ListaDobleEnlazada;
import TDALista.Position;
import TDALista.PositionList;

public class NodoArbol<E> implements Position<E>{
  
	private E elemento;
	private NodoArbol<E> padre;
	private PositionList<NodoArbol<E>> hijos;
	//Constructor
	public NodoArbol(E elem, NodoArbol<E> p) {
		elemento = elem;
		hijos = new ListaDobleEnlazada<NodoArbol<E>>();
		padre = p;
	}
	//Metodos
	public E element() {
		return elemento;
	}
	public NodoArbol<E> getPadre() {
		return padre;
	}
	public boolean isHijo(NodoArbol<E> p) {
		return padre == p;
	}
	public void setHijo(NodoArbol<E> n) {
		hijos.addLast(n);
	}
	public void setPadre(NodoArbol<E> n) {
		padre = n;
	}
	public void setElement(E elem) {
		elemento = elem;
	}
	public PositionList<NodoArbol<E>> getHijos(){
		return hijos;
	}
	
}
