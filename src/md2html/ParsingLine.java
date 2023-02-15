package md2html;



public class ParsingLine {
    private final String line;
    private final int headerSize;
    private int pos;

    public ParsingLine(String line) {
        this.line = line;
        this.headerSize = headerSize();
    }

    private int headerSize() {
        for (int i = 0 ; i < line.length(); i++) {
            Character literal = line.charAt(i);
            if (literal.equals(' ')) {
                return i;
            } else if (!literal.equals('#')) {
                break;
            }
        }
        return 0;
    }

    public String result(){
        if (headerSize > 0) {
            this.pos = headerSize + 1;
            return ("<h" + headerSize + ">" + parse("text", "") + "</h" + headerSize + ">");
        } else {
            this.pos = 0;
            return ("<p>" + parse("text", "") + "</p>");
        }
    }

    private String parse(String operationType, String underType) {
        StringBuilder parsedLine = new StringBuilder();

        for(; pos < line.length(); pos++) {
            
            Character literal = line.charAt(pos);

            if (operationType.equals("image1")) {
                switch (literal){
                    case ']':
                        if (pos < (line.length() - 1) && line.charAt(pos + 1) == '('){
                            pos += 2;
                            return parse("image2", parsedLine.toString());
                        }
                    
                    default:
                        parsedLine.append(literal);
                        break;
                }
                continue;
            }

            if (operationType.equals("image2")) {
                switch (literal){
                    case ')':
                        return "<img alt=\'" + underType + "\' src=\'" + parsedLine + "\'>";

                    default:
                        parsedLine.append(literal);
                        break;
                }
                continue;
            }
            
            switch (literal) {
                case '<':
                    parsedLine.append("&lt;");
                    break;

                case '>':
                    parsedLine.append("&gt;");
                    break;
                
                case '&':
                    parsedLine.append("&amp;");
                    break;

                case '\\':
                    if (pos < (line.length() - 1)) {
                        pos++;
                        parsedLine.append(line.charAt(pos));
                    }
                    break;


                case '`':
                    if (operationType.equals("code")) {
                        return ("<code>" + parsedLine + "</code>");
                    } else {
                        pos++;
                        parsedLine.append(parse("code", Character.toString(literal)));
                    }
                    break;


                case '-':
                    if (pos < (line.length() - 1) && line.charAt(pos + 1) == '-') {
                        pos++;
                        if (operationType.equals("strike")) {
                            return ("<s>" + parsedLine + "</s>");
                        } else {
                            pos++;
                            parsedLine.append(parse("strike", Character.toString(literal)));
                        }
                    } else {
                        parsedLine.append(literal);
                    }
                    break;

                case '!':
                    if (pos < (line.length() - 1) && line.charAt(pos + 1) == '[') {
                        pos++;
                        pos++;
                        parsedLine.append(parse("image1", Character.toString(literal)));
                    } else {
                        parsedLine.append(literal);
                    }

                    break;

                case '*', '_':
                    if (pos < (line.length() - 1)) {
                        pos++;
                        Character newLiteral = line.charAt(pos);
                        if (newLiteral.equals('*') || newLiteral.equals('_')) {
                            if ( operationType.equals("strong")) {
                                return ("<strong>" + parsedLine + "</strong>");
                            }
                            pos++;
                            parsedLine.append(parse("strong", Character.toString(literal)));
                        } else {
                            if (operationType.equals("emphasis") && Character.toString(literal).equals(underType)) {
                                pos -= 1;
                                return ("<em>" + parsedLine + "</em>");
                            }
                            parsedLine.append(parse("emphasis", Character.toString(literal)));
                        }
                    } else {
                        if (operationType.equals("emphasis") && Character.toString(literal).equals(underType)) {
                            return ("<em>" + parsedLine + "</em>");
                        }
                        parsedLine.append(literal);
                    }
                    break;

                
                default:
                    parsedLine.append(literal);
                    break;
            }
        }

        return (underType + parsedLine.toString());
    }

    
}
