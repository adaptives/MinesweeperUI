import java.io.IOException;
import java.util.Properties;

/**
 * 
 */

/**
 * @author pshah
 *
 */
public class FileSystemConnectionFactoryProvider {
	
	public static IFileSystemConnectionFactory getFileSystemConnectionFactory() throws Exception {
		String fqn = getFileSystemConnectionFactoryFQN();
		Class<? extends IFileSystemConnectionFactory> clazz = Class.forName(fqn).asSubclass(IFileSystemConnectionFactory.class);
		IFileSystemConnectionFactory factory = clazz.newInstance();
		return factory;
	}

	private static String getFileSystemConnectionFactoryFQN() throws IOException {
		Properties providerProps = new Properties();
		providerProps.load(FileSystemConnectionFactoryProvider.class.getResourceAsStream("provider.properties"));
		return providerProps.getProperty("filesystem.connection.factory");
	}

	
}
