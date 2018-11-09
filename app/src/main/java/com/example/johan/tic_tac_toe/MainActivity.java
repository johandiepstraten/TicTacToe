package com.example.johan.tic_tac_toe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.example.johan.tic_tac_toe.GameState.DRAW;
import static com.example.johan.tic_tac_toe.GameState.PLAYER_TWO;

public class MainActivity extends AppCompatActivity {

    // Initialise game and arrays to loop through later on
    Game game;
    int tiles[] = {R.id.button1, R.id.button2, R.id.button3, R.id.button4, R.id.button5,
            R.id.button6, R.id.button7, R.id.button8, R.id.button9};
    String tilenames[] = {"button1", "button2", "button3", "button4", "button5", "button6",
            "button7", "button8", "button9"};

    // save text in tiles, tilestates and button enabled states
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState); // always call super
        for (int i = 0; i < tiles.length; i++) {
            outState.putCharSequence(tilenames[i], ((TextView) findViewById(tiles[i])).getText());
        }
        outState.putString("text", ((TextView) findViewById(R.id.textView3)).getText().toString());
        outState.putSerializable("Buttons", game);
        outState.putBoolean("Enabled", findViewById(tiles[0]).isEnabled());
    }

    // restore text in tiles, tilestates and button enabled states
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            for (int i = 0; i < tiles.length; i++) {
                ((TextView) findViewById(tiles[i])).setText(savedInstanceState.getCharSequence(tilenames[i]));
            }
            ((TextView) findViewById(R.id.textView3)).setText(savedInstanceState.getString("text"));
            game  = (Game) savedInstanceState.getSerializable("Buttons");
            Boolean Enabled = savedInstanceState.getBoolean("Enabled");
            if(!Enabled) {
                disableButtons();
            }
        }   else {
            game = new Game();
        }
    }

    // check which tile is clicked and by which player
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

        // set text in clicked tile to right character
        switch(state) {
            case CROSS:
                Button cross_button = findViewById(id);
                cross_button.setText("X");
                break;
            case CIRCLE:
                Button circle_button = findViewById(id);
                circle_button.setText("O");
                break;
            case INVALID:
                break;
        }

        // check progress of the game and let players know the outcome
        GameState won = game.won();
        switch(won){
            case IN_PROGRESS:
                break;
            case PLAYER_ONE:
                TextView player_one = findViewById(R.id.textView3);
                player_one.setText("Player 1 wins");
                disableButtons();
                break;
            case PLAYER_TWO:
                TextView player_two = findViewById(R.id.textView3);
                player_two.setText("Player 2 wins");
                disableButtons();
                break;
            case DRAW:
                TextView draw = findViewById(R.id.textView3);
                draw.setText("Draw");
                disableButtons();
                break;

        }
    }

    // disable all buttons
    public void disableButtons()    {
        for (int i = 0; i < tiles.length; i++) {
            Button disable_button = findViewById(tiles[i]);
            disable_button.setEnabled(false);
        }
    }

    // set up UI to start new game
    public void resetClicked(View view)  {
        game = new Game();
        TextView draw = findViewById(R.id.textView3);
        draw.setText("");
        for (int i = 0; i < tiles.length; i++) {
            Button clear_button = findViewById(tiles[i]);
            clear_button.setText(" ");
            clear_button.setEnabled(true);
        }
    }
}
