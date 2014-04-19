package selforg.model;

public class Sortware {
	
	public static String dbname = "dm_software"; //имя таблицы БД
    private int id_software;
    private int id_oltp_software;
    private String name;
    
	public int getId_software() {
		return id_software;
	}
	public void setId_software(int id_software) {
		this.id_software = id_software;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId_oltp_software() {
		return id_oltp_software;
	}
	public void setId_oltp_software(int id_oltp_software) {
		this.id_oltp_software = id_oltp_software;
	}
    
    
	
}
