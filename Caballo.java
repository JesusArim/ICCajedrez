import java.util.ArrayList;
/**
 * Clase para la pieza caballo
 * @author Jesús Alberto Rivera Mora
 * @version 1.0
 */
public class Caballo extends Pieza {

	/**
	 * 
	 */
	public Caballo(boolean color) {
		super(color);
		val= 3;
	}

	public Caballo copiar() {
		return new Caballo(color);
	}

	public String toString() {
		if(color == Pieza.BLANCA)
			return "C";
		else
			return "c";
	}

	public ArrayList<Mover> getMovimiento(Tablero b, int x, int y) {
		ArrayList<Mover> movimiento = new ArrayList<Mover>();


		if(valido(x+1, y+2) && 
				(!b.getCasilla(x+1, y+2).estaOcupada() || 
				(b.getCasilla(x+1, y+2).estaOcupada() && b.getCasilla(x+1, y+2).getPieza().getColor() != color)))
			        movimiento.add(new Mover(x,y,x+1, y+2));

	
		if(valido(x+2, y+1) && 
		        (!b.getCasilla(x+2, y+1).estaOcupada() || 
			(b.getCasilla(x+2, y+1).estaOcupada() && b.getCasilla(x+2, y+1).getPieza().getColor() != color)))
			movimiento.add(new Mover(x,y,x+2, y+1));

		if(valido(x+2,y-1) && 
				(!b.getCasilla(x+2,y-1).estaOcupada() || 
				(b.getCasilla(x+2,y-1).estaOcupada() && b.getCasilla(x+2,y-1).getPieza().getColor() != color)))
			        movimiento.add(new Mover(x,y,x+2,y-1));

		if(valido(x+1,y-2) && 
		         (!b.getCasilla(x+1,y-2).estaOcupada() || 
			(b.getCasilla(x+1,y-2).estaOcupada() && b.getCasilla(x+1,y-2).getPieza().getColor() != color)))
			movimiento.add(new Mover(x,y,x+1,y-2));

		if(valido(x-1,y-2) && 
		         (!b.getCasilla(x-1,y-2).estaOcupada() || 
			(b.getCasilla(x-1,y-2).estaOcupada() && b.getCasilla(x-1,y-2).getPieza().getColor() != color)))
			movimiento.add(new Mover(x,y,x-1,y-2));


		if(valido(x-2,y-1) && 
			(!b.getCasilla(x-2,y-1).estaOcupada() || 
			(b.getCasilla(x-2,y-1).estaOcupada() && b.getCasilla(x-2,y-1).getPieza().getColor() != color)))
			movimiento.add(new Mover(x,y,x-2,y-1));


		if(valido(x-2,y+1) && 
			(!b.getCasilla(x-2,y+1).estaOcupada() || 
			(b.getCasilla(x-2,y+1).estaOcupada() && b.getCasilla(x-2,y+1).getPieza().getColor() != color)))
			movimiento.add(new Mover(x,y,x-2,y+1));


		if(valido(x-1,y+2) && 
			(!b.getCasilla(x-1,y+2).estaOcupada() || 
			(b.getCasilla(x-1,y+2).estaOcupada() && b.getCasilla(x-1,y+2).getPieza().getColor() != color)))
			movimiento.add(new Mover(x,y,x-1,y+2));

		return movimiento;
	}
}
