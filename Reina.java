import java.util.ArrayList;

/**
 * Clase para reina ajedrez
 * @author Jesús Alberto Rivera Mora
 * @version 1.0
 */
public class Reina extends Pieza {

	/**
	 * 
	 */
	public Reina(boolean color) {
		super(color);
		val= 6;
	}

	public String toString() {
		if(color == Pieza.BLANCA)
			return "Q";
		else
			return "q";
	}

	public Reina copiar() {
		return new Reina(color);
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
		for(int i = 1; i < 6; i++) {
			if(valido(x+i, y+i)) {
				if(b.getCasilla(x+i, y+i).estaOcupada()) {
					if(b.getCasilla(x+i, y+i).getPieza().color != color)
						movimiento.add(new Mover(x,y,x+i,y+i));	

					break;
				}
				else
					movimiento.add(new Mover(x,y,x+i,y+i));	
			}
		}
		for(int i = 1; i < 6; i++) {
			if(valido(x-i, y+i)) {
				if(b.getCasilla(x-i, y+i).estaOcupada()) {
					if(b.getCasilla(x-i, y+i).getPieza().color != color)
						movimiento.add(new Mover(x,y,x-i,y+i));	

					break;
				}
				else
					movimiento.add(new Mover(x,y,x-i,y+i));	
			}
		}
		for(int i = 1; i < 6; i++) {
			if(valido(x+i, y-i)) {
				if(b.getCasilla(x+i, y-i).estaOcupada()) {
					if(b.getCasilla(x+i, y-i).getPieza().color != color)
						movimiento.add(new Mover(x,y,x+i,y-i));	

					break;
				}
				else
					movimiento.add(new Mover(x,y,x+i,y-i));	
			}
		}
		for(int i = 1; i < 6; i++) {
			if(valido(x-i, y-i)) {
				if(b.getCasilla(x-i, y-i).estaOcupada()) {
					if(b.getCasilla(x-i, y-i).getPieza().color != color)
						movimiento.add(new Mover(x,y,x-i,y-i));	

					break;
				}
				else
					movimiento.add(new Mover(x,y,x-i,y-i));	
			}
		}

		return movimiento;
	}
}

