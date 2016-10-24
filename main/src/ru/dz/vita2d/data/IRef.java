package ru.dz.vita2d.data;

/**
 * <p>Reference to any system data object, such as entity or unit.</p>
 * <p>Can be used to instantiate (load from cache or from server) such an object.</p>
 * 
 * @author dz
 *
 */
public interface IRef {

	public int getId();

	public String serialize();
	public static IRef deserialize(String s)
	{
		switch(s.substring(0, 5))
		{
		//case "unit:": return new UnitRef(s);
		case "enti:": return new EntityRef(s);
		}
		
		return null;
	}
	
	// TODO IEntityDataSource instantiate();
	
}
