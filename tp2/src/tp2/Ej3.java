package tp2;
import java.util.Stack;
public class Ej3 {
	public static <E> Stack<E> intercalar(Stack<E> p1, Stack<E> p2) {
		Stack<E> toReturn = new Stack<>();
		while (!p1.isEmpty() && !p2.isEmpty()) {
			toReturn.push(p1.pop());
			toReturn.push(p2.pop());
		}
		  while (!p1.isEmpty()) {
	            toReturn.push(p1.pop());
	        }
	        while (!p2.isEmpty()) {
	            toReturn.push(p2.pop());
	        }
		return toReturn;
	}
public static void main(String[] args) {
	Stack<Integer> ejemplo1 = new Stack<>();
	Stack<Integer> ejemplo2 = new Stack<>();
	Stack<Integer> resultado = new Stack<>();
	ejemplo1.push(1);  ejemplo1.push(2);ejemplo1.push(3);
	ejemplo2.push(10); ejemplo2.push(11); ejemplo2.push(12);
	resultado = intercalar(ejemplo1, ejemplo2);
	System.out.println(resultado.pop());
	System.out.println(resultado.pop());
	System.out.println(resultado.pop());
	System.out.println(resultado.pop());
}
}
