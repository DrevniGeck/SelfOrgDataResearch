package selforg.generator;

public class Event implements Comparable{
   public final static int EV_ENTER = 1; 	 //Событие вход новой заяки
   public final static int EV_FINISH_WORK = 2;//Событие завершение обработки
   public final static int EV_CLOSE_SESSION = 3; //Событие завершение сеанса
		   
		   
   private int id;   //Порядковый номер события
   private int plan_time; //Время свершения события
   private int id_codefrag; //Идентификтор фрагмента кода
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
   
   //наследуем для организации сортировки
   @Override
   public int compareTo(Object e) {
       //сортировка 
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
