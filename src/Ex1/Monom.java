package Ex1;

import java.util.Comparator;

/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real number and a is an integer (summed a none negative), 
 * see: https://en.wikipedia.org/wiki/Monomial 
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply. 
 * @author Boaz
 *
 */
public class Monom implements function{
	public static final Monom ZERO = new Monom(0,0);
	public static final Monom MINUS1 = new Monom(-1,0);
	public static final double EPSILON = 0.0000001;
	public static final Comparator<Monom> _Comp = new Monom_Comperator();
	public static Comparator<Monom> getComp() {return _Comp;}
	public Monom(double a, int b){
		this.set_coefficient(a);
		this.set_power(b);
	}
	public Monom(Monom ot) {
		this(ot.get_coefficient(), ot.get_power());
	}

	public double get_coefficient() {
		return this._coefficient;
	}
	public int get_power() {
		return this._power;
	}
	/**
	 * this method returns the derivative monom of this.
	 * @return
	 */
	public Monom derivative() {
		if(this.get_power()==0) {return getNewZeroMonom();}
		return new Monom(this.get_coefficient()*this.get_power(), this.get_power()-1);
	}
	public double f(double x) {
		double ans=0;
		double p = this.get_power();
		ans = this.get_coefficient()*Math.pow(x, p);
		return ans;
	}
	public boolean isZero() {return this.get_coefficient() == 0;
	}


	// * add your code below **
	public Monom(String s) {
		try {
			s.toLowerCase(); // changes the char from capital letters to small letters
			if (s.contains("x")) { // checks if the given string contains the char 'x'. if yes then:
				s = s.replace(" ","");
				if(s.charAt(0) == '+'){
					s = s.substring(1);
				}
				String[] parts = s.split("x"); // splits the string s by removing the char 'x' and pushing the parts before and after 'x' into different parts of a new array named parts 
				if ((s.charAt(0) == 'x')) { // if the first char of the given string is 'x' then:
					//1*x^1 = x
					this._coefficient = 1; //sets the coefficient to be 1
					if (s.length() == 1) { //  checks if the string length is 1 char
						this._power = 1; // sets the power to be 1
					}
					//x^__ check ^?
					else if (s.length() > 1) { // checks if the string length is greater than 1 char
						this._power = Integer.parseInt(parts[1].substring(1)); // sets the power to be all the chars after 'x^'
					}
				}
				// __x^1 = _*x
				else if (s.charAt(s.length()-1) == 'x') { // checks if the last char is 'x'
					if (parts[0].equals("-")) { // checks if the string in the first cell equals '-'
						this._coefficient = -1; // sets the coefficient to be '-1'
						this._power =1; //sets the power to be '1'
					}

					else { // if the first string in the first cell of parts is not equal to '-'
						this._coefficient = Double.parseDouble(parts[0]); // sets the coefficient to be the string in the first cell of parts and changes it to type double
						this._power =1; // sets the power to be double
					}
				}
				else if (parts[0].equals("-")) { //checks if the first cell after the split equals '-'
					this._coefficient = -1; // sets the coefficient to be '-1'
					this._power = Integer.parseInt(parts[1].substring(1)); // sets the power to be the string in the second cell after the split without '^' and changes to type int
				}
				//*x^
				else {
					this._coefficient = Double.parseDouble(parts[0]); // sets the coefficient to be the string first cell after the split and change to type double
					this._power = Integer.parseInt(parts[1].substring(1)); //sets the power to be the string in the second cell after the split without '^' and changes to type int
				}
			}
			//_*x^0= _
			else {
				this._coefficient = Double.parseDouble(s); // sets the coefficient to be string s and change to type double 
				this._power = 0;
			}
		}catch (Exception e) {
			System.out.println("The string format is invalid ITS ME");
		}
	}
	/**
	 * this function adds a monom to a given monom of the same power
	 *
	 */
	public void add(Monom m){
		if (this._coefficient == 0) {
			this._coefficient = m._coefficient;
			this._power = m._power;
		}
		else if (this._power == m._power) {  // check if the power of the monom is equal
			this._coefficient += m._coefficient; // adds the coefficient of the users monom to the given nonom
		}
		else {
			System.out.println("Cannot add two Monoms with a diffrent power");
		}
	}
	/**
	 * this function multiplies tow monoms
	 *
	 */
	public void multipy(Monom d) {
		this._coefficient *= d._coefficient; // set the new coefficient to be the product of the tow original coefficients
		this._power += d._power; //// set the new power to be the sum of the tow original powers
	}
	/**
	 * this function returns a print of string of a monom
	 */
	public String toString() {
		String ans = this._coefficient + "x^" + this._power; //define a new string called "ans" that will print a monom with a given coefficient and power
		if (this._power == 1 && this._coefficient != 0) { // checks if the power equals 1 and the coefficient is different from '0'
			ans = this._coefficient + "x"; // sets the coefficient to be the given coefficient
		}
		else if (this._power == 0 || this._coefficient == 0) { //checks if the given power or the coefficient equals '0'
			ans = this._coefficient + ""; // sets the coefficient to be the given coefficient
		}

		return ans; // returns string "ans"
	}

	@Override
	public function initFromString(String s) {
		function m = new Monom(s);
		return m;
	}

	@Override
	public function copy() {
		function m1 = new Monom(this._coefficient ,this._power);
		return m1;

	}

	/**
	 *
	 * this fonction returns true if tow monoms are equal otherwise returns false
	 * @return
	 */
	public boolean equals(Object a) {
		if (this.isZero() && ((Monom)a).isZero()) { //checks if the tow coefficients both equals zero. if yes then returns true.
			return true; // 
		}

		else {
			double n1 = this._coefficient - ((Monom)a)._coefficient;
			double n2 = ((Monom)a)._coefficient - this._coefficient;
			if (((n1 < 0.000001  && n1 > 0) || (n2 < 0.000001 && n2 > 0)) && this._power == ((Monom)a)._power) {
				return true;
			}
		}
		if (this._coefficient != ((Monom)a)._coefficient || this._power != ((Monom)a)._power) { //checks if the tow coefficients or the tow powers are different. if yes then returns false.
			return false;
		}
		return true; // returns true for 2 equal monoms.

	}

	//* Private Methods and Data **


	public void set_coefficient(double a){
		this._coefficient = a;
	}



	public void set_power(int p) {
		if(p<0) {throw new RuntimeException("ERR the power of Monom should not be negative, got: "+p);}
		this._power = p;
	}


	private static Monom getNewZeroMonom() {return new Monom(ZERO);}
	private double _coefficient;
	private int _power;
}

	
	
	
