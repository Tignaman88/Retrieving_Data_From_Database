public class Student {

    String idstudents;
    String name;
    String last_name;
    String overall_score;

    public Student(String idstudents, String name, String last_name, String overall_score) {
        this.idstudents = idstudents;
        this.name = name;
        this.last_name = last_name;
        this.overall_score = overall_score;
    }

    public String getIdstudents() {
        return idstudents;
    }

    public String getName() {
        return name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getOverall_score() {
        return overall_score;
    }

    @Override
    public String toString() {
        return "Student{" +
                "idstudents='" + idstudents + '\'' +
                ", name='" + name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", overall_score='" + overall_score + '\'' +
                '}';
    }
}
