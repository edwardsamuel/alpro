/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package alpro;

/**
 *
 * @author User
 */
public class ColoredTree {

    private ColoredNode root;
    public ColoredNode TNil = new ColoredNode(-999, true, root, root, root);

    public ColoredTree() {
        TNil = new ColoredNode(-999, true, TNil, root, root);
    }

    public ColoredTree(int key) {
        //initialize root and connect it
        root = new ColoredNode(key, true, TNil, TNil, TNil);
    }

    //methods
    public void leftRotate(ColoredNode x) {
        ColoredNode y = x.getRight();
        x.setRight(y.getLeft());

        if (y.getLeft() != TNil) {
            y.getLeft().setParent(x);
        }

        y.setParent(x.getParent());

        if (x.getParent() == TNil) {
            root = y;
        } else if (x == x.getParent().getLeft()) {
            x.getParent().setLeft(y);
        } else {
            x.getParent().setRight(y);
        }

        y.setLeft(x);
        x.setParent(y);
        y.setSize(x.getSize());
        x.setSize(x.getLeft().getSize() + x.getRight().getSize() + 1);
    }

    public void rightRotate(ColoredNode x) {
        ColoredNode y = x.getLeft();
        x.setLeft(y.getRight());

        if (y.getRight() != TNil) {
            y.getRight().setParent(x);
        }

        y.setParent(x.getParent());

        if (x.getParent() == TNil) {
            root = y;
        } else if (x == x.getParent().getRight()) {
            x.getParent().setRight(y);
        } else {
            x.getParent().setLeft(y);
        }

        y.setRight(x);
        x.setParent(y);
        y.setSize(x.getSize());
        x.setSize(x.getLeft().getSize() + x.getRight().getSize() + 1);
    }

    public void insert(ColoredNode z) {
        ColoredNode y = TNil;
        ColoredNode x = root;

        while (x != TNil) {
            y = x;
            y.setSize(y.getSize() + 1);
            if (z.getKey() < x.getKey()) {
                x = x.getLeft();
            } else {
                x = x.getRight();
            }
        }

        z.setParent(y);

        if (y == TNil) {
            root = z;
        } else if (z.getKey() < y.getKey()) {
            y.setLeft(z);
        } else {
            y.setRight(z);
        }

        z.setLeft(TNil);
        z.setRight(TNil);
        z.setBlack(false);

        insertFixup(z);
    }

    private void insertFixup(ColoredNode z) {
        ColoredNode y = null;

        while (z.getParent().getColor() == 'R') {
            if (z.getParent() == z.getParent().getParent().getLeft()) {
                y = z.getParent().getParent().getRight();
                if (y.getColor() == 'R') {
                    z.getParent().setBlack(true);
                    y.setBlack(true);
                    z.getParent().getParent().setBlack(false);
                    z = z.getParent().getParent();
                } else {
                    if (z == z.getParent().getRight()) {
                        z = z.getParent();
                        leftRotate(z);
                    }
                    z.getParent().setBlack(true);
                    z.getParent().getParent().setBlack(false);
                    rightRotate(z.getParent().getParent());
                }
            } else {
                y = z.getParent().getParent().getLeft();

                if (y != null && y.getColor() == 'R') {
                    z.getParent().setBlack(true);
                    y.setBlack(true);
                    z.getParent().getParent().setBlack(false);
                    z = z.getParent().getParent();
                } else {
                    if (z == z.getParent().getLeft()) {
                        z = z.getParent();
                        rightRotate(z);
                    }
                    z.getParent().setBlack(true);
                    z.getParent().getParent().setBlack(false);
                    leftRotate(z.getParent().getParent());
                }
            }
        }

        root.setBlack(true);
    }

    public void transplant(ColoredNode u, ColoredNode v) {
        if (u.getParent() == null) {
            root = v;
        } else if (u == u.getParent().getLeft()) {
            u.getParent().setLeft(v);
        } else {
            u.getParent().setRight(v);
        }

        v.setParent(u.getParent());
    }

    public ColoredNode treeMinimum(ColoredNode x) {
        while (x.getLeft() != TNil) {
            x = x.getLeft();
        }

        return x;
    }

