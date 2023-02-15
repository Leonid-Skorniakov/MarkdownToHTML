package markup;
import java.util.List;

public class Emphasis extends Mark {
    public Emphasis(List<Mark> list){
        super.list = list;
        super.tag = "*";
        super.htmlTag = "em";
    }
}
