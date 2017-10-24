package com.gokool.tictactoe;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{


    private static final int MAX_BUTTONS = 9;
    Button block1, block2, block3, block4, block5, block6, block7, block8, block9, restart, b[];
    TextView result;
    private Player player;
    private boolean gameOver = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b = new Button[MAX_BUTTONS];

        b[0] = (Button) findViewById(R.id.bt_block1);
        b[1] = (Button) findViewById(R.id.bt_block2);
        b[2] = (Button) findViewById(R.id.bt_block3);
        b[3] = (Button) findViewById(R.id.bt_block4);
        b[4] = (Button) findViewById(R.id.bt_block5);
        b[5] = (Button) findViewById(R.id.bt_block6);
        b[6] = (Button) findViewById(R.id.bt_block7);
        b[7] = (Button) findViewById(R.id.bt_block8);
        b[8] = (Button) findViewById(R.id.bt_block9);
        result = (TextView) findViewById(R.id.tv_show_result);
        restart = (Button) findViewById(R.id.bt_restart_game);




        initBoard();

        /**
         * Restarts the game
         */
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showConfirmation();
            }
        });

    }

    private void showConfirmation() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.restart_title));
        builder.setMessage(getString(R.string.restart_msg));

        builder.setPositiveButton(getString(R.string.yes_btn), new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {

                initBoard();
                dialog.dismiss();
            }

        });

        builder.setNegativeButton(getString(R.string.no_btn), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }

    private void initBoard() {
        for(int i = 0;i<MAX_BUTTONS;i++){
            b[i].setOnClickListener(new CustomOnClick(i));
            b[i].setText("");
            b[i].setEnabled(true);
        }
        player = null;
        player = new Player();
        gameOver = false;
        result.setText("");
    }

    private class CustomOnClick implements View.OnClickListener {
        private final int whichb;

        public CustomOnClick(int i) {
            this.whichb = i;
        }

        @Override
        public void onClick(View v) {
            if(!gameOver) {
                if (b[whichb].isEnabled()) {
                    restart.setText(R.string.restart_button_text_in_middle_of_game);
                    b[whichb].setEnabled(false);
                    b[whichb].setText(player.getPlayer());
                    String winner = checkWinner();
                    if (winner.isEmpty()) {
                        if(anyKeysleft()){
                            player.setPlayer(player.next());
                            result.setText(getString(R.string.player_1_move) + player.getPlayer());
                        }
                        else {
                            result.setText(R.string.ties_string);
                        }
                    } else {
                        gameOver = true;
                        result.setText("Winner is " + player.getPlayer());
                    }
                }
            }else{
                result.setText("Game over, Please restart the game.");
            }

        }
    }

    private String checkWinner() {


        if(!rows().isEmpty() || !cols().isEmpty() || !diagnols().isEmpty()){
            return player.getPlayer();
        }
        return "";
    }

    private boolean anyKeysleft() {
        for(int i = 0;i<MAX_BUTTONS;i++){
           if(b[i].isEnabled())
           {
               return true;
           };
        }
        return false;
    }

    private String diagnols() {
        if((b[0].getText() == player.getPlayer() && b[0].getText() == b[4].getText() && b[4].getText() == b[8].getText())
                || ( b[2].getText() == player.getPlayer() && b[2].getText() == b[4].getText() && b[4].getText() == b[6].getText())
                ){
            return player.getPlayer();
        }
        return "";
    }

    private String cols() {
        if((b[0].getText() == player.getPlayer() && b[0].getText() == b[3].getText() && b[3].getText() == b[6].getText())
                || (b[1].getText() == player.getPlayer() && b[1].getText() == b[4].getText() && b[4].getText() == b[7].getText())
                || (b[2].getText() == player.getPlayer() && b[2].getText() == b[5].getText() && b[5].getText() == b[8].getText())){
            return player.getPlayer();
        }
        return "";
    }

    private String rows() {
        if((b[0].getText() == player.getPlayer() && b[0].getText() == b[1].getText() && b[1].getText() == b[2].getText())
                || (b[3].getText() == player.getPlayer() && b[3].getText() == b[4].getText() && b[4].getText() == b[5].getText())
                || (b[6].getText() == player.getPlayer() && b[6].getText() == b[7].getText() && b[7].getText() == b[8].getText())){
            Log.d("==========","rowa");
            return player.getPlayer();
        }
        return "";
    }

}
