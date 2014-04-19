package selforg.aid;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;


public class CopySourceReciver {
private char sourceType;     //��� ���������: ������, ��������� ���� 
private char reciverType;    //��� ���������: ������, ��������� ����
private char dataType;       //��� ������������ ������: �����, ������


//�������� �� ������� ����� ����� � ������ ����� ����� ��� � ��������� ����
//mode - ����� �����������: 
//       (��������� � ������� ������� ����: b, 
//        ������ �������: a)
//
public static void DataCopy(byte source[], byte reciver[]){

  try{	 
	ByteArrayInputStream bai = new ByteArrayInputStream(source);
	ByteArrayOutputStream bao = new ByteArrayOutputStream();
	
	   byte r = (byte)bai.read(); //�� ��������� ������������ int	
	   while ( r > -1  ) {         //������� -1 ��������, ��� ��������� ��� 
		  bao.write(r);
		  System.out.println(r);
		  r = (byte)bai.read();
		};
	  reciver = bao.toByteArray(); 
	 	  
	  bao.close(); // ��������� ������� � �������� ������	  
	  bai.close();
  } catch (IOException e) {
	   System.out.println("������ �����/������: " + e.toString());
  } finally{
	// ������� ���������� ������
		  for (short i=0;i<reciver.length;i++){
			  System.out.print(reciver[i]+" ");
		  };	  
	};
};

//�������� �� ������� ������� ����� � ������ ����� ����� ��� � ��������� ����
public static void DataCopy(byte source[], String filePath){
	try{
		FileOutputStream fos = new FileOutputStream (filePath); 
		fos.write(source);
		fos.close();
	} catch (IOException e) {
		   System.out.println("������ �����/������: " + e.toString());	   
		
	};  	
};

//�������� ������ �� ����� �� ����� ��� � ������ csv � ��������� ������
//������ �������� �����������
//�������� mode ���������� ����� ������ �������: b - ��������, c - ����������
//��������, ������������ ��������: s - ����� ��� c - csv ����

public static void DataCopy(String filePath, char mode, char dest){	

byte[] readed = new byte[6];
if (mode == 'b'){
 try{
	   FileInputStream fis = new FileInputStream(filePath);
	   //���������� ����� ����������� ������
	   int av = fis.available();
	   System.out.println(av);
	   //System.out.println("����� ������� "+fis.available()+" ����");	   
	   //� �������� ������ �� ����� ��������� ������ �� �����	   
	   //System.out.println("������� "+fis.read(readed, 0, av) +" ���� �� "+av);
	   
	   while (av>0){
		   for (int i=0; i<=9;i++){
			   //System.out.println((byte)fis.read()+" ��������: "+fis.available()+"av = "+av);
			   System.out.print(fis.read()+",");
			   av = fis.available();
		   };		   
		   System.out.println();
	   }; 
	   
	   fis.close();
 } catch (IOException e) {
	   System.out.println("������ �����/������: " + e.toString());	   
 } finally{
	   //������� ���������� ������
		//  for (short i=0;i<readed.length;i++){
		//	  System.out.print(readed[i]+" ");
     //};  	
 };
 
};
};
//����� ��� ������ ������ �� ���� ���
//
//

public static void ParseNPP(String filePath){	
		
	int a = 0;
	
	 try{
	
		   RandomAccessFile fis = new RandomAccessFile(filePath, "r"); 
		   //���������� ����� ����������� ������
		   		   
		   byte[] rec_b = new byte[10];		   
		   int bytesRead;
		   
		   while ((bytesRead = fis.read(rec_b,0, rec_b.length))>0){
			   //   for (int i=0; i<=9;i++){		             				   
				   //System.out.print(fis.read(rec_b)+",");
				   //fis.read(rec_b,fis.getFilePointer(),10);
				   //av = fis.available();
			   System.out.print("������� "+bytesRead+" ���� || ");
			   for (int i=0;i<=9;i++){
				   System.out.println(Integer.toBinaryString(rec_b[i])+" ----- "+rec_b[i]);
			   };
			   System.out.println("*******************************************************");
			   System.out.println("*******************************************************");
			   };		   
			  
		   
		   fis.close();
	 } catch (IOException e) {
		   System.out.println("������ �����/������: " + e.toString());	   
	 } finally{
		   	
	 };
	
};

};