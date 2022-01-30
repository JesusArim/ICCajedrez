/**
 * Clase abstracta Jugador
 * @author Jesus Rivera
 * @version 
 */
public abstract  class Jugador {
        protected boolean color;
	/**
	 * @param color
	 *     
	 */
	public Jugador(boolean color) {
	       this.color = color;
	}
	  /**
	   * 
	   */
	   public boolean getColor(){
	      return color;
	   }
	  /**
	   *
	   */
	    public void setColor(boolean color){
	         this.color = color;
	    }
        /**
	 * 
	 * 
	 *  @param b
	 *  @return siguiente movimiento
	 */
	public Mover getSegMover(Tablero b) {
		return null;
	}

}
