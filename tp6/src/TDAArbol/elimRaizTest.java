package TDAArbol;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import TDALista.InvalidPositionException;
public class elimRaizTest {

	    private Arbol<String> arbol;

	    @BeforeEach
	    public void setUp() {
	        arbol = new Arbol<>();
	        try {
	        arbol.createRoot("Raiz");  // Asumimos que existe un método para insertar la raíz.
			arbol.addLastChild(arbol.root(), "Hijo1");
			  // Insertamos primer hijo a la raíz.
	        arbol.addLastChild(arbol.root(), "Hijo2");  // Insertamos segundo hijo a la raíz.
	    } catch (InvalidPositionException | EmptyTreeException | InvalidOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();}
		
	    }

	    @Test
	    public void testEliminarRootConMasDeUnHijo() {
	        InvalidOperationException excepcion = assertThrows(
	            InvalidOperationException.class,
	            () -> arbol.eliminarRoot(),
	            "Se esperaba una InvalidOperationException cuando la raíz tiene más de un hijo."
	        );

	        assertEquals("la raiz tiene mas de un hijo", excepcion.getMessage());
	    }
	}

