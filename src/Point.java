import java.math.BigInteger;

public class Point {
    private BigInteger x, y;

    Point(int x, int y) {
        this.x = BigInteger.valueOf(x);
        this.y = BigInteger.valueOf(y);
    }

    Point(BigInteger x, BigInteger y) {
        this.x = x;
        this.y = y;
    }

    public BigInteger getX() { return x; }
    public BigInteger getY() { return y; }
    public void setX(BigInteger x) { this.x = x; }
    public void setY(BigInteger y) { this.y = y; }

    public boolean isInf() { return (x.compareTo(BigInteger.ZERO) == 0 && y.compareTo(BigInteger.ZERO) == 0); }

    @Override
    public String toString() { return "(" + x + ", " + y + ")"; }
    @Override
    public Point clone() { return new Point(x, y); }
}
