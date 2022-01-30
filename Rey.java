import java.util.ArrayList;
/**
 * 
 */
public class Rey extends Pieza {
	boolean cambioPosc = false;

	/**
	 * 
	 */
	public Rey(boolean color) {
		super(color);
		val = 0;
	}

	public Rey(boolean color, boolean cambioPosc) {
		super(color);
		this.cambioPosc = cambioPosc;
		val = 0;
	}

	public Rey copiar() {
		return new Rey(color, cambioPosc);
	}

	public String toString() {
		if(color == Pieza.BLANCA)
			return "R";
		else
			return "r";
	}

	public ArrayList<Mover> getMovimiento(Tablero b, int x, int y) {
		ArrayList<Mover> movimiento = new ArrayList<Mover>();
		if(valido(x, y+1) && 
			(!b.getCasilla(x, y+1).estaOcupada() || 
			(b.getCasilla(x, y+1).estaOcupada() && b.getCasilla(x, y+1).getPieza().getColor() != color)))
			movimiento.add(new Mover(x,y,x,y+1));

		if(valido(x+1, y+1) && 
				(!b.getCasilla(x+1, y+1).estaOcupada() || 
				(b.getCasilla(x+1, y+1).estaOcupada() && b.getCasilla(x+1, y+1).getPieza().getColor() != color)))
			        movimiento.add(new Mover(x,y,x+1,y+1));

		if(valido(x+1,y) && 
				(!b.getCasilla(x+1,y).estaOcupada() || 
				(b.getCasilla(x+1,y).estaOcupada() && b.getCasilla(x+1,y).getPieza().getColor() != color)))
			        movimiento.add(new Mover(x,y,x+1,y));

		if(valido(x+1,y-1) && 
				(!b.getCasilla(x+1,y-1).estaOcupada() || 
				(b.getCasilla(x+1,y-1).estaOcupada() && b.getCasilla(x+1,y-1).getPieza().getColor() != color)))
			        movimiento.add(new Mover(x,y,x+1,y-1));

		if(valido(x,y-1) && 
				(!b.getCasilla(x,y-1).estaOcupada() || 
				(b.getCasilla(x,y-1).estaOcupada() && b.getCasilla(x,y-1).getPieza().getColor() != color)))
			        movimiento.add(new Mover(x,y,x,y-1));

		if(valido(x-1,y-1) && 
				(!b.getCasilla(x-1,y-1).estaOcupada() || 
				(b.getCasilla(x-1,y-1).estaOcupada() && b.getCasilla(x-1,y-1).getPieza().getColor() != color)))
			        movimiento.add(new Mover(x,y,x-1,y-1));

		if(valido(x-1,y) && 
				(!b.getCasilla(x-1,y).estaOcupada() || 
				(b.getCasilla(x-1,y).estaOcupada() && b.getCasilla(x-1,y).getPieza().getColor() != color)))
			        movimiento.add(new Mover(x,y,x-1,y));

		if(valido(x-1,y+1) && 
				(!b.getCasilla(x-1,y+1).estaOcupada() || 
				(b.getCasilla(x-1,y+1).estaOcupada() && b.getCasilla(x-1,y+1).getPieza().getColor() != color)))
			        movimiento.add(new Mover(x,y,x-1,y+1));

		if(color == Pieza.BLANCA) {
			if(!cambioPosc && x == Tablero.e && y == 1-1) {
				if(!b.getCasilla(Tablero.d, 1-1).estaOcupada() &&
				 !b.getCasilla(Tablero.e, 1-1).estaOcupada() &&
				  b.getCasilla(Tablero.f, 1-1).estaOcupada() && 
				  b.getCasilla(Tablero.f, 1-1).getPieza().toString().equals("R"))
				  movimiento.add(new Mover(x,y,x+2,y));


			}
			else 
				cambioPosc = true;
		}
		else { 
			if(!cambioPosc && x == Tablero.e && y == 6-1) {

			}
			else 
				cambioPosc = true;
		}
		return movimiento;
	}
}
