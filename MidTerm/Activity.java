public class Activity {

    public int ID;
    public int start;
    public int end;
    public String description;

    Activity(int ID, int start, int end, String description) {
        this.ID = ID;
        this.start = start;
        this.end = end;
        this.description = description;
    }

    public String toString() {
        return this.ID + " " + this.start + " " + this.end + " " + this.description;
    }

}
