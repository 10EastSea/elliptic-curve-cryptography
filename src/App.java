import java.math.BigInteger;

public class App {

    public static void testMod() {
        System.out.println((new BigInteger("-1")).mod(BigInteger.TEN));
    }

    public static void test1() throws Exception {
        EllipticCurve ec = new EllipticCurve(new BigInteger("2"), new BigInteger("3"), new BigInteger("97"));
        
        Point P = new Point(3, 6);
        System.out.println("  P: " + P.toString());
        Point P2 = ec.find_2P(P);
        System.out.println(" 2P: " + P2);
        Point P3 = ec.find_P_plus_Q(P2, P);
        System.out.println(" 3P: " + P3);
        Point P4 = ec.find_2P(P2);
        System.out.println(" 4P: " + P4);
        Point P5 = ec.find_P_plus_Q(P4, P);
        System.out.println(" 5P: " + P5);
        Point P6 = ec.find_2P(P3);
        System.out.println(" 6P: " + P6);
        Point P7 = ec.find_P_plus_Q(P6, P);
        System.out.println(" 7P: " + P7);
        Point P8 = ec.find_2P(P4);
        System.out.println(" 8P: " + P8);
        Point P9 = ec.find_P_plus_Q(P8, P);
        System.out.println(" 9P: " + P9);
        Point P10 = ec.find_2P(P5);
        System.out.println("10P: " + P10);
    }

    public static void test2() throws NoExistInverse {
        int n = 243;
        try {
            EllipticCurve ec = new EllipticCurve(new BigInteger("2"), new BigInteger("3"), new BigInteger("97"));
            Point P = new Point(3, 6);
            System.out.println(n + "P: " + ec.find_nP(P, new BigInteger("" + n)));
        } catch(NotECC e) {
            System.out.println("It is not an elliptic curve used for encryption..");
            return;
        }
    }

    public static void test3() throws NoExistInverse {
        int n = 16;
        try {
            EllipticCurve ec = new EllipticCurve(new BigInteger("1"), new BigInteger("6"), new BigInteger("11"));
            Point P = new Point(2, 7);
            System.out.println(n + "P: " + ec.find_nP(P, new BigInteger("" + n)));
        } catch(NotECC e) {
            System.out.println("It is not an elliptic curve used for encryption..");
            return;
        }
    }

    public static void test4() throws NoExistInverse {
        try {
            new EllipticCurve(new BigInteger("-3"), new BigInteger("2"), new BigInteger("11"));
        } catch(NotECC e) {
            System.out.println("It is not an elliptic curve used for encryption..");
            return;
        }
    }

    public static boolean checkDigits(String s) {
        for(int i=0; i<s.length(); i++) {
            if(!Character.isDigit(s.charAt(i))) return false;
        }
        return true;
    }

    public static void help(String msg) {
        System.out.println("\n" + msg + "\n");
        System.out.println("    y^2 = x^3 + ax + b (mod p)");
        System.out.println("        => n * P(xp, yp) = Q(xq, yq)");
        System.out.println();
        System.out.println("    params: <a> <b> <p> <xp> <yp> <n>");
        System.out.println("        a : Coefficient of x on the elliptic curve");
        System.out.println("        b : Constant on the elliptic curve");
        System.out.println("        p : Prime number used for modular operations");
        System.out.println("        xp: Point x above the elliptic curve");
        System.out.println("        yp: Point y above the elliptic curve");
        System.out.println("        n : Number to do scalar multiplication");
        System.out.println();
    }

    public static void main(String[] args) throws Exception {
        if(args[0].equals("-help")) { help("Usage: java -cp .:../lib/bignum-projects.jar App <a> <b> <p> <xp> <yp> <n>\nThis program provides \'xq\' and \'yq\'"); return; }
        
        if(args.length < 6) { help("Please enter all required parameters"); return; }
        if(!checkDigits(args[0]) || !checkDigits(args[1]) || !checkDigits(args[2]) || !checkDigits(args[3]) || !checkDigits(args[4]) || !checkDigits(args[5])) {
            help("<a>, <b>, <p>, <xp>, <yp>, and <n> must be number"); return;
        }

        int n = Integer.parseInt(args[5]);
        try {
            EllipticCurve ec = new EllipticCurve(new BigInteger(args[0]), new BigInteger(args[1]), new BigInteger(args[2]));
            Point P = new Point(args[3], args[4]);
            System.out.println(n + "P: " + ec.find_nP(P, new BigInteger("" + n)));
        } catch(NotECC e) { System.out.println("It is not an elliptic curve used for encryption.."); }

        return;
    }
}
