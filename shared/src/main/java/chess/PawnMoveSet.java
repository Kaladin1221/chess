package chess;

import java.util.HashSet;

public class PawnMoveSet
{
    private ChessBoard board;
    private ChessPosition currentPosition;
    private ChessGame.TeamColor color;

    public PawnMoveSet(ChessBoard board, ChessPosition currentPosition)
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
        int currentRow = currentPosition.getRow();
        int currentCol = currentPosition.getColumn();
        ChessGame.TeamColor color = board.getColorOfSquare(currentPosition);
        ChessPiece.PieceType[] promotionPieces = new ChessPiece.PieceType[]{null};
        if (color == ChessGame.TeamColor.WHITE)
        {

            if (currentRow == 2)
            {

                ChessPosition front1 = new ChessPosition(currentRow + 1, currentCol);
                if (board.getPiece(front1) == null)
                {
                    possibleMoves.add(new ChessMove(currentPosition, front1, null));
                }

                ChessPosition diagLeft = new ChessPosition(currentRow + 1, currentCol - 1);
                if (board.getPiece(diagLeft) != null && board.getColorOfSquare(diagLeft) != color)
                {
                    possibleMoves.add(new ChessMove(currentPosition, diagLeft, null));
                }

                ChessPosition diagRight = new ChessPosition(currentRow + 1, currentCol + 1);
                if (board.getPiece(diagRight) != null && board.getColorOfSquare(diagRight) != color)
                {
                    possibleMoves.add(new ChessMove(currentPosition, diagRight, null));
                }

                ChessPosition front2 = new ChessPosition(currentRow + 2, currentCol);
                if (board.getPiece(front2) == null)
                {
                    possibleMoves.add(new ChessMove(currentPosition, front2, null));
                }
            } else if (currentRow == 7)
            {
                promotionPieces = new ChessPiece.PieceType[]{ChessPiece.PieceType.ROOK, ChessPiece.PieceType.KNIGHT, ChessPiece.PieceType.BISHOP, ChessPiece.PieceType.QUEEN};

                ChessPosition front1 = new ChessPosition(currentRow + 1, currentCol);
                if (board.getPiece(front1) == null)
                {
                    for (ChessPiece.PieceType promotionPiece : promotionPieces)
                    {
                        possibleMoves.add(new ChessMove(currentPosition, front1, promotionPiece));
                    }

                }

                ChessPosition diagLeft = new ChessPosition(currentRow + 1, currentCol - 1);
                if (board.getPiece(diagLeft) != null && board.getColorOfSquare(diagLeft) != color)
                {
                    for (ChessPiece.PieceType promotionPiece : promotionPieces)
                    {
                        possibleMoves.add(new ChessMove(currentPosition, diagLeft, promotionPiece));
                    }
                }

                ChessPosition diagRight = new ChessPosition(currentRow + 1, currentCol + 1);
                if (board.getPiece(diagRight) != null && board.getColorOfSquare(diagRight) != color)
                {
                    for (ChessPiece.PieceType promotionPiece : promotionPieces)
                    {
                        possibleMoves.add(new ChessMove(currentPosition, diagRight, promotionPiece));
                    }
                }
            }
            else
            {
                int newRow = currentRow + 1;
                int newCol = currentCol + 1;
                if (inbounds(newRow, newCol))
                {
                    ChessPosition front1 = new ChessPosition(currentRow + 1, currentCol);
                    if (board.getPiece(front1) == null)
                    {
                        possibleMoves.add(new ChessMove(currentPosition, front1, null));
                    }

                    ChessPosition diagLeft = new ChessPosition(currentRow + 1, currentCol - 1);
                    if (board.getPiece(diagLeft) != null && board.getColorOfSquare(diagLeft) != color)
                    {
                        possibleMoves.add(new ChessMove(currentPosition, diagLeft, null));
                    }

                    ChessPosition diagRight = new ChessPosition(currentRow + 1, currentCol + 1);
                    if (board.getPiece(diagRight) != null && board.getColorOfSquare(diagRight) != color)
                    {
                        possibleMoves.add(new ChessMove(currentPosition, diagRight, null));
                    }
                }
            }
        }

        else
        {
            if (currentRow == 7)
            {
                ChessPosition front1 = new ChessPosition(currentRow - 1, currentCol);
                if (board.getPiece(front1) == null)
                {
                    possibleMoves.add(new ChessMove(currentPosition, front1, null));
                }

                ChessPosition diagLeft = new ChessPosition(currentRow - 1, currentCol - 1);
                if (board.getPiece(diagLeft) != null && board.getColorOfSquare(diagLeft) != color)
                {
                    possibleMoves.add(new ChessMove(currentPosition, diagLeft, null));
                }

                ChessPosition diagRight = new ChessPosition(currentRow - 1, currentCol + 1);
                if (board.getPiece(diagRight) != null && board.getColorOfSquare(diagRight) != color)
                {
                    possibleMoves.add(new ChessMove(currentPosition, diagRight, null));
                }

                ChessPosition front2 = new ChessPosition(currentRow - 2, currentCol);
                if (board.getPiece(front2) == null)
                {
                    possibleMoves.add(new ChessMove(currentPosition, front2, null));
                }
            }
            else if (currentRow == 2)
            {
                promotionPieces = new ChessPiece.PieceType[]{ChessPiece.PieceType.ROOK, ChessPiece.PieceType.KNIGHT, ChessPiece.PieceType.BISHOP, ChessPiece.PieceType.QUEEN};

                ChessPosition front1 = new ChessPosition(currentRow + 1, currentCol);
                if (board.getPiece(front1) == null) {
                    for (ChessPiece.PieceType promotionPiece : promotionPieces)
                    {
                        possibleMoves.add(new ChessMove(currentPosition, front1, promotionPiece));
                    }

                }

                ChessPosition diagLeft = new ChessPosition(currentRow - 1, currentCol - 1);
                if (board.getPiece(diagLeft) != null && board.getColorOfSquare(diagLeft) != color)
                {
                    for (ChessPiece.PieceType promotionPiece : promotionPieces)
                    {
                        possibleMoves.add(new ChessMove(currentPosition, diagLeft, promotionPiece));
                    }
                }

                ChessPosition diagRight = new ChessPosition(currentRow - 1, currentCol + 1);
                if (board.getPiece(diagRight) != null && board.getColorOfSquare(diagRight) != color)
                {
                    for (ChessPiece.PieceType promotionPiece : promotionPieces)
                    {
                        possibleMoves.add(new ChessMove(currentPosition, diagRight, promotionPiece));
                    }
                }
            }
            else
            {
                int newRow = currentRow + 1;
                int newCol = currentCol + 1;
                if (inbounds(newRow, newCol))
                {
                    ChessPosition front1 = new ChessPosition(currentRow - 1, currentCol);
                    if (board.getPiece(front1) == null)
                    {
                        possibleMoves.add(new ChessMove(currentPosition, front1, null));
                    }

                    ChessPosition diagLeft = new ChessPosition(currentRow - 1, currentCol - 1);
                    if (board.getPiece(diagLeft) != null && board.getColorOfSquare(diagLeft) != color)
                    {
                        possibleMoves.add(new ChessMove(currentPosition, diagLeft, null));
                    }

                    ChessPosition diagRight = new ChessPosition(currentRow - 1, currentCol + 1);
                    if (board.getPiece(diagRight) != null && board.getColorOfSquare(diagRight) != color)
                    {
                        possibleMoves.add(new ChessMove(currentPosition, diagRight, null));
                    }
                }
            }
        }

        return possibleMoves;
    }
}
