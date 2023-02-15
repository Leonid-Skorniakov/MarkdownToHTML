package markup;
import java.util.List;

public class Mark implements ToMark, ToHtml {
    protected String tag = "";
    protected String htmlTag = "";
    protected List<Mark> list;

    @Override
    public void toMarkdown(StringBuilder sb){
        sb.append(tag);
        for (Mark mark : list){
            mark.toMarkdown(sb);
        }
        sb.append(tag);
    }

    @Override
    public void toHtml(StringBuilder sb){
        sb.append("<" + htmlTag + ">");
        for (Mark mark : list){
            mark.toHtml(sb);
        }
        sb.append("</" + htmlTag + ">");
    }
}
