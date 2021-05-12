package com.example.tictactoe;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import org.w3c.dom.Text;

import java.text.BreakIterator;

public class TicTacToe extends View {
    private final int boardColor;
    private final int XColor;
    private final int OColor;
    private final int winningline;
    private final MainGame game;
    private boolean winningLine = false;


    private final Paint paint = new Paint();
    private int cellSize = getWidth() / 3;


    public TicTacToe(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        game = new MainGame();
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.TicTacToe, 0, 0);

        try {
            boardColor = a.getInteger(R.styleable.TicTacToe_boardColor, 0);
            XColor = a.getInteger(R.styleable.TicTacToe_XColor, 0);
            OColor = a.getInteger(R.styleable.TicTacToe_OColor, 0);
            winningline = a.getInteger(R.styleable.TicTacToe_winniglinecolor, 0);
        } finally {
            a.recycle();
        }
    }

    @Override
    protected void onMeasure(int width, int height) {
        super.onMeasure(width, height);
        int dimensions = Math.min(getMeasuredWidth(), getMeasuredHeight());
        cellSize = dimensions / 3;
        setMeasuredDimension(dimensions, dimensions);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        drawGameBoard(canvas);
        drawMarkers(canvas);
        if(winningLine){
            paint.setColor(winningline);
            drawWinningLine(canvas);
        }else {
        }

    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN) {
            int row = (int) Math.ceil(y / cellSize);
            int col = (int) Math.ceil(x /cellSize);
            if (!winningLine){
                if (game.updateGameBoard(row, col)){
                    invalidate();
                    if (game.winnercheck()){
                        winningLine = true;
                        invalidate();
                    }

                    if (game.getPlayer() % 2 == 0){
                        game.setPlayer(game.getPlayer()-1);
                    }
                    else{
                        game.setPlayer(game.getPlayer()+1);
                    }
                }

            }
            invalidate();
            return true;
        }

        return false;
    }

    private void drawGameBoard(Canvas canvas) {
        paint.setColor(boardColor);
        paint.setStrokeWidth(16);
     for (int c=1; c<3;c++)
     {
         canvas.drawLine(cellSize*c,0,cellSize*c,canvas.getWidth(),paint);
     }
     for (int d=1; d<3; d++)
     {
         canvas.drawLine(0,cellSize*d,canvas.getWidth(),cellSize*d,paint);

     }

    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void drawMarkers(Canvas canvas){
        for (int d=0;d<3;d++){
            for (int c=0;c<3;c++){
                if (game.getGameBoard()[d][c] !=0){
                    if (game.getGameBoard()[d][c]==1){
                        drawX(canvas,d,c);
                    }
                    else {
                        drawO(canvas,d,c);
                    }
                }
            }
        }

    }
    private void drawX(Canvas canvas,int row,int col) {
        paint.setColor(XColor);
        canvas.drawLine((float) ((col + 1) * cellSize - cellSize * 0.2),
                (float) (row * cellSize + cellSize * 0.2),
                (float) (col * cellSize + cellSize * 0.2),
                (float) ((row + 1) * cellSize - cellSize * 0.2),
                paint);
        canvas.drawLine((float) (col * cellSize + cellSize * 0.2),
                (float) (row * cellSize + cellSize * 0.2),
                (float) ((col + 1) * cellSize - cellSize * 0.2),
                (float) ((row + 1) * cellSize - cellSize * 0.2),
                paint);
    }
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        private void drawO(Canvas canvas, int row, int col){
        paint.setColor(OColor);
        canvas.drawOval((float) (col*cellSize + cellSize*0.2), (float) (row*cellSize + cellSize*0.2), (float) ((col*cellSize + cellSize) - cellSize*0.2), (float) ((row*cellSize + cellSize) - cellSize*0.2),paint);

    }
    public void drawHorizontalLine(Canvas canvas, int row, int col){
        canvas.drawLine(col,row*cellSize + (float)cellSize/2,cellSize*3,row*cellSize + (float)cellSize/2,paint);
    }
    public void drawVerticalLine (Canvas canvas,int row , int col){
        canvas.drawLine((float)col*cellSize + (float)cellSize/2,row,col*cellSize + (float)cellSize/2,cellSize*3,paint);
    }
    private void drawDiagonalLinePos(Canvas canvas){
        canvas.drawLine(0,cellSize*3,cellSize*3,0,paint);
    }
    private void drawDiagonalLineNeg(Canvas canvas){
        canvas.drawLine(0,0,cellSize*3,cellSize*3,paint);
    }
    private void drawWinningLine(Canvas canvas){
        int row = game.getWinType()[0];
        int col = game.getWinType()[1];


        switch (game.getWinType()[2]){
            case 1:
                drawHorizontalLine(canvas,row,col);
                break;
            case 2:
                drawVerticalLine(canvas,row,col);
                break;
            case 3:
                drawDiagonalLineNeg(canvas);
                break;
            case 4:
                drawDiagonalLinePos(canvas);
                break;
        }
    }

    public void setUpGame(TextView PlayAgain, TextView playdisplay,String[] names){
      game.setPlayAgain(PlayAgain);
      game.setPlayturn(playdisplay);
      game.setPlayerNames(names);
    }
    public void resetGame(){
        game.resetGame();
        winningLine = false;
    }


}

