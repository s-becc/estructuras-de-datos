package TDADiccionario;

import TDAMapeo.Entry;
import TDAMapeo.InvalidKeyException;

import TDAMapeo.Entry;
import TDAMapeo.InvalidKeyException;
import TDAMapeo.InvalidEntryException;

import java.util.Arrays;

public class Diccionario<K, V> implements Dictionary<K, V> {
    private static class HashEntry<K, V> implements Entry<K, V> {
        private K key;
        private V value;

        public HashEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }
    }

    private HashEntry<K, V>[] table;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    @SuppressWarnings("unchecked")
    public Diccionario() {
        table = new HashEntry[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Entry<K, V> find(K key) throws InvalidKeyException {
        validateKey(key);
        int index = hash(key);
        for (int i = 0; i < table.length; i++) {
            int probeIndex = (index + i) % table.length;
            HashEntry<K, V> entry = table[probeIndex];
            if (entry == null) {
                return null; // No hay entrada en esa posición
            }
            if (entry.getKey().equals(key)) {
                return entry; // Clave encontrada
            }
        }
        return null; // No se encontró la entrada
    }

    @Override
    public Iterable<Entry<K, V>> findAll(K key) throws InvalidKeyException {
        validateKey(key);
        // Este método retorna las entradas con la misma clave
        return new Iterable<Entry<K, V>>() {
            @Override
            public java.util.Iterator<Entry<K, V>> iterator() {
                return new java.util.Iterator<Entry<K, V>>() {
                    private int currentIndex = 0;
                    private int foundCount = 0;

                    @Override
                    public boolean hasNext() {
                        return foundCount < size && currentIndex < table.length;
                    }

                    @Override
                    public Entry<K, V> next() {
                        while (currentIndex < table.length) {
                            HashEntry<K, V> entry = table[currentIndex++];
                            if (entry != null && entry.getKey().equals(key)) {
                                foundCount++;
                                return entry;
                            }
                        }
                        throw new java.util.NoSuchElementException();
                    }
                };
            }
        };
    }

    @Override
    public Entry<K, V> insert(K key, V value) throws InvalidKeyException {
        validateKey(key);
        if (size >= table.length * 0.75) {
            resize();
        }
        int index = hash(key);
        for (int i = 0; i < table.length; i++) {
            int probeIndex = (index + i) % table.length;
            if (table[probeIndex] == null || table[probeIndex].getKey().equals(key)) {
                table[probeIndex] = new HashEntry<>(key, value);
                if (table[probeIndex] == null) {
                    size++;
                }
                return table[probeIndex];
            }
        }
        throw new IllegalStateException("No se pudo insertar la entrada");
    }

    @Override
    public Entry<K, V> remove(Entry<K, V> e) throws InvalidEntryException {
        if (e == null) {
            throw new InvalidEntryException("Entrada no válida");
        }
        K key = e.getKey();
        int index = hash(key);
        for (int i = 0; i < table.length; i++) {
            int probeIndex = (index + i) % table.length;
            HashEntry<K, V> entry = table[probeIndex];
            if (entry != null && entry.getKey().equals(key)) {
                table[probeIndex] = null; // Eliminar la entrada
                size--;
                return entry; // Retornar la entrada eliminada
            }
        }
        throw new InvalidEntryException("La entrada no se encontró");
    }

    @Override
    public Iterable<Entry<K, V>> entries() {
        return new Iterable<Entry<K, V>>() {
            @Override
            public java.util.Iterator<Entry<K, V>> iterator() {
                return new java.util.Iterator<Entry<K, V>>() {
                    private int currentIndex = 0;

                    @Override
                    public boolean hasNext() {
                        while (currentIndex < table.length && table[currentIndex] == null) {
                            currentIndex++;
                        }
                        return currentIndex < table.length;
                    }

                    @Override
                    public Entry<K, V> next() {
                        if (!hasNext()) {
                            throw new java.util.NoSuchElementException();
                        }
                        return table[currentIndex++];
                    }
                };
            }
        };
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % table.length;
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        HashEntry<K, V>[] oldTable = table;
        table = new HashEntry[oldTable.length * 2];
        size = 0; // Resetear tamaño y volver a insertar entradas
        for (HashEntry<K, V> entry : oldTable) {
            if (entry != null) {
                insert(entry.getKey(), entry.getValue());
            }
        }
    }

    private void validateKey(K key) throws InvalidKeyException {
        if (key == null) {
            throw new InvalidKeyException("La clave no puede ser nula");
        }
    }
}
public boolean alMenosEEntradasConClaveK(K key, int e) throws InvalidKeyException{
	if (key == null)
		throw new InvalidKeyException("Clave invalida");
	int cont = 0;
	for (Entrada<K,V> entry : buckets[Math.abs(key..hashCode() % N])]) {
		if (entry != null && entry.getKey().equals(key))
			cont++;
		if (cont == e)
			break;
			}
	return cont == e;
}
}
 
