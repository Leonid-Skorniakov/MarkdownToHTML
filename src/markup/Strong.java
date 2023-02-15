package markup;
import java.util.List;

public class Strong extends Mark {
    public Strong(List<Mark> list){
        super.list = list;
        super.tag = "__";
        super.htmlTag = "strong";
    }
}
