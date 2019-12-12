package Ex1Testing;

import Ex1.Monom;
import Ex1.Polynom;
import Ex1.function;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PolynomTest {

    @Test
    void f() {
        Polynom p1 = new Polynom("5");
        Polynom p2 = new Polynom("6.5-2x^5");
        Polynom p3 = new Polynom("-23x+10x^2-2.7x^3");
        Polynom p4 = new Polynom("x");
        assertEquals(5, p1.f(30), 0 / 00001);
        assertEquals(-57.5, p2.f(2), 0.000001);
        assertEquals(-27.6, p3.f(2), 0.00001);
        assertEquals(17, p4.f(17), 0.000001);
    }

    @Test
    void add() {
        Polynom p1 = new Polynom("4x^20+8x+1+2");
        Polynom p2 = new Polynom("6.5-17x");
        Polynom p3 = new Polynom("-x+5x^2-3.8x^3");
        Monom m1 = new Monom("-1.5x^2");
        Monom m2 = new Monom("9.5x");
        Monom m3 = new Monom("-x^4");
        p1.add(m1);
        p2.add(m2);
        p3.add(m3);
        assertEquals("4.0x^20-1.5x^2+8.0x+3.0", p1.toString());
        assertEquals("-7.5x+6.5", p2.toString());
        assertEquals("-1.0x^4-3.8x^3+5.0x^2-1.0x", p3.toString());
    }

    @Test
    void testAdd() {
        Polynom p1 = new Polynom("14x^2-7x");
        Polynom p2 = new Polynom("x");
        Polynom p3 = new Polynom("-x+5x^2-3.8x^3");
        Polynom p4 = new Polynom("12");
        p1.add(p2);
        p2.add(p3);
        p4.add(p1);
        assertEquals("14.0x^2-6.0x", p1.toString());
        assertEquals("-3.8x^3+5.0x^2", p2.toString());
        assertEquals("14.0x^2-6.0x+12.0", p4.toString());
    }

    @Test
    void substract() {
        Polynom p1 = new Polynom("2x");
        Polynom p2 = new Polynom("12.5-8x");
        Polynom p3 = new Polynom("-x+6x^2-4.3x^3");
        Polynom p4 = new Polynom("9");
        p2.substract(p3);
        p4.substract(p1);
        assertEquals("4.3x^3-6.0x^2-7.0x+12.5", p2.toString());
        assertEquals("-2.0x+9.0", p4.toString());
    }

    @Test
    void multiply() {
        Polynom p1 = new Polynom("x");
        Polynom p2 = new Polynom("3-5x");
        Polynom p3 = new Polynom("-x+5x^2-3.8x^3");
        Monom m1 = new Monom("15");
        Monom m2 = new Monom("9x");
        Monom m3 = new Monom("-x^5");
        p1.multiply(m1);
        p2.multiply(m2);
        p3.multiply(m3);
        assertEquals("15.0x", p1.toString());
        assertEquals("-45.0x^2+27.0x", p2.toString());
        assertEquals("3.8x^8-5.0x^7+1.0x^6", p3.toString());
    }

    @Test
    void testMultiply() {
        Polynom p1 = new Polynom("2x^3-15");
        Polynom p2 = new Polynom("12.5-8x");
        Polynom p3 = new Polynom("-x+6x^2-4.3x^3");
        Polynom p4 = new Polynom("9x^7-17x^8");
        p2.multiply(p3);
        p4.multiply(p1);
        assertEquals("34.4x^4-101.75x^3+83.0x^2-12.5x", p2.toString());
        assertEquals("-34.0x^11+18.0x^10+255.0x^8-135.0x^7", p4.toString());
    }

    @Test
    void testEquals() {
        Monom m1 = new Monom("-5.00000011x+15x^6");
        Monom m2 = new Monom("-5x+15x^6");
        Monom m3 = new Monom("2x^7+65x^3+12.1x");
        Monom m4 = new Monom("1.99x^7+65x^3+12.1x");
        Monom m5 = new Monom("1.99x^7+65x^3+12.1x");
        assertEquals(true , m1.equals(m2));
        assertEquals(false , m2.equals(m3));
        assertEquals(false , m3.equals(m4));
        assertEquals(true , m4.equals(m5));
    }

    @Test
    void isZero() {
        Polynom p1 = new Polynom("0");
        Polynom p2 = new Polynom("1.99x^7+65x^3+12.1x");
        assertEquals(true, p1.isZero());
        assertEquals(false, p2.isZero());
    }

    @Test
    void root() {
        Polynom p1 = new Polynom("3x^2-6x^3+9x-2");
        assertEquals(0.2135, p1.root(0, 1, 0.0001), 0.0001);
    }

    @Test
    void copy() {
        Polynom p = new Polynom("15.00000025x^8+15x^3+12.5x+1");
        function p1 = p.copy();
        assertEquals("15.00000025x^8+15.0x^3+12.5x+1.0",p1.toString());
        Polynom p2 = (Polynom) p1;
        p2.add(new Polynom("2x^8"));
        assertEquals("17.00000025x^8+15.0x^3+12.5x+1.0",p2.toString());
        assertEquals("15.00000025x^8+15.0x^3+12.5x+1.0",p.toString());
    }

    @Test
    void derivative() {
        Polynom p1 = new Polynom("x");
        Polynom p2 = new Polynom("40-15x");
        Polynom p3 = new Polynom("-x+6x^3-3.0x^4");
        Polynom p4 = new Polynom("30x^2+12");
        assertEquals(new Polynom("1.0"), p1.derivative());
        assertEquals(new Polynom("-15.0"), p2.derivative());
        assertEquals(new Polynom("-12.0x^3+18.0x^2-1.0"), p3.derivative());
        assertEquals(new Polynom("60.0x"), p4.derivative());
    }

    @Test
    void area() {
        Polynom p1 = new Polynom("3x^2-6x^3+9x-2");
        assertEquals(0, p1.area(-1, 0, 0.0001), 0.0001);
    }

    @Test
    void initFromString() {
        Polynom p = new Polynom();
        function f1 = p.initFromString("-5x+12x^2");
        function f2 = p.initFromString("10x ^2 +15x^7+12");
        assertEquals("12.0x^2-5.0x",f1.toString());
        assertEquals("15.0x^7+10.0x^2+12.0",f2.toString());
    }
}