    public void deleteFixup(ColoredNode x) {
        ColoredNode w = null;
        while (x != root && x.getColor() == 'B') {
            if (x == x.getParent().getLeft()) {
                w = x.getParent().getRight();
                if (w.getColor() == 'R') {
                    w.setBlack(true);
                    x.getParent().setBlack(false);
                    leftRotate(x.getParent());
                    w = x.getParent().getRight();
                }

                if (w.getLeft().getColor() == 'B' && w.getRight().getColor() == 'B') {
                    w.setBlack(false);
                    x = x.getParent();
                } else if (w.getRight().getColor() == 'B') {
                    w.getLeft().setBlack(true);
                    w.setBlack(false);
                    rightRotate(w);
                    w = x.getParent().getRight();
                }

                w.setBlack(x.getParent().getBlack());
                x.getParent().setBlack(true);
                w.getRight().setBlack(true);
                leftRotate(x.getParent());
                x = root;
            } else {
                if (x == x.getParent().getRight()) {
                    w = x.getParent().getLeft();
                    if (w.getColor() == 'R') {
                        w.setBlack(true);
                        x.getParent().setBlack(false);
                        rightRotate(x.getParent());
                        w = x.getParent().getLeft();
                    }

                    if (w.getRight().getColor() == 'B' && w.getLeft().getColor() == 'B') {
                        w.setBlack(false);
                        x = x.getParent();
                    } else if (w.getLeft().getColor() == 'B') {
                        w.getRight().setBlack(true);
                        w.setBlack(false);
                        leftRotate(w);
                        w = x.getParent().getLeft();
                    }

                    w.setBlack(x.getParent().getBlack());
                    x.getParent().setBlack(true);
                    w.getLeft().setBlack(true);
                    rightRotate(x.getParent());
                    x = root;
                }
            }
        }
        x.setBlack(true);
    }
    
    public void fixNodeSize(ColoredNode x) {
        x = x.getParent();
        while (x != TNil){
            x.setSize(x.getSize()-1);
            x = x.getParent();
        }
    }

    public void delete(ColoredNode z) {
        ColoredNode y = z;
        ColoredNode x = null;
        char color = y.getColor();
        fixNodeSize(z);
        if (z.getLeft() == TNil) {
            x = z.getRight();
            transplant(z, z.getRight());
        } else if (z.getRight() == TNil) {
            x = z.getLeft();
            transplant(z, z.getLeft());
        } else {
            y = treeMinimum(z.getRight());
            color = y.getColor();
            x = y.getRight();
            if (y.getParent() == z) {
                x.setParent(y);
            } else {
                transplant(y, y.getRight());
                y.setRight(z.getRight());
                y.getRight().setParent(y);
            }
            transplant(z, y);

            y.setLeft(z.getLeft());
            y.getLeft().setParent(y);
            y.setBlack(z.getBlack());
        }

        if (color == 'B') {
            deleteFixup(x);
        }
        
    }

    public void printPreOrder(ColoredNode x) {
        if (x != TNil) {
            System.out.print("(" + x.getKey() + x.getColor() + x.getSize() +" ");
            printPreOrder(x.getLeft());
            System.out.print(" ");
            printPreOrder(x.getRight());
            System.out.print(")");
        } else {
            System.out.print("()");
        }
    }

    public ColoredNode treeSearch(ColoredNode x, int k) {
        if (x == null || k == x.getKey()) {
            return x;
        }

        if (k < x.getKey()) {
            return treeSearch(x.getLeft(), k);
        } else {
            return treeSearch(x.getRight(), k);
        }
    }

    //getter
    public ColoredNode getRoot() {
        return this.root;
    }
    
    public ColoredNode OSSelect(ColoredNode x, int i){
        int r = x.getLeft().getSize() + 1;
        System.out.println("X : "+x.getKey()+" Left X :"+x.getLeft().getKey()+" R : "+r);
        if (i == r)
            return x;
        else if (i < r)
            return OSSelect(x.getLeft(), i);
        else
            return OSSelect(x.getRight(), i - r);
    }
    
