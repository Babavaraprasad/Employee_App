package dk.itu.navigationexample;

public class Item {
    private String mWhat = null;
    private String mWhere = null;
    private String mAge = null;
    public Item(String name, String role,String age) { mWhat = name;  mWhere = role;mAge = age; }
    @Override
    public String toString() { return oneLine(""," in: "); }
    public String getWhat() { return mWhat; }
    public void setWhat(String name) { mWhat = name; }
    public String getWhere() { return mWhere; }
    public void setWhere(String role) { mWhere = role; }
    public String oneLine(String pre, String post) {
        return pre+mWhat + post + mWhere;
    }
    public String getAge() { return mAge; }
}
