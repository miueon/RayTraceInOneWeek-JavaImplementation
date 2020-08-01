package testJava;

public class testDeco {


    public static void main(String[] args) {
        TextNode n1 = new BoldDeco(new BoldDeco(new SpanNode("fuck xi")));
        // 0.get is a.ref (:b).get => b.ref (:c).get => base.get
        System.out.println(n1.getText());
    }

}

interface TextNode {
    void setText(String text);
    String getText();
}

abstract class NodeDecorator implements TextNode {
    protected final TextNode target;
    public int count;
    protected NodeDecorator(TextNode target) {
        this.target = target;
    }

    public void setText(String text) {
        this.target.setText(text);
    }
}

/*class SpanNode extends NodeDecorator {
    private String text;

    public SpanNode(String text) {
        this.text = text;
        super(this);
        // cannot use this before super is not constructed
    }
    public String getText() {
        return "<span>" + text + "</span>";
    }
}*/
class SpanNode implements TextNode {
    private String text;

    public SpanNode(String text) {
        this.text = text;
    }
    public String getText() {
        return "<span>" + text + "</span>";
    }

    public void setText(String text) {
        this.text = text;
    }
}

class BoldDeco extends NodeDecorator {
    public BoldDeco(TextNode target) {
        super(target);
        count++;
    }

    public String getText() {
        System.out.println(count);
        return "<b>" + target.getText() + "</b>";
    }
}