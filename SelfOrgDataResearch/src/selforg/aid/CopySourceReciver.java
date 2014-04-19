package selforg.aid;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;


public class CopySourceReciver {
private char sourceType;     //Тип источника: массив, текстовый файл 
private char reciverType;    //Тип приемника: массив, текстовый файл
private char dataType;       //Тип передаваемых данных: число, строка


//Копируем из массива целых чисел в массив целых чисел или в текстовый файл
//mode - режим копирования: 
//       (побайтово с выводом каждого шага: b, 
//        массив целиком: a)
//
public static void DataCopy(byte source[], byte reciver[]){

  try{	 
	ByteArrayInputStream bai = new ByteArrayInputStream(source);
	ByteArrayOutputStream bao = new ByteArrayOutputStream();
	
	   byte r = (byte)bai.read(); //по умолчанию возвращается int	
	   while ( r > -1  ) {         //возврат -1 означает, что элементов нет 
		  bao.write(r);
		  System.out.println(r);
		  r = (byte)bai.read();
		};
	  reciver = bao.toByteArray(); 
	 	  
	  bao.close(); // закрываем входной и выходной потоки	  
	  bai.close();
  } catch (IOException e) {
	   System.out.println("Ошибка ввода/вывода: " + e.toString());
  } finally{
	// выводим записанные данные
		  for (short i=0;i<reciver.length;i++){
			  System.out.print(reciver[i]+" ");
		  };	  
	};
};

//Копируем из массива дробных чисел в массив целых чисел или в текстовый файл
public static void DataCopy(byte source[], String filePath){
	try{
		FileOutputStream fos = new FileOutputStream (filePath); 
		fos.write(source);
		fos.close();
	} catch (IOException e) {
		   System.out.println("Ошибка ввода/вывода: " + e.toString());	   
		
	};  	
};

//Копируем данные из файла на экран или в формат csv в текстовом режиме
//Второй параметр отсутствует
//параметр mode определяет режим обмена данными: b - байтовый, c - символьный
//параметр, определяющий приемник: s - экран или c - csv файл

public static void DataCopy(String filePath, char mode, char dest){	

byte[] readed = new byte[6];
if (mode == 'b'){
 try{
	   FileInputStream fis = new FileInputStream(filePath);
	   //Определяем объем считываемых данных
	   int av = fis.available();
	   System.out.println(av);
	   //System.out.println("Будет считано "+fis.available()+" байт");	   
	   //В операции вывода на экран считываем данные из файла	   
	   //System.out.println("Считано "+fis.read(readed, 0, av) +" байт из "+av);
	   
	   while (av>0){
		   for (int i=0; i<=9;i++){
			   //System.out.println((byte)fis.read()+" осталось: "+fis.available()+"av = "+av);
			   System.out.print(fis.read()+",");
			   av = fis.available();
		   };		   
		   System.out.println();
	   }; 
	   
	   fis.close();
 } catch (IOException e) {
	   System.out.println("Ошибка ввода/вывода: " + e.toString());	   
 } finally{
	   //выводим записанные данные
		//  for (short i=0;i<readed.length;i++){
		//	  System.out.print(readed[i]+" ");
     //};  	
 };
 
};
};
//Метод для чтения данных из базы НПП
//
//

public static void ParseNPP(String filePath){	
		
	int a = 0;
	
	 try{
	
		   RandomAccessFile fis = new RandomAccessFile(filePath, "r"); 
		   //Определяем объем считываемых данных
		   		   
		   byte[] rec_b = new byte[10];		   
		   int bytesRead;
		   
		   while ((bytesRead = fis.read(rec_b,0, rec_b.length))>0){
			   //   for (int i=0; i<=9;i++){		             				   
				   //System.out.print(fis.read(rec_b)+",");
				   //fis.read(rec_b,fis.getFilePointer(),10);
				   //av = fis.available();
			   System.out.print("Считано "+bytesRead+" байт || ");
			   for (int i=0;i<=9;i++){
				   System.out.println(Integer.toBinaryString(rec_b[i])+" ----- "+rec_b[i]);
			   };
			   System.out.println("*******************************************************");
			   System.out.println("*******************************************************");
			   };		   
			  
		   
		   fis.close();
	 } catch (IOException e) {
		   System.out.println("Ошибка ввода/вывода: " + e.toString());	   
	 } finally{
		   	
	 };
	
};

};