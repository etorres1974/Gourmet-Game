package domain.entity;

public class QuestionNode {
    private String name;
    private QuestionNode nextTrue = null;
    private QuestionNode nextFalse = null;
    private QuestionNode parent = null;

    public QuestionNode(String name, QuestionNode nextTrue, QuestionNode nextFalse) {
        this.name = name;
        setChildren(nextTrue, nextFalse);
        nextTrue.setParent(this);
        nextFalse.setParent(this);
    }

    public QuestionNode(String name) {
        this.name = name;
        setChildren(nextTrue, nextFalse);
    }

    public Boolean hasNext() {
        return nextTrue != null && nextFalse != null;
    }

    public void setParent(QuestionNode parent) {
        this.parent = parent;
    }

    public void setChildren(QuestionNode right, QuestionNode wrong) {
        this.nextTrue = right;
        this.nextFalse = wrong;
    }

    public void setName(String name) {
        this.name = name;
    }

    public QuestionNode getNext(Boolean answer) {
        if (answer)
            return nextTrue;
        else
            return nextFalse;
    }

    public String getName() {
        return name;
    }
}
