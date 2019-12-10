package Ex1;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;


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
        line = scan.nextLine();
        while (scan.hasNext()) {
            f.add((cf.initFromString(line)));
            line = scan.nextLine();
        }
        for(int i = 0; i < f.size(); i++) {
            System.out.println("0000000000000000");
            String fString = f.get(i).toString();
            System.out.print(fString +"\n");
        }
    }
    @Override
    public void saveToFile(String file) throws IOException {
        try {
            File file1 = new File("string file.txt");
            FileWriter fw = new FileWriter(file1);
            PrintWriter pw = new PrintWriter(fw);
            Iterator<function> iterator = this.f.iterator();
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



    public static void main(String[] args) throws IOException {
        File file1 = new File("C:\\MATALA/function_file.txt");
        String file2 = new String("");
        Functions_GUI gf =new Functions_GUI();
        gf.initFromFile("C:\\MATALA/function_file.txt");

        // gf.add(new ComplexFunction(Operation.Plus,new Polynom("x"), new Polynom("2x")));
	   /* gf.add(new Polynom("x^2"));
		gf.add(new Polynom("x^3"));
		gf.add(new Polynom("x^4"));
		gf.add(new Polynom("x^5"));
		gf.add(new Polynom("x^6"));
		gf.add(new Polynom("x^7"));
		gf.add(new Polynom("x"));*/
        gf.saveToFile(file2);


    }
}