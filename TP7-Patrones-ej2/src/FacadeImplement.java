import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FacadeImplement implements DBFacade {

	private static final String DBuser = "root";
	private static final String DBpwd = "";
	private static final String DBurl = "jdbc:mysql://localhost:3306/tp7_ej1";
	Connection conexion;

	@Override
	public void open() {
		try {
			conexion = DriverManager.getConnection(DBurl, DBuser, DBpwd);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Map<String, String>> queryResultAsAsociation(String sql) {
		open();
		try (PreparedStatement statement = conexion.prepareStatement(sql);) {
			ResultSet result = statement.executeQuery();
			ResultSetMetaData rsmd = result.getMetaData();
			List<Map<String, String>> listaValores = new ArrayList<Map<String, String>>();

			// Map<String, String> columnaValor = new HashMap<>();
			// List<HashMap<String, String>> filas = new ArrayList<>();
			while (result.next()) {
				for (int x = 1; x <= rsmd.getColumnCount(); x++) {
					String valor1 = rsmd.getColumnName(x);
					String valor2 = result.getString(x);
					Map<String, String> columnaValor = new HashMap<>();
					columnaValor.put(valor1, valor2);
					listaValores.add((HashMap<String, String>) columnaValor);
				}
			}
			close();
			return listaValores;
		} catch (RuntimeException re) {
			throw new RuntimeException(re);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	/**
	 * Ejecuta una sentencia SQL.
	 *
	 * @return una Lista de filas, donde cada fila es un Mapa con nombreColumna =>
	 *         valorColumna. O una lista vacia.
	 * @throws RuntimeException si la conexión no esta abierta o existe un error en
	 *                          la sentencia sql
	 *
	 *
	 * 
	 */

	@Override
	public List<String[]> queryResultAsArray(String sql) {
		open();
		try (PreparedStatement statement = conexion.prepareStatement(sql);) {
			ResultSet result = statement.executeQuery();
			ResultSetMetaData rsmd = result.getMetaData();
			List<String[]> arreglo = new ArrayList<>();

			while (result.next()) {
				for (int x = 1; x <= rsmd.getColumnCount(); x++) {
					String[] valores = { rsmd.getColumnName(x), result.getString(x) };
					arreglo.add(valores);
				}
			}
			close();
			return arreglo;
		} catch (RuntimeException re) {
			throw new RuntimeException(re);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Ejecuta una sentencia SQL.
	 *
	 * @return una Lista de filas, donde cada fila es una arreglo. O una lista
	 *         vacia.
	 *
	 * @throws RuntimeException si la conexión no esta abierta o existe un error en
	 *                          la sentencia sql
	 */

	@Override
	public void close() {
		try {
			conexion.close();
		} catch (Exception exception) {
			// TODO: handle exception
			System.out.println("Error cerrando la conexion");
		}
	}

}
