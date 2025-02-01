package tp3;


public class MainTp3 {
	 public static Stack<Character> pilaSinChar(Stack<Character> p, Character ch){
		 Stack<Character> aux = new Pila<>();
		 while (!(p.isEmpty()))
			try {
				if (p.top().equals(ch))
					try {
						p.pop();
					} catch (EmptyStackException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				else
					try {
						aux.push(p.pop());
					} catch (EmptyStackException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			} catch (EmptyStackException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 while (!aux.isEmpty())
			try {
				p.push(aux.pop());
			} catch (EmptyStackException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 return p;
		  
	  }
}
