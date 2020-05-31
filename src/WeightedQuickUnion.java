import java.io.*;

public class WeightedQuickUnion {

    private int[] id;
    private int[] sz;

    public WeightedQuickUnion(int n) {

        id = new int[n];
        sz = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
            sz[i] = i;

        }
    }

    public boolean connected(int p, int q) {

        return find(p) == find(q);
    }

    public int find(int p) {

        while (p != id[p]) p = id[p];
        return p;
    }

    public void union(int p, int q) {

        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot) return;
        if (sz[pRoot] < sz[qRoot]) {
            id[pRoot] = qRoot;
            sz[pRoot] += sz[qRoot];
        } else {
            id[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        }

    }


    public static void main(String[] args) throws IOException {

        final int n = 512000;
        FileWriter fw = new FileWriter("Zahlen");
        BufferedWriter bw = new BufferedWriter(fw);
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < n; j++) {
                int p = (int) (Math.random() * n);
                bw.write(p + ",");
            }
            bw.newLine();
        }
        bw.close();


        FileReader fr = new FileReader("Zahlen");
        BufferedReader br = new BufferedReader(fr);
        String pZeile = br.readLine();
        String qZeile = br.readLine();
        String[] pWerte = pZeile.split(",");
        String[] qWerte = qZeile.split(",");
        int[] qArray = new int[n];
        int[] pArray = new int[n];
        for (int k = 0; k < n; k++) {
            pArray[k] = Integer.parseInt(pWerte[k]);
            qArray[k] = Integer.parseInt(qWerte[k]); // Texdatei eingelesen und in zwei int-Arrays gespeichert
        }

        WeightedQuickUnion quickUnion = new WeightedQuickUnion(n);


        for (int j = 0; j < n; j++) {
            if (quickUnion.connected(pArray[j], qArray[j])) {
                System.out.println("Already connected: " + pArray[j] + " &  " + qArray[j]);
            } else {
                quickUnion.union(pArray[j], qArray[j]);
                System.out.println("Now connected: " + pArray[j] + " & " + qArray[j]);
            }
        }
    }

}