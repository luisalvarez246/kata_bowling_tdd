package org.game;

import java.util.ArrayList;

public class Game
{
    private int                 frame;
    private int                 ball;
    private int                 score;
    private int                 pinTotal;
    private Bonus               bonus;
    private LinkedListScores    firstFrame;
    private LinkedListScores    currentFrame;
    private ArrayList<Integer>  frameScores;

    public Game()
    {
        this.frame = 1;
        this.ball = 1;
        this.score = 0;
        this.pinTotal = 0;
        this.bonus = Bonus.None;
        this.firstFrame = new LinkedListScores();
        this.currentFrame = this.firstFrame;
        this.frameScores = new ArrayList<>();
    }

    public void result(int pins)
    {
        this.pinTotal = this.pinTotal + pins;
    }

    public void checkBonus()
    {
        if ((this.ball == 1) && (pinTotal == 10))
        {
            this.bonus = Bonus.Strike;
            this.currentFrame.setFrameBonus(this.bonus);
        }
        else if ((this.ball == 2) && (pinTotal == 10))
        {
            this.bonus = Bonus.Spare;
            this.currentFrame.setFrameBonus(this.bonus);
        }
        else if ((this.ball == 2))
            this.currentFrame.setFrameBonus(this.bonus);
    }

    public void calculateFrameScores()
    {
        this.currentFrame.calculateFrameTotal();
        this.firstFrame.updateFrameTotals();
    }

    public void frameScore(int pins)
    {
        boolean isBonus;

        isBonus = this.currentFrame.getFrameBonus() != Bonus.Strike;
        if (this.ball == 1)
            this.currentFrame.setPinsA(pins);
        else if ((this.ball == 2) && (isBonus))
        {
            this.currentFrame.setPinsB(pins);
        }
        calculateFrameScores();
    }

    public void nextFrame()
    {
        if ((this.bonus == Bonus.Strike) || (this.ball == 2))
        {
            this.frame++;
            this.ball = 1;
            this.pinTotal = 0;
            this.bonus = Bonus.None;
            this.currentFrame.newFrame();
            this.currentFrame = this.currentFrame.getNext();
        }
    }

    public void Roll(int pins)
    {
        result(pins);
        checkBonus();
        frameScore(pins);
        nextFrame();
    }

    public enum Bonus
    {
        Spare,
        Strike,
        None
    }

    public int  getFrame()
    {
        return (this.frame);
    }

    public int  getBall()
    {
        return (this.ball);
    }

    public int getScore()
    {
        return (score);
    }

    public Bonus getBonus()
    {
        return (bonus);
    }

    public int getPinTotal()
    {
        return (pinTotal);
    }

    public LinkedListScores getCurrentFrame()
    {
        return (currentFrame);
    }

    public LinkedListScores getFirstFrame()
    {
        return (firstFrame);
    }

    public void setFrame(int frame)
    {
        this.frame = frame;
    }

    public void setBall(int ball)
    {
        this.ball = ball;
    }

    public void setScore(int score)
    {
        this.score = score;
    }

    public void setBonus(Bonus bonus)
    {
        this.bonus = bonus;
    }

    public void setPinTotal(int pinTotal)
    {
        this.pinTotal = pinTotal;
    }

    public void setCurrentFrame(LinkedListScores currentFrame)
    {
        this.currentFrame = currentFrame;
    }

    public void setFirstFrame(LinkedListScores firstFrame)
    {
        this.firstFrame = firstFrame;
    }
}
