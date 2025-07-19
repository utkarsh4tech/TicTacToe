package PlayerStrategies;

import CommonEnum.Symbol;
import Utility.Board;
import Utility.Position;

// Strategy Interface for Player Moves
// Defines a makeMove(Board board) method.
public interface PlayerStrategy {
    // Allows different player strategies to be used interchangeably without
    // modifying client code.
    Position makeMove(Board board, Symbol mySymbol);
}

