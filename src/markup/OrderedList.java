package markup;

import java.util.List;

public class OrderedList extends AbstractList implements HtmlList{
    public OrderedList(List<ListItem> list){
        super.list = list;
        super.htmlTag = "ol";
    }
}
