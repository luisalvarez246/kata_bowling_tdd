package org.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import java.lang.reflect.Constructor;

import static org.junit.jupiter.api.Assertions.*;

class GameTest
{
    @Nested
    class GameStartTests
    {
        Game    gameStart = new Game();

        @Test
        public void Game_class_constructor_receives_no_arguments()
        {
            //arrange
            Class<Game> game = Game.class;
            Constructor[] gameConstructor = game.getConstructors();
            //act
            int params = gameConstructor[0].getParameterCount();
            //assert
            assertEquals(0, params);
        }

        @Test
        public void Game_starts_at_frame_1()
        {
            //arrange
            int     frame;
            //act
            frame = gameStart.getFrame();
            //assert
            assertEquals(1, frame);
        }

        @Test
        public void Game_starts_with_ball_1()
        {
            //arrange
            int     ball;
            //act
            ball = gameStart.getBall();
            //assert
            assertEquals(1, ball);
        }

        @Test
        public void Game_starts_at_score_0()
        {
            //arrange
            int     score;
            //act
            score = gameStart.getScore();
            //assert
            assertEquals(0, score);
        }

        @Test
        public void Game_starts_at_pinTotal_0()
        {
            //arrange
            int     pinTotal;
            //act
            pinTotal = gameStart.getPinTotal();
            //assert
            assertEquals(0, pinTotal);
        }

        @Test
        public void Game_starts_at_bonus_None()
        {
            //arrange
            Game.Bonus  bonus;
            //act
            bonus = gameStart.getBonus();
            //assert
            assertEquals(Game.Bonus.None, bonus);
        }
    }

    @Nested
    class GameBonusesTests
    {
        private Game gameBonus;

        @BeforeEach
        public void setup()
        {
            gameBonus = new Game();
        }

        @Test
        public void throwing_10_pins_when_ball_is_1_is_a_Strike()
        {
            //arrange
            Game.Bonus  bonus;
            Game.Bonus  bonusFrame;
            //act
            gameBonus.setPinTotal(10);
            gameBonus.checkBonus();
            bonus = gameBonus.getBonus();
            bonusFrame = gameBonus.getCurrentFrame().getFrameBonus();
            //assert
            assertEquals(Game.Bonus.Strike, bonus);
            assertEquals(Game.Bonus.Strike, bonusFrame);
        }

        @Test
        public void throwing_10_pins_when_ball_is_2_is_a_Spare()
        {
            //arrange
            Game.Bonus  bonus;
            Game.Bonus  bonusFrame;
            //act
            gameBonus.setBall(2);
            gameBonus.setPinTotal(10);
            gameBonus.checkBonus();
            bonus = gameBonus.getBonus();
            bonusFrame = gameBonus.getCurrentFrame().getFrameBonus();
            //assert
            assertEquals(Game.Bonus.Spare, bonus);
            assertEquals(Game.Bonus.Spare, bonusFrame);
        }

        @Test
        public void throwing_less_than_10_pins_when_ball_is_2_is_None()
        {
            //arrange
            Game.Bonus  bonus;
            Game.Bonus  bonusFrame;
            //act
            gameBonus.setBall(2);
            gameBonus.setPinTotal(5);
            gameBonus.checkBonus();
            bonus = gameBonus.getBonus();
            bonusFrame = gameBonus.getCurrentFrame().getFrameBonus();
            //assert
            assertEquals(Game.Bonus.None, bonus);
            assertEquals(Game.Bonus.None, bonusFrame);
        }
    }

    @Nested
    class   GameNextFrameTests
    {
        Game gameNextFrame = new Game();

        @Test
        public void Strike_directs_to_next_frame()
        {
            //arrange
            int         frame;
            //act
            gameNextFrame.setBonus(Game.Bonus.Strike);
            gameNextFrame.nextFrame();
            frame = gameNextFrame.getFrame();
            //assert
            assertEquals(2, frame);
        }

        @Test
        public void ball_2_directs_to_next_frame()
        {
            //arrange
            int         frame;
            //act
            gameNextFrame.setBonus(Game.Bonus.None);
            gameNextFrame.setFrame(1);
            gameNextFrame.setBall(2);
            gameNextFrame.nextFrame();
            frame = gameNextFrame.getFrame();
            //assert
            assertEquals(2, frame);
        }

        @Test
        public void nextFrame_resets_bonus_pinTotal_and_ball()
        {
            //arrange
            int         frame;
            //act
            gameNextFrame.setBonus(Game.Bonus.Strike);
            gameNextFrame.setPinTotal(7);
            gameNextFrame.setBall(2);
            gameNextFrame.setFrame(1);
            gameNextFrame.nextFrame();
            frame = gameNextFrame.getFrame();
            //assert
            assertEquals(2, frame);
            assertEquals(1, gameNextFrame.getBall());
            assertEquals(0, gameNextFrame.getPinTotal());
            assertEquals(Game.Bonus.None, gameNextFrame.getBonus());
        }
    }

    @Nested
    class   FrameScores
    {
        private Game gameScores;

        @BeforeEach
        public void setup()
        {
            gameScores = new Game();
        }

        @Test
        public void first_roll_is_always_saved_in_pinsA()
        {
            //arrange
            int pins;
            //act
            pins = 5;
            gameScores.frameScore(pins);
            //assert
            assertEquals(5, gameScores.getCurrentFrame().getPinsA());
        }
        @Test
        public void if_frameBonus_is_None_second_roll_is_saved_in_pinsB()
        {
            //arrange
            int pins;
            //act
            pins = 3;
            gameScores.setBall(2);
            gameScores.getCurrentFrame().setFrameBonus(Game.Bonus.None);
            gameScores.frameScore(pins);
            //assert
            assertEquals(3, gameScores.getCurrentFrame().getPinsB());
        }

        @Test
        public void frameTotal_is_calculated_after_each_roll()
        {
            //arrange
            int pins;
            //act
            pins = 5;
            gameScores.frameScore(pins);
            //assert
            assertEquals(5, gameScores.getCurrentFrame().getFrameTotal());
            //act2
            pins = 3;
            gameScores.setBall(2);
            gameScores.getCurrentFrame().setFrameBonus(Game.Bonus.None);
            gameScores.frameScore(pins);
            assertEquals(8, gameScores.getCurrentFrame().getFrameTotal());
            assertEquals(8, gameScores.getFirstFrame().getFrameTotal());
        }

        @Test
        public void if_frameBonus_is_Spare_pinsC_FrameA_equals_pinsA_FrameB()
        {
            //arrange
            LinkedListScores    frameA;
            LinkedListScores    frameB;
            //act
            frameA = new LinkedListScores();
            frameA.setPinsA(5);
            frameA.setPinsB(5);
            frameA.setFrameBonus(Game.Bonus.Spare);
            frameA.newNode();
            frameB = frameA.getNext();
            frameB.setPinsA(5);
            frameA.updateFrameTotals();
            //assert
            assertEquals(15, frameA.getFrameTotal());
        }
        @Test
        public void if_frameBonus_is_Spare_but_frameB_has_not_been_played_frameA_is_10()
        {
            //arrange
            LinkedListScores    frameA;
            //act
            frameA = gameScores.getCurrentFrame();
            frameA.setPinsA(5);
            gameScores.setBall(2);
            frameA.setFrameBonus(Game.Bonus.Spare);
            gameScores.frameScore(5);
            //assert
            assertEquals(10, frameA.getFrameTotal());
        }
    }
}