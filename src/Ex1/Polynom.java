package Ex1;
	//throw new RuntimeException("both above zreo");
	import java.util.ArrayList;
	import java.util.Iterator;
	import java.util.function.Predicate;
	
	import Ex1.Monom;
	/**
	 * This class represents a Polynom with add, multiply functionality, it also should support the following:
	 * 1. Riemann's Integral: https://en.wikipedia.org/wiki/Riemann_integral
	 * 2. Finding a numerical value between two values (currently support root only f(x)=0).
	 * 3. Derivative
	 * 
	 * @author Boaz
	 *
	 */
	public class Polynom implements Polynom_able{
		private ArrayList<Monom> poly ;
		private Monom_Comperator comp = new Monom_Comperator();
		/**
		 * Zero (empty polynom)
		 */
		public Polynom() {
			poly = new ArrayList<Monom>();
		}
		/**
		 * init a Polynom from a String such as:
		 *  {"x", "3+1.4X^3-34x"};
		 * @param s: is a string represents a Polynom
		 */
		public Polynom(String s) {
				try{ s = s.toLowerCase(); //change the capital letters to small letters.
				poly = new ArrayList<Monom>(); // Define a new ArrayList of type Monom.
				String[] st = s.split("[+]"); //split the string and Remove all the char "+" from the string and push it to array 
				
				for (int i = 0; i < st.length; i++) { //running throw the array
					if (st[i].contains("-")) { //if the cell in the i position contains "-" than go in
						String temp = st[i].substring(0, 1); //define a new variable called temp as the first char at the strings first cell
						if (!temp.equals("-")) { //Check if the char "-" is located at the beginning.
							String[] temp2 = st[i].split("[-]"); //if the char "-" is not located at the beginning, than split the array at "-" and push it into array. 
							String st1 = temp2[0]; //take first cell of the string after the spilt
							Monom m1 = new Monom(st1); //Creates a new monom with the first cell values.
							this.add(m1); //adds the monom to the polynom
							}
						String[] temp2 = st[i].split("[-]"); //if the char "-" is located at the beginning than split the array at "-" and push it to array.
						for (int j = 1; j < temp2.length; j++) { //running throw the array after the split at char '-'
							String st2 = temp2[j]; //takes the monom with "-" at the beginning
							if (st2.charAt(0) == 'x'){ //if there isnt a coefficient on the 'x'
								Monom m2 = new Monom("-1*"+st2);; //Creates a new monom and saves the char "-" before the "x"
								this.add(m2); //adds the Monom to the polynom
								}
							else { 
								Monom m2 = new Monom("-"+st2); //creats a new monom with a '-' before the coefficient
								this.add(m2); //adds the Monom to the polynom
								}
							}
						}
					else { //if the cell in the i position doesnt contain "-".
						String st1 = st[i];
						Monom m1 = new Monom(st1); //creats a new positive monom
						this.add(m1);  //adding the Monom to the polynom 
					}
				}
			}catch (Exception e) {
				System.out.println("The string format is invalid");
			}
		}
		@Override
		/**
		 * calculates the value of f at a given point 'x'
		 */
		public double f(double x) {
			Iterator<Monom> iter = poly.iterator(); //sets an iterator 
			double sum = 0; //defines a variable "sum" of type double and reset it to 0 
			while(iter.hasNext()) { //runs on the polynom until the end
				Monom temp = iter.next(); //define a variable of type monom to be the pointer  of the iterator
				sum += temp.f(x); //add the value of f at point x to sum
			}
			return sum; //returns the value of sum
		}
		
	    /**
	     * this function adds tow polynoms together
	     */
		@Override
		public void add(Polynom_able p1){
			Iterator<Monom> iter = p1.iteretor();  //sets an iterator 
			while(iter.hasNext()) {  //runs on the polynom until the end
				Monom temp = iter.next(); //define a variable of type monom to be the pointer  of the iterator
				this.add(temp); //adds the monom pointed by the iterator using monom's add function				
			}
		}
		/** 
		 * this function adds a given monom to a polynom 
		 */
		@Override
		public void add(Monom m1) {
			if (m1.get_coefficient() == 0) { // checks i the given monom equels 0 than return
				return; 
			}
			Iterator<Monom> iter = poly.iterator(); //sets an iterator 
			while(iter.hasNext()) { //runs on the polynom until the end
				Monom temp = iter.next(); //define a variable of type monom to be the pointer  of the iterator
				if (temp.get_power() == m1.get_power()) { // checks if the power of the given monom is equal to one of the polynoms power
				temp.add(m1); // adds the givan monom to to the monom of the same power in the polynom
				return;
			}
		}
			poly.add(m1); // adds the givan monom to the polynom
			this.poly.sort(comp); // sorts the polynom in order of higher power to lowest
		}
	
		@Override
		/**
		 * this function substructs 2 polynoms
		 */
		public void substract(Polynom_able p1) {
			if (this.equals(p1)) {
				Iterator<Monom> iter1 = poly.iterator(); //sets an iterator 
				while(iter1.hasNext()) {
					iter1.next().set_coefficient(0);
				}
			}
			else {
				final Monom MINUS1 = new Monom(-1,0); // sets an zero monom
				p1.multiply(MINUS1); //multiplies the given polynom with '-1'
				this.add(p1); // adds the tow polynoms 
			}
		}
		
		@Override
		/**
		 * this function multiplies the polynom with a given monom 
		 */
	    public void multiply(Monom m1) {
			Iterator<Monom> iter1 = poly.iterator(); //sets an iterator 
			while(iter1.hasNext()) { //runs on the polynom until the end
				Monom tmp = iter1.next(); //define a variable of type monom to be the pointer  of the iterator
					tmp.multipy(m1); //multiplies the monom pointed by the iterator and the given monom using monom's multipy function	
					
				}	
			}
	
		@Override
		/**
		 * this function multiplies tow polynoms 
		 */
		
		public void multiply(Polynom_able p1){
			Polynom ans = new Polynom(); //define a new polynom named "ans"
			Iterator<Monom> iter1 = this.iteretor(); //sets an iterator on the original polynom
			while(iter1.hasNext()) {  //runs on the polynom until the end
				Iterator<Monom> iter2 = p1.iteretor(); //sets an iterator on the given polynom
				Monom tmp1 = iter1.next();  //define a variable of type monom to be the pointer  of the iterator tmp1
				iter1.remove(); // delets the monom pointed by iter1 from the original polynom
				while(iter2.hasNext()) {  //runs on the polynom until the end
					Monom tmp2 = new Monom(tmp1.get_coefficient(),tmp1.get_power()); //sets a new monom with the same power and coefficient as the monom we deleted
					Monom tmp3 = iter2.next(); //define a variable of type monom to be the pointer  of the iterator tmp2
					tmp2.multipy(tmp3); //multiplies the tow monoms using monom multiply function
					ans.add(tmp2); //adds the product we get as a new monom in the new polynom "ans"
					}
				
				}
			this.add(ans); //adds all the monoms in polynom "asn" to the original polynom
			}

		//@Override
		/**
		 * this function returns true if the tow polynoms are equals and false otherwise
		 */
		public boolean equals(Object p1) {
			boolean flag = false; //define a boolean flag and reset it to false
			Iterator<Monom> iter1 = poly.iterator(); //sets an iterator on the original polynom
			Iterator<Monom> iter2 = ((Polynom_able)p1).iteretor(); //sets an iterator on the given polynom
				while(iter1.hasNext() && iter2.hasNext()) { //runs on both of the polynoms until the end
					Monom tmp2 = iter2.next(); //define a variable of type monom to be the pointer  of the iterator tmp2
					Monom tmp1 = iter1.next(); //define a variable of type monom to be the pointer  of the iterator tmp1
					if (tmp1.equals(tmp2)) { //checks if the tow members pointed by the iterators are equals
						flag = true; //sets the flag as true
					} 
					else return false; //if the tow members are different returns false
				}
			return flag; //returns the set of flag
		}
	
		@Override
		/**
		 * this function returns true if the polynom equals zero
		 */
		/*public boolean isZero() {
			boolean flag = false; //define a boolean flag and reset it to false
			Iterator<Monom> iter1 = poly.iterator(); //sets an iterator on the original polynom
			while (iter1.hasNext()) { //runs on the polynom until the end
				Monom tmp1 = iter1.next(); //define a variable of type monom to be the pointer  of the iterator tmp1
				if (tmp1.isZero()) { //checks if the monom pointed by the iterator equals zero using monom function
					flag = true; //sets flag to true
				} 
				else return false;	//if the monom pointed by the iterator doesnt equals zero return false
			}
			return flag; //returns the set of falg
		}*/
		public boolean isZero() { // function that check if all the Monoms in the polynom equals 0;
			Iterator<Monom> iter = poly.iterator();
			while (iter.hasNext()) {
				if (!iter.next().isZero()) { // check if the coefficient of the Monom pointed by the iterartor not equals 0.
					return false;
				}
			}
			return true;
		}
	
		@Override
		/**
		 * this function Compute a value x' (x0<=x'<=x1) for with |f(x')| < eps
		 */
		public double root(double x0, double x1, double eps) {
			double xMid = (x0 + x1)/2; //define a variable xMid that is the middle of the 2 given points
			if ((f(x0)*f(x1)) <= 0) { //checks if there is a point of intersection on the x hinge
				while (Math.abs(f(xMid)) > eps) { //while the value of f(mid) is greater than epsilon
					if ((f(x0)*f(xMid) <= 0)) { //checks if the value of f between x0 and xMid is negative
						x1 = xMid; //sets x1 with the value of xMid
						 xMid = (x0+x1)/2;
					}
					else { 
						x0 = xMid; //sets x0 with the value of xMid
						xMid = (x0+x1)/2; //sets a new Xmid with the new x0
					    }
					}  
				}
			return xMid; //returns the x value of the intersection point
		}
				
			
		@Override
		/**
		 * this function duplicats a polynoom
		 */
		public function copy(){
			function copyPol = new Polynom (); //sets a new polynom
			Iterator<Monom> iter1 = poly.iterator(); //sets an iterator on the original polynom
			while (iter1.hasNext()) { //runs on the polynom until the end
				Monom tmp = new Monom( iter1.next()); //define a variable of type monom to be the pointer  of the iterator tmp1
				((Polynom)copyPol).add(tmp); //adds the monom pointed by the iterator to the new polynom using monoms add function
			}
			return copyPol; //returns the new polynom
		}
	
		@Override
		/**
		 * this function returns the derivative of a given polynom
		 */
		public Polynom_able derivative(){
			Polynom_able deriPol = new Polynom (); //sets a new polynom
			Iterator<Monom> iter1 = poly.iterator(); //sets an iterator on the original polynom
			while (iter1.hasNext())
				try {
					{ //runs on the polynom until the end
						Monom tmp = iter1.next().derivative(); //define a variable of type monom to be the derivative of the monom pointed by the iterator using monoms function
						deriPol.add(tmp); //adds monom tmp to the new polynom
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			return deriPol; //returns the new polynom
		}
		
	
		@Override
	/**
	 * this function Calculates the functions area between range x0 and x1.
	 * the functions maximum deviation of the answer = eps.
	 */
		public double area(double x0, double x1, double eps) {
			double sumArea = 0; //define a new variable sumArea and sets it to 0
			for (double i=x0; i<=x1; i += eps ) { //runs on the x points between x0 to x1 with range of epsilon
				if (this.f(i) < 0) {
				sumArea += 0;
				}
				else {
					sumArea += (this.f(i)*eps); //adds the value of f(i) multiplies with epsilon
				  }
				}
			return Math.abs(sumArea); //returns the absolute value of sumArea
		}
	
		@Override
		public Iterator<Monom> iteretor() {
			Iterator<Monom> iter = this.poly.iterator();
			return iter;
		}
		
		/**
		 * this function returns a string of a polynom
		 */
		public String toString() {
			String ans ="";
			Iterator<Monom> iter = poly.iterator();
			while (iter.hasNext()) {
				Monom t = iter.next();
				if (t.get_coefficient() >= 0) {
					ans += "+"+t.toString();
				}else {
					ans += t.toString();
				}
				
			}
			return ans;
		}

		@Override
		public function initFromString(String s) {
			function p = new Polynom(s);
			return p;
		}

	}
