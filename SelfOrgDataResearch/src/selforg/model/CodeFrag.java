package selforg.model;

public class CodeFrag {
    public final static int UDSmalDisp = 1; // ���������� ������������� ���������� ������������ � ����� ���������
    public final static int UDLargeDisp = 2; // ���������� ������������� ���������� ������������ � ������� ���������
    public final static int HQSERV = 3; // ������ ������������ � ������� �������� ������� 
    public final static int NORMALSERV = 4; // ��������� ���������� �������, ����� ���������� ���� �� ������������ � ��������� �����
    public final static int BADSERV = 5; // ������� ���������� �������, ����� ���������� ���� �� ������������ � ��������� �����
    public final static int OFTENQUERY = 6; // ������ �������
    public final static int SELDOMQUERY = 7; // ������ �������
    //----------------------------------------
	
    public static String dbname = "dm_codefrag"; //��� ������� ��
	private int id_codefrag;
    private int id_oltp_codefrag;    
    private String name;

    //----------------------------------------
    // ��������� ��� ����������
    private int pause_law; //��� ���������� ��������� ����� ����������������� ������������ ��������� ����
                            // 1 - ����������� � ��������� ���������
                            // 2 - ����������� � ������� ���������                            
    private int processing_law;         //��� ������� ���������� ��������� ����
    private int processing_control_time; //���������� ����� ��������� ����
    private int servtype; // ��� (�������� ������������)
    //----------------------------------------
    public CodeFrag (int id_codefrag, String name, int pause_law, int processing_law, int processing_control_time){
    	this.id_codefrag = id_codefrag;
    	this.name = name; 
    	this.setPause_law(pause_law); 
    	this.setProcessing_law(processing_law); 
        this.setProcessing_control_time(processing_control_time); 
    };
    //���������� �����������
    public CodeFrag (int id_codefrag, int servtype, int processing_control_time){
    	this.id_codefrag = id_codefrag;
    	this.setServtype(servtype);
    	this.setProcessing_control_time(processing_control_time); 
    };
    //----------------------------------------    
    public int getId_codefrag() {
		return id_codefrag;
	}
	public void setId_codefrag(int id_codefrag) {
		this.id_codefrag = id_codefrag;
	}
	public int getId_oltp_codefrag() {
		return id_oltp_codefrag;
	}
	public void setId_oltp_codefrag(int id_oltp_codefrag) {
		this.id_oltp_codefrag = id_oltp_codefrag;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getServtype() {
		return servtype;
	}
	public void setServtype(int servtype) {
		this.servtype = servtype;
	}
	public int getProcessing_control_time() {
		return processing_control_time;
	}
	public void setProcessing_control_time(int processing_control_time) {
		this.processing_control_time = processing_control_time;
	}
	public int getProcessing_law() {
		return processing_law;
	}
	public void setProcessing_law(int processing_law) {
		this.processing_law = processing_law;
	}
	public int getPause_law() {
		return pause_law;
	}
	public void setPause_law(int pause_law) {
		this.pause_law = pause_law;
	}
}
