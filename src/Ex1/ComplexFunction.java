package Ex1;

import javax.management.RuntimeErrorException;

public class ComplexFunction implements complex_function {
    public Operation op;
    public function left;
    public function right;

    public ComplexFunction() {
        this.op = Operation.None;
        this.left = null;
        this.right = null;
    }

    public ComplexFunction(Operation op, function f1, function f2) {
        this.op = op;
        this.left = f1;
        this.right = f2;
    }

    public ComplexFunction(ComplexFunction cf) {
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

        if(this.op == Operation.Plus) {
            return this.left.f(x)+this.right.f(x);
        }

        else if(this.op == Operation.Times) {
            return this.left.f(x)*this.right.f(x);
        }

        else if(this.op == Operation.Divid) {
            if (this.right.f(x) == 0 ){
                System.out.println("ERROR: cannot divide in zero");
                return Integer.MIN_VALUE;
            }
            return this.left.f(x)/this.right.f(x);
        }

        else if(this.op == Operation.Max) {
            return Math.max(this.left.f(x),this.right.f(x));
        }

        else if(this.op == Operation.Min) {
            return Math.min(this.left.f(x),this.right.f(x));
        }

        else if(this.op == Operation.Comp) {
            return this.left.f(this.right.f(x));
        }

        else // if this.op == none
            return this.left.f(x);
    }

    //5x+7
    @Override
    public function initFromString(String s) {
        s.toLowerCase();
        if (!s.contains("(")) {
            if (s.contains("+") || s.contains("-")) {
                Polynom p = new Polynom();
                function f = p.initFromString(s);
                return f;
            } else {
                Monom m = new Monom(0,0);
                function f = m.initFromString(s);
                return f;
            }

        } else if (!basicFormat(s)) {
            System.out.println("ERROR: invalid input");
        } else {
            String operation = s.substring(0, s.indexOf('('));
            switch (operation) {
                case "plus":
                    s.substring(s.indexOf('('));
                    return new ComplexFunction(Operation.Plus, initFromString(s.substring(s.indexOf('(') + 1, s.indexOf(','))),
                            initFromString(s.substring(s.indexOf(',') + 1, s.lastIndexOf(')'))));

                case "mul":
                    s.substring(s.indexOf('('));
                    return new ComplexFunction(Operation.Times, initFromString(s.substring(s.indexOf('(') + 1, s.indexOf(','))),
                            initFromString(s.substring(s.indexOf(',') + 1, s.lastIndexOf(')'))));

                case "div":
                    s.substring(s.indexOf('('));
                    return new ComplexFunction(Operation.Divid, initFromString(s.substring(s.indexOf('(') + 1, s.indexOf(','))),
                            initFromString(s.substring(s.indexOf(',') + 1, s.lastIndexOf(')'))));

                case "max":
                    s.substring(s.indexOf('('));
                    return new ComplexFunction(Operation.Max, initFromString(s.substring(s.indexOf('(') + 1, s.indexOf(','))),
                            initFromString(s.substring(s.indexOf(',') + 1, s.lastIndexOf(')'))));

                case "min":
                    s.substring(s.indexOf('('));
                    return new ComplexFunction(Operation.Min, initFromString(s.substring(s.indexOf('(') + 1, s.indexOf(','))),
                            initFromString(s.substring(s.indexOf(',') + 1, s.lastIndexOf(')'))));


                case "comp":
                    s.substring(s.indexOf('('));
                    return new ComplexFunction(Operation.Comp, initFromString(s.substring(s.indexOf('(') + 1, s.indexOf(','))),
                            initFromString(s.substring(s.indexOf(',') + 1, s.lastIndexOf(')'))));

                case "None":
                    s.substring(s.indexOf('('));
                    return new ComplexFunction(Operation.None, initFromString(s.substring(s.indexOf('(') + 1, s.indexOf(','))),
                            null);

                default:
                    System.out.println("ERROR: invalid operation");
            }
        }
        return this;
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
        ComplexFunction f = new ComplexFunction();
        function n = f.initFromString(this.toString());
        return n;
    }
    @Override
    public String toString() {

        if(this.op == Operation.Plus) {
            return "plus("+this.left.toString()+","+this.right.toString()+")";
        }

        else if(this.op == Operation.Times) {
            return "mul("+this.left.toString()+","+this.right.toString()+")";
        }

        else if(this.op == Operation.Divid) {
            return  "div("+this.left.toString()+","+this.right.toString()+")";
        }

        else if(this.op == Operation.Max) {
            return "max("+this.left.toString()+","+this.right.toString()+")";
        }

        else if(this.op == Operation.Min) {
            return "min("+this.left.toString()+","+this.right.toString()+")";
        }

        else if(this.op == Operation.Comp) {
            return "comp("+this.left.toString()+","+this.right.toString()+")";
        }

        else // if this.op == none
            return this.left.toString();
    }

    @Override
    public boolean equals(Object obj){
        return false;
    }

    public static void main(String[] args) {
        function f1 = new Polynom("2x^2+5");
        function f2 = new Polynom("x^2-4");
        function f3 = new Monom("115x");
        function f4 = new Polynom( "555x+666x^6");
        /*ComplexFunction bdika = new ComplexFunction(Operation.Divid, f1, f2);
        ComplexFunction copyCf =(ComplexFunction)(bdika.copy());
        System.out.println("test 1");
        System.out.println("BDIKA:    "+bdika);
        System.out.println("COPIED:    "+ copyCf);
        System.out.println("test 2");
        copyCf.plus(f3);
        System.out.println("BDIKA:  AFTER PLUS    "+bdika);
        System.out.println("COPIED: AFTER PLUS    "+copyCf);*/
        //bdika.plus(f4);
        // System.out.println("AFTER PLUS BDIKA:    "+bdika.op+"  op,   "+ bdika.left +"  left,  "+ bdika.right + "  right,  ");

        String s ="comp(2x^2+5,min(6x^7-15,mul(115x,555x+666x^3)))";
        ComplexFunction f = new ComplexFunction();
        function cf = f.initFromString(s);
        System.out.println(cf);
        System.out.println(cf.f(2));

    }

    }