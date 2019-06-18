public class Pair {

    private String name;
    private String number;
    private long numberOfOutgoigCalls =0;

    public Pair() {
    }

    public Pair(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public Pair(String name, String number, long numberOfOutgoigCalls) {
        this.name = name;
        this.number = number;
        this.numberOfOutgoigCalls = numberOfOutgoigCalls;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public long getNumberOfOutgoigCalls() {
        return numberOfOutgoigCalls;
    }

    public void setNumberOfOutgoigCalls(long numberOfOutgoigCalls) {
        this.numberOfOutgoigCalls = numberOfOutgoigCalls;
    }

    @Override
    public String toString() {
        return "Name: "+name+System.lineSeparator()+"   Phone Number: "+number+System.lineSeparator()+"   Outgoing calls: "+ numberOfOutgoigCalls;
    }
}
