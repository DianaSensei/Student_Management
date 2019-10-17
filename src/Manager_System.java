import java.util.ArrayList;

public class Manager_System {
    private  ArrayList<Student> student_List;
    public Manager_System(){
        student_List = new ArrayList<>();
    }
    public Boolean AddStudent(Student student){
        if(student_List == null) return false;
        student_List.add(student);
        return true;
    }
    public Boolean RemoveStudent(Student student){
        if(student_List == null) return false;
       student_List.remove(student);
        return true;
    }
    public Boolean RemoveStudent_by_ID(String ID){
        if(student_List == null) return false;
        Student mark = null;
        for(Student s: student_List)
            if (s.getID().equals(ID)) {
                mark = s; break;
            }
        if( mark== null ) return false;
        student_List.remove(mark);
        return true;
    }
    public Boolean RemoveStudent_by_Name(String Name){
        if(student_List == null) return false;
        Student mark = null;
        for(Student s: student_List)
            if (s.getName().equals(Name)) {
                mark = s; break;
            }
        if( mark== null ) return false;
        student_List.remove(mark);
        return true;
    }
}
