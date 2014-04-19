package selforg.generator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import selforg.model.CodeFrag;

//��� ������ � ��������� ������
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;

//*******************************************************************************************
//    ����� Generator 1.0 
//    ��������� ����� ��������� �������� ������� 
//    ��������� ������
//    ��������� INSERT �����������
//    ������������ ������ ������, ���������������� ����������� � ������ 1 ������ 
//    �� ������ ������, ������� �������� ���������� ���������� ������ dim_codefrag, fact_monitoringtable �� ����� 
//
//*******************************************************************************************
public class Generator {

	//-----------------------------------
	// �������� ����������
	private int cf_amount; //���������� ����������� �� ���� ���������� 
    private int nd_amount; //���������� �������������� �����    
    private int model_time;//��������� �����
    private String init_time;// ��������� �����
    private int full_time; //������ ����� �������������
    
    private Event current_event;//������� �������
    //----------------------------------------------------------------------------
    //������, �������� sql-�����������
    private String sql_initdb;  //������, ���������� ����������� �������� ���������� ������                               
    private StringBuilder sql_ins_dim_codefrag = new StringBuilder(); //��� ������������ � ����� ����������� ������� ������ � dim_codefrag
    //----------------------------------------------------------------------------    
    public List<Event> events = new ArrayList<Event>();  //������ �������
    public List<CodeFrag> cf = new ArrayList<CodeFrag>();//������ ����������� ���������� ����
    
        
    //***********************************************************************
    public Generator(int cf_amount, int nd_amount, int full_time, String init_time){
		 //������������� ������� �������������
		 this.full_time = full_time;
		 this.cf_amount = cf_amount;
		 this.nd_amount = nd_amount;
		 this.model_time = 0;
		 this.init_time = init_time; 
		 
		 //���������� ������ ����� ����������, ������������ ����������� ��������� ����	 
		 
		 for(int i=0; i < cf_amount; i++){
			 cf.add(new CodeFrag( i, 
					              3 + (int)(Math.random() * ((5 - 3) + 1)), 
               					  10 + (int)(Math.random() * ((100 - 10) + 1))) );			
			 
			 sql_ins_dim_codefrag.append("INSERT INTO dim_codefrag(id_codefrag, id_oltp_codefrag, id_master_codefrag, name) " +
			 		                     " VALUES (");
			 sql_ins_dim_codefrag.append(i); sql_ins_dim_codefrag.append(", "); 
			 sql_ins_dim_codefrag.append(i);			 
			 sql_ins_dim_codefrag.append(", NULL, 'cl_");
			 sql_ins_dim_codefrag.append(i);
			 sql_ins_dim_codefrag.append("');");
			 			 			  
			 };
	
		 //������� ������� ������ � �������-��������� ���������� ����
		 this.sql_initdb="DELETE FROM fact_monitoringtable; DELETE FROM dim_codefrag; DELETE FROM dim_time;";
	  };
    //***********************************************************************	
	public void SetCurrentEvent(Event current_event){this.current_event = current_event;};
	public void SetModelTime(int new_time){this.model_time = new_time;};
	
	public int GetModelTime(){return model_time;};
	public int GetFullTime(){return full_time;};
	public int GetCurrentEventId(){return current_event.get_id();};
	public int GetCurrentEventPlanTime(){return current_event.get_plan_time();};		
	public int GetCurrentIdCodeFrag(){return current_event.getId_codefrag();};
    //***********************************************************************
    public void PlanEvent(int id_ev, int plan_time, int id_codefrag) {
    	//�������� �������
    	events.add(new Event(id_ev, plan_time, id_codefrag));  
    	//���������� �� ������� ���������
    	Collections.sort(events);   
    };
    //��� �������� ���������� ������� �����������
    public void PlanEvent(int id_ev, int plan_time) {
    	//�������� �������
    	events.add(new Event(id_ev, plan_time,-1));   // 0 ��������� �������� (�� ������ ��������� ����)
    	//���������� �� ������� ���������
    	Collections.sort(events);   
    };
    //***********************************************************************
    //������� �������, ������� ��������� ������ ����
    public void GetEvent() {
    	//��������� ������, ������������� ������
    	this.current_event = events.get(0);
    	//������� ������ ������� ������ �������
    	events.remove(0);    	
    };
    //***********************************************************************
    //�� ������ ��������� ���� �������� ��� �������� � ���������� ������ ������� ���������� ����������
    public int GenExeTime(int j){
    	int t0 = 0;
    	
    	//����������� ��������� ����� �� 0 �� 100
		double rnd = Math.random();
		int ct = cf.get(j).getProcessing_control_time();
		
		switch (cf.get(j).getServtype()) {
		case CodeFrag.HQSERV: {
			 t0 = (int)(ct-Math.random()*20);
			 break;
		 } //������������� ��������� �� ��������� ����������� ��������
		case CodeFrag.NORMALSERV:  {
			if (rnd<0.2){
				  t0 = (int)(ct-Math.random()*20);
				} 
			else 
			    {
				  t0 = (int)(ct+Math.random()*10);
				};
				break;	
		}
		case CodeFrag.BADSERV: {	
			if (rnd<0.85){
			  t0 = (int)(ct-Math.random()*20);
			} 
		else 
		    {
			  t0 = (int)(ct+Math.random()*10);
			};}			
		};
		System.out.println("j = "+j+";  t0 = "+t0); //������� ��� �������� �������� ������� ���������
		return t0;
    };
    
