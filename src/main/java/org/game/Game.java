package org.game;

import java.util.ArrayList;

public class Game
{
    private int                 frame;
    private int                 ball;
    private int                 score;
    private int                 pinTotal;
    private Bonus               bonus;
    private ArrayList<Integer>  scoreFrame;
    private ArrayList<Bonus>    bonusFrame;

    public Game()
    {
        this.frame = 1;
        this.ball = 1;
        this.score = 0;
        this.pinTotal = 0;
        this.bonus = Bonus.None;
        this.bonusFrame = new ArrayList<>();
        this.scoreFrame = new ArrayList<>();
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
            this.bonusFrame.add(this.bonus);
        }
        else if ((this.ball == 2) && (pinTotal == 10))
        {
            this.bonus = Bonus.Spare;
            this.bonusFrame.add(this.bonus);
        }
        else if ((this.ball == 2))
            this.bonusFrame.add(this.bonus);
    }

    public void nextFrame()
    {
        if ((this.bonus == Bonus.Strike) || (this.ball == 2))
        {
            this.frame++;
            this.ball = 1;
            this.pinTotal = 0;
            this.bonus = Bonus.None;
        }
    }

    public void Roll(int pins)
    {
        result(pins);
        checkBonus();
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

    public ArrayList<Integer> getScoreFrame()
    {
        return (scoreFrame);
    }

    public ArrayList<Bonus> getBonusFrame()
    {
        return (bonusFrame);
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

    public void setScoreFrame(ArrayList<Integer> scoreFrame)
    {
        this.scoreFrame = scoreFrame;
    }

    public void setBonusFrame(ArrayList<Bonus> bonusFrame) {
        this.bonusFrame = bonusFrame;
    }
}
