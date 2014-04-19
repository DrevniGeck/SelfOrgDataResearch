import selforg.generator.Generator;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
       // TODO Auto-generated method stub
       //Настраиваем и создаем генератор
	   Generator gen = new Generator(5,1,100000, "02.03.2014 7:03:05");
       //Генерируем даннные
       gen.Step();
	   //gen.EventsTest();
	   
	}

}
