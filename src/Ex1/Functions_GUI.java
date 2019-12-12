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
            File file1 = new File(file);
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



}
