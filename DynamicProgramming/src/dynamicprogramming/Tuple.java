package dynamicprogramming;

public class Tuple<T1, T2> {

    protected T1 t1;
    protected T2 t2;

    public Tuple(T1 f1, T2 f2) {
        this.t1 = f1;
        this.t2 = f2;
    }

    public T1 getT1() {
        return t1;
    }

    public T2 getT2() {
        return t2;
    }
}
