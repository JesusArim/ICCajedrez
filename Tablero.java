import java.util.ArrayList;

/**
 * Clase que representa un tablero de ajedrez
 * @author Jesús Alberto Rivera Mora
 * @version 1.0
 */
public class Tablero {
	public static final int a=0, b=1, c=2, d=3, e=4, f=5;

	private Casilla[][] casillas;
	/**	 
	 *	 6     t c q t c t
	 *	 5     p p p p p p  
	 *	 4     . . . . . .  
	 *	 3     . . . . . .  
	 *	 2     P P P P P P  
	 *	 1     T C Q T C T
	 *    	        
	 *    	       a b c d e f 
	 *    
	 * P=peon, R=rey, Q=reina, T=torre, C=caballo,
	 */

	public Tablero(Casilla[][] casillas) {
		this.casillas = casillas;
	}

	/**
	 * 
	 */
	public Tablero() {
		boolean co = Pieza.BLANCA;
		casillas = new Casilla[6][6];
		casillas[a][1-1] = new Casilla(new Torre(co));
		casillas[b][1-1] = new Casilla(new Caballo(co));
		casillas[c][1-1] = new Casilla(new Reina(co));
		casillas[d][1-1] = new Casilla(new Rey(co));
		casillas[e][1-1] = new Casilla(new Caballo(co));
		casillas[f][1-1] = new Casilla(new Torre(co));

		for(int i = 0; i < 6; i++) {
			casillas[i][2-1] = new Casilla(new Peon(co));
		}

		for(int i = 2; i < 5; i++) {
			for(int j = 0; j < 6; j++) {
				casillas[j][i] = new Casilla();
			}
		}

		co = Pieza.NEGRA;
	        casillas[a][6-1] = new Casilla(new Torre(co));
		casillas[b][6-1] = new Casilla(new Caballo(co));
		casillas[c][6-1] = new Casilla(new Reina(co));
		casillas[d][6-1] = new Casilla(new Rey(co));
		casillas[e][6-1] = new Casilla(new Caballo(co));
		casillas[f][6-1] = new Casilla(new Torre(co));

		for(int i = 0; i < 6; i++) {
			casillas[i][5-1] = new Casilla(new Peon(co));
		}
	}

	/**
	 * 
	 */
	public static void main(String[] args) {
		Tablero tablero = new Tablero();
		System.out.println(tablero);
	}

	public String toString() {
		String str = "";
		for(int i = 5; i >= 0; i--) {
			str += (i+1) + "  ";
			for(int j = 0; j < 6; j++) {
				str += casillas[j][i] + " ";
			}
			str += "\n";
		}

		str += "\n   a b c d e f ";

		return str;
	}

	public ArrayList<Mover> getMovimiento(boolean color) {
		return getMovimiento(color, true);
	}


	/**
	 *
	 * 
	 * @param color
	 */
	public boolean verificar(boolean color) {
		int x = -1, y = -1;
		for(int i = 0; i < 6; i++)
			for(int j = 0; j < 6; j++) {
				if(casillas[i][j].estaOcupada() && 
						casillas[i][j].getPieza().getColor() == color &&
						casillas[i][j].getPieza().toString().equalsIgnoreCase("K")) {
					x = i; y = j;
				}
			}

		ArrayList<Mover> movimientoOponente = getMovimiento(!color, false);

		for(int j = 0; j < movimientoOponente.size(); j++) {
			if(movimientoOponente.get(j).getX2() == x && movimientoOponente.get(j).getY2() == y)
				return true;
		}

		return false;	
	}

	/**
	 * 
	 * @param color
         * @param movimiento
	 */
	public boolean verificar(boolean color, ArrayList<Mover> movimiento) {

		Casilla[][] newCasillas = getCasillasAfter(movimiento);

		int x = -1, y = -1;
		for(int i = 0; i < 6; i++)
			for(int j = 0; j < 6; j++) {
				if(newCasillas[i][j].estaOcupada() && 
						newCasillas[i][j].getPieza().getColor() == color &&
						newCasillas[i][j].getPieza().toString().equalsIgnoreCase("K")) {
					x = i; y = j;
				}
			}

		ArrayList<Mover> movimientoOponente = getMovimientoAfter(!color, movimiento, false);

		for(int j = 0; j < movimientoOponente.size(); j++) {
			if(movimientoOponente.get(j).getX2() == x && movimientoOponente.get(j).getY2() == y)
				return true;
		}

		return false;	
	}

