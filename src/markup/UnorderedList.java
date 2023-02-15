package markup;

import java.util.List;

public class UnorderedList extends AbstractList implements HtmlList{
    public UnorderedList(List<ListItem> list){
        super.list = list;
        super.htmlTag = "ul";
    }
}
