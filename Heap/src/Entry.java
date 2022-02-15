public class Entry <Key extends Comparable<Key>, Value>{
    private Key key;
    private Value value;
    public Entry(Key k, Value v){
        key = k; value = v;
    }
    public void setKey(Key k){key = k;}
    public void setValue(Value v){value = v;}
    public Key getKey(){return key;}
    public Value getValue(){return value;}
}
