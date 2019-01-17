package com.hp.tic_tac_toe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //0 is yellow, 1 is red.
    boolean gameIsActive=true;
    int activeplayer = 0;
    int[] gamestate = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningpositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {0, 4, 8}, {1, 4, 7}, {2, 4, 6}, {2, 5, 8}};


    public void dropIn(View view) {

        ImageView counter = (ImageView) view;


        int tappedcounter = Integer.parseInt(counter.getTag().toString());

        if (gamestate[tappedcounter] == 2 && gameIsActive) {

            gamestate[tappedcounter] = activeplayer;

            counter.setTranslationY(-1000f);

            if (activeplayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activeplayer = 1;


            } else {
                counter.setImageResource(R.drawable.red);
                activeplayer = 0;

            }
            counter.animate().translationYBy(1000f).rotation(360).setDuration(300);
            for (int[] winningposition : winningpositions) {
                if (gamestate[winningposition[0]] == gamestate[winningposition[1]]
                        && gamestate[winningposition[1]] == gamestate[winningposition[2]]
                        && gamestate[winningposition[1]] != 2) {

                    // someone has won
                    gameIsActive=false;

                    String winner = "Black ";
                    if (gamestate[winningposition[0]] == 0) {

                        winner = "Red ";

                    }
                    TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
                    winnerTextView.setText(winner + "has won");
                    LinearLayout winnerLayout = (LinearLayout) findViewById(R.id.winnerLayout);
                    winnerLayout.setVisibility(view.VISIBLE);


                }
                else {
                    boolean gameIsOver=true;
                    for(int counterVariable:gamestate)
                        if(counterVariable==2){
                            gameIsOver=false;
                        }
                    if(gameIsOver){
                        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
                        winnerTextView.setText("It's a Draw!");
                        LinearLayout winnerLayout = (LinearLayout) findViewById(R.id.winnerLayout);
                        winnerLayout.setVisibility(view.VISIBLE);
                    }
                }
            }


        }
    }


        public void playAgain(View view) {

            LinearLayout winnerLayout = (LinearLayout) findViewById(R.id.winnerLayout);
            winnerLayout.setVisibility(view.INVISIBLE);
            gameIsActive = true;
            activeplayer = 0;
            for (int i = 0; i < gamestate.length; i++) {
                gamestate[i] = 2;
            }
            GridLayout tttGridLayout = (GridLayout) findViewById(R.id.tttGridLayout);
            for (int i = 0; i < tttGridLayout.getChildCount(); i++) {
                ((ImageView) tttGridLayout.getChildAt(i)).setImageResource(0);
            }
        }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
