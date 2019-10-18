import java.util.StringTokenizer;

class Student {
    private String ID;
    private String Name;
    private Double GPA;
    private String Address;
    private String Notes;
    Student(){}
    Student(String ID, String Name,Double GPA, String Address,String Notes){
        setID(ID);
        setName(Name);
        setGPA(GPA);
        setAddress(Address);
        setNotes(Notes);
    }
    String getID() {
        return ID;
    }
    void setID(String ID) {
        this.ID = ID;
    }

    String getName() {
        return Name;
    }
    void setName(String name) {
        Name = name;
    }

    Double getGPA() {
        return GPA;
    }
    void setGPA(Double GPA) {
        this.GPA = GPA;
    }

    String getAddress() {
        return Address;
    }
    void setAddress(String address) {
        Address = address;
    }

    String getNotes() {
        return Notes;
    }
    void setNotes(String notes) {
        Notes = notes;
    }

    String information() {
        return  "ID: " + getID() +
                " - Name: " + getName() +"\n" +
                "Address: "+getAddress() +
                " - GPA: "+getGPA()+"\n"+
                "Note: "+getNotes()+"\n";
    }
    static Student infomation_to_Student(String info) {
        Student student = new Student();
        StringTokenizer tokenizer = new StringTokenizer(info,"\n");
        student.setID(tokenizer.nextToken().substring(3));
        student.setName(tokenizer.nextToken().substring(5));
        student.setAddress(tokenizer.nextToken().substring(8));
        student.setGPA(Double.parseDouble(tokenizer.nextToken().substring(4)));
        student.setNotes(tokenizer.nextToken().substring(5));
        return student;
    }
}
