package Ex1;

import java.util.ArrayList;

public class PolynomTest {
	public static void main(String[] args) {
		
		BadTest();
		AddPolynomTest();
		SubstractTest();
		MultiplyPolynomTest();
		EqualsTest();	
		AreaTest();
		IsZeroTest();
		copyTest();
		derivativeTest();
		rootTest();
		
	}
	
	private static void BadTest() {
		System.out.println("BadTest:");
		Polynom p1 = new Polynom("6x+15j^3");
		System.out.println();
		
		}
	public static void AddPolynomTest() {
		Polynom p3 = new Polynom(), p2 =  new Polynom();
		String[] monoms1 = {"2", "-x","-3x^2","4","-4x^2"};
		String[] monoms2 = {"5", "x","3x^2"};
		for(int i=0;i<monoms1.length;i++) {
			Monom m = new Monom(monoms1[i]);
			p3.add(m);
		}
		for(int i=0;i<monoms2.length;i++) {
			Monom m = new Monom(monoms2[i]);
			p2.add(m);
		}
		System.out.println( "add polynom test: ");
		System.out.println("p3: "+p3);
		System.out.println("p2: "+p2);
		p3.add(p2);
		System.out.println("p3+p2 =  "+p3);
		System.out.println();
		
		}
	
	public static void SubstractTest() {
		Polynom p3 = new Polynom(), p2 =  new Polynom();
		String[] monoms1 = {"2", "-x","-3x^2","4","-4x^2"};
		String[] monoms2 = {"5", "x","3x^2"};
		for(int i=0;i<monoms1.length;i++) {
			Monom m = new Monom(monoms1[i]);
			p3.add(m);
		}
		for(int i=0;i<monoms2.length;i++) {
			Monom m = new Monom(monoms2[i]);
			p2.add(m);
		}
		System.out.println( "substract test: ");
		System.out.println("p3: "+p3);
		System.out.println("p2: "+p2);
		p3.substract(p2);
		System.out.println("p3-p2 =  "+p3);
		System.out.println();
	}
	
	public static void MultiplyPolynomTest() {
		Polynom p3 = new Polynom(), p2 =  new Polynom();
		String[] monoms1 = {"2", "-x","-3x^2","4","-4x^2"};
		String[] monoms2 = {"5", "x"};
		for(int i=0;i<monoms1.length;i++) {
			Monom m = new Monom(monoms1[i]);
			p3.add(m);
		}
		for(int i=0;i<monoms2.length;i++) {
			Monom m = new Monom(monoms2[i]);
			p2.add(m);
		}
		System.out.println( "Multiply test: ");
		System.out.println("p3: "+p3);
		System.out.println("p2: "+p2);
		p3.multiply(p2);
		System.out.println("p3*p2 =  "+p3);
		System.out.println();
	}
	
	public static void EqualsTest() {
		Polynom p3 = new Polynom(), p2 =  new Polynom(),  p1 =  new Polynom();
		String[] monoms3 = {"2", "-x","-3x^2","4","-4x^2"};
		String[] monoms2 = {"5", "x"};
		String[] monoms1 = {"3", "-x","-5x^2","3","-2x^2"};
		for(int i=0;i<monoms3.length;i++) {
			Monom m = new Monom(monoms3[i]);
			p3.add(m);
		}
		for(int i=0;i<monoms2.length;i++) {
			Monom m = new Monom(monoms2[i]);
			p2.add(m);
		}
		for(int i=0;i<monoms1.length;i++) {
			Monom m = new Monom(monoms1[i]);
			p1.add(m);
		}
		
		System.out.println( "Equals test: ");
		System.out.println("p3: "+p3);
		System.out.println("p2: "+p2);
		System.out.println("p3 = p2 ? :  "+p3.equals(p2));
		System.out.println("p1: "+p1);
		System.out.println("p3 = p1 ? :  "+p3.equals(p1));
		System.out.println();
	}
	public static void AreaTest() {
		System.out.println( "Area test: ");
		Polynom p = new Polynom("3x^2");
		double areaCheck = p.area(0, 2, 0.1);
		System.out.println(areaCheck);
		System.out.println();
	}
	public static void IsZeroTest() {
		System.out.println( "IsZero test: ");
		Polynom p1 = new Polynom("0x^1+0x^2");
		System.out.println("is: "+ p1.toString()+ " = 0 ?" + p1.isZero());
		Polynom p2 = new Polynom("x^2+0x^2");
		System.out.println("is: "+ p2.toString()+ " = 0 ?" + p2.isZero());
		System.out.println();
	}
	
	public static void copyTest() {
		System.out.println( "copy test: ");
		Polynom p3 = new Polynom();
		String[] monoms3 = {"2", "-x","-3x^2","4","-4x^2"};
		for(int i=0;i<monoms3.length;i++) {
			Monom m = new Monom(monoms3[i]);
			p3.add(m);
		}
		p3.copy();
		System.out.println("p3 = " + p3.toString());
		System.out.println("p3 copy = " + p3.copy());		
	}
	
	public static void derivativeTest() {
	System.out.println( "derivative test: ");
	Polynom p3 = new Polynom();
	String[] monoms3 = {"2", "-x","-3x^2","4","-4x^2"};
	for(int i=0;i<monoms3.length;i++) {
		Monom m = new Monom(monoms3[i]);
		p3.add(m);
	}
	System.out.println("p3 = " + p3.toString());
	System.out.println("p3 derivative = " + p3.derivative());		
	System.out.println();
	}
	
	public static void rootTest() {
		System.out.println( "root test: ");
		Polynom p = new Polynom("x^2+5");
		double rootCheck = p.root(-2, 3, 0.1);
		System.out.println(rootCheck);
		System.out.println();
		
	}
}

