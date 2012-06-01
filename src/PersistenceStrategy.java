
public interface PersistenceStrategy {

	public void save(Square squares[][]) throws PersistenceException;
	public Square[][] load() throws PersistenceException;
	
}
