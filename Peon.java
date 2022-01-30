import java.util.ArrayList;
/**
 * Clase de peon para el juego de ajedrez
 * @author Jesús ALberto Rivera Mora
 */
public class Peon extends Pieza {

	/**
	 * 
	 */
	public Peon(boolean color) {
		super(color);
		val = 1;
	}

	public Peon copiar() {
		return new Peon(color);
	}

	public String toString() {
		if(color == Pieza.BLANCA)
			return "P";
		else
			return "p";
	}

	/**
	 * @param b TABLERO
	 * @param x x COORDENADA
	 * @param y y COORDENADA
	 */
	public ArrayList<Mover> getMovimiento(Tablero b, int x, int y) {
		ArrayList<Mover> movimiento = new ArrayList<Mover>();

		if(color == Pieza.BLANCA) {
			
			if(valido(x,y+1) && !b.getCasilla(x, y+1).estaOcupada())
				movimiento.add(new Mover(x,y,x,y+1));

			
			if(valido(x+1,y+1) && b.getCasilla(x+1, y+1).estaOcupada() && b.getCasilla(x+1, y+1).getPieza().getColor() != color)
				movimiento.add(new Mover(x,y,x+1,y+1));

			if(valido(x-1,y+1) && b.getCasilla(x-1, y+1).estaOcupada() && b.getCasilla(x-1, y+1).getPieza().getColor() != color)
				movimiento.add(new Mover(x,y,x-1,y+1));
		}
		else {
		
			if(valido(x,y-1) && !b.getCasilla(x, y-1).estaOcupada())
				movimiento.add(new Mover(x,y,x,y-1));

			if(valido(x+1,y-1) && b.getCasilla(x+1, y-1).estaOcupada() && b.getCasilla(x+1, y-1).getPieza().getColor() != color)
				movimiento.add(new Mover(x,y,x+1,y-1));

		
			if(valido(x-1,y-1) && b.getCasilla(x-1, y-1).estaOcupada() && b.getCasilla(x-1, y-1).getPieza().getColor() != color)
				movimiento.add(new Mover(x,y,x-1,y-1));
		}

		return movimiento;
	}
}
