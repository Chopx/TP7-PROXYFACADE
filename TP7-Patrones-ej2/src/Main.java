import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		DBFacade facade = new FacadeImplement();

		String sql = "select * from personas p left join telefonos t on (p.id = t.idpersona)";

		List<String[]> lista = facade.queryResultAsArray(sql);

		for (String[] strings : lista) {
			System.out.println(Arrays.deepToString(strings));
		}

		List<Map<String, String>> listaValores = facade.queryResultAsAsociation(sql);
		System.out.println(listaValores);

	}

}
