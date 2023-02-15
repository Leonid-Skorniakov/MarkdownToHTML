package markup;
import java.util.List;

public abstract class AbstractList{
    protected List<ListItem> list;
    protected String htmlTag;
    

    public void toHtml(StringBuilder sb){
        sb.append("<" + htmlTag + ">");
        for (ListItem mark : list){
            mark.toHtml(sb);
        }
        sb.append("</" + htmlTag + ">");
    }
}
