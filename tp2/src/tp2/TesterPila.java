package tp2;
import java.util.Stack;
public class TesterPila {
   public static void main(String[] args) {
	   Integer[] arreglo = {1,2,3,4,5,6};
	   Stack<Integer> pila = new Stack<>();
	   for (int i = arreglo.length -1; i>=0; i--)
		   pila.push(arreglo[i]);
	   System.out.println(pila.peek());
   }
}
