import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class PersonaDao {

	private Connection obtenerConexion() {
		Connection dbConn;
		String user = "root";
		String password = "";
		String url = "jdbc:mysql://localhost:3306/tp7_ej1";
		try {
			return dbConn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public Persona personaPorId(int id) {
		String sql = "select nombre from personas where id = ?";
		try (Connection conn = obtenerConexion(); PreparedStatement statement = conn.prepareStatement(sql);) {
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			String nombrePersona = null;
			if (result.next()) {
				nombrePersona = result.getString(1);
			}
			return new Persona(id, nombrePersona, new TelefonoProxy<Telefono>(this, id));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Set<Telefono> telefonosDePersonaID(int id) {
		String sql = "select t.numero from telefonos t where t.idpersona = ?";
		try (Connection conn = obtenerConexion(); PreparedStatement statement = conn.prepareStatement(sql);) {
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			/* Sujeto */ Set<Telefono> telefonos = new HashSet<Telefono>(); // SujetoReal
			while (result.next()) {
				telefonos.add(new Telefono(result.getString(1)));
			}
			return telefonos;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
