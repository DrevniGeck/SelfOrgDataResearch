package selforg.generator;

public class Event implements Comparable{
   public final static int EV_ENTER = 1; 	 //������� ���� ����� �����
   public final static int EV_FINISH_WORK = 2;//������� ���������� ���������
   public final static int EV_CLOSE_SESSION = 3; //������� ���������� ������
		   
		   
   private int id;   //���������� ����� �������
   private int plan_time; //����� ��������� �������
   private int id_codefrag; //������������ ��������� ����
   //----------------------------------------------------
   public Event(int id, int plan_time, int id_codefrag){
       this.id = id;
       this.plan_time = plan_time;
       this.id_codefrag = id_codefrag;
	   };
   //----------------------------------------------------	   
   public int get_id(){
	   return id;
   };	   
   //----------------------------------------------------
   public int get_plan_time(){
	   return plan_time;
   };
   //----------------------------------------------------
   public int getId_codefrag() {
		return id_codefrag;
	}
   //----------------------------------------------------
	public void setId_codefrag(int id_codefrag) {
		this.id_codefrag = id_codefrag;
	}
	//----------------------------------------------------
   
   //��������� ��� ����������� ����������
   @Override
   public int compareTo(Object e) {
       //���������� 
       int res = 0;
       Event compared_event = (Event) e;
       if (compared_event.plan_time < this.plan_time) {
           res = 1;
       }
       if (compared_event.plan_time > this.plan_time) {
           res = -1;
       }
       return res;
   }
   //----------------------------------------------------
   @Override
   public String toString() {
       return "id="+this.id+" value="+this.plan_time;
   }
   //----------------------------------------------------

}
