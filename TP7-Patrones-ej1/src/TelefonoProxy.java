import java.util.HashSet;
import java.util.Set;

public class TelefonoProxy<E> extends HashSet<E> {

	private Set<Telefono> telefonos;
	private int id;
	private PersonaDao persona;

	public TelefonoProxy(PersonaDao persona, int id) {
		this.id = id;
		this.persona = persona;

	}

	@Override
	public <T> T[] toArray(T[] a) {
		this.telefonos = persona.telefonosDePersonaID(this.id);
		return telefonos.toArray(a);
	}

}
