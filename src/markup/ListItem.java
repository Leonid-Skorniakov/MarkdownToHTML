package markup;
import java.util.List;

public class ListItem extends AbstractList{
    private List<HtmlList> list;

    public ListItem(List<HtmlList> list){
        this.list = list;
    }

    @Override
    public void toHtml(StringBuilder sb){
        sb.append("<li>");
        for (HtmlList mark : list){
            mark.toHtml(sb);
        }
        sb.append("</li>");

    }
    
}
