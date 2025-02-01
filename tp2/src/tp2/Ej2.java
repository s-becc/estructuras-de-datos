package tp2;
public class Ej2 {
	 
   public static void main(String[] args) {
	 Extension cola = new Extension();
	cola.add(1);
	cola.add(2);
	cola.add(3);
	cola.add(4);
	Extension nuevaCola = new Extension();
	nuevaCola =  (Extension) nuevaCola.soloImpares(cola);
	System.out.println(nuevaCola.poll());
	System.out.println(nuevaCola.poll());
}
}
