import java.util.ArrayList;
/**
 *
 */
public class Torre extends Pieza {

	/**
	 * 
	 */
	public Torre(boolean color) {
		super(color);
		val = 4;
	}

	public String toString() {
		if(color == Pieza.BLANCA)
			return "T";
		else
			return "t";
	}

	public Torre copiar() {
		return new Torre(color);
	}

          public ArrayList<Mover> getMovimiento(Tablero b, int x, int y) {
		ArrayList<Mover> movimiento = new ArrayList<Mover>();


		for(int i = 1; i < 6; i++) {
		     if(valido(x, y+i)) {
			if(b.getCasilla(x, y+i).estaOcupada()) {
				if(b.getCasilla(x, y+i).getPieza().color != color)
					movimiento.add(new Mover(x,y,x,y+i));	

					break;
				}
				else
					movimiento.add(new Mover(x,y,x,y+i));	
			}
		}

		for(int i = 1; i < 6; i++) {
			if(valido(x, y-i)) {
			     if(b.getCasilla(x, y-i).estaOcupada()) {
				   if(b.getCasilla(x, y-i).getPieza().color != color)
				        movimiento.add(new Mover(x,y,x,y-i));	

					break;
				}
				else
					movimiento.add(new Mover(x,y,x,y-i));	
			}
		}
		for(int i = 1; i < 6; i++) {
			if(valido(x-i, y)) {
			    if(b.getCasilla(x-i, y).estaOcupada()) {
				if(b.getCasilla(x-i, y).getPieza().color != color)
					movimiento.add(new Mover(x,y,x-i,y));	

					break;
				}
				else
					movimiento.add(new Mover(x,y,x-i,y));	
			}
		}

		for(int i = 1; i < 6; i++) {
			if(valido(x+i, y)) {
			     if(b.getCasilla(x+i, y).estaOcupada()) {
				if(b.getCasilla(x+i, y).getPieza().color != color)
				        movimiento.add(new Mover(x,y,x+i,y));	

					break;
				}
				else
					movimiento.add(new Mover(x,y,x+i,y));	
			}
		}


		return movimiento;
	}
}
