package Ex1;
import java.util.ArrayList;
/**

 */
public class MonomTest {

public static void main(String[] args) {
	test1 ();
	test2 ();
	test3 ();
	test4Bad ();
	
		}
	private static void test1() {
		System.out.println("*****  Test1:  *****");
		ArrayList<Monom> monoms = new ArrayList<Monom>();
		monoms.add(new Monom(0,5));
		monoms.add(new Monom(-1,0));
		monoms.add(new Monom(-1.3,1));
     	monoms.add(new Monom(-2.2,2));
     	monoms.add(new Monom(4,2));
     	monoms.add(new Monom(5,0));
     	monoms.add(new Monom(0,3));
     	
		for(int i=0;i<monoms.size();i++) {
			Monom m = new Monom(monoms.get(i));
			String s = m.toString();
			Monom m1 = new Monom(s);
			boolean e = m.equals(m1);
			System.out.println(i+") "+m.toString() +"    \tisZero: "+m.isZero()+"  \teq: "+e);	
			System.out.println();
			
		}
	}
		private static void test2() {
			System.out.println("*****  Test2:  *****");
			
			ArrayList<Monom> monoms = new ArrayList<Monom>();
			
			monoms.add(new Monom(-1,0));
	     	monoms.add(new Monom(-2.2,2));
	     	monoms.add(new Monom(4,2));
	     	

		System.out.println("add function:");
		 Monom m2 = new Monom(monoms.get(1));
		 Monom m3 = new Monom(monoms.get(2));
		 System.out.print(m2.toString()+ " + " +m3.toString()+ " = ");
		 m2.add(m3);
		 System.out.println(m2.toString());
		 Monom m8 = new Monom(monoms.get(1));
		 Monom m9 = new Monom(monoms.get(0));
		 System.out.print(m8.toString()+ " + " +m9.toString()+ " = ");
		 m8.add(m9);
		 System.out.println(m8.toString());
		 System.out.println();
		 
		}

		 
		 private static void test3() {
				System.out.println("*****  Test3:  *****");
				
				ArrayList<Monom> monoms = new ArrayList<Monom>();
				monoms.add(new Monom(0,5));
				monoms.add(new Monom(-1,0));
		     	monoms.add(new Monom(4,2));
		     	
		 
		 System.out.println("multiply function:");
		 Monom m4 = new Monom(monoms.get(1));
		 Monom m5 = new Monom(monoms.get(2));
		 System.out.print(m4.toString()+ " * " +m5.toString()+ " = ");
		 m4.multipy(m5);
		 System.out.println(m4.toString());
		 Monom m6 = new Monom(monoms.get(0));
		 Monom m7 = new Monom(monoms.get(2));
		 System.out.print(m6.toString()+ " * " +m7.toString()+ " = ");
		 m6.multipy(m7);
		 System.out.println(m6.toString());
		 System.out.println();
		 
	
	}
			private static void test4Bad() {
				System.out.println("*****  Test4:  *****");
			String st1 = new String ("2x^5#, 2a, 6x^a");
			Monom m1 = new Monom (st1);
			System.out.println(m1.toString());
				
	}
}

