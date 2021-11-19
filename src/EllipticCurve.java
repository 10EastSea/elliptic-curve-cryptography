import java.math.BigInteger;

public class EllipticCurve {
    private BigInteger a, b; // y^2 = x^3 + ax + b
    private BigInteger p; // mod p (prime)

    EllipticCurve(BigInteger a, BigInteger b, BigInteger p) {
        this.a = a;
        this.b = b;
        this.p = p;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////

    public BigInteger find_slope_of_tangent_line(Point P) throws NoExistInverse {
        BigInteger numerator = P.getX().pow(2).multiply(new BigInteger("3")).add(a).mod(p);
        BigInteger denominator = P.getY().multiply(BigInteger.TWO).mod(p);

        denominator = (new Inverse(p, denominator)).getxInv();
        if(denominator == null) throw new NoExistInverse();

        return numerator.multiply(denominator).mod(p);
    }

    public BigInteger find_slope_of_two_point(Point P, Point Q) throws NoExistInverse {
        BigInteger numerator, denominator;
        if(P.getX().compareTo(Q.getX()) >= 0) {
            numerator = P.getY().subtract(Q.getY()).mod(p);
            denominator = P.getX().subtract(Q.getX()).mod(p);
        } else {
            numerator = Q.getY().subtract(P.getY()).mod(p);
            denominator = Q.getX().subtract(P.getX()).mod(p);
        }

        denominator = (new Inverse(p, denominator)).getxInv();
        if(denominator == null) throw new NoExistInverse();

        return numerator.multiply(denominator).mod(p);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////

    public Point find_2P(Point P) {
        BigInteger m;
        try { m = find_slope_of_tangent_line(P); }
        catch(NoExistInverse e) { return new Point(0, 0); }

        BigInteger x = m.pow(2).subtract(P.getX()).subtract(P.getX()).mod(p);
        BigInteger y = x.subtract(P.getX()).multiply(m).add(P.getY()).mod(p);

        return new Point(x, y.negate().mod(p));
    }

    public Point find_P_plus_Q(Point P, Point Q) {
        BigInteger m;
        try { 
            if(P.toString().equals(Q.toString())) m = find_slope_of_tangent_line(P);
            else m = find_slope_of_two_point(P, Q);
        }
        catch(NoExistInverse e) { 
            return new Point(0, 0);
        }

        BigInteger x = m.pow(2).subtract(P.getX()).subtract(Q.getX()).mod(p);
        BigInteger y = x.subtract(P.getX()).multiply(m).add(P.getY()).mod(p);

        return new Point(x, y.negate().mod(p));
    }

    public Point find_nP(Point P, BigInteger n) {
        StringBuilder bStr = new StringBuilder(n.toString(2));
        Point nP = new Point(0, 0), tmp = P.clone();

        while(bStr.length() > 0) {
            char b = bStr.charAt(bStr.length() - 1);
            
            if(b == '1') {
                if(nP.isInf()) nP = tmp.clone();
                else if(tmp.isInf()) continue;
                else nP = find_P_plus_Q(nP, tmp);
            }
            tmp = find_2P(tmp);

            bStr.deleteCharAt(bStr.length() - 1);
        }

        return nP;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public String toString() { return "y^2 = x^3 + (" + a + ") * x + (" + b + ") (mod " + p + ")"; }
}
