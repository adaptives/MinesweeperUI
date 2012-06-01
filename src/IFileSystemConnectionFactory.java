import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 
 */

/**
 * @author pshah
 *
 */
public interface IFileSystemConnectionFactory {

	public PrintWriter getWriter() throws IOException;
	public BufferedReader getReader()  throws IOException;
}
