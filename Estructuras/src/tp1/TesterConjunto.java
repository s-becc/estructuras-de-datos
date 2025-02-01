package tp1;
 
public class TesterConjunto {
	public static void main(String[] args) {
  String[] arr = {"hola","dea",null};
  ConjuntoArreglo<String> obj = new ConjuntoArreglo<>(arr);
  System.out.println(obj.get(1));
}
}
