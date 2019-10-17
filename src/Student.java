public class Student {
    private String ID;
    private String Name;
    private Double GPA;
    private String Address;
    private String Notes;

    public String getID() {
        return ID;
    }
    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }

    public Double getGPA() {
        return GPA;
    }
    public void setGPA(Double GPA) {
        this.GPA = GPA;
    }

    public String getAddress() {
        return Address;
    }
    public void setAddress(String address) {
        Address = address;
    }

    public String getNotes() {
        return Notes;
    }
    public void setNotes(String notes) {
        Notes = notes;
    }

    public String information() {
        return "* ID: " + getID() +"\n" +
                " Name: " + getName() +"\n" +
                " Address: "+getAddress() +"\n"+
                " GPA: "+getGPA()+"/10.00\n"+
                " Note: "+getNotes()+"\n";
    }
}
