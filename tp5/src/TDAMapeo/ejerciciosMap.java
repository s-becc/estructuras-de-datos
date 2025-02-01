package TDAMapeo;

import TDALista.Position;
import TDALista.PositionList;

public class ejerciciosMap {
	public static Map<Character,Integer> cantidadApariciones(PositionList<Character> pl){
		Map<Character,Integer> map = new MapeoHash<>();
		int n = 0;
		for (Position<Character> ch : pl.positions()) {
			try {
				 n = map.get(ch.element());
			}catch (InvalidKeyException e) {
				try {
					map.put(ch.element(), 0);
				} catch (InvalidKeyException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		try {
			map.put(ch.element(), n+1);
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		return map;
	}
}
