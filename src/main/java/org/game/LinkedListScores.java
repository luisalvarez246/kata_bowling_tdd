package org.game;

public class LinkedListScores
{
    private Game.Bonus          frameBonus;
    private int                 pinsA;
    private int                 pinsB;
    private int                 pinsC;
    private int                 frameTotal;
    private boolean             calculated;
    private LinkedListScores    next;

    public LinkedListScores()
    {
        this.pinsA = 0;
        this.pinsB = 0;
        this.pinsC = 0;
        this.calculated = false;
        this.next = null;
    }

    public void newFrame()
    {
        this.next = new LinkedListScores();
    }

    public LinkedListScores searchFrame(int index)
    {
        LinkedListScores    current;
        int                 i;

        current = this;
        i = 0;
        while (i < index)
        {
            current = current.getNext();
            i++;
        }
        return (current);
    }

    public void calculateFrameTotal()
    {
        this.frameTotal = this.pinsA + this.pinsB + this.pinsC;
        if ((pinsB != 0) && (pinsA + pinsB < 10))
            this.calculated = true;
    }

    public void calculateSpare(LinkedListScores current)
    {
        current.setPinsC(current.getNext().getPinsA());
        current.calculateFrameTotal();
        current.calculated = true;
    }

    public void calculateStrike(LinkedListScores current)
    {
        LinkedListScores    frameB;
        LinkedListScores    frameC;

        frameB = current.getNext();
        frameC = frameB.getNext();
        if ((frameB.calculated) || (frameB.frameBonus == Game.Bonus.Spare))
        {
            current.setPinsB(frameB.getPinsA());
            current.setPinsC(frameB.getPinsB());
            current.calculateFrameTotal();
            current.calculated = true;
        }
        else if ((frameB.frameBonus == Game.Bonus.Strike) && (frameC != null))
        {
            current.setPinsB(frameB.getPinsA());
            current.setPinsC(frameC.getPinsA());
            current.calculateFrameTotal();
            current.calculated = true;
        }
    }

    public void updateFrameTotals()
    {
        LinkedListScores    current;

        current = this;
        while (current.getNext() != null)
        {
            if((current.frameBonus == Game.Bonus.Spare) && (!current.calculated))
            {
                calculateSpare(current);
            }
            else if ((current.frameBonus == Game.Bonus.Strike) && (!current.calculated))
            {
                calculateStrike(current);
            }
            current = current.getNext();
        }
    }

    public int totalScore()
    {
        LinkedListScores    current;
        int                 iterator;
        int                 totalizer;

        current = this;
        iterator = 0;
        totalizer = 0;
        while (iterator < 10)
        {
            totalizer = current.frameTotal + totalizer;
            current = current.getNext();
            iterator++;
        }
        return (totalizer);
    }

    public Game.Bonus getFrameBonus()
    {
        return frameBonus;
    }

    public void setFrameBonus(Game.Bonus frameBonus) {
        this.frameBonus = frameBonus;
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

    public int getFrameTotal() {
        return frameTotal;
    }

    public void setFrameTotal(int frameTotal) {
        this.frameTotal = frameTotal;
    }

    public LinkedListScores getNext() {
        return next;
    }

    public void setNext(LinkedListScores next) {
        this.next = next;
    }

    public boolean isCalculated() {
        return calculated;
    }

    public void setCalculated(boolean calculated) {
        this.calculated = calculated;
    }
}
