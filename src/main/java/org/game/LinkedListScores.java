package org.game;

public class LinkedListScores
{
    private Game.Bonus          bonusFrame;
    private int                 pinsA;
    private int                 pinsB;
    private int                 pinsC;
    private LinkedListScores    next;

    public LinkedListScores()
    {
        this.next = null;
    }

    public void newNode()
    {
        this.next = new LinkedListScores();
    }

    public Game.Bonus getBonusFrame() {
        return bonusFrame;
    }

    public void setBonusFrame(Game.Bonus bonusFrame) {
        this.bonusFrame = bonusFrame;
    }

    public int getPinsA() {
        return pinsA;
    }

    public void setPinsA(int pinsA) {
        this.pinsA = pinsA;
    }

    public int getPinsB() {
        return pinsB;
    }

    public void setPinsB(int pinsB) {
        this.pinsB = pinsB;
    }

    public int getPinsC() {
        return pinsC;
    }

    public void setPinsC(int pinsC) {
        this.pinsC = pinsC;
    }

    public LinkedListScores getNext() {
        return next;
    }

    public void setNext(LinkedListScores next) {
        this.next = next;
    }
}
