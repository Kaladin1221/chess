package chess;
import java.util.HashSet;
public class RookMoveSet
{
    private ChessBoard board;
    private ChessPosition currentPosition;
    private ChessGame.TeamColor color;

    public RookMoveSet(ChessBoard board, ChessPosition currentPosition)
    {
        this.board = board;
        this.currentPosition = currentPosition;
        this.color = board.getColorOfSquare(currentPosition);
    }



    public static HashSet<ChessMove> getPossibleMoves(ChessBoard board,ChessPosition currentPosition)
    {
        HashSet<ChessMove> possibleMoves = new HashSet<>();
        int currentRow = currentPosition.getRow();
        int currentCol = currentPosition.getColumn();
        ChessGame.TeamColor color = board.getColorOfSquare(currentPosition);

        for (int i = currentRow + 1; i <= 8; i++)
        {
            ChessPosition checkPosition = new ChessPosition(i,currentCol);
            if (board.getPiece(checkPosition) == null)
            {
                possibleMoves.add(new ChessMove(currentPosition,checkPosition,null));
            }
            if (board.getPiece(checkPosition) != null)
            {
                ChessGame.TeamColor checkPositionsPieceColor = board.getColorOfSquare(checkPosition);
                if (color == checkPositionsPieceColor)
                {
                    break;
                }
                else
                {
                    possibleMoves.add(new ChessMove(currentPosition,checkPosition,null));
                    break;
                }
            }

        }

        for (int i = currentRow - 1; i >= 1; i--)
        {
            ChessPosition checkPosition = new ChessPosition(i,currentCol);
            if (board.getPiece(checkPosition) == null)
            {
                possibleMoves.add(new ChessMove(currentPosition,checkPosition,null));
            }
            if (board.getPiece(checkPosition) != null)
            {
                ChessGame.TeamColor checkPositionsPieceColor = board.getColorOfSquare(checkPosition);
                if (color == checkPositionsPieceColor)
                {
                    break;
                }
                else
                {
                    possibleMoves.add(new ChessMove(currentPosition,checkPosition,null));
                    break;
                }
            }

        }

        for (int i = currentCol + 1; i <= 8; i++)
        {
            ChessPosition checkPosition = new ChessPosition(currentRow,i);
            if (board.getPiece(checkPosition) == null)
            {
                possibleMoves.add(new ChessMove(currentPosition,checkPosition,null));
            }
            if (board.getPiece(checkPosition) != null)
            {
                ChessGame.TeamColor checkPositionsPieceColor = board.getColorOfSquare(checkPosition);
                if (color == checkPositionsPieceColor)
                {
                    break;
                }
                else
                {
                    possibleMoves.add(new ChessMove(currentPosition,checkPosition,null));
                    break;
                }
            }

        }

        for (int i = currentCol -1; i >= 1; i--)
        {
            ChessPosition checkPosition = new ChessPosition(currentRow,i);
            if (board.getPiece(checkPosition) == null)
            {
                possibleMoves.add(new ChessMove(currentPosition,checkPosition,null));
            }
            if (board.getPiece(checkPosition) != null)
            {
                ChessGame.TeamColor checkPositionsPieceColor = board.getColorOfSquare(checkPosition);
                if (color == checkPositionsPieceColor)
                {
                    break;
                }
                else
                {
                    possibleMoves.add(new ChessMove(currentPosition,checkPosition,null));
                    break;
                }
            }

        }

        return possibleMoves;
    }


}