    public int OSRank(ColoredNode x){
        int r = x.getLeft().getSize() + 1;
        System.out.println("X : "+x.getKey()+" Left X :"+x.getLeft().getKey()+" R : "+r);
        ColoredNode y = x;
        while (y != this.root){
            if (y == y.getParent().getRight())
                r = r + y.getParent().getLeft().getSize() + 1;
            System.out.println("Y : "+y.getKey()+" R : "+r);
            y = y.getParent();
        }
        return r;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ColoredTree tree = new ColoredTree(34);

//        tree.insert(new ColoredNode(50, true, tree.TNil, tree.TNil, tree.TNil));
//        tree.insert(new ColoredNode(40, true, tree.TNil, tree.TNil, tree.TNil));
//        tree.insert(new ColoredNode(30, true, tree.TNil, tree.TNil, tree.TNil));
//        tree.insert(new ColoredNode(20, true, tree.TNil, tree.TNil, tree.TNil));
//        tree.insert(new ColoredNode(10, true, tree.TNil, tree.TNil, tree.TNil));
//        tree.insert(new ColoredNode(9, true, tree.TNil, tree.TNil, tree.TNil));
//        tree.insert(new ColoredNode(8, true, tree.TNil, tree.TNil, tree.TNil));
//        tree.insert(new ColoredNode(7, true, tree.TNil, tree.TNil, tree.TNil));
//        tree.insert(new ColoredNode(6, true, tree.TNil, tree.TNil, tree.TNil));
//        tree.insert(new ColoredNode(5, true, tree.TNil, tree.TNil, tree.TNil));

        //insert code
        tree.insert(new ColoredNode(39, true, tree.TNil, tree.TNil, tree.TNil));
        tree.insert(new ColoredNode(10, true, tree.TNil, tree.TNil, tree.TNil));
        tree.insert(new ColoredNode(20, true, tree.TNil, tree.TNil, tree.TNil));
        tree.insert(new ColoredNode(30, true, tree.TNil, tree.TNil, tree.TNil));
        tree.insert(new ColoredNode(35, true, tree.TNil, tree.TNil, tree.TNil));
        tree.insert(new ColoredNode(5, true, tree.TNil, tree.TNil, tree.TNil));
        tree.insert(new ColoredNode(70, true, tree.TNil, tree.TNil, tree.TNil));
        tree.insert(new ColoredNode(40, true, tree.TNil, tree.TNil, tree.TNil));
        tree.printPreOrder(tree.getRoot());
        System.out.println("");
        tree.delete(tree.treeSearch(tree.getRoot(), 35));
        tree.printPreOrder(tree.getRoot());
        System.out.println("");
        System.out.println("3rd smallest node : "+tree.OSSelect(tree.getRoot(), 3).getKey());
        System.out.println("Rank node "+tree.treeSearch(tree.getRoot(), 39).getKey()+" in this tree is "+tree.OSRank(tree.treeSearch(tree.getRoot(), 39)));
        
    }
}

class ColoredNode {

    private boolean black;
    private int key;
    private ColoredNode parent;
    private ColoredNode left;
    private ColoredNode right;
    private int size;

    public ColoredNode(int key, boolean black, ColoredNode parent, ColoredNode left, ColoredNode right) {
        this.key = key;
        this.black = black;
        this.parent = parent;
        this.left = left;
        this.right = right;
        if (key != -999)
            this.size = 1;
    }

    //setter
    public void setSize(int size){
        this.size = size;
    }
    
    public void setRight(ColoredNode right) {
        this.right = right;
    }

    public void setLeft(ColoredNode left) {
        this.left = left;
    }

    public void setParent(ColoredNode parent) {
        this.parent = parent;
    }

    public void setBlack(boolean black) {
        this.black = black;
    }
    
    public void setKey(int key){
        this.key = key;
    }

    //getter
    public int getSize(){
        return this.size;
    }
    
    public int getKey() {
        return this.key;
    }

    public ColoredNode getParent() {
        return this.parent;
    }

    public ColoredNode getRight() {
        return this.right;
    }

    public ColoredNode getLeft() {
        return this.left;
    }

    public boolean getBlack() {
        return this.black;
    }

    public char getColor() {
        if (this.black) {
            return 'B';
        } else {
            return 'R';
        }
    }
}
