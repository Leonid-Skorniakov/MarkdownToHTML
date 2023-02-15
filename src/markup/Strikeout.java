package markup;
import java.util.List;

public class Strikeout extends Mark {
    public Strikeout(List<Mark> list){
        super.list = list;
        super.tag = "~";
        super.htmlTag = "s";
    }
}
