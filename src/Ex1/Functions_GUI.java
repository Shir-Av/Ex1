package Ex1;


import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.Random;


public class Functions_GUI implements functions {

    ArrayList<function> f = new ArrayList<function>();
    //Iterator<function> iterator = f.iterator();

    @Override
    public void initFromFile(String file) throws IOException {
        ComplexFunction cf = new ComplexFunction();
        String line = "";
        File file1 = new File(file);
        Scanner scan = null;
        try {
            scan = new Scanner(file1);
        } catch (IOException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
        line = scan.nextLine();
        while (scan.hasNext() || line != null) {
            f.add((cf.initFromString(line)));
            if (scan.hasNext()) {
                line = scan.nextLine();
            } else break;
        }
        for(int i = 0; i < f.size(); i++) {
            String fString = f.get(i).toString();
            System.out.println(fString );
        }
    }
    @Override
    public void saveToFile(String file) throws IOException {
        try {
            File file1 = new File("string file.txt");
            FileWriter fw = new FileWriter(file1);
            PrintWriter pw = new PrintWriter(fw);
            Iterator<function> iterator = f.iterator();
            while (iterator.hasNext()) {
                String s = iterator.next().toString();
                pw.println(s);
            }
            pw.close();
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("File not found");
            return;
        }
    }

    @Override
    public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {
        Random rand = new Random();
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        Color randomColor = new Color(r, g, b);
        StdDraw.setCanvasSize(width, height);
        StdDraw.setXscale(rx.get_min(), rx.get_max());
        StdDraw.setYscale(ry.get_min(), ry.get_max());
        double rxRes = (Math.abs(rx.get_max()) + Math.abs(rx.get_min()));
        double x_step = (rxRes/resolution);
        // double x_step = (rx.get_max()-rx.get_min())/resolution;
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.005);
        StdDraw.line(rx.get_min(),0, rx.get_max(), 0);
        StdDraw.line(0, ry.get_min(), 0, ry.get_max());
        Font font = new Font("Arial", Font.BOLD, 15);
        StdDraw.setFont(font);
        for(int  n = (int)rx.get_min(); n<rx.get_max();n++){
            StdDraw.setPenColor(StdDraw.GRAY);
            if(n==0){continue;}
            StdDraw.text(n, 0.3, ""+n);
        }
        for(int  n = (int)ry.get_min(); n<ry.get_max();n++){
            StdDraw.setPenColor(StdDraw.GRAY);
            if(n==0){continue;}
            StdDraw.text(0.3, n, ""+n);
        }
        Iterator<function> iterator = f.iterator();
        while (iterator.hasNext() || iterator.next() != null){
            function fun = iterator.next();
            if (iterator.hasNext()) {
                fun = iterator.next();
            } else break;
            StdDraw.setPenColor(randomColor);
            for (double i = rx.get_min(); i <= rx.get_max(); i +=x_step){
                StdDraw.point(i , fun.f(i));
            }
        }

    }

    @Override
    public void drawFunctions(String json_file) {

    }
    @Override
    public int size() {
        return f.size();
    }

    @Override
    public boolean isEmpty() {
        return f.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        if(o instanceof function) {
            function n=(function)o;
            Iterator<function> it=this.f.iterator();
            while(it.hasNext()) {
                function temp=it.next();
                if(temp.equals(n)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    @Override
    public Iterator<function> iterator() {
        Iterator<function> it=f.iterator();
        return it;
    }

    @Override
    public Object[] toArray() {
        return f.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return f.toArray(a);
    }

    @Override
    public boolean add(function e) {
        f.add(e);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        return f.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return f.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends function> c) {
        return this.f.addAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return this.f.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return this.f.retainAll(c);
    }

    @Override
    public void clear() {
        f.clear();
    }

    public static functions FunctionsFactory() {
        functions ans = new Functions_GUI();
        String s1 = "3.1+2.4x^2-x^4";
        String s2 = "5+2x-3.3x+0.1x^5";
        String[] s3 = {"x+3", "x-2", "x-4"};
        Polynom p1 = new Polynom(s1);
        Polynom p2 = new Polynom(s2);
        Polynom p3 = new Polynom(s3[0]);
        ComplexFunction cf3 = new ComplexFunction(p3);
        for (int i = 1; i < s3.length; i++) {
            cf3.mul(new Polynom(s3[i]));
        }
        ComplexFunction cf = new ComplexFunction(Operation.Plus, p1, p2);
        //remmember to change the constructor.
        ComplexFunction cf4 = new ComplexFunction(Operation.Divid, new Polynom("x+1"), cf3);
        cf4.plus(new Monom("2"));
        ans.add(cf.copy());
        ans.add(cf4.copy());
        cf.div(p1);
        ans.add(cf.copy());
        String s = cf.toString();
        function cf5 = cf4.initFromString(s1);
        function cf6 = cf4.initFromString(s2);
        ans.add(cf5.copy());
        ans.add(cf6.copy());
        Iterator<function> iter = ans.iterator();
        function f = iter.next();
        ComplexFunction max = new ComplexFunction(f);
        ComplexFunction min = new ComplexFunction(f);
        while (iter.hasNext()) {
            f = iter.next();
            max.max(f);
            min.min(f);
        }
        ans.add(max);
        ans.add(min);
        return ans;
    }
    public static void main(String[] args) throws IOException {
        File file1 = new File("C:\\MATALA\\function_file.txt");
        // String file2 = new String("");
        Functions_GUI gf =new Functions_GUI();
        //  gf.initFromFile("C:\\MATALA\\function_file.txt");
        // gf.saveToFile(file2);
        Functions_GUI gf1 = new Functions_GUI();

        //(plus(div(1.0x+1.0,mul(mul(1.0x+3.0,1.0x-2.0),1.0x-4.0)),2.0));
        //  div(plus(-1.0x^4+2.4x^2+3.1,0.1x^5-1.2999999999999998x+5.0),-1.0x^4+2.4x^2+3.1)
        // function f1 = new ComplexFunction("plus(-1.0x^4+2.4x^2+3.1,0.1x^5-1.2999999999999998x+5.0)")
        //gf1.add()

        //gf.drawFunctions(2500, 2000,new Range(-50, 50), new Range(-50, 50), 100);
        functions fun = FunctionsFactory();
        fun.drawFunctions(1000, 500,new Range(-50, 50), new Range(-50, 50), 10000);
    }


}
