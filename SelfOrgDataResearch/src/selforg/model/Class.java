package selforg.model;

public class Class {
	public static String dbname = "dm_class"; //имя таблицы БД
    private int id_class;
    private int id_oltp_class;
    private String name;
    
	public int getId_oltp_class() {
		return id_oltp_class;
	}
	public void setId_oltp_class(int id_oltp_class) {
		this.id_oltp_class = id_oltp_class;
	}
	public int getId_class() {
		return id_class;
	}
	public void setId_class(int id_class) {
		this.id_class = id_class;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
