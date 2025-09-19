package chess;
import java.util.HashSet;
public class BishopMoveSet
{
    private ChessBoard board;
    private ChessPosition currentPosition;
    private ChessGame.TeamColor color;

    public BishopMoveSet(ChessBoard board, ChessPosition currentPosition)
    {
        this.board = board;
        this.currentPosition = currentPosition;
        this.color = board.getColorOfSquare(currentPosition);
    }

    //static boolean isSquareOnBoard(ChessPosition position)
    //{
    //    return (position.getRow() >= 1 && position.getRow()<=8 && position.getColumn() >= 1 && position.getColumn() <= 8);
    //}

    public static HashSet<ChessMove> getPossibleMoves(ChessBoard board,ChessPosition currentPosition)
    {
        HashSet<ChessMove> possibleMoves = new HashSet<>();
        int currentRow = currentPosition.getRow();
        int currentCol = currentPosition.getColumn();
        ChessGame.TeamColor color = board.getColorOfSquare(currentPosition);

        for (int i =1 ; currentRow + i <= 8 && currentCol + i <= 8;  i++)
        {
            ChessPosition checkPosition = new ChessPosition(currentRow + i,currentCol + i);
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

        for (int i = 1; currentRow - i >= 1 && currentCol - i >= 1; i++)
        {
            ChessPosition checkPosition = new ChessPosition(currentRow - i, currentCol - i);
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

        for (int i = 1; currentRow - i >= 1 && currentCol + i <= 8; i++)
        {
            ChessPosition checkPosition = new ChessPosition(currentRow - i, currentCol + i);
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

        for (int i = 1; currentRow + i <= 8 && currentCol - i >= 1; i++)
        {
            ChessPosition checkPosition = new ChessPosition(currentRow + i, currentCol - i);
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

