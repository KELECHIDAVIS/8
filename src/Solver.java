import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solver
{
    public static int g =0;

    public static ArrayList<String> Astar(Puzzle puzzle, Puzzle goal) // returns a list of moves to get from the puzzle to the goal
    {
        ArrayList<String> moveList = new ArrayList<String>(); // path to start

        // make a 'finished' empty set
        ArrayList<Puzzle>  finished =new ArrayList<Puzzle>();
        //make frontier priorityqueue
        PriorityQueue<Puzzle> frontier = new PriorityQueue<Puzzle>();

        puzzle.setH(goal);
        //add start to frontier
        frontier.add(puzzle);
        //until frontier empty ; while frontier isnt empty
        while(!frontier.isEmpty())
        {
            g++;
            //take min - priority parent from frontier
            Puzzle parent = frontier.poll();
            //stop if parent is goal
            if(solved(parent, goal) )
            {
                parent.printBoard();
                System.out.println("SOLVED\nMOVE COUNT: "+moveList.size());
                break;

            }

            //otherwise add parent to finished
            finished.add(parent);

            //for each move from parent
            for(String move : parent.moves())
            {
                //let child be neigbor of parent via move
                Puzzle child = parent.neighbor(move);
                //if child is not in frontier and not in finished ;
                if(!frontier.contains(child)&&!finished.contains(child))
                {
                    //add child to frontier
                    frontier.add(child);
                }

                //if the best path to child so far is through parent;
                moveList.add(move);
                
                // note path to child is the parent to parent followed by one move; note priority of child is the number of moveslist.size() + child.h
            }


        }



        return moveList ;
    }


    public static boolean solved(Puzzle p , Puzzle goal)
    {
        for(int i =0; i<3; i++)
        {
            for(int j = 0; j<3; j++)
            {
                if(p.grid[i][j]!=goal.grid[i][j])
                {
                    return false;
                }
            }
        }

        return true;
    }

}
