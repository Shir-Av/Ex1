package Ex1;

import javax.management.RuntimeErrorException;

public class ComplexFunction implements complex_function {
    public Operation op ;
    public function left;
    public function right;

    public ComplexFunction(){
        this.op = Operation.None;
        this.left = null;
        this.right = null;
    }
    public ComplexFunction (Operation op , function f1, function f2){
        this.op = op;
        this.left = f1;
        this.right = f2;
    }
    public ComplexFunction(ComplexFunction cf){
        this.op = cf.op;
        this.left = cf.left;
        this.right = cf.right;
    }
    @Override
    public void plus(function f1) {
        ComplexFunction copied = new ComplexFunction(this);
        this.op = Operation.Plus;
        this.right = f1;
        this.left = copied;

    }

    @Override
    public void mul(function f1) {
        ComplexFunction copied = new ComplexFunction(this);
        this.op = Operation.Times;
        this.right = f1;
        this.left = copied;
    }

    @Override
    public void div(function f1) {
        ComplexFunction copied = new ComplexFunction(this);
        this.op = Operation.Divid;
        this.right = f1;
        this.left = copied;
    }

    @Override
    public void max(function f1) {
        ComplexFunction copied = new ComplexFunction(this);
        this.op = Operation.Max;
        this.right = f1;
        this.left = copied;
    }

    @Override
    public void min(function f1) {
        ComplexFunction copied = new ComplexFunction(this);
        this.op = Operation.Min;
        this.right = f1;
        this.left = copied;
    }

    @Override
    public void comp(function f1) {
        ComplexFunction copied = new ComplexFunction(this);
        this.op = Operation.Comp;
        this.right = f1;
        this.left = copied;
    }

    @Override
    public function left() {
        return this.left;
    }

    @Override
    public function right() {
        return this.right;
    }

    @Override
    public Operation getOp() {
        return this.op;
    }

    @Override
    public double f(double x) {
        return 0;
    }
//5x+7
    @Override
    public function initFromString(String s) {
        s.toLowerCase();
        if (!basicFormat(s)){
            System.out.println("ERROR: invalid input");
        }
        else {
            if (!s.contains("(")){
                if(s.contains("+") || s.contains("-")){
                    function f = new Polynom(s);
                    return f;
                }
                else {
                    function f = new Monom(s);
                    return f;
                }
            }
            else {
                String operation = s.substring(0, s.indexOf('('));
                //System.out.println(operation);
                        switch (operation)
                                {
                                    case "plus":
                                        s.substring( s.indexOf('('));
                                        return new ComplexFunction(Operation.Plus,initFromString(s.substring(1,s.indexOf(','))),
                                                initFromString(s.substring(s.indexOf(',')+1,s.indexOf(')'))));

                                    case "mul":
                                        s.substring( s.indexOf('('));
                                        return new ComplexFunction(Operation.Times,initFromString(s.substring(1,s.indexOf(','))),
                                                initFromString(s.substring(s.indexOf(',')+1,s.indexOf(')'))));

                                    case "div":
                                        s.substring( s.indexOf('('));
                                        return new ComplexFunction(Operation.Divid,initFromString(s.substring(1,s.indexOf(','))),
                                                initFromString(s.substring(s.indexOf(',')+1,s.indexOf(')'))));

                                    case "max":
                                        s.substring( s.indexOf('('));
                                        return new ComplexFunction(Operation.Max,initFromString(s.substring(1,s.indexOf(','))),
                                                initFromString(s.substring(s.indexOf(',')+1,s.indexOf(')'))));

                                    case "min":
                                        s.substring( s.indexOf('('));
                                        return new ComplexFunction(Operation.Min,initFromString(s.substring(1,s.indexOf(','))),
                                                initFromString(s.substring(s.indexOf(',')+1,s.indexOf(')'))));

                                    case "comp":
                                        s.substring( s.indexOf('('));
                                        return new ComplexFunction(Operation.Comp,initFromString(s.substring(1,s.indexOf(','))),
                                                initFromString(s.substring(s.indexOf(',')+1,s.indexOf(')'))));

                                    default:
                                        System.out.println("ERROR: invalid operation");
                                }
            }   }
        return null;
        }

    

    public boolean basicFormat(String s) {
        int openParenthesis = 0;
        int closeParenthesis = 0;
        int comma = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                openParenthesis++;
            } else if (s.charAt(i) == ')') {
                closeParenthesis++;
            } else if (s.charAt(i) == ',') {
                comma++;
            }
        }
        if (openParenthesis != closeParenthesis || comma > openParenthesis ){
            System.out.println(s+"          fail on basic format");
            return false;
        }
        return true;
    }
//(f1,min(f2,mul(f4,f5)))
    @Override
    public function copy() {
        /*function n = initFromString(this.toString());
        if (this.left == null){
            function c = new ComplexFunction(this.op, this, this.right);
            return c;
        }
        function cCf = new ComplexFunction(this.op, this.left.copy(), this.right.copy());
        return cCf;*/
        return null;
    }
    @Override
    public String toString(){
        return null;
    }
    @Override
    public boolean equals(Object obj){
        return false;
    }

    public static void main(String[] args) {
        function f1 = new Polynom("2x^2+5");
        function f2 = new Polynom("6x^7-15");
        function f3 = new Monom("115x");
        function f4 = new Polynom( "555x+666x^6");
        /*ComplexFunction bdika = new ComplexFunction(Operation.Plus, f1, f2);
        ComplexFunction copyCf =(ComplexFunction)(bdika.copy());
       *//* System.out.println("test 1");
        System.out.println("BDIKA:    "+bdika.op+"  op,   "+ bdika.left +"  left,  "+ bdika.right + "  right,  ");
        System.out.println("COPIED:    "+copyCf.op+"  op,   "+ copyCf.left +"  left,  "+ copyCf.right + "  right,  ");
        System.out.println("test 2");
        copyCf.right = f3;
        System.out.println("BDIKA:    "+bdika.op+"  op,   "+ bdika.left +"   left,  "+ bdika.right + "  right,  ");
        System.out.println("COPIED:    "+copyCf.op+"  op,   "+ copyCf.left +"  left,  "+ copyCf.right + "  right,  ");*//*
        bdika.plus(f4);
        System.out.println("AFTER PLUS BDIKA:    "+bdika.op+"  op,   "+ bdika.left +"  left,  "+ bdika.right + "  right,  ");
        */
        String s ="comp(2x^2+5,min(6x^7-15,mul(115x,555x+666x^6)))";
        ComplexFunction f = new ComplexFunction();
        function cf = f.initFromString(s);
        System.out.println(cf + "hi");
        /*String operation = s.substring( s.indexOf('('));
        System.out.println(operation);*/


    }

}
