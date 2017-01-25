package logica;

public interface TableroInmutable {
	public int GetFil();
	public int GetCol();
	public Ficha GetFicha(int fila, int col);
	public String toString();
}
