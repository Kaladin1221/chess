package chess;

import java.util.HashSet;

public class QueenMoveSet
{

    private ChessBoard board;
    private ChessPosition currentPosition;
    private ChessGame.TeamColor color;

   public QueenMoveSet( ChessBoard board, ChessPosition currentPosition)
   {
       this.board = board;
       this.currentPosition = currentPosition;
       this.color = board.getColorOfSquare(currentPosition);
   }

    public static HashSet<ChessMove> getPossibleMoves(ChessBoard board, ChessPosition currentPosition)
    {
        HashSet<ChessMove> possibleMoves = new HashSet<>();
        possibleMoves.addAll(RookMoveSet.getPossibleMoves(board,currentPosition));
        possibleMoves.addAll(BishopMoveSet.getPossibleMoves(board,currentPosition));
        return possibleMoves;
    }


}
