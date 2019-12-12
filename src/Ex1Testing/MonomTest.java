package Ex1Testing;

import Ex1.Monom;
import Ex1.function;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MonomTest {

    @Test
    void derivative() {
        Monom m1 = new Monom("-5x");
        Monom m2 = new Monom("15");
        Monom m3 = new Monom("2x^7");
        Monom m4 = new Monom("+x^2");
        assertEquals("-5.0",m1.derivative().toString());
        assertEquals("0.0",m2.derivative().toString());
        assertEquals("14.0x^6",m3.derivative().toString());
        assertEquals("2.0x",m4.derivative().toString());
    }

    @Test
    void f() {
        Monom m1 = new Monom("-5x");
        Monom m2 = new Monom("15");
        Monom m3 = new Monom("2x^7");
        Monom m4 = new Monom("+x^2");
        assertEquals(-50, m1.f(10), 0.00001);
        assertEquals(15 , m2.f(3), 0.00001);
        assertEquals(256 , m3.f(2), 0.00001);
        assertEquals(25 , m4.f(5), 0.00001);
    }

    @Test
    void isZero() {
        Monom m1 = new Monom("0.0");
        Monom m2 = new Monom("15x");
        assertEquals(true , m1.isZero());
        assertEquals(false , m2.isZero());
    }

    @Test
    void add() {
        Monom m1 = new Monom("-5x");
        Monom m2 = new Monom("15x");
        Monom m3 = new Monom("2x^7");
        Monom m4 = new Monom("+x^7");
        m1.add(m2);
        m2.add(m3);
        m3.add(m4);
        assertEquals("10.0x",m1.toString());
        assertEquals("15.0x",m2.toString());
        assertEquals("3.0x^7",m3.toString());

    }

    @Test
    void multipy() {
        Monom m1 = new Monom("-5x");
        Monom m2 = new Monom("10x");
        Monom m3 = new Monom("2x^7");
        Monom m4 = new Monom("+x^7");
        m1.multipy(m2);
        m2.multipy(m3);
        m3.multipy(m4);
        assertEquals("-50.0x^2",m1.toString());
        assertEquals("20.0x^8",m2.toString());
        assertEquals("2.0x^14",m3.toString());

    }


    @Test
    void initFromString() {
        Monom m = new Monom(0,0);
        function f1 = m.initFromString("-5x");
        function f2 = m.initFromString("10x ^2");
        assertEquals("-5.0x",f1.toString());
        assertEquals("10.0x^2",f2.toString());
    }

    @Test
    void copy() {
        Monom m = new Monom("15.00000025x^8");
        function m1 = m.copy();
        assertEquals("15.00000025x^8",m1.toString());
        Monom m2 = (Monom)m1;
        m2.add(new Monom("2x^8"));
        assertEquals("17.00000025x^8",m2.toString());
        assertEquals("15.00000025x^8",m.toString());
    }

    @Test
    void testEquals() {
        Monom m1 = new Monom("-5.00000011x");
        Monom m2 = new Monom("-5x");
        Monom m3 = new Monom("2x^7");
        Monom m4 = new Monom("1.99x^7");
        Monom m5 = new Monom("1.99x^7");
        assertEquals(true , m1.equals(m2));
        assertEquals(false , m2.equals(m3));
        assertEquals(false , m3.equals(m4));
        assertEquals(true , m4.equals(m5));

    }
}