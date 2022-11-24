package LA3Q1;

public class EthanValueEntry {
    private Integer key; //initialize Integer type key
    public EthanValueEntry() {
        key = -1; //empty constructor sets the key value to -1 "null"
    }
    public EthanValueEntry(Integer key) {
        this.key = key; //constructor sets parameter key to this.key
    }
    public void setKey(Integer key) {
        this.key = key; //setter method sets key to input key
    }
    public Integer getKey() {
        return key; //getter method returns key value
    }
}
