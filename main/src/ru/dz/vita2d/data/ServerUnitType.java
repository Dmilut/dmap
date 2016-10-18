package ru.dz.vita2d.data;

/**
 * This is actually a string with a server unitType name
 * @author dz
 *
 */
public class ServerUnitType 
{
	static final public ServerUnitType OBJECTS = new ServerUnitType("obj","������");
	static final public ServerUnitType MEANS = new ServerUnitType("mean","��������");
	static final public ServerUnitType JOBS = new ServerUnitType("job","������");
	static final public ServerUnitType EVENTS = new ServerUnitType("event","�������");
	
	
	private final String plural;
	private final String single;
	private final String displayName;
	
	private ServerUnitType(String name, String displayName) {
		this.displayName = displayName;
		plural = name+"s";
		single = name;
	}
	
	/**
	 * Single form.
	 * @return type name as in JSON data.
	 */
	public String getObjectTypeName() { return single; }
	
	/**
	 * Plural form.
	 * @return type name as in requests.
	 */
	public String getPluralTypeName() { return plural; }
	
	public String getDisplayName() {		return displayName;	}

	@Override
	public String toString() {
		return plural;
	}
	
}
