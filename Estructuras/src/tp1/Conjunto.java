package tp1;

public interface Conjunto<E> {
    int size();
    int capacity();
    boolean isEmpty();
    E get(int index);
    boolean pertenece(E elem);
}
