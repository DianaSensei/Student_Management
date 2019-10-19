import java.io.*;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Manager_System {
    private  ArrayList<Student> student_List;
    Manager_System(){
        student_List = new ArrayList<>();
    }
    public Manager_System(Manager_System manager_system){
        student_List = manager_system.student_List;
    }
    Boolean AddStudent(Student student){
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
    void View_by_ID(Boolean type){
        ArrayList<Student> view_List = student_List;
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
        for (Student s : view_List)
            System.out.println(s.information());

    }
    void View_by_GPA(Boolean type){
        ArrayList<Student> view_List = student_List;
        view_List.sort(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return Double.compare(o1.getGPA(),o2.getGPA());
            }
        });
        if(!type) {
            Collections.reverse(view_List);
        }
        for (Student s : view_List)
            System.out.println(s.information());
    }
    void Export(String path) throws Exception {
        OutputStreamWriter out_writer = new OutputStreamWriter(new FileOutputStream(path));
        StringBuilder data_Builder = new StringBuilder();
        data_Builder.append("Student List").append('\n');
        for(Student s: student_List){
            data_Builder.append(s.toCSV()).append('\n');
        }
        out_writer.write(data_Builder.toString());
        out_writer.close();
    }
    void Save(String path) throws Exception{
        OutputStreamWriter out_writer = new OutputStreamWriter(new FileOutputStream(path));
        for(Student s: student_List){
            out_writer.write(s.getID()+";"+s.getName()+";"+s.getImagePath()+";"+s.getGPA()+";"+s.getAddress()+";"+s.getNotes()+"\n");
        }
        out_writer.close();
    }
    void Import(String path) throws Exception {
        BufferedReader data_reader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
        String line = data_reader.readLine();
        while((line = data_reader.readLine())!=null){
            String[] token = QuoteHandle(line);
            for(int i=0;i<token.length;i++){
                if(token[i].isEmpty()) token[i]=" ";
            }
            Student student = new Student(token[0],token[1],token[2],Double.parseDouble(token[3]),token[4],token[5]);
            student_List.add(student);
        }
        data_reader.close();
    }
    static String[] QuoteHandle(String s){
        int quote_index = s.indexOf('\"');
        String[] token = s.split(",");
        if(quote_index == -1) return token;
        boolean flag = false;
        int mark = 0 ;
        ArrayList<Integer> remove = new ArrayList<>();
        for(int i=0;i<token.length;i++){
            if(token[i].charAt(0) == '\"'){
                flag = true;
                    token[i] = token[i].substring(2);
                    mark = i;
                    continue;
            }
            else if(token[i].charAt(token[i].length()-1)=='\"'){
                    token[mark] = token[mark]+", "+token[i].substring(0,token[i].length()-1);
                    remove.add(i);
                    flag = false; continue;
            }
            if(flag){
                token[mark] = token[mark]+", "+token[i].substring(0,token[i].length()-1);
                remove.add(i);
            }
        }
        ArrayList<String> res = new ArrayList<>(Arrays.asList(token));
        for(int i = 0;i<remove.size();i++){
            res.remove(remove.get(i)-1);
        }
        System.out.println("size "+res.size());
        return GetStringArray(res);
    }
    public static String[] GetStringArray(ArrayList<String> arr) {

        // declaration and initialise String Array
        String str[] = new String[arr.size()];

        // ArrayList to Array Conversion
        for (int j = 0; j < arr.size(); j++) {

            // Assign each value to String array
            str[j] = arr.get(j);
        }

        return str;
    }
    void Load(String path) throws Exception{
        BufferedReader data_reader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
        String line;
        while((line=data_reader.readLine())!=null){
            String[] token = line.split(";");
            Student student = new Student(token[0],token[1],token[2],Double.parseDouble(token[3]),token[4],token[5]);
            student_List.add(student);
        }
        data_reader.close();
    }
}
