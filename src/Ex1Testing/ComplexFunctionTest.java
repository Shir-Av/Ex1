package Ex1Testing;

import Ex1.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ComplexFunctionTest {

    @Test
    void plus() {
        ComplexFunction cf1 = new ComplexFunction(new Monom("9x^7"));
        ComplexFunction cf2 = new ComplexFunction(new Polynom("-15+88x"));
        ComplexFunction cf3 = new ComplexFunction(Operation.Comp , cf1 , new Polynom("18-12x"));
        cf1.plus(cf2);
        cf3.plus(cf2);
        assertEquals(Operation.Plus.toString() , cf1.getOp().toString());
        assertEquals("plus(9.0x^7,88.0x-15.0)" , cf1.toString());
        assertEquals("plus(comp(plus(9.0x^7,88.0x-15.0),-12.0x+18.0),88.0x-15.0)" , cf3.toString());

    }

    @Test
    void mul() {
        ComplexFunction cf1 = new ComplexFunction(new Monom("9x^7"));
        ComplexFunction cf2 = new ComplexFunction(new Polynom("-15+88x"));
        ComplexFunction cf3 = new ComplexFunction(Operation.Comp , cf1 , new Polynom("18-12x"));
        cf1.mul(cf2);
        cf3.mul(cf2);
        assertEquals(Operation.Times.toString() , cf1.getOp().toString());
        assertEquals("mul(9.0x^7,88.0x-15.0)" , cf1.toString());
        assertEquals("mul(comp(mul(9.0x^7,88.0x-15.0),-12.0x+18.0),88.0x-15.0)" , cf3.toString());
    }

    @Test
    void div() {
        ComplexFunction cf1 = new ComplexFunction(new Monom("9x^7"));
        ComplexFunction cf2 = new ComplexFunction(new Polynom("-15+88x"));
        ComplexFunction cf3 = new ComplexFunction(Operation.Comp , cf1 , new Polynom("18-12x"));
        cf1.div(cf2);
        cf3.div(cf2);
        assertEquals(Operation.Divid.toString() , cf1.getOp().toString());
        assertEquals("div(9.0x^7,88.0x-15.0)" , cf1.toString());
        assertEquals("div(comp(div(9.0x^7,88.0x-15.0),-12.0x+18.0),88.0x-15.0)" , cf3.toString());
    }

    @Test
    void max() {
        ComplexFunction cf1 = new ComplexFunction(new Monom("9x^7"));
        ComplexFunction cf2 = new ComplexFunction(new Polynom("-15+88x"));
        ComplexFunction cf3 = new ComplexFunction(Operation.Comp , cf1 , new Polynom("18-12x"));
        cf1.max(cf2);
        cf3.max(cf2);
        assertEquals(Operation.Max.toString() , cf1.getOp().toString());
        assertEquals("max(9.0x^7,88.0x-15.0)" , cf1.toString());
        assertEquals("max(comp(max(9.0x^7,88.0x-15.0),-12.0x+18.0),88.0x-15.0)" , cf3.toString());
    }

    @Test
    void min() {
        ComplexFunction cf1 = new ComplexFunction(new Monom("9x^7"));
        ComplexFunction cf2 = new ComplexFunction(new Polynom("-15+88x"));
        ComplexFunction cf3 = new ComplexFunction(Operation.Comp , cf1 , new Polynom("18-12x"));
        cf1.min(cf2);
        cf3.min(cf2);
        assertEquals(Operation.Min.toString() , cf1.getOp().toString());
        assertEquals("min(9.0x^7,88.0x-15.0)" , cf1.toString());
        assertEquals("min(comp(min(9.0x^7,88.0x-15.0),-12.0x+18.0),88.0x-15.0)" , cf3.toString());
    }

    @Test
    void comp() {
        ComplexFunction cf1 = new ComplexFunction(new Monom("9x^7"));
        ComplexFunction cf2 = new ComplexFunction(new Polynom("-15+88x"));
        ComplexFunction cf3 = new ComplexFunction(Operation.Comp , cf1 , new Polynom("18-12x"));
        cf1.comp(cf2);
        cf3.comp(cf2);
        assertEquals(Operation.Comp.toString() , cf1.getOp().toString());
        assertEquals("comp(9.0x^7,88.0x-15.0)" , cf1.toString());
        assertEquals("comp(comp(comp(9.0x^7,88.0x-15.0),-12.0x+18.0),88.0x-15.0)" , cf3.toString());
    }

    @Test
    void f() {
        Polynom f1 = new Polynom("2x^2+2");
        Monom f2 = new Monom("-5x");
        ComplexFunction cf1 = new ComplexFunction("plus", f1 , f2);
        ComplexFunction cf2 = new ComplexFunction("div" , cf1 , f2);
        ComplexFunction cf3 = new ComplexFunction("max" , cf1 , cf2);
        assertEquals(27 , cf1.f(5) , 0.00000001);
        assertEquals(-0.7 , cf2.f(4) , 0.00000001);
        assertEquals(77 , cf3.f(-5) , 0.0000001);
    }

    @Test
    void initFromString() {
        String s1 = "3+4x-5x^6";
        String s2 = "mul(1.4,5x+3x^2)";
        String s3 = "min(min(div(plus(x,2x^2),3x^3),4x^4),5x^5)";
        String s4 = "div(plus(x,2x^2),3x^3)";
        ComplexFunction f = new ComplexFunction();
        ComplexFunction c1 = new ComplexFunction("plus" , new Monom("x") , new Monom("2x^2"));
        ComplexFunction c2 = new ComplexFunction("div" , c1 , new Monom("3x^3"));
        ComplexFunction c3 = new ComplexFunction("min" , c2 , new Monom("4x^4"));
        ComplexFunction c4 = new ComplexFunction("min" , c3 , new Monom("5x^5"));
        assertEquals(new ComplexFunction(new Polynom(s1)) , f.initFromString(s1));
        assertEquals(new ComplexFunction("mul" , new Monom("1.4") , new Polynom("5x+3x^2")) , f.initFromString(s2));
        assertEquals(c4.toString() , f.initFromString(s3).toString());
    }


    @Test
    void copy() {
        String s = "mul(plus(-1.0x^4+2.4x^2+3.1,+0.1x^5-1.2999999999999998x+5.0),-1.0x^4+2.4x^2+3.1)";
        function f = new ComplexFunction().initFromString(s);
        function f1 = f.copy();
        assertFalse(f == f1);
    }


    @Test
    void testEquals() {
        function f = new ComplexFunction().initFromString("mul(plus(-1.0x^4+2.4x^2+3.1,+0.1x^5-1.2999999999999998x+5.0),-1.0x^4+2.4x^2+3.1)");
        function f1 = new ComplexFunction().initFromString( "mul(-1.0x^4+2.4x^2+3.1,plus(+0.1x^5-1.2999999999999998x+5.0,-1.0x^4+2.4x^2+3.1))");
        assertEquals(f,f1);
        f = new ComplexFunction().initFromString("div(plus(-1.0x^4+2.4x^2+3.1,+0.1x^5-1.2999999999999998x+5.0),-1.0x^4+2.4x^2+3.1)");
        f1 = new ComplexFunction().initFromString("mul(-1.0x^4+2.4x^2+3.1,plus(+0.1x^5-1.2999999999999998x+5.0,-1.0x^4+2.4x^2+3.1))");
        assertFalse(f.equals(f1));
    }
}