    //***********************************************************************
    public void InitCF(){
    	int t;
    	for (int j=0; j<cf_amount; j++){
    		t = GenExeTime(j);
			
			PlanEvent(Event.EV_ENTER, t, j); //��������� ������� ����������� ���� ���������� �� ���������
		};
      
    } 
    //***********************************************************************
    public void EventsTest(){
    	int i=0;
    	do{
    		i++;
    	    //PlanEvent(1 + (int)(Math.random() * ((2 - 1) + 1)),1 + (int)(Math.random() * ((30 - 1) + 1)),1);	   	    
    	}
    	while (i<10);	
    	//..........................................
    	for(Event e: events){
    		 System.out.println(e.get_id()+"  "+e.get_plan_time());};
    		 System.out.println("******************************");	 
    	//������� 3 �������
    		 GetEvent();  GetEvent();  GetEvent();  	 
    			//..........................................
    	    	for(Event e: events){
    	    		 System.out.println(e.get_id()+"  "+e.get_plan_time());}; 
    };
    //***********************************************************************
    // class       1    
    // codefrag    9, 10
    // eventtype:  1-start 2-finish
    // session, time � monitoringtable ����������� �����������
    // ��������� ������� ����� 1 
    //***********************************************************************
    
    
	public void Step(){
		int i; //������� �������������� �������
		int cf;//��� �������� ���������
		int pl_time=0; //��������������� �����
		int timekey=6; //������������� ������ �������
		
		PrintWriter pwr = null;
		
		//��������� ������ �� ������� � ������� ������
		Date d = new Date();
		SimpleDateFormat f = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");		
		 try
		         {
		              pwr = new PrintWriter(new FileOutputStream("d:\\PGScript.txt"));
		          }
		        catch(FileNotFoundException e)
		          {
		              System.out.println("������ �������� ����� PGScript");
		              System.exit(0);
		          }
		 //pwr.println("INSERT INTO dim_session (name) VALUES ('session_"+(String)f.format(d)+"')"); // ������� ID = 1 � �� 
		 
		//��������� ������ ������� ��� ���� ���������� ���� 
		InitCF();
		//��������� ������� ���������� ����� ������
		PlanEvent(3,GetFullTime());
		
		//����� ������������� ������ �� �������, ���������� �������� fact_monitoringtable � dim_codefrag �� ���������� ������
		pwr.println(sql_initdb);
		//���������� ������������ �������-��������� ���������� ���� ������ �������		
		pwr.println(sql_ins_dim_codefrag.toString());
		
		do{
		//	���� �� ��������
		GetEvent();                               //������� ����� �������
		i = GetCurrentEventId();                  //�������� ��� �������
		SetModelTime(GetCurrentEventPlanTime());  //�������� ��������� �����  
		cf = GetCurrentIdCodeFrag();
		if (cf!=-1)                               //��������� ��������, �� �� ���������� �������� ���� � ��� ���� ��������� �����   
			 pl_time = GenExeTime(cf);
		
		//pwr.println("Case_"+i+";  "+GetModelTime()+" "+GetFullTime());	
       
		switch(i){ 
		  		case Event.EV_ENTER:{
		  			 System.out.println("Case 1 "+GetModelTime()+" "+GetFullTime());		  			
		  			 PlanEvent(2,(GetModelTime()+pl_time),cf);
		  			 
		  			 //����� ������ ���������
		  			 timekey++;
		  			 pwr.println("INSERT INTO dim_time (datetime, year, quarter, month, week, day, hour, minute, second, milisecond, id_time) " +
		  			 		                   "VALUES ("+GetModelTime()+", 2014, 0, 0, 0, 0, 0, 0, 0, 0, "+timekey+");");
		  			 //����� ����� ��������� (��� ��� ��� �������� ��������������� ����� ���������)
		  			 timekey++;
		  			 pwr.println("INSERT INTO dim_time (datetime, year, quarter, month, week, day, hour, minute, second, milisecond, id_time) " +
	 		                   "VALUES ("+(int)(GetModelTime()+pl_time)+", 2014, 0, 0, 0, 0, 0, 0, 0, 0, "+timekey+");");
		  			 
		  			 //���� ���������
		  			 pwr.println("INSERT INTO fact_monitoringtable (id_session, id_class, id_codefrag, id_eventtype," +
		  			 		" id_hardwareplatformtype,  id_hypervisor, id_hypervisortype," +
		  			 		" id_nodetype, id_operationsystem, id_operationsystemcore, id_package," +
		  			 		" id_processvirtualmachine, id_processvirtualmachinetype, id_software," +
		  			 		" id_virtualmachine, id_start_time, id_stop_time, delta_t, avg_time) " +
	 		                   "VALUES (1, 1, "+cf+", 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, "+(timekey-1)+", "+timekey+", "+pl_time+", 0);");
		  			 break;
		  			 
		  			 
		  			//class       1    
		  		    // codefrag    9, 10
		  		    // eventtype:  1-start 2-finish
		  		    // session, time � monitoringtable ����������� �����������
		  		    // ��������� ������� ����� 1 
		  			 
		  			 
		  		}
		  		case Event.EV_FINISH_WORK:{
		  			 System.out.println("Case 2 "+GetModelTime()+" "+GetFullTime());
		  			 PlanEvent(1,GetModelTime()+GenExeTime(cf)+25,cf);
		  			 break;
		  		}
		  		case Event.EV_CLOSE_SESSION:{
		  			 System.out.println("Case 3___stop   "+GetModelTime()+" "+GetFullTime());
		  			 //PlanEvent(1 + (int)(Math.random() * ((2 - 1) + 1)),GetModelTime()+20);		  		
		  		}		  		
			}
		}	
		while (i != 3);
		pwr.close();
	};
	 //*********************************************************************** 
	
	

}
