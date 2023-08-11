package org.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import java.lang.reflect.Constructor;
import java.util.ArrayList;

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
            int         pins;
            //act
            pins = 10;
            gameBonus.checkBonus(pins);
            bonus = gameBonus.getBonus();
            bonusFrame = gameBonus.getBonusFrame().get(0);
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
            int         pins;
            //act
            gameBonus.setBall(2);
            gameBonus.setPinTotal(10);
            pins = 3;
            gameBonus.checkBonus(pins);
            bonus = gameBonus.getBonus();
            bonusFrame = gameBonus.getBonusFrame().get(0);
            //assert
            assertEquals(Game.Bonus.Spare, bonus);
            assertEquals(Game.Bonus.Spare, bonusFrame);
        }

        @Test
        public void throwing_less_than_10_pins_when_ball_is_2_is_None()
        {
            //arrange
            Game.Bonus  bonus;
            int         pins;
            //act
            gameBonus.setBall(2);
            gameBonus.setPinTotal(5);
            pins = 3;
            gameBonus.Roll(pins);
            bonus = gameBonus.getBonus();
            //assert
            assertEquals(Game.Bonus.None, bonus);
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
    class   GameBonusFrames
    {
        @Test
        public void if_bonus_is_Strike_it_is_saved_in_bonusFrame()
        {

        }
    }



}