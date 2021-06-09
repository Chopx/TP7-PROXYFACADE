import java.util.Arrays;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		DBFacade facadeahre = new FacadeImplement();

		String sql = "select * from personas p left join telefonos t on (p.id = t.idpersona)";

		List<String[]> ahre = facadeahre.queryResultAsArray(sql);

		for (String[] strings : ahre) {
			System.out.println(Arrays.deepToString(strings));
		}

		// List<Map<String, String>> listaValores =
		// facadeahre.queryResultAsAsociation(sql);

		// System.out.println(listaValores);

	}

}
