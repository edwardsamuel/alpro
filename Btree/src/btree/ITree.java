package btree;


public interface ITree<T> {

    public boolean add(T value);
	boolean contains(T value);
    public T remove(T value);
    public int size();
    public boolean validate();
    public java.util.Collection<T> toCollection();
}
