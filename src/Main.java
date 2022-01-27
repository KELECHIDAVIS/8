import java.util.ArrayList;

public class Main {

    public static void main(String[] args)
    {
        int[][] start = {{1,2,5},{4,8,7},{3,6,0}};
        Puzzle puzzle = new Puzzle(start);
        puzzle.printBoard();
        int[][] g =  {{1,2,3},{4,5,6},{7,8,0}};
        Puzzle goal = new Puzzle(g);

        ArrayList<String> m = Solver.Astar(puzzle, goal);

        for(String move: m)
        {
            puzzle = puzzle.neighbor(move);
            puzzle.printBoard();

        }
    }
}
