package chess;

import java.util.Collection;
import java.util.Collections;
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
        board.resetBoard(); // this sets the board up for the first time
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
            return Collections.emptyList();
        }

        HashSet<ChessMove> possibleMoves = (HashSet<ChessMove>) board.getPiece(startPosition).pieceMoves(board,startPosition);
        HashSet<ChessMove> validMoves = new HashSet<>(possibleMoves.size());


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
        Collection<ChessMove> goodMoves = validMoves(move.getStartPosition());

        if (goodMoves == null)
        {
            throw new InvalidMoveException("No valid moves");
        }


        boolean isMoveValid = goodMoves.contains(move);



        if (isMoveValid && isTeamsTurn)
        {
            ChessPiece pieceToMove = board.getPiece(move.getStartPosition());
            if (move.getPromotionPiece() != null)
            {
                pieceToMove = new ChessPiece(pieceToMove.getTeamColor(), move.getPromotionPiece());
            }

            board.addPiece(move.getStartPosition(),null);
            board.addPiece(move.getEndPosition(),pieceToMove);

            setTeamTurn(getTeamTurn() == TeamColor.WHITE ? TeamColor.BLACK : TeamColor.WHITE);

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
    public boolean isInCheck(TeamColor teamColor)
    {
        ChessPosition kingsPosition = null;
        for (int x = 1; x <=8 && kingsPosition == null; x++)
        {
            for (int y = 1; y <= 8 && kingsPosition == null; y++)
            {
                ChessPiece currentPiece = board.getPiece(new ChessPosition(x,y));
                if (currentPiece == null)
                {
                    continue;
                }
                if (currentPiece.getTeamColor() == teamColor && currentPiece.getPieceType() == ChessPiece.PieceType.KING)
                {
                    kingsPosition = new ChessPosition(x,y);
                }
            }
        }

        for (int x = 1; x <=8; x++)
        {
            for (int y = 1; y <= 8; y++)
            {
                ChessPiece currentPiece = board.getPiece(new ChessPosition(x,y));
                if (currentPiece == null || currentPiece.getTeamColor() == teamColor)
                {
                    continue;
                }
                for (ChessMove enemyMove : currentPiece.pieceMoves(board, new ChessPosition(x,y)))
                {
                    if (enemyMove.getEndPosition().equals(kingsPosition))
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Determines if the given team is in checkmate
     *
     * @param teamColor which team to check for checkmate
     * @return True if the specified team is in checkmate
     */
    public boolean isInCheckmate(TeamColor teamColor)
    {
        if (!isInCheck(teamColor))
        {
            return false;
        }
        for (int x = 1; x <=8; x++)
        {
            for (int y = 1; y <=8; y++)
            {
                ChessPosition currentposition = new ChessPosition(x,y);
                ChessPiece currentPiece = board.getPiece(currentposition);
                if (currentPiece != null && currentPiece.getTeamColor() == teamColor)
                {
                    Collection<ChessMove> moves = validMoves(currentposition);
                    if (moves != null && !moves.isEmpty())
                    {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Determines if the given team is in stalemate, which here is defined as having
     * no valid moves while not in check.
     *
     * @param teamColor which team to check for stalemate
     * @return True if the specified team is in stalemate, otherwise false
     */
    public boolean isInStalemate(TeamColor teamColor)
    {

        if (isInCheck(teamColor)) // make sure we are not in check
        {
            return false;
        }
        for (int x = 1; x <= 8; x++)
        {
            for (int y = 1; y <= 8; y++)
            {
                ChessPosition currentposition = new ChessPosition(x, y);
                ChessPiece currentPiece = board.getPiece(currentposition);
                Collection<ChessMove> moves;

                if(currentPiece != null && teamColor == currentPiece.getTeamColor())
                {
                    moves = validMoves(currentposition);
                    if (moves != null && !moves.isEmpty())
                    {
                        return false;
                    }
                }
            }

        }
        return true;
    }

    /**
     * Sets this game's chessboard with a given board
     *
     * @param board the new board to use
     */
    public void setBoard(ChessBoard board)
    {
        this.board = board;
    }

    /**
     * Gets the current chessboard
     *
     * @return the chessboard
     */
    public ChessBoard getBoard()
    {
        return board;
    }


    @Override
    public String toString()
    {
        return "ChessGame{" +
                "teamTurn=" + teamTurn +
                ", board=" + board +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        ChessGame chessGame = (ChessGame) o;
        return teamTurn == chessGame.teamTurn && Objects.equals(board, chessGame.board);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(teamTurn, board);
    }
}






