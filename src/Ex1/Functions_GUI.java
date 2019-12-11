package Ex1;


import com.google.gson.Gson;
import sun.plugin2.util.ColorUtil;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.Random;


public class Functions_GUI implements functions {

    ArrayList<function> f = new ArrayList<function>();

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
        while (scan.hasNext()) {
            line = scan.nextLine();
            f.add((cf.initFromString(line)));
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
        createCoordinateSystem(width,height,rx,ry);
        double rxRes = (Math.abs(rx.get_max()) + Math.abs(rx.get_min()));
        double x_step = (rxRes/resolution);
        Iterator<function> iterator = f.iterator();
        while (iterator.hasNext()){
            StdDraw.setPenColor(f_color());
            function fun = iterator.next();
            for (double i = rx.get_min(); i <= rx.get_max(); i +=x_step){
                StdDraw.line(i,fun.f(i),i+x_step,fun.f(i+x_step));
            }
        }

    }
    private void createCoordinateSystem(int width, int height, Range rx, Range ry){
        StdDraw.setCanvasSize(width, height);
        StdDraw.setXscale(rx.get_min()-0.3, rx.get_max()+0.3);
        StdDraw.setYscale(ry.get_min()-0.3, ry.get_max()+0.3);
        //drawing the basic coordinate system
        StdDraw.setPenColor(Color.LIGHT_GRAY);
        for (double i = rx.get_min(); i <= rx.get_max(); i++) {
            StdDraw.line(i, ry.get_min(), i, ry.get_max());
        }
        for (double i = ry.get_min(); i <= ry.get_max(); i++) {
            StdDraw.line(rx.get_min(), i, rx.get_max(), i);
        }
        //drawing X & Y Axis.
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.setPenRadius(0.005);
        StdDraw.setFont(new Font("Ariel", Font.BOLD, 15));

        StdDraw.line(0, ry.get_min(), 0, ry.get_max());
        for (double i = ry.get_min(); i <= ry.get_max(); i++) {
            if (i != 0)
                StdDraw.text(-0.3, i - 0.07, Integer.toString((int)i));
        }

        StdDraw.line(rx.get_min(), 0, rx.get_max(), 0);
        for (double i = rx.get_min(); i <= rx.get_max(); i++) {
            StdDraw.text(i - 0.07, -0.3, Integer.toString((int)i));
        }
    }
    private Color f_color(){
        Random rand = new Random();
        int r = rand.nextInt(255);
        int g = rand.nextInt(255);
        int b = rand.nextInt(255);
        Color randomColor = new Color(r,g,b);
        return randomColor;
    }

    @Override
    public void drawFunctions(String json_file) {
        Gson gson = new Gson();
        try {
            FileReader reader = new FileReader(json_file);
            Jsonparam jp = gson.fromJson(new FileReader(json_file), Jsonparam.class);
            Range rx = new Range(jp.Range_X[0], jp.Range_X[1]);
            Range ry = new Range(jp.Range_Y[0], jp.Range_Y[1]);
            drawFunctions(jp.Width, jp.Height, rx, ry, jp.Resolution);
        } catch (Exception e) {
            System.out.println("The Json file is incorrect, draws diffult");
            Range rx = new Range(-15, 15);
            Range ry = new Range(-15, 15);
            drawFunctions(800, 600, rx, ry, 500);
        }
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

   /* public static functions FunctionsFactory() {
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
        ComplexFunction cf4 = new ComplexFunction("div", new Polynom("x+1"), cf3);
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
    }*/
    public static void main(String[] args) throws IOException {
       /* File file1 = new File("C:\\MATALA\\function_file.txt");
         String file2 = new String("");
        Functions_GUI gf =new Functions_GUI();
          gf.initFromFile("C:\\MATALA\\function_file.txt");
         gf.saveToFile(file2);*/
        //Functions_GUI gf1 = new Functions_GUI();

        //(plus(div(1.0x+1.0,mul(mul(1.0x+3.0,1.0x-2.0),1.0x-4.0)),2.0));
        //  div(plus(-1.0x^4+2.4x^2+3.1,0.1x^5-1.2999999999999998x+5.0),-1.0x^4+2.4x^2+3.1)
        // function f1 = new ComplexFunction("plus(-1.0x^4+2.4x^2+3.1,0.1x^5-1.2999999999999998x+5.0)")
        //gf1.add()

        //gf.drawFunctions(2500, 2000,new Range(-50, 50), new Range(-50, 50), 100);
      /*  functions fun = FunctionsFactory();
        fun.drawFunctions(1500, 800,new Range(-17, 17), new Range(-17, 20), 200);*/
        //functions fun = FunctionsFactory();
        //  fun.drawFunctions(1000, 600,new Range(-10, 10), new Range(-15, 5), 400);
        Functions_GUI fun = new Functions_GUI();
        fun.initFromFile("C:\\MATALA\\function_file.txt");
        fun.drawFunctions("C:\\MATALA\\GUI_params.txt");
        // fun.drawFunctions("C:\\Users\\User\\Downloads\\GUI_params.txt");
    }


}
