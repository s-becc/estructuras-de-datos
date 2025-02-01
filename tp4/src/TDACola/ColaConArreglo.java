package TDACola;

public class ColaConArreglo<E> implements Queue<E> {
	private E[] array;        // Arreglo que contiene los elementos de la cola
    private int front;        // Índice del frente de la cola
    private int rear;         // Índice del fondo de la cola
    private int size;         // Tamaño actual de la cola
    private int capacity;     // Capacidad máxima de la cola

    public ColaConArreglo() {
        capacity = 100;
        array = (E[]) new Object[capacity]; // Inicializa el arreglo
        front = 0;
        rear = -1;
        size = 0;
    }
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return (size == 0);
	}

	@Override
	public E front() throws EmptyQueueException {
		// TODO Auto-generated method stub
		if (isEmpty()) {
            throw new EmptyQueueException("La cola está vacía.");
        }
        return array[front]; // Devuelve el elemento en el frente
	}

	@Override
	public void enqueue(E element) {
		// TODO Auto-generated method stub
		if (size == capacity) {
            throw new IllegalStateException("La cola está llena.");
        }
        rear = (rear + 1) % capacity; // Actualiza el índice del fondo
        array[rear] = element;         // Agrega el nuevo elemento
        size++;
	}

	@Override
	public E dequeue() throws EmptyQueueException {
		// TODO Auto-generated method stub
		if (isEmpty()) {
            throw new EmptyQueueException("La cola está vacía.");
        }
        E element = array[front];        // Obtiene el elemento del frente
        array[front] = null;             // Opcional: Limpia la referencia
        front = (front + 1) % capacity; // Actualiza el índice del frente
        size--;
        return element;                  // Devuelve el elemento removido
    }
	}
	

