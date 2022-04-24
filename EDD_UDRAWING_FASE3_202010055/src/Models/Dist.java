package Models;

public class Dist implements Comparable<Dist>{
    public int id;
    public int dist;

    public Dist(int id, int dist){
        this.id = id;
        this.dist = dist;
    }

    @Override
    public int compareTo(Dist o) {
        return 0;
    }

}