import java.util.Scanner;
public class Q2 {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int x;
        long n,t;//计算总数时不包括n
        x=scanner.nextInt();
        n=scanner.nextLong();
        t=n/10;
        AddThread1 add1 = new AddThread1();
        add1.x=x;
        add1.n=t;
        AddThread2 add2 = new AddThread2();
        add2.x=x;
        add2.n=t;
        AddThread3 add3 = new AddThread3();
        add3.x=x;
        add3.n=t;
        AddThread4 add4 = new AddThread4();
        add4.x=x;
        add4.n=t;
        AddThread5 add5 = new AddThread5();
        add5.x=x;
        add5.n=t;
        AddThread6 add6 = new AddThread6();
        add6.x=x;
        add6.n=t;
        AddThread7 add7 = new AddThread7();
        add7.x=x;
        add7.n=t;
        AddThread8 add8 = new AddThread8();
        add8.x=x;
        add8.n=t;
        AddThread9 add9 = new AddThread9();
        add9.x=x;
        add9.n=t;
        AddThread10 add10 = new AddThread10();
        add10.x=x;
        add10.n=t;
        AddThread11 add11 = new AddThread11();
        add11.x=x;
        add11.n=t;
        add11.N=n;
        add1.start();
        add2.start();
        add3.start();
        add4.start();
        add5.start();
        add6.start();
        add7.start();
        add8.start();
        add9.start();
        add10.start();
        add11.start();
        add1.join();
        add2.join();
        add3.join();
        add4.join();
        add5.join();
        add6.join();
        add7.join();
        add8.join();
        add9.join();
        add10.join();
        add11.join();
        System.out.println(Counter.count1+Counter.count2+Counter.count3+Counter.count4+Counter.count5+
                Counter.count6+Counter.count7+Counter.count8+Counter.count9+Counter.count10+Counter.count11);
    }
}
class Counter {
    public static long count1 = 0;
    public static long count2 = 0;
    public static long count3 = 0;
    public static long count4 = 0;
    public static long count5 = 0;
    public static long count6 = 0;
    public static long count7 = 0;
    public static long count8 = 0;
    public static long count9 = 0;
    public static long count10 = 0;
    public static long count11 = 0;
}
class AddThread1 extends Thread {
    public int x;
    public long n;
    public void run() {
        for(long i=1;i<=n;i++){
            if (String.valueOf(i).contains(String.valueOf(x)))
                Counter.count1 += i;
        }
    }
}
class AddThread2 extends Thread {
    public int x;
    public long n;
    public void run() {
        for(long i=n+1;i<=2*n;i++){
            if (String.valueOf(i).contains(String.valueOf(x)))
                Counter.count2 += i;
        }
    }
}
class AddThread3 extends Thread {
    public int x;
    public long n;
    public void run() {
        for(long i=2*n+1;i<=3*n;i++){
            if (String.valueOf(i).contains(String.valueOf(x)))
                Counter.count3 += i;
        }
    }
}
class AddThread4 extends Thread {
    public int x;
    public long n;
    public void run() {
        for(long i=3*n+1;i<=4*n;i++){
            if (String.valueOf(i).contains(String.valueOf(x)))
                Counter.count4 += i;
        }
    }
}
class AddThread5 extends Thread {
    public int x;
    public long n;
    public void run() {
        for(long i=4*n+1;i<=5*n;i++){
            if (String.valueOf(i).contains(String.valueOf(x)))
                Counter.count5 += i;
        }
    }
}
class AddThread6 extends Thread {
    public int x;
    public long n;
    public void run() {
        for(long i=5*n+1;i<=6*n;i++){
            if (String.valueOf(i).contains(String.valueOf(x)))
                Counter.count6 += i;
        }
    }
}
class AddThread7 extends Thread {
    public int x;
    public long n;
    public void run() {
        for(long i=6*n+1;i<=7*n;i++){
            if (String.valueOf(i).contains(String.valueOf(x)))
                Counter.count7 += i;
        }
    }
}
class AddThread8 extends Thread {
    public int x;
    public long n;
    public void run() {
        for(long i=7*n+1;i<=8*n;i++){
            if (String.valueOf(i).contains(String.valueOf(x)))
                Counter.count8 += i;
        }
    }
}
class AddThread9 extends Thread {
    public int x;
    public long n;
    public void run() {
        for(long i=8*n+1;i<=9*n;i++){
            if (String.valueOf(i).contains(String.valueOf(x)))
                Counter.count9 += i;
        }
    }
}
class AddThread10 extends Thread {
    public int x;
    public long n;
    public void run() {
        for(long i=9*n+1;i<10*n;i++){
            if (String.valueOf(i).contains(String.valueOf(x)))
                Counter.count10 += i;
        }
    }
}
class AddThread11 extends Thread {
    public int x;
    public long n;
    public long N;
    public void run() {
        for(long i=10*n;i<N;i++){
            if (String.valueOf(i).contains(String.valueOf(x)))
                Counter.count11 += i;
        }
    }
}