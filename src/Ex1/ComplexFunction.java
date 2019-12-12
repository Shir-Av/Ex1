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

    public ComplexFunction(function cf) {
        if (cf instanceof Monom || cf instanceof Polynom){
            this.op = Operation.None;
            this.left = cf;
            this.right = null;
        }
        else if(cf instanceof ComplexFunction) {

            this.op = ((ComplexFunction) cf).op;
            this.left = ((ComplexFunction) cf).left;
            this.right = ((ComplexFunction) cf).right;
        }
    }
    public ComplexFunction(String op, function f1, function f2){
        this.op = oper(op);
        this.left = f1;
        this.right = f2;
    }

    public Operation oper(String s){
        Operation p = Operation.Error;
        String operation = s;
        switch (operation) {
            case "plus":
                return Operation.Plus;
            case "mul":
                return Operation.Times;
            case "div":
                return Operation.Divid;
            case "max":
                return Operation.Max;
            case "min":
                return Operation.Min;
            case "comp":
                return Operation.Comp;
            case "None":
                return Operation.None;
            default:
                System.out.println("ERROR: invalid operation");
        }
        return p;
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

        if (this.op == Operation.Plus) {
            return this.left.f(x) + this.right.f(x);
        } else if (this.op == Operation.Times) {
            return this.left.f(x) * this.right.f(x);
        } else if (this.op == Operation.Divid) {
            if (this.right.f(x) == 0) {
                System.out.println("ERROR: cannot divide in zero");
                return Integer.MIN_VALUE;
            }
            return this.left.f(x) / this.right.f(x);
        } else if (this.op == Operation.Max) {
            return Math.max(this.left.f(x), this.right.f(x));
        } else if (this.op == Operation.Min) {
            return Math.min(this.left.f(x), this.right.f(x));
        } else if (this.op == Operation.Comp) {
            return this.left.f(this.right.f(x));
        } else // if this.op == none
            return this.left.f(x);
    }

    @Override
    public function initFromString(String s) {
        s.toLowerCase();
        if (!s.contains("(")) {
            if (s.contains("+") || s.contains("-")) {
                Polynom p = new Polynom();
                function f = p.initFromString(s);
                return f;
            } else {
                Monom m = new Monom(0, 0);
                function f = m.initFromString(s);
                return f;
            }
        } else if (!basicFormat(s)) {
            System.out.println("ERROR: invalid input");
        } else {
            String operation = s.substring(0, s.indexOf('('));
            return new ComplexFunction(oper(operation), initFromString(s.substring(s.indexOf('(') + 1 , middle(s))),
                    initFromString(s.substring(middle(s) + 1, s.lastIndexOf(')'))));
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
        if (openParenthesis != closeParenthesis || comma > openParenthesis) {
            return false;
        }
        return true;
    }

    public int middle(String s) {
        int finalMid = s.indexOf('(')+1;
        String tempS = s.substring(finalMid);
        int i = 0;
        int mid = tempS.indexOf(',');
        String st = tempS.substring(0,mid);
        if (st.contains("(")) {
            while (i < s.length()) {
                if (!basicFormat(st)){
                    tempS = st+tempS.substring(mid+1,tempS.length());
                    finalMid++;
                    if (tempS.contains(",")) {
                        mid = tempS.indexOf(',');
                        st = tempS.substring(0, mid);
                        i++;
                    }
                    else{
                        break;
                    }
                }
                else {
                    return mid+finalMid;
                }
            }
        }
        return mid+finalMid;
    }
    @Override
    public function copy () {
        ComplexFunction f = new ComplexFunction();
        function n = f.initFromString(this.toString());
        return n;
    }
    @Override
    public String toString () {

        if (this.op == Operation.Plus) {
            return "plus(" + this.left.toString() + "," + this.right.toString() + ")";
        } else if (this.op == Operation.Times) {
            return "mul(" + this.left.toString() + "," + this.right.toString() + ")";
        } else if (this.op == Operation.Divid) {
            return "div(" + this.left.toString() + "," + this.right.toString() + ")";
        } else if (this.op == Operation.Max) {
            return "max(" + this.left.toString() + "," + this.right.toString() + ")";
        } else if (this.op == Operation.Min) {
            return "min(" + this.left.toString() + "," + this.right.toString() + ")";
        } else if (this.op == Operation.Comp) {
            return "comp(" + this.left.toString() + "," + this.right.toString() + ")";
        } else // if this.op == none
            return this.left.toString();
    }

    @Override
    public boolean equals (Object obj){
        function o = (function) obj;
        for (int i = 0; i < 100; i++) {
            int r = (int) (Math.random() * ((50 - (-50)) + 1)) + (-50);
            if (this.f(r) != o.f(r)) {
                return false;
            }
        }
        return true;
    }

}