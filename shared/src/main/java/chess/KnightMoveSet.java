package chess;

import java.util.HashSet;

public class KnightMoveSet
{
    private ChessBoard board;
    private ChessPosition currentPosition;
    private ChessGame.TeamColor color;

    public KnightMoveSet(ChessBoard board, ChessPosition currentPosition)
    {
        this.board = board;
        this.currentPosition = currentPosition;
        this.color = board.getColorOfSquare(currentPosition);
    }

    public static boolean inbounds(int row, int col)
    {
        return (row >= 1 && row <= 8) && (col >= 1 && col <= 8);
    }

    public static HashSet<ChessMove> getPossibleMoves(ChessBoard board, ChessPosition currentPosition)
    {
        HashSet<ChessMove> possibleMoves = new HashSet<>();
        int[][] offsets = {{1,2},{2,1},{2,-1},{1,-2},{-1,-2},{-2,-1},{-2,1},{-1,2}};
        int currentRow = currentPosition.getRow();
        int currentCol = currentPosition.getColumn();
        ChessGame.TeamColor color = board.getColorOfSquare(currentPosition);
        for (int[] offset : offsets)
        {
            if (inbounds(currentRow + offset[0],currentCol+offset[1]))
            {
                ChessPosition checkPosition = new ChessPosition(currentRow + offset[0],currentCol+offset[1]);
                if (board.getPiece(checkPosition) == null)
                {
                    possibleMoves.add(new ChessMove(currentPosition,checkPosition,null));
                }
                else
                {
                    ChessGame.TeamColor checkPositionsPieceColor = board.getColorOfSquare(checkPosition);
                    if (color == checkPositionsPieceColor)
                    {
                        continue;
                    }
                    else
                    {
                        possibleMoves.add(new ChessMove(currentPosition,checkPosition,null));
                        continue;
                    }
                }
            }
        }



        return possibleMoves;
    }

}
