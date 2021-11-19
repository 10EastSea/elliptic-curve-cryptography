import java.math.BigInteger;

public class App {

    public static void testMod() {
        System.out.println((new BigInteger("-1")).mod(BigInteger.TEN));
    }

    public static void test1() throws NoExistInverse {
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
        EllipticCurve ec = new EllipticCurve(new BigInteger("2"), new BigInteger("3"), new BigInteger("97"));
        
        Point P = new Point(3, 6);
        System.out.println(n + "P: " + ec.find_nP(P, new BigInteger("" + n)));
    }

    public static void test3() throws NoExistInverse {
        int n = 16;
        EllipticCurve ec = new EllipticCurve(new BigInteger("1"), new BigInteger("6"), new BigInteger("11"));
        
        Point P = new Point(2, 7);
        System.out.println(n + "P: " + ec.find_nP(P, new BigInteger("" + n)));
    }

    public static void main(String[] args) throws Exception {
        // test1();
        // test2();
        // test3();
    }
}
