package demo.example.chuangke.Reality;

public class New_issues {
    public String  title;
    public String  content;
    public String  usename;
    public String  time;

    public New_issues() {
    }

    public New_issues(String title, String content, String usename, String time) {
        this.title = title;
        this.content = content;
        this.usename = usename;
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getUsename() {
        return usename;
    }

    public String getTime() {
        return time;
    }
}
