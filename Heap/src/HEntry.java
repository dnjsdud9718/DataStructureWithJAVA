public class HEntry {
    private int frequency;
    private String word;
    private HEntry left;
    private HEntry right;
    private String code;
    public HEntry(int newFreq, String newVal, HEntry lt, HEntry rt, String s){
        frequency = newFreq; word = newVal; left = lt; right = rt; code = s;
    }
    public int getKey(){return frequency;}
    public String getValue(){return word;}
    public String getCode(){return code;}
    public HEntry getLeft(){return left;}
    public HEntry getRight(){return right;}
    public void setCode(String newCode){code = newCode;}
}
