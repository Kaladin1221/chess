package chess;

import java.util.HashSet;

public class PawnMoveSet {
    private ChessBoard board;
    private ChessPosition currentPosition;
    private ChessGame.TeamColor color;

    public PawnMoveSet(ChessBoard board, ChessPosition currentPosition) {
        this.board = board;
        this.currentPosition = currentPosition;
        this.color = board.getColorOfSquare(currentPosition);
    }

    public static boolean inbounds(int row, int col)
    {
        return (row >= 1 && row <= 8) && (col >= 1 && col <= 8);
    }

   private static HashSet<ChessMove> frontLeftRight(ChessBoard board, ChessPosition currentPosition)
    {
        HashSet<ChessMove> possiblePawnMoves = new HashSet<>();
        ChessGame.TeamColor color = board.getColorOfSquare(currentPosition);
        int currentRow = currentPosition.getRow();
        int currentCol = currentPosition.getColumn();
        if (color == ChessGame.TeamColor.WHITE)
        {
            if (inbounds(currentRow+1,currentCol))
            {
                ChessPosition checkPosition = new ChessPosition(currentRow + 1, currentCol);
                if (board.getPiece(checkPosition) == null)
                {
                    possiblePawnMoves.add(new ChessMove(currentPosition, checkPosition, null));
                }
            }
            if (inbounds(currentRow + 1,currentCol + 1))
            {
                ChessPosition checkPosition = new ChessPosition(currentRow + 1, currentCol + 1);
                ChessGame.TeamColor checkPositionsPieceColor = board.getColorOfSquare(checkPosition);
                if (board.getPiece(checkPosition) != null && checkPositionsPieceColor != color)
                {
                    possiblePawnMoves.add(new ChessMove(currentPosition, checkPosition, null));
                }
            }
            if (inbounds(currentRow + 1,currentCol - 1))
            {
                ChessPosition checkPosition = new ChessPosition(currentRow + 1, currentCol - 1);
                ChessGame.TeamColor checkPositionsPieceColor = board.getColorOfSquare(checkPosition);
                if (board.getPiece(checkPosition) != null && checkPositionsPieceColor != color)
                {
                    possiblePawnMoves.add(new ChessMove(currentPosition, checkPosition, null));
                }
            }

        }
        else
        {
            if (inbounds(currentRow-1,currentCol))
            {
                ChessPosition checkPosition = new ChessPosition(currentRow - 1, currentCol);
                if (board.getPiece(checkPosition) == null)
                {
                    possiblePawnMoves.add(new ChessMove(currentPosition, checkPosition, null));
                }
            }
            if (inbounds(currentRow - 1,currentCol + 1))
            {
                ChessPosition checkPosition = new ChessPosition(currentRow - 1, currentCol + 1);
                ChessGame.TeamColor checkPositionsPieceColor = board.getColorOfSquare(checkPosition);
                if (board.getPiece(checkPosition) != null && checkPositionsPieceColor != color)
                {
                    possiblePawnMoves.add(new ChessMove(currentPosition, checkPosition, null));
                }
            }
            if (inbounds(currentRow - 1,currentCol - 1))
            {
                ChessPosition checkPosition = new ChessPosition(currentRow - 1, currentCol - 1);
                ChessGame.TeamColor checkPositionsPieceColor = board.getColorOfSquare(checkPosition);
                if (board.getPiece(checkPosition) != null && checkPositionsPieceColor != color)
                {
                    possiblePawnMoves.add(new ChessMove(currentPosition, checkPosition, null));
                }
            }
        }
        return possiblePawnMoves;
    }

    public static HashSet<ChessMove> getPossibleMoves(ChessBoard board, ChessPosition currentPosition) {
        HashSet<ChessMove> possibleMoves = new HashSet<>();
        int currentRow = currentPosition.getRow();
        int currentCol = currentPosition.getColumn();

        ChessPiece.PieceType[] promotionPieces = new ChessPiece.PieceType[]{null};
        promotionPieces = new ChessPiece.PieceType[]{ChessPiece.PieceType.ROOK, ChessPiece.PieceType.KNIGHT, ChessPiece.PieceType.BISHOP, ChessPiece.PieceType.QUEEN};

        ChessGame.TeamColor color = board.getColorOfSquare(currentPosition);

        if (color == ChessGame.TeamColor.WHITE && currentRow == 7)
        {
            HashSet<ChessMove> standardMoves = frontLeftRight(board, currentPosition);
            if (!standardMoves.isEmpty())
            {
                for (ChessMove each : standardMoves)
                {
                    for (ChessPiece.PieceType promotionPiece : promotionPieces)
                    {
                        possibleMoves.add(new ChessMove(currentPosition, each.getEndPosition(), promotionPiece));
                    }
                }
            }

        }

        else if (color == ChessGame.TeamColor.BLACK && currentRow == 2)
        {
            HashSet<ChessMove> standardMoves = frontLeftRight(board, currentPosition);
            if (!standardMoves.isEmpty())
            {
                for (ChessMove each : standardMoves)
                {
                    for (ChessPiece.PieceType promotionPiece : promotionPieces)
                    {
                        possibleMoves.add(new ChessMove(currentPosition, each.getEndPosition(), promotionPiece));
                    }
                }
            }

        }

        else if (color == ChessGame.TeamColor.WHITE && currentRow == 2)
        {
            HashSet<ChessMove> standardMoves = frontLeftRight(board, currentPosition);


                for (ChessMove each : standardMoves)
                {
                    possibleMoves.add(new ChessMove(currentPosition, each.getEndPosition(), null));
                }
                ChessPosition checkPosition1 = new ChessPosition(currentRow + 1, currentCol);
                ChessPosition checkPosition2 = new ChessPosition(currentRow + 2, currentCol);
                if (board.getPiece(checkPosition1) == null && board.getPiece(checkPosition2) == null)
                {
                    possibleMoves.add(new ChessMove(currentPosition, checkPosition2, null));
                }

        }

        else if (color == ChessGame.TeamColor.BLACK && currentRow == 7)
        {
            HashSet<ChessMove> standardMoves = frontLeftRight(board, currentPosition);


                for (ChessMove each : standardMoves)
                {
                    possibleMoves.add(new ChessMove(currentPosition, each.getEndPosition(), null));
                }
                ChessPosition checkPosition1 = new ChessPosition(currentRow - 1, currentCol);
                ChessPosition checkPosition2 = new ChessPosition(currentRow - 2, currentCol);
                if (board.getPiece(checkPosition1) == null && board.getPiece(checkPosition2) == null)
                {
                    possibleMoves.add(new ChessMove(currentPosition, checkPosition2, null));
                }

        }

        else
        {
            HashSet<ChessMove> standardMoves = frontLeftRight(board, currentPosition);
            for (ChessMove each : standardMoves)
            {
                possibleMoves.add(new ChessMove(currentPosition, each.getEndPosition(), null));
            }
        }







        return possibleMoves;
    }
}


