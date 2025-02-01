package TDAArbolBinario;

import TDALista.Position;

public class BTNodo<E> implements Position<E>{
	BTNodo<E> padre;
	BTNodo<E> hijoD;
	BTNodo<E> hijoL;
	E elemento;
	public BTNodo(BTNodo<E> p, E elem) {
		padre = p;
		elemento = elem;
	}
	@Override
	public E element() {
		// TODO Auto-generated method stub
		return elemento;
	}
	public void setHijoD(BTNodo<E> h) {
		hijoD = h;
	}
	public void setHijoL(BTNodo<E> h) {
		hijoL = h;
	}
	public void setPadre(BTNodo<E> h) {
		padre = h;
		
	}
	public BTNodo<E> getHijoD() {
		return hijoD;
	}
	public BTNodo<E> getHijoL() {
		return hijoD;
	}
	public BTNodo<E> getPadre() {
		return padre;
	}
}
