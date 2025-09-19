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

    //static boolean isSquareOnBoard(ChessPosition position)
    //{
    //    return (position.getRow() >= 1 && position.getRow()<=8 && position.getColumn() >= 1 && position.getColumn() <= 8);
    //}

    public HashSet<ChessMove> getPossibleMoves(ChessBoard board,ChessPosition currentPosition)
    {
        HashSet<ChessMove> possibleMoves = HashSet.newHashSet(14);
        int currentX = currentPosition.getRow();
        int currentY = currentPosition.getColumn();

        for (int i = currentX + 1; i <= 8; i++)
        {
            ChessPosition checkPosition = new ChessPosition(i,currentY);
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

        for (int i = currentX - 1; i >= 1; i--)
        {
            ChessPosition checkPosition = new ChessPosition(i,currentY);
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

        for (int i = currentY + 1; i <= 8; i++)
        {
            ChessPosition checkPosition = new ChessPosition(currentX,i);
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

        for (int i = currentY -1; i >= 1; i--)
        {
            ChessPosition checkPosition = new ChessPosition(currentX,i);
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
