import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Manager_System {
    private  ArrayList<Student> student_List;
    public Manager_System(){
        student_List = new ArrayList<>();
    }
    public Manager_System(Manager_System manager_system){
        student_List = manager_system.student_List;
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
    ///type: true for ascending
    ///      false for descending
    public void View_by_ID(Boolean type){
        ArrayList<Student> view_List = student_List;
        OutputStreamWriter screen_Output = new OutputStreamWriter(System.out);
        view_List.sort(new Comparator<Student>() {
                @Override
                public int compare(Student o1, Student o2) {
                    int result = o1.getID().compareToIgnoreCase(o2.getID());
                    if (result < 0) return -1;
                    if (result == 0) return 0;
                    return 1;
                }
            });
        if(!type) {
            Collections.reverse(view_List);
        }
        try {
            for (Student s : view_List)
                screen_Output.write(s.information());
            screen_Output.close();
        } catch (IOException ignored) {
        }

    }
    public void View_by_GPA(Boolean type){
        ArrayList<Student> view_List = student_List;
        OutputStreamWriter screen_Output = new OutputStreamWriter(System.out);
        view_List.sort(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return Double.compare(o1.getGPA(),o2.getGPA());
            }
        });
        if(!type) {
            Collections.reverse(view_List);
        }
        try {
            for (Student s : view_List)
                screen_Output.write(s.information());
            screen_Output.close();
        } catch (IOException ignored) {
        }
    }
    public Boolean Export(OutputStreamWriter out_writer) throws IOException {
        if(out_writer == null ) return  false;
        StringBuilder data_Builder = new StringBuilder();
        data_Builder.append("Student List").append('\n');
        for(Student s: student_List){
            data_Builder.append(s.information()).append('\n');
        }
        out_writer.write(data_Builder.toString());
        return true;
    }
    public Boolean Import(String path) throws Exception {
        File temp = new File(path);
        if(!temp.exists()) return false;

        BufferedReader data_reader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
        String line = data_reader.readLine();
        if(!line.equals("Student List")) return false;

        while((line = data_reader.readLine())!= null){
            student_List.add(Student.infomation_to_Student(line));
        }
        data_reader.close();
        return true;
    }
}
