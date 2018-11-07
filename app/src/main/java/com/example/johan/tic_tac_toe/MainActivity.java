package com.example.johan.tic_tac_toe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Game game;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        game = new Game();
    }
    public void tileClicked(View view) {
        Log.d("tictactoe", "tileClicked: " + String.valueOf(view.getId()));
        int id = view.getId();
        TileState state = TileState.BLANK;
        switch(id)  {
            case R.id.button1:
                state = game.choose(0, 0);
                break;
            case R.id.button2:
                state = game.choose(0, 1);
                break;
            case R.id.button3:
                state = game.choose(0, 2);
                break;
            case R.id.button4:
                state = game.choose(1, 0);
                break;
            case R.id.button5:
                state = game.choose(1, 1);
                break;
            case R.id.button6:
                state = game.choose(1, 2);
                break;
            case R.id.button7:
                state = game.choose(2, 0);
                break;
            case R.id.button8:
                state = game.choose(2, 1);
                break;
            case R.id.button9:
                state = game.choose(2, 2);
                break;
        }
        switch(state) {
            case CROSS:
                Button cross_button = (Button)findViewById(id);
                cross_button.setText("X");
                // do something
                break;
            case CIRCLE:
                Button circle_button = (Button)findViewById(id);
                circle_button.setText("O");
                // do something
                break;
            case INVALID:
                // do something different
                break;
        }
    }
    public void resetClicked()  {
        int [] tiles = {R.id.button1, R.id.button2, R.id.button3, R.id.button4, R.id.button5,
                        R.id.button6, R.id.button7, R.id.button8, R.id.button9}
        for (int i = 0; i < 9; i++) {
            Button tile = (Button) findViewById(getResources().getIdentifier("button" + i,
                    "id", this.getPackageName()));
            tile.setText(" ");
            // do something
        }
        game = new Game();
    }
}
