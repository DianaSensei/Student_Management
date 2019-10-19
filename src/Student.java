import java.util.StringTokenizer;

class Student {
    private String ID;
    private String Name;
    private Double GPA;
    private String ImagePath;
    private String Address;
    private String Notes;
    Student(){}
    Student(String ID, String Name,String ImagePath,Double GPA, String Address,String Notes){
        setID(ID);
        setName(Name);
        setImagePath(ImagePath);
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

    String getImagePath() { return ImagePath; }
    void setImagePath(String imagePath) { ImagePath = imagePath;}

    String getNotes() {
        return Notes;
    }
    void setNotes(String notes) {
        Notes = notes;
    }

    String information() {
        return  "ID: " + getID() +
                " - Name: " + getName() +"\n" +
                "ImagePath: "+ getImagePath() +"\n"+
                "Address: "+getAddress() +"\n"+
                "GPA: "+getGPA()+
                " - Note: "+getNotes()+"\n";
    }
    String toCSV(){
        StringBuilder str = new StringBuilder();
        if(getID().indexOf(',') != -1)  str.append(",\" ").append(getID()).append("\"");
        else str.append(getID()).append(", ");

        if(getName().indexOf(',') != -1)  str.append(",\" ").append(getName()).append("\"").append(", ");
        else str.append(getName()).append(", ");

        if(getImagePath().indexOf(',') != -1)  str.append(",\" ").append(getImagePath()).append("\"").append(", ");
        else str.append(getImagePath()).append(", ");

        str.append(getGPA());

        if(getAddress().indexOf(',') != -1)   str.append(",\" ").append(getAddress()).append("\"").append(", ");
        else str.append(", ").append(getAddress()).append(", ");

        if(getNotes().indexOf(',') != -1)   str.append(",\" ").append(getNotes()).append("\"");
        else str.append(getNotes());
        return str.toString();
    }
    static Student infomation_to_Student(String info) {
        Student student = new Student();
        StringTokenizer tokenizer = new StringTokenizer(info,", ");
        student.setID(tokenizer.nextToken());
        student.setName(tokenizer.nextToken());
        student.setImagePath(tokenizer.nextToken());
        student.setAddress(tokenizer.nextToken());
        student.setGPA(Double.parseDouble(tokenizer.nextToken()));
        student.setNotes(tokenizer.nextToken());
        return student;
    }
}
