package TestModel;

public class Answears {
    public String answear_text;
    public String id;

    public String getAnswear_text(String text_raspuns) {
        return answear_text;
    }

    public void setAnswear_text(String answear_text) {
        this.answear_text = answear_text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Answears{" +
                "answear_text='" + answear_text + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