	public ArrayList<Mover> getMovimiento(boolean color, boolean verificarVerificar) {
		ArrayList<Mover> movimiento = new ArrayList<>();

		for(int i = 0; i < 6; i++)
			for(int j = 0; j < 6; j++) {
				if(casillas[i][j].estaOcupada() && 
						casillas[i][j].getPieza().getColor() == color) {
					movimiento.addAll(casillas[i][j].getPieza().getMovimiento(this, i, j));
				}
			}
		if(verificarVerificar) {
			int x = -1, y = -1;
			for(int i = 0; i < 6; i++)
				for(int j = 0; j < 6; j++) {
					if(casillas[i][j].estaOcupada() && 
							casillas[i][j].getPieza().getColor() == color &&
							casillas[i][j].getPieza().toString().equalsIgnoreCase("R")) {
						x = i; y = j;
					}
				}
			ArrayList<Mover> removeThese = new ArrayList<Mover>();
			for(int i = 0; i < movimiento.size(); i++) {
				ArrayList<Mover> verificarThis = new ArrayList<Mover>(movimiento.subList(i, i+1));
				ArrayList<Mover> movimientoOponente = getMovimientoAfter(!color, verificarThis, false);

				int xActualizar = x, yActualizar = y;
				if(verificarThis.get(0).getX1() == x && verificarThis.get(0).getY1() == y) {
					xActualizar = verificarThis.get(0).getX2();
					yActualizar = verificarThis.get(0).getY2();
				}

				for(int j = 0; j < movimientoOponente.size(); j++) {
					if(movimientoOponente.get(j).getX2() == xActualizar && movimientoOponente.get(j).getY2() == yActualizar)
						removeThese.add(verificarThis.get(0));
				}
			}

			movimiento.removeAll(removeThese); 
		}

		return movimiento;
	}

	public ArrayList<Mover> getMovimientoAfter(boolean color, ArrayList<Mover> movimiento) {
		return getMovimientoAfter(color, movimiento, true);
	}

	public ArrayList<Mover> getMovimientoAfter(boolean color, ArrayList<Mover> movimiento, boolean verificarVerificar) {
                Casilla[][] temp = new Casilla[6][6];
		for (int y = 0; y < 6; y++) {
			for (int x = 0; x < 6; x++) {
				temp[x][y] = new Casilla(this.casillas[x][y]);
			}
		}

		Tablero b = new Tablero(temp);

		for(int i = 0; i < movimiento.size(); i++)
			b.ejecMover(movimiento.get(i));

		ArrayList<Mover> siguienteMovimiento = b.getMovimiento(color, verificarVerificar);

		return siguienteMovimiento;
	}

	public Casilla[][] getCasillasAfter(ArrayList<Mover> movimiento) {

		Casilla[][] temp = new Casilla[6][6];
		for (int y = 0; y < 6; y++) {
			for (int x = 0; x < 6; x++) {
				temp[x][y] = new Casilla(this.casillas[x][y]);
			}
		}

		Tablero b = new Tablero(temp);

		for(int i = 0; i < movimiento.size(); i++)
			b.ejecMover(movimiento.get(i));

		Casilla[][] temp2 = new Casilla[6][6];
		for (int y = 0; y < 6; y++) {
			for (int x = 0; x < 6; x++) {
				temp2[x][y] = new Casilla(b.getCasilla(x, y));
			}
		}

		return temp2;
	}

	/**
	 *
	 */
	public int ejecMover(Mover m) {
		Casilla CasillaPrev = casillas[m.getX1()][m.getY1()];

		casillas[m.getX2()][m.getY2()] = casillas[m.getX1()][m.getY1()];
		casillas[m.getX1()][m.getY1()] = new Casilla();

		if(m.estaEnrocada()) {
			if(m.getX2() == e && m.getY2() == 1-1) {
				casillas[d][1-1] = casillas[f][1-1];
				casillas[f][1-1] = new Casilla();
			}
			if(m.getX2() == b && m.getY2() == 1-1) {
				casillas[c][1-1] = casillas[a][1-1];
				casillas[a][1-1] = new Casilla();			
			}
			if(m.getX2() == e && m.getY2() == 6-1) {
				casillas[d][6-1] = casillas[f][6-1];
				casillas[f][6-1] = new Casilla();
			}
			if(m.getX2() == b && m.getY2() == 6-1) {
				casillas[c][6-1] = casillas[a][6-1];
				casillas[a][6-1] = new Casilla();	
			}
		}

		if(CasillaPrev.getPieza().toString().equals("P") && m.getY2() == 6-1)
			casillas[m.getX2()][m.getY2()] = new Casilla(new Reina(Pieza.BLANCA));

		if(CasillaPrev.getPieza().toString().equals("p") && m.getY2() == 1-1)
			casillas[m.getX2()][m.getY2()] = new Casilla(new Reina(Pieza.NEGRA));

		return 0;
	}

	public Casilla getCasilla(int x, int y) {
		return casillas[x][y];
	}

}
