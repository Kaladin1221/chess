package chess;

import java.util.Collection;
import java.util.Objects;

/**
 * Represents a single chess piece
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPiece
{
    private ChessGame.TeamColor color;
    private PieceType type;

    public ChessPiece(ChessGame.TeamColor pieceColor, ChessPiece.PieceType type)
    {
        this.color = pieceColor;
        this.type = type;
    }

    /**
     * The various different chess piece options
     */
    public enum PieceType {
        KING,
        QUEEN,
        BISHOP,
        KNIGHT,
        ROOK,
        PAWN
    }

    /**
     * @return Which team this chess piece belongs to
     */
    public ChessGame.TeamColor getTeamColor()
    {
        return color;
    }

    /**
     * @return which type of chess piece this piece is
     */
    public PieceType getPieceType()
    {
        return type;
    }

    /**
     * Calculates all the positions a chess piece can move to
     * Does not take into account moves that are illegal due to leaving the king in
     * danger
     *
     * @return Collection of valid moves
     */
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition)
    {
        if (type == PieceType.ROOK)
        {
            return RookMoveSet.getPossibleMoves(board, myPosition);
        }
        if (type == PieceType.PAWN)
        {
            return PawnMoveSet.getPossibleMoves(board, myPosition);
        }
        if (type == PieceType.KNIGHT)
        {
            return KnightMoveSet.getPossibleMoves(board, myPosition);
        }
        if (type == PieceType.BISHOP)
        {
            return BishopMoveSet.getPossibleMoves(board, myPosition);
        }
        if (type == PieceType.QUEEN)
        {
            return QueenMoveSet.getPossibleMoves(board, myPosition);
        }
        else
        {
            return KingMoveSet.getPossibleMoves(board, myPosition);
        }



    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessPiece that = (ChessPiece) o;
        return color == that.color && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, type);
    }

}
