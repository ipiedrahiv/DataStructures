public class Activity {

    private int ID;
    private int start;
    private Integer end;
    private String description;

    Activity(int ID, int start, int end, String description) {
        this.ID = ID;
        this.start = start;
        this.end = end;
        this.description = description;
    }

    public String toString() {
        return this.ID + " " + this.start + " " + this.end + " " + this.description;
    }


    public int getStart() {
        return this.start;
    }

    public Integer getEnd() {
        return this.end;
    }
}
