package org.game;

import java.util.ArrayList;

public class Game
{
    private int                 frame;
    private int                 ball;
    private int                 score;
    private int                 pinTotal;
    private Bonus               bonus;
    private LinkedListScores    frameScores;

    public Game()
    {
        this.frame = 1;
        this.ball = 1;
        this.score = 0;
        this.pinTotal = 0;
        this.bonus = Bonus.None;
        this.frameScores = new LinkedListScores();
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
            this.frameScores.setBonusFrame(this.bonus);
        }
        else if ((this.ball == 2) && (pinTotal == 10))
        {
            this.bonus = Bonus.Spare;
            this.frameScores.setBonusFrame(this.bonus);
        }
        else if ((this.ball == 2))
            this.frameScores.setBonusFrame(this.bonus);
    }

    /*public void frameScore()
    {
        int arrayFrame;

        arrayFrame = this.frame - 1;
        if ((this.bonusFrame.get(arrayFrame) == Bonus.None))
        {
            this.scoreFrame.add(arrayFrame, pinTotal);
        }
    }*/

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
        //frameScore();
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

    public LinkedListScores getFrameScores()
    {
        return (frameScores);
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

    public void setFrameScores(LinkedListScores frameScores)
    {
        this.frameScores = frameScores;
    }
}
