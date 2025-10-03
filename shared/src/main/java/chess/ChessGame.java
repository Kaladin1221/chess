package chess;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

/**
 * For a class that can manage a chess game, making moves on a board
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessGame
{

    private TeamColor teamTurn;
    private ChessBoard board;

    public ChessGame()
    {
        board = new ChessBoard();
        setTeamTurn(TeamColor.WHITE);
    }

    /**
     * @return Which team's turn it is
     */
    public TeamColor getTeamTurn()
    {
        return teamTurn;
    }

    /**
     * Set's which teams turn it is
     *
     * @param team the team whose turn it is
     */
    public void setTeamTurn(TeamColor team)
    {
        teamTurn = team;
    }

    /**
     * Enum identifying the 2 possible teams in a chess game
     */
    public enum TeamColor
    {
        WHITE,
        BLACK
    }

    /**
     * Gets a valid moves for a piece at the given location
     *
     * @param startPosition the piece to get valid moves for
     * @return Set of valid moves for requested piece, or null if no piece at
     * startPosition
     */
    public Collection<ChessMove> validMoves(ChessPosition startPosition)
    {
        ChessPiece currentPiece = board.getPiece(startPosition);
        if (currentPiece == null)
        {
            return null;
        }

        HashSet<ChessMove> possibleMoves = (HashSet<ChessMove>) board.getPiece(startPosition).pieceMoves(board,startPosition);
        HashSet<ChessMove> validMoves = HashSet.newHashSet(possibleMoves.size());

        for (ChessMove move : possibleMoves)
        {
            ChessPiece temp = board.getPiece(move.getEndPosition());
            board.addPiece(startPosition, null);
            board.addPiece(move.getEndPosition(),currentPiece);
            if (!isInCheck(currentPiece.getTeamColor()))
            {
                validMoves.add(move);
            }
            board.addPiece(move.getEndPosition(), temp);
            board.addPiece(startPosition, currentPiece);
        }
        return validMoves;
    }

    /**
     * Makes a move in a chess game
     *
     * @param move chess move to perform
     * @throws InvalidMoveException if move is invalid
     */
    public void makeMove(ChessMove move) throws InvalidMoveException
    {
        boolean isTeamsTurn = getTeamTurn() == board.getColorOfSquare(move.getStartPosition());
        Collection<ChessMove> validMoves = validMoves(move.getStartPosition());

        if (validMoves == null)
        {
            throw new InvalidMoveException("No valid moves");
        }

        boolean isMoveValid = validMoves.contains(move);

        if (isMoveValid && isTeamsTurn)
        {
            ChessPiece pieceToMove = board.getPiece(move.getStartPosition());
            if (move.getPromotionPiece() != null)
            {
                pieceToMove = new ChessPiece(pieceToMove.getTeamColor(), move.getPromotionPiece());
            }

            board.addPiece(move.getStartPosition(),null);
            board.addPiece(move.getEndPosition(),pieceToMove);

            if (getTeamTurn() == TeamColor.WHITE)
            {
                setTeamTurn(TeamColor.BLACK);
            }
            else
            {
                setTeamTurn(TeamColor.WHITE);
            }

        }
        else
        {
            throw new InvalidMoveException(String.format("Valid move: %b  Your Turn: %b", isMoveValid, isTeamsTurn));
        }
    }

    /**
     * Determines if the given team is in check
     *
     * @param teamColor which team to check for check
     * @return True if the specified team is in check
     */
    public boolean isInCheck(TeamColor teamColor) {
        throw new RuntimeException("Not implemented");
    }

    /**
     * Determines if the given team is in checkmate
     *
     * @param teamColor which team to check for checkmate
     * @return True if the specified team is in checkmate
     */
    public boolean isInCheckmate(TeamColor teamColor) {
        throw new RuntimeException("Not implemented");
    }

    /**
     * Determines if the given team is in stalemate, which here is defined as having
     * no valid moves while not in check.
     *
     * @param teamColor which team to check for stalemate
     * @return True if the specified team is in stalemate, otherwise false
     */
    public boolean isInStalemate(TeamColor teamColor) {
        throw new RuntimeException("Not implemented");
    }

    /**
     * Sets this game's chessboard with a given board
     *
     * @param board the new board to use
     */
    public void setBoard(ChessBoard board) {
        throw new RuntimeException("Not implemented");
    }

    /**
     * Gets the current chessboard
     *
     * @return the chessboard
     */
    public ChessBoard getBoard() {
        throw new RuntimeException("Not implemented");
    }




    @Override
    public String toString() {
        return "ChessGame{" +
                "teamTurn=" + teamTurn +
                ", board=" + board +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessGame chessGame = (ChessGame) o;
        return teamTurn == chessGame.teamTurn && Objects.equals(board, chessGame.board);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamTurn, board);
    }

}




