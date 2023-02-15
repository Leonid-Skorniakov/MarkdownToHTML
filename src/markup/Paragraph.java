package markup;
import java.util.List;

public class Paragraph implements HtmlList, ToMark {
    protected List<Mark> list;

    public Paragraph(List<Mark> list){
        this.list = list;
    }

    @Override
    public void toMarkdown(StringBuilder sb){
        for (Mark mark : list){
            mark.toMarkdown(sb);
        }
    }

    @Override
    public void toHtml(StringBuilder sb){
        for (Mark mark : list){
            mark.toHtml(sb);
        }
    }
}
