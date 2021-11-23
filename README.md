# Elliptic Curve Cryptography

y^2 = x^3 + `a`x + `b` (mod `p`)
<br> &nbsp; &nbsp; &nbsp; &nbsp;
=> `n` * P(`xp`, `yp`) = Q(`xq`, `yq`)

> This program provides `xq` and `yq`.

<br />

# Usage

```Bash
# java version: openjdk 11.0.11
cd bin
java -cp .:../lib/bignum-projects.jar App <a> <b> <p> <xp> <yp> <n>
```

### Params
- `a` : Coefficient of x on the elliptic curve
- `b` : Constant on the elliptic curve
- `p` : Prime number used for modular operations
- `xp` : Point x above the elliptic curve
- `yp` : Point y above the elliptic curve
- `n` : Number to do scalar multiplication

### Example
```
>> java -cp .:../lib/bignum-projects.jar App 2 3 97 3 6 243
243P: (80, 87)

>> java -cp .:../lib/bignum-projects.jar App 1 6 11 2 7 16
16P: (8, 3)

>> java -cp .:../lib/bignum-projects.jar App -3 2 11 2 2 1
It is not an elliptic curve used for encryption..

>> java -cp .:../lib/bignum-projects.jar App -help         

Usage: java -cp .:../lib/bignum-projects.jar App <a> <b> <p> <xp> <yp> <n>
This program provides 'xq' and 'yq'

    y^2 = x^3 + ax + b (mod p)
        => n * P(xp, yp) = Q(xq, yq)

    params: <a> <b> <p> <xp> <yp> <n>
        a : Coefficient of x on the elliptic curve
        b : Constant on the elliptic curve
        p : Prime number used for modular operations
        xp: Point x above the elliptic curve
        yp: Point y above the elliptic curve
        n : Number to do scalar multiplication

